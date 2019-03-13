package dev.igpe.theamazingame.entities.creatures;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.ItemSelectable;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.audio.AudioPlayer;
import dev.igpe.theamazingame.craftinterface.CraftMenu;
import dev.igpe.theamazingame.entities.Entity;
import dev.igpe.theamazingame.gfx.Animation;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.inventory.Inventory;
import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.items.weapons.AssaultRifle;
import dev.igpe.theamazingame.items.weapons.FlameThrower;
import dev.igpe.theamazingame.items.weapons.Gun;
import dev.igpe.theamazingame.items.weapons.RocketLauncher;
import dev.igpe.theamazingame.items.weapons.Weapon;
import dev.igpe.theamazingame.net.packets.Packet02Move;
import dev.igpe.theamazingame.tiles.Tile;
import dev.igpe.theamazingame.ui.UsernameTag;
import dev.igpe.theamazingame.worlds.World;


public class Player extends Creature{

	//Inventario
	private Inventory inventory;
	private CraftMenu craftMenu;
	private AudioPlayer pickAxeSound = new AudioPlayer("/sounds/pickaxe.wav");
	private AudioPlayer walk = new AudioPlayer("/sounds/walk2.wav");
	private UsernameTag tag;
	private long lastwalkTimer, walkCooldown = 400, walkTimer = walkCooldown;

	//Animations
	private Animation animLeft,animRight,animUp,animDown,animStatic;
	private Animation anim_gun_Left,anim_gun_Right,anim_gun_Up,anim_gun_Down,anim_gun_Static;
	private Animation anim_RL_Left,anim_RL_Right,anim_RL_Up,anim_RL_Down,anim_RL_Static;
	private Animation anim_AR_Left,anim_AR_Right,anim_AR_Up,anim_AR_Down,anim_AR_Static;
	private Animation animLeftS,animRightS,animUpS,animDownS,animStaticS;
	//TImer Attacchi, cooldown in millisecondi (100 = 1 secondo)
	private long lastAttackTimer, attackCooldown = 5000, attackTimer = attackCooldown;
	private int damage;
	private int range;
	private String username;

	public Player(Handler handler,float x, float y, String username) {
		super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,Creature.DEFAULT_CREATURE_DAMAGE, 3.0f);
		bounds.x= 12;
		bounds.y = 24;
		bounds.width=20;
		bounds.height=20;
		this.username=username;

		//Animations
		animLeft = new Animation(300,Assets.player_left);
		animRight = new Animation(300,Assets.player_right);
		animDown = new Animation(300,Assets.player_down);
		animUp = new Animation(300,Assets.player_up);
		animStatic = new Animation(300,Assets.playerStatic);

		animLeftS = new Animation(300,Assets.player_left_swimming);
		animRightS = new Animation(300,Assets.player_right_swimming);
		animDownS = new Animation(300,Assets.player_down_swimming);
		animUpS = new Animation(300,Assets.player_up_swimming);
		animStaticS = new Animation(300,Assets.playerStatic_swimming);

		anim_gun_Left = new Animation(300,Assets.player_gun_left);
		anim_gun_Right = new Animation(300,Assets.player_gun_right);
		anim_gun_Down = new Animation(300,Assets.player_gun_down);
		anim_gun_Up = new Animation(300,Assets.player_gun_up);
		anim_gun_Static = new Animation(300,Assets.player_gun_Static);

		anim_AR_Left = new Animation(300,Assets.player_AR_left);
		anim_AR_Right = new Animation(300,Assets.player_AR_right);
		anim_AR_Down = new Animation(300,Assets.player_AR_down);
		anim_AR_Up = new Animation(300,Assets.player_AR_up);
		anim_AR_Static = new Animation(300,Assets.player_AR_Static);

		anim_RL_Left = new Animation(300,Assets.player_RL_left);
		anim_RL_Right = new Animation(300,Assets.player_RL_right);
		anim_RL_Down = new Animation(300,Assets.player_RL_down);
		anim_RL_Up = new Animation(300,Assets.player_RL_up);
		anim_RL_Static = new Animation(300,Assets.player_RL_Static);



		inventory = new Inventory(handler);
		craftMenu=new CraftMenu(handler);
		tag =new UsernameTag(x, y, this.username);

	}

	public void FightTipe() {
		if(Inventory.activeWeapon == ItemList.gunItem) {
			attackCooldown=Gun.getDEFAULTFIRERATE();
			damage =Gun.getDEFAULTDAMAGE();
			range = Gun.getDEFAULTFIRERANGE();

		}
		else if(Inventory.activeWeapon == ItemList.assaultRifleItem) {
			attackCooldown= AssaultRifle.getDEFAULTFIRERATE();
			damage =AssaultRifle.getDEFAULTDAMAGE();
			range = AssaultRifle.getDEFAULTFIRERANGE();
		}
		else if(Inventory.activeWeapon == ItemList.rocketLauncherItem) {
			attackCooldown= RocketLauncher.getDEFAULTFIRERATE();
			damage =RocketLauncher.getDEFAULTDAMAGE();
			range = RocketLauncher.getDEFAULTFIRERANGE();
		}
		else if(Inventory.activeWeapon == ItemList.flameThrowerItem) {
			attackCooldown= FlameThrower.getDEFAULTFIRERATE();
			damage =FlameThrower.getDEFAULTDAMAGE();
			range = FlameThrower.getDEFAULTFIRERANGE();
		}
		else if(Inventory.activeWeapon == ItemList.pickAxeItem){
			attackCooldown=350;
			damage =20;
			range = 55;
		}



	}

	//controlla l'esito degli attacchi con le collision box
	private void checkAttacks() {

		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();

		if(inventory.isActive()||craftMenu.isActive())
			return;

		//cooldown
		if(attackTimer < attackCooldown)
			return;


		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize =20;
		ar.width = arSize;
		ar.height=arSize;


		if(handler.getKeyManager().aUp && Inventory.activeWeapon == ItemList.pickAxeItem){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;

		}else if(handler.getKeyManager().aDown && Inventory.activeWeapon == ItemList.pickAxeItem){
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft && Inventory.activeWeapon == ItemList.pickAxeItem){
			System.out.println("attacco in corso");
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight && Inventory.activeWeapon == ItemList.pickAxeItem){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		//attacco nuovo con mouse
		else if(handler.getKeyManager().attack && Inventory.activeWeapon != ItemList.pickAxeItem) {
			spaceAttack(cb, ar);
		}else{
			return;
		}



		attackTimer = 0;


		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				if(inventory.CheckAmmo()==true) {

					if(Math.abs(e.getX()-this.getX())<=range && Math.abs(e.getY()-this.getY())<=range ) {
						if(e.isEnemy()) {
							inventory.RemoveAmmo();
							e.hurt(damage);
							return;
						}
						else if(Inventory.activeWeapon == ItemList.pickAxeItem) {
							pickAxeSound.play(); 
							e.hurt(damage);
							return;
						}

					}
				}
			}
		}

	}

	public void spaceAttack(Rectangle cb, Rectangle ar) {

		ar.x = (int) ((handler.getMouseManager().getMouseX())+(handler.getGameCamera().getxOffset()));
		ar.y = (int) ((handler.getMouseManager().getMouseY())+(handler.getGameCamera().getyOffset()));

		//System.out.println("POSIZIONE MOUSE" + " "+ cb.x +" "+  cb.y);	
	}

	public void die() {

		System.out.println("Hai Perso!");
	}


	@Override
	public void tick() {
		tag.tick(this.x - handler.getGameCamera().getxOffset(),this.y - handler.getGameCamera().getyOffset());
//VERIFICA che il player sia quello che devi controllare
		if (Game.getPlayer().getUsername().equals(getUsername())) {
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

			anim_gun_Left.tick();
			anim_gun_Right.tick();
			anim_gun_Down.tick();
			anim_gun_Up.tick();

			anim_RL_Left.tick();
			anim_RL_Right.tick();
			anim_RL_Down.tick();
			anim_RL_Up.tick();

			anim_AR_Left.tick();
			anim_AR_Right.tick();
			anim_AR_Down.tick();
			anim_AR_Up.tick();

			//Movimenti
			getInput();
			move();

			handler.getGameCamera().centerOnEntity(this);


			Packet02Move packet = new Packet02Move(this.getUsername(), this.x, this.y, this.isMoving);
			packet.writeClientData(handler.getGame().getSocketClient());



			//attacchi
			checkAttacks();
			FightTipe();

			//tick inventario
			inventory.tick();
			inventory.switchweapon();
			inventory.adminadd();
			inventory.CheckAmmo();


			//tick craft
			craftMenu.tick();
			craftMenu.checkCraftable();
			craftMenu.addItem();
			
		

	}

	}

	private void getInput() {
		if(inventory.isActive()||craftMenu.isActive())
			return;

		xMove=0;
		yMove=0;

		
		
		
		
		if(handler.getKeyManager().up) {
			yMove= -speed;			 
			isMoving = true;
		}
		if(handler.getKeyManager().down) {
			yMove= +speed;	
			isMoving = true;
		}
		if(handler.getKeyManager().left) {
			xMove= -speed;
			isMoving = true;
		}

		if(handler.getKeyManager().right) {
			xMove= +speed;
			isMoving = true;
		}
		else {
			isMoving = false;
		}
	
	}




	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height,null);

		//			g.setColor(Color.blue);
		//				g.fillRect( (int) (x + bounds.x-handler.getGameCamera().getxOffset()),
		//					(int)(y + bounds.y-handler.getGameCamera().getyOffset()),
		//						bounds.width, bounds.height);

	}

	//render per non fare casini quando apri l'inventario(immagini sovrapposte)
	public void postRender(Graphics g){
		tag.paint(g);
		inventory.render(g);
		craftMenu.render(g);
	}

	private BufferedImage getCurrentAnimationFrame() {


		if(this.inWater()) {
			if(xMove<0)return animLeftS.getCurrentFrame();
			if(xMove<0)return animLeftS.getCurrentFrame();
			else if(xMove>0)return animRightS.getCurrentFrame();
			else if(yMove<0)return animDownS.getCurrentFrame();
			else if(yMove>0)return animUpS.getCurrentFrame();
			else return animStaticS.getCurrentFrame();
		}

		if(Inventory.activeWeapon == ItemList.gunItem) {
			if(xMove<0)return anim_gun_Left.getCurrentFrame();
			else if(xMove>0)return anim_gun_Right.getCurrentFrame();
			else if(yMove<0)return anim_gun_Down.getCurrentFrame();
			else if(yMove>0)return anim_gun_Up.getCurrentFrame();
			else return anim_gun_Static.getCurrentFrame();

		}
		else if(Inventory.activeWeapon == ItemList.assaultRifleItem) {
			if(xMove<0)return anim_AR_Left.getCurrentFrame();
			else if(xMove>0)return anim_AR_Right.getCurrentFrame();
			else if(yMove<0)return anim_AR_Down.getCurrentFrame();
			else if(yMove>0)return anim_AR_Up.getCurrentFrame();
			else return anim_AR_Static.getCurrentFrame();
		}
		else if(Inventory.activeWeapon == ItemList.rocketLauncherItem) {
			if(xMove<0)return anim_RL_Left.getCurrentFrame();
			else if(xMove>0)return anim_RL_Right.getCurrentFrame();
			else if(yMove<0)return anim_RL_Down.getCurrentFrame();
			else if(yMove>0)return anim_RL_Up.getCurrentFrame();
			else return anim_RL_Static.getCurrentFrame();
		}
		else if(Inventory.activeWeapon == ItemList.flameThrowerItem) {
			if(xMove<0)return animLeft.getCurrentFrame();
			else if(xMove>0)return animRight.getCurrentFrame();
			else if(yMove<0)return animDown.getCurrentFrame();
			else if(yMove>0)return animUp.getCurrentFrame();
			else return animStatic.getCurrentFrame();
		}
		else {
			if(xMove<0)return animLeft.getCurrentFrame();
			else if(xMove>0)return animRight.getCurrentFrame();
			else if(yMove<0)return animDown.getCurrentFrame();
			else if(yMove>0)return animUp.getCurrentFrame();
			else return animStatic.getCurrentFrame();
		}	

	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public CraftMenu getCraftMenu() {
		return craftMenu;
	}

	public void setCraftMenu(CraftMenu craftMenu) {
		this.craftMenu = craftMenu;
	}

	public long getAttackCooldown() {
		return attackCooldown;
	}

	public void setAttackCooldown(long attackCooldown) {
		this.attackCooldown = attackCooldown;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}





}
