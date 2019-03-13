package dev.igpe.theamazingame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.entities.Entity;
import dev.igpe.theamazingame.gfx.Animation;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.inventory.Inventory;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.items.weapons.Gun;
import dev.igpe.theamazingame.tiles.Tile;
import dev.igpe.theamazingame.worlds.World;

public class turret extends StaticEntity {
	private int fov=500; //distanza in pixel in cui la torretta può vedere, field of view
	private int range=350;//distanza in pixel in cui la torretta può sparare
	private int damage=10;
	private long lastAttackTimer, attackCooldown = 350, attackTimer = attackCooldown;
	private Animation animLeft,animRight,animUp,animDown	;
	private Entity target=handler.getWorld().entityManager.getEntities().get(0);

	public turret(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);


		bounds.x = 28;
		bounds.y = 48;
		bounds.width = 48;
		bounds.height = 32;

		animLeft = new Animation(300,Assets.turret_left);
		animRight = new Animation(300,Assets.turret_right);
		animDown = new Animation(300,Assets.turret_down);
		animUp = new Animation(300,Assets.turret_up);
	}

	@Override
	public void die() {
		//manca suono torretta distrutta
	}

	@Override
	public void tick() {
		animLeft.tick();
		animRight.tick();
		animDown.tick();
		animUp.tick();

		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		for(Entity e:handler.getWorld().getEntityManager().getEntities()) {
		
			if(Math.abs(e.getX()-this.getX())<=fov && Math.abs(e.getY()-this.getY())<=fov&&e.isEnemy() ) {
				target=e;
				if(Math.abs(e.getX()-this.getX())<=range && Math.abs(e.getY()-this.getY())<=range ) {
					e.hurt(damage);
					
//					e.setHealth((e.getHealth())-(damage));
					Gun.getDEFAULTSHOOTSOUND().play();
				}

			}
		}
		attackTimer = 0;//controllare timer


	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height,null);
		//		g.setColor(Color.red);
		//		g.fillRect((int)(x +bounds.x - handler.getGameCamera().getxOffset()),(int) (y +bounds.y - handler.getGameCamera().getyOffset()), 48, 48);

	}

	private BufferedImage getCurrentAnimationFrame() {
		//		System.out.println("PLAYER X: "+ handler.getGame().getPlayer().getX()+" Y :"+handler.getGame().getPlayer().getY());
		//		System.out.println("TURRET X: "+getX()+" Y :"+getY());

		if(target.getX()-getX()>0&&target.getX()>target.getY())return animRight.getCurrentFrame();
		else if(target.getX()-getX()<0&&target.getX()<target.getY())return animLeft.getCurrentFrame();
		else if(target.getY()-getY()<0)return animDown.getCurrentFrame();
		return animUp.getCurrentFrame();


	}

}
