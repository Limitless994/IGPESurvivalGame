package dev.igpe.theamazingame.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Animation;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.utils.Utils;
import dev.igpe.theamazingame.worlds.World;

public class Enemy extends Creature {

	//Animations
	private Animation animLeft,animRight,animUp,animDown,animStatic;

	private int fov=110;
	private int range=50;

	public Enemy(Handler handler,float x, float y) {
		super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,Creature.DEFAULT_CREATURE_DAMAGE);
		bounds.x= 16;
		bounds.y = 32;
		bounds.width=32;
		bounds.height=32;

		//Animations
		animLeft = new Animation(100,Assets.enemy_left);
		animRight = new Animation(100,Assets.enemy_right);
		animDown = new Animation(100,Assets.enemy_down);
		animUp = new Animation(100,Assets.enemy_up);
		animStatic = new Animation(500,Assets.enemyStatic);
	}

	@Override
	public void die() {

		System.out.println("Ucciso Enemy");
	}

	@Override
	public void tick() {
		//Animations
		animLeft.tick();
		animRight.tick();
		animDown.tick();
		animUp.tick();
		animStatic.tick();
		//Movimenti


		if(Math.abs(getX()-World.entityManager.getPlayer().getX())<=fov && Math.abs(getY()-World.entityManager.getPlayer().getY())<=fov ) {
			follow();
			move();
			if(Math.abs(getX()-World.entityManager.getPlayer().getX())<=range && Math.abs(getY()-World.entityManager.getPlayer().getY())<=range ) {

				//Aggiunto metodo set healt in ENTITY, la vita è stata spostata li rivedi condizioni di sconfitta e quella per ricominciare
				World.entityManager.getPlayer().setHealth((World.entityManager.getPlayer().getHealth())-(DEFAULT_CREATURE_DAMAGE));
//				World.entityManager.getPlayer().setX(100);
//				World.entityManager.getPlayer().setY(100);
			}
		}
	}

	private void follow() {


		if(World.entityManager.getPlayer().getY()<getY()-30) {
			yMove = -speed+1;

		}else if(World.entityManager.getPlayer().getY()>getY()+30) {
			yMove = +speed-1;
		}
		if(World.entityManager.getPlayer().getX()<getX()-30) {
			xMove = -speed+1;
		}else if(World.entityManager.getPlayer().getX()>getX()+30) {
			xMove = +speed-1;
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height,null);

	}

	private BufferedImage getCurrentAnimationFrame() {
		if(xMove<0)return  animLeft.getCurrentFrame();
		else if(xMove>0)return animRight.getCurrentFrame();
		else if(yMove<0)return animDown.getCurrentFrame();
		else if(yMove>0)return animUp.getCurrentFrame();
		else return animStatic.getCurrentFrame();
	}


}
