package dev.igpe.theamazingame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.text.Font;
import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.items.weapons.FlameThrower;
import dev.igpe.theamazingame.items.weapons.Gun;
import dev.igpe.theamazingame.items.weapons.RocketLauncher;
import dev.igpe.theamazingame.worlds.World;
import dev.igpe.theamazingame.gfx.Text;


public class Inventory {


	private Handler handler;
	private boolean active = false;
	private static ArrayList<Item> inventoryItems;
	private ArrayList<Item> tempinventoryItems;
	private int ammoCount=0;
	private int invX = 64, invY = 148,
			invWidth = 512, invHeight = 384,
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;

	private int invImageX = 452, invImageY = 182,
			invImageWidth = 64, invImageHeight = 64;

	private int invCountX = 484, invCountY = 272;

	private int selectedItem = 0;


	//COSTRUTTORE
	public Inventory(Handler handler){
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}

	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) //attivare il menu
			active = !active;
		if(!active)
			return;

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem++;

		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;


	}

	public void render(Graphics g){
		if(!active)
			return;

		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

		int len = inventoryItems.size();
		if(len == 0)
			return;

		//render dell'item selezionato nell'inventario. fa vedere l'imm della risorsa e il numero
		for(int i = -5;i < 6;i++){
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			if(i == 0){
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
			}else{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
			}
		}

		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
	}

	// Metodi Inventario


	//Metodo che aggiunge gli oggetti in inventario utilizzabile sia se raccogli le risorse da terra sia per il craft
	public void addItem(Item item){
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}

		}

		inventoryItems.add(item);
	}

	public void adminadd(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
			
		addItem(ItemList.bulletItem);
			addItem(ItemList.gunItem);
			addItem(ItemList.rocketLauncherItem);
			addItem(ItemList.flameThrowerItem);
			addItem(ItemList.assaultRifleItem);
			addItem(ItemList.incendiarytItem);
			addItem(ItemList.rocketItem);
		addItem(ItemList.pickAxeItem);
			for(Item i : inventoryItems){	
				if(i.getId() ==10 ||i.getId() ==11 ||i.getId() ==12 ){
					if(i.getCount()<101)
					i.setCount(i.getCount() + 100);
				}
			}
			
			
			return;

		}



	}




	public void removeItem(Item item) {
		ArrayList<Item> tempInventory;
		tempInventory=handler.getGame().getPlayer().getInventory().getInventoryItems();
		for(Item i : tempInventory){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() - 1);
			}
		}
		inventoryItems=tempInventory;	
	}


	public static Item activeWeapon;
	Item next=null;

	
	public void switchweapon(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)){

			for(Item i : inventoryItems){


				if((i.getId()>=20 && i.getId()<=24) && i.getCount()>=1 && next ==null ) {

					activeWeapon =i;

					next =i;
					System.out.println("L'arma attiva ha id " +i.getId());
					return;
				}
				else if((i.getId()>=20 && i.getId()<=24) && i.getCount()>=1 && next!=null &&( i.getId() > activeWeapon.getId() ) ){
					activeWeapon =i;
	
					System.out.println("L'arma attiva ha id " + i.getId());
					if(i.getId()==24) {
						next =null;
					}
					return;
				}

			}
		}
	}

	public boolean CheckAmmo() {

		//Check Gun
			if(activeWeapon ==ItemList.gunItem){
				for(Item i : inventoryItems){
					if(i.getId()==10 && i.getCount()>=0) {
						ammoCount=i.getCount();
						return true;
					}
				}	
			}
			
			//Check Assault Rifle
			if(activeWeapon== ItemList.assaultRifleItem){
				for(Item i : inventoryItems){
					if(i.getId()==10 && i.getCount()>=1) {
						ammoCount=i.getCount();
						return true;
					}
				}	
			}
			//Check rocket
			if(activeWeapon==ItemList.rocketLauncherItem){
				for(Item i : inventoryItems){
					if(i.getId()==11 && i.getCount()>=1) {
						ammoCount=i.getCount();
						return true;
					}
				}	
			}
			//Check Lanciafiamme
			if(activeWeapon==ItemList.flameThrowerItem){
				for(Item i : inventoryItems){
					if(i.getId()==12 && i.getCount()>=1) {
						ammoCount=i.getCount();
						return true;
					}
				}	
			}
			if(activeWeapon==ItemList.pickAxeItem){
				return true;
			}

		
		return false;

	}
	
	public void RemoveAmmo() {
		Item ammoRem = null;
		if(activeWeapon ==ItemList.gunItem ||activeWeapon ==ItemList.assaultRifleItem){
			ammoRem = ItemList.bulletItem;
			Gun.getDEFAULTSHOOTSOUND().play(); //il suono avviene solo quando viene rimosso un proiettile
		}
		else if(activeWeapon==ItemList.rocketLauncherItem) {
			ammoRem = ItemList.rocketItem;
			RocketLauncher.getDEFAULTSHOOTSOUND().play();
		}
		else if(activeWeapon==ItemList.flameThrowerItem) {
			ammoRem = ItemList.incendiarytItem;
			FlameThrower.getDEFAULTSHOOTSOUND().play();
		}
		for(Item i : inventoryItems){
			if(i == ammoRem) {
				i.setCount(i.getCount()-1 );
				
			}
		}
		
	}
	
	
	
	// get e set vari

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	public ArrayList<Item> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(ArrayList<Item> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}


	public static Item getActiveWeapon() {
		return activeWeapon;
	}

	public void setActiveWeapon(Item activeWeapon) {
		this.activeWeapon = activeWeapon;
	}


	public int getAmmoCount() {
		return ammoCount;
	}

	public void setAmmoCount(int ammoCount) {
		this.ammoCount = ammoCount;
	}


}
