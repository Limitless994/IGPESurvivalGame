package dev.igpe.theamazingame.entities.creatures;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;



import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.craftinterface.craftmenu;
import dev.igpe.theamazingame.entities.Entity;
import dev.igpe.theamazingame.gfx.Animation;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.inventory.Inventory;

public class Player extends Creature{
	
	//Inventario
	private Inventory inventory;
	//craft menu

	//Animations
private Animation animLeft,animRight,animUp,animDown,animStatic;
//TImer Attacchi, cooldown in millisecondi (100 è 1 secondo)
private long lastAttackTimer, attackCooldown = 10, attackTimer = attackCooldown;


	public Player(Handler handler,float x, float y) {
		super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,Creature.DEFAULT_CREATURE_DAMAGE);
		bounds.x= 10;
		bounds.y = 20;
		bounds.width=20;
		bounds.height=20;
		
		//Animations
		animLeft = new Animation(300,Assets.player_left);
		animRight = new Animation(300,Assets.player_right);
		animDown = new Animation(300,Assets.player_down);
		animUp = new Animation(300,Assets.player_up);
		animStatic = new Animation(300,Assets.playerStatic);
		
		inventory = new Inventory(handler);
	}
	
	//controlla l'esito degli attacchi con le collision box
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(inventory.isActive())
			return;
		
		//cooldown
		if(attackTimer < attackCooldown)
			return;
		
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize =20;
		ar.width = arSize;
		ar.height=arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft){
			System.out.println("attacco in corso");
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else{
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				e.hurt(1);
				return;
			}
		}
		
	}

	public void die() {
		
		System.out.println("Hai Perso!");
	}
	
	
	@Override
	public void tick() {
		//Animations
		animLeft.tick();
		animRight.tick();
		animDown.tick();
		animUp.tick();
		//Movimenti
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//attacchi
				checkAttacks();
				
				//tick inventario
				inventory.tick();
				
				//tick craft
	}
	
	private void getInput() {
	if(inventory.isActive())
		return;
	
		xMove=0;
		yMove=0;
		
		if(handler.getKeyManager().up) {
			yMove= -speed;
		}
		if(handler.getKeyManager().down) {
			yMove= +speed;
		}
		if(handler.getKeyManager().left) {
			xMove= -speed;	
		}
			
		if(handler.getKeyManager().right) {
			xMove= +speed;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height,null);
				
		g.setColor(Color.red);
		g.fillRect( (int) (x + bounds.x-handler.getGameCamera().getxOffset()),
			(int)(y + bounds.y-handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
	}
	
	//render per non fare casini quando apri l'inventario(immagini sovrapposte)
	public void postRender(Graphics g){
		inventory.render(g);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if(xMove<0)return animLeft.getCurrentFrame();
		else if(xMove>0)return animRight.getCurrentFrame();
		else if(yMove<0)return animDown.getCurrentFrame();
		else if(yMove>0)return animUp.getCurrentFrame();
		else return animStatic.getCurrentFrame();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	
	
	
	
}
