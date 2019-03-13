package dev.igpe.theamazingame.items;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.craftinterface.CraftMenu;
import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.inventory.Inventory;

public class Item {
	
	// Handler
	
	public static Item[] getItems() {
		return items;
	}

	public static void setItems(Item[] items) {
		Item.items = items;
	}

	public static Item[] items = new Item[256];

	//ITEM
	
	//risorse
	public Item woodItem = ItemList.getWoodItem();
	public Item rockItem = ItemList.getRockItem();
	
	//ammo
	public Item bulletItem = ItemList.getBulletItem();
	
	//Weapons
	public Item gunItem = ItemList.getGunItem();
	
	
	// Class
	
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected int id;
	
	protected Rectangle bounds;
	
	protected int x, y, count;
	private boolean pickedUp= false;
	
	public Item(BufferedImage texture, String name, int id){
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		
		bounds = new Rectangle(x,y,ITEMWIDTH, ITEMHEIGHT);
		
		items[id] = this;
	}
	
	public void tick(){
		if(handler.getGame().getPlayer().getCollisionBounds(0f	, 0f).intersects(bounds)) {
			pickedUp= true;
			
			handler.getGame().getPlayer().getInventory().addItem(this);

		}
		
	}
	
	//questo render renderizza solo le coordinate delle risorse
	public void render(Graphics g){
		if(handler == null)
			return;
		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
	}
	
	//Disegna le texure delle risorse
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
//questo metodo CRAFTA l'item direttamente nell'inventario
	public Item createNew(int count){
		Item i = new Item(texture, name, id);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	//Questo metodo CREA l'item SOLO SULLA MAPPA
	public Item createNew(int x, int y){
		Item i = new Item(texture, name, id);
		i.setPosition(x, y);
		return i;
	}
	
	//METODO PER IL CRAFT VECCHIO
	public Item craftNew(Inventory inv) {
		
		Item i = new Item(texture, name, id);
		return i;
		
		
	}
	
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		
		bounds.x=x;
		bounds.y=y;
	}
	


	
	
	// get e set
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public Item getBulletItem() {
		return bulletItem;
	}

	public void setBulletItem(Item bulletItem) {
		this.bulletItem = bulletItem;
	}

	public Item getWoodItem() {
		return woodItem;
	}

	public void setWoodItem(Item woodItem) {
		this.woodItem = woodItem;
	}

	public int getFireRange() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
