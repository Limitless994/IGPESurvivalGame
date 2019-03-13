package dev.igpe.theamazingame.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.audio.AudioPlayer;
import dev.igpe.theamazingame.gfx.Animation;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.states.GameState;
import dev.igpe.theamazingame.ui.HealthBar;
import dev.igpe.theamazingame.worlds.World;

public class Enemy extends Creature {
	protected AudioPlayer zombieDie=new AudioPlayer("/sounds/zombieDie.wav");
	//Animations
	private Animation animLeft,animRight,animUp,animDown,animStatic;
	private Animation animLeftS,animRightS,animUpS,animDownS,animStaticS;
	private static volatile int id = 0;
	private volatile int currentId;


	private int fov=800;
	private int range=50;
	long lastTurn ;

	public Enemy(Handler handler,float x, float y) {
		super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,Creature.DEFAULT_HEALTH,2.0f);
		bounds.x= 16;
		bounds.y = 32;
		bounds.width=32;
		bounds.height=32;
		this.setHealth(50);
		currentId = id++;


		//Animations
		animLeft = new Animation(100,Assets.enemy_left);
		animRight = new Animation(100,Assets.enemy_right);
		animDown = new Animation(100,Assets.enemy_down);
		animUp = new Animation(100,Assets.enemy_up);
		animStatic = new Animation(500,Assets.enemyStatic);


		animLeftS = new Animation(100,Assets.enemy_left_swimming);
		animRightS = new Animation(100,Assets.enemy_right_swimming);
		animDownS = new Animation(100,Assets.enemy_down_swimming);
		animUpS = new Animation(100,Assets.enemy_up_swimming);
		animStaticS = new Animation(500,Assets.enemyStatic_swimming);

	}
	HealthBar enemyHealth = new HealthBar(this.health,(x +bounds.x - handler.getGameCamera().getxOffset()),(y +bounds.y - handler.getGameCamera().getyOffset()),5,""); //((int)(x +bounds.x - handler.getGameCamera().getxOffset()),(int) (y +bounds.y - handler.getGameCamera().getyOffset()), 15, 15)
	@Override
	public void die() {
		zombieDie.play();
		GameState.score+=100;
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

		animLeftS.tick();
		animRightS.tick();
		animDownS.tick();
		animUpS.tick();
		animStaticS.tick();
		//Movimenti




		if(Math.abs(getX()-handler.getGame().getPlayer().getX())<=fov && Math.abs(getY()-handler.getGame().getPlayer().getY())<=fov ) {
			if (handler.getGame().getSocketServer() != null) {
				follow(targetChooser(handler.getGame().getSocketServer().getConnectedPlayers()));
				move();
			}
			follow(handler.getGame().getPlayer());
			move();
			if(Math.abs(getX()-handler.getGame().getPlayer().getX())<=range && Math.abs(getY()-handler.getGame().getPlayer().getY())<=range ) {

				//Aggiunto metodo set healt in ENTITY, la vita e' stata spostata li rivedi condizioni di sconfitta e quella per ricominciare
				handler.getGame().getPlayer().setHealth((handler.getGame().getPlayer().getHealth())-(DEFAULT_CREATURE_DAMAGE));
				//				World.entityManager.getPlayer().setX(100);
				//				World.entityManager.getPlayer().setY(100);
			}
		}else if(Math.abs(getX()-handler.getGame().getPlayer().getX())<=1000 && Math.abs(getY()-handler.getGame().getPlayer().getY())<=1000 ) { 

			walkAround();
			move();

		}

		enemyHealth.tick(this.health);
		enemyHealth.setHeight((int) (this.y - handler.getGameCamera().getyOffset()));
		enemyHealth.setWidth((int) (this.x - handler.getGameCamera().getxOffset()));
	}

	private void follow(Player target) {

		if(target.getY()<getY()-30) {
			yMove = -speed+1;

		}else if(target.getY()>getY()+30) {
			yMove = +speed-1;
		}
		if(target.getX()<getX()-30) {
			xMove = -speed+1;
		}else if(target.getX()>getX()+30) {
			xMove = +speed-1;
		}

	}

	private PlayerMP targetChooser(List<PlayerMP> list) {
		PlayerMP min=list.get(0);
		for(PlayerMP p:list) {
			if(Math.abs (min.getX()-getX()) > (p.getY()-getY())&&Math.abs (min.getY()-getY()) > (p.getY()-getY()) ) {
				min=p;
			}
		}
		return min;
	}

	private void walkAround() {

		if (System.currentTimeMillis() - lastTurn >= 2000) { //l'enemy cambia direzione ogni 2000 millisecondi

			if(new Random().nextInt(4) == 0){
				yMove = -speed+2;
			}
			if(new Random().nextInt(4) == 1){
				yMove = +speed-2;
			}
			if(new Random().nextInt(4) == 2){
				xMove = -speed+2;
			}
			if(new Random().nextInt(4) == 3){
				xMove = +speed-2;
			}
			lastTurn = System.currentTimeMillis(); //tengo conto dell'ultima


		}
	}


	private void follow() {

		if(handler.getGame().getPlayer().getY()<getY()-30) {
			yMove = -speed+1;

		}else if(handler.getGame().getPlayer().getY()>getY()+30) {
			yMove = +speed-1;
		}
		if(handler.getGame().getPlayer().getX()<getX()-30) {
			xMove = -speed+1;
		}else if(handler.getGame().getPlayer().getX()>getX()+30) {
			xMove = +speed-1;
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height,null);
		enemyHealth.paint(g);
	}

	private BufferedImage getCurrentAnimationFrame() {

		if(this.inWater()) {
			if(xMove<0)return  animLeftS.getCurrentFrame();
			else if(xMove>0)return animRightS.getCurrentFrame();
			else if(yMove<0)return animDownS.getCurrentFrame();
			else return animUpS.getCurrentFrame();
		}else {

			if(xMove<0)return  animLeft.getCurrentFrame();
			else if(xMove>0)return animRight.getCurrentFrame();
			else if(yMove<0)return animDown.getCurrentFrame();
			else return animUp.getCurrentFrame();
		}
	}

	@Override 
	public boolean isEnemy() {
		return true;
	}


}
