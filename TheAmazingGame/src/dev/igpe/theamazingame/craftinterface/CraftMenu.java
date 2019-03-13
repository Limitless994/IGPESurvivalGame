package dev.igpe.theamazingame.craftinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.text.Font;
import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.entities.Entity;
import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.entities.statics.IronOre;
import dev.igpe.theamazingame.entities.statics.Petrol;
import dev.igpe.theamazingame.entities.statics.Wall;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.tiles.Tile;
import dev.igpe.theamazingame.worlds.World;
import dev.igpe.theamazingame.gfx.Text;
import dev.igpe.theamazingame.inventory.Inventory;


public class CraftMenu {


	private static final Item Item = null;
	private Handler handler;
	private boolean active = false;
	private boolean craftObW= false;
	private boolean craftObT= false;

	private ArrayList<Item> craftableItems;
	private ArrayList<Item> TempinventoryItems;

	private int invX = 664, invY = 48,
			invWidth = 512, invHeight = 384,
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;

	private int invImageX = 1052, invImageY = 82,
			invImageWidth = 64, invImageHeight = 64;

	private int invCountX = 484, invCountY = 172;

	private int selectedItem = 0;
	private HashMap hm = new HashMap();

	//COSTRUTTORE
	public CraftMenu(Handler handler){
		this.handler = handler;
		craftableItems = new ArrayList<Item>();

		int [] bullet = {0,0,0,0,1};
		hm.put(10, bullet);

		int [] rocket = {0,0,3,2,1};
		hm.put(11, rocket);

		int [] gas = {0,0,1,0,1};
		hm.put(12, gas);

		int [] gun = {2,0,0,0,2};
		hm.put(20, gun);

		int [] rl = {0,3,0,3,3};
		hm.put(21, rl);

		int [] flameLauncher = {0,1,0,2,2};
		hm.put(22, flameLauncher);

		int [] AR = {0,2,0,0,4};
		hm.put(23, AR);

		int [] pickaxe = {1,0,0,0,0};
		hm.put(24, pickaxe);

		int [] mura = {0,1,0,0,0};
		hm.put(30, mura);

		int [] torretta = {0,0,2,5,5};
		hm.put(31, torretta);
	}

	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) //attivare il menu
			active = !active;
		if(!active)
			return;

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
			selectedItem++;

		if(selectedItem < 0)
			selectedItem = craftableItems.size() - 1;
		else if(selectedItem >= craftableItems.size())
			selectedItem = 0;

	}

	public void render(Graphics g){
		if(!active)
			return;

		g.drawImage(Assets.craftScreen, invX, invY, invWidth, invHeight, null);

		int len = craftableItems.size();
		if(len == 0)
			return;

		//render dell'item selezionato nell'inventario. fa vedere l'imm della risorsa e il numero
		for(int i = -5;i < 6;i++){
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			if(i == 0){
				Text.drawString(g, "> " + craftableItems.get(selectedItem + i).getName() + " <", invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
			}else{
				Text.drawString(g, craftableItems.get(selectedItem + i).getName(), invListCenterX, 
						invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
			}
		}

		Item item = craftableItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		//Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
		if(item.getId()==10) { 

			Text.drawString(g,"COPPER " + Integer.toString(0), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(1), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(0), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(0), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(0), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
		}	
		if(item.getId()==11) { 

			Text.drawString(g,"COPPER " + Integer.toString(2), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(1), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(3), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(0), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(0), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
		}
		if(item.getId()==12) { 

			Text.drawString(g,"COPPER " + Integer.toString(0), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(1), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(0), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(0), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(1), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
		}
		if(item.getId()==20) { 
			
			Text.drawString(g,"COPPER " + Integer.toString(0), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(2), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(0), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(2), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(0), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
			}
		if(item.getId()==21) { 
			
			Text.drawString(g,"COPPER " + Integer.toString(3), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(3), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(3), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(0), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(0), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
			}
		if(item.getId()==22) { 
			
			Text.drawString(g,"COPPER " + Integer.toString(2), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(2), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(1), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(0), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(0), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
			}
		if(item.getId()==23) { 
			
			Text.drawString(g,"COPPER " + Integer.toString(0), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(4), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(2), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(0), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(0), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
			}
		if(item.getId()==24) { 
			
			Text.drawString(g,"COPPER " + Integer.toString(0), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(0), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(0), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(1), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(0), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
			}
		if(item.getId()==30) { 
			
			Text.drawString(g,"COPPER " + Integer.toString(0), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(0), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(1), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(0), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(0), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
			}
		if(item.getId()==31) { 
			
			
			Text.drawString(g,"COPPER " + Integer.toString(5), invCountX+600, invCountY+60, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"IRON " + Integer.toString(5), invCountX+600, invCountY+85, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"STONE " + Integer.toString(0), invCountX+600, invCountY+110, true, Color.WHITE, Assets.font28);	
			Text.drawString(g,"WOOD " + Integer.toString(0), invCountX+600, invCountY+135, true, Color.WHITE, Assets.font28);
			Text.drawString(g,"FUEL " + Integer.toString(2), invCountX+600, invCountY+160, true, Color.WHITE, Assets.font28);
			}
	}

	// Metodi Inventario

	public Item ReturnSelected() {
		Item selezionato = craftableItems.get(selectedItem);
		return selezionato;
	}

	//metodo che sposta le cose dal craft all'inventario
	public void addItem(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){

			TempinventoryItems=handler.getGame().getPlayer().getInventory().getInventoryItems();
			Item item = this.ReturnSelected();

			if(checkRecipes(item)==true) {
				//CONTROLLO MURO
				if(item==ItemList.wallItem) {
					craftObW=true;
					return;	
				}
				//controllo TORRETTA
				else if(item==ItemList.turretItem) {
					craftObT=true;
					return;	
				}
				else {
					for(Item i : TempinventoryItems){
						if(i.getId() == item.getId()){

							i.setCount(i.getCount() + 1);
							return;
						}
					}
					TempinventoryItems.add(item);
				}

			}
			handler.getGame().getPlayer().getInventory().setInventoryItems(TempinventoryItems);
		}
		craftObW=false;
		craftObT=false;
	}

	//Gli passi un item e se e' craftabile rimuove gli item, altrimenti non fa nulla
	public boolean checkRecipes(Item temp) {

		for(Item i : TempinventoryItems){
			//RECIPE PISTOLA
			//Costo: 2 Metallo, 2 legno
			if(temp.getId()== 20){
				if(checkResources(0,4,0,2,2,0)==true) {
					removeItem(ItemList.woodItem, 2);
					removeItem(ItemList.ironItem, 2);
					return true;
				}
			}
			//Recipe Piccone
			//COSTO: 1 Legno
			if(temp.getId()== 24){
				if(checkResources(0,0,0,1,0,0)==true) {
					removeItem(ItemList.woodItem, 1);
					return true;
				}
			}
			//Recipe Lanciarazzi
			//COSTO: 3 Ferro, 3 Pietra, 3 Copper
			if(temp.getId()== 21){
				if(checkResources(4,1,3,3,3,3)==true) {
					removeItem(ItemList.rockItem, 3);
					removeItem(ItemList.ironItem, 3);
					removeItem(ItemList.copperItem, 3);
					return true;
				}
			}
			//RECIPE LANCIAFIAMME
			//COSTO: 2 ferro, 2 copper,1 Pietra
			if(temp.getId()== 22){
				if(checkResources(4,1,3,2,1,2)==true) {
					removeItem(ItemList.rockItem, 1);
					removeItem(ItemList.ironItem, 2);
					removeItem(ItemList.copperItem, 2);
					return true;
				}
			}
			//RECIPE ASSAULTRIFLE
			//COSTO: 4 ferro, 2 Pietra
			if(temp.getId()== 23){
				if(checkResources(4,1,0,4,2,0)==true) {
					removeItem(ItemList.rockItem, 2);
					removeItem(ItemList.ironItem, 4);
					return true;
				}
			}
			//RECIPE BULLET
			//COSTO: 1 Metallo
			if(temp.getId()== 10){
				if(checkResources(4,0,0,1,0,0)==true) {
					removeItem(ItemList.ironItem, 1);

					return true;
				}
			}
			//RECIPE RAZZO
			//COSTO: 1 Metallo,2 copper,3 fuel
			if(temp.getId()== 11){
				if(checkResources(4,3,2,1,2,3)==true) {
					removeItem(ItemList.ironItem, 1);
					removeItem(ItemList.copperItem, 2);
					removeItem(ItemList.solidfuelItem, 3);
					return true;
				}
			}
			//RECIPE GAS Lanciafiamme
			//COSTO: 1 Metallo,1 Fuel
			if(temp.getId()== 12){
				if(checkResources(4,2,0,1,1,0)==true) {
					removeItem(ItemList.ironItem, 1);
					removeItem(ItemList.solidfuelItem, 3);
					return true;
				}
			}
			//RECIPE MURA
			//COSTO: 1 roccia
			if(temp.getId()== 30){
				if(checkResources(1,0,0,1,0,0)==true) {
					removeItem(ItemList.rockItem, 1);
					return true;
				}
			}
			//RECIPE Torretta
			//COSTO: 5 Ferro, 5 Copper, 2 Fuel
			if(temp.getId()== 31){
				if(checkResources(4,3,2,5,5,2)==true) {
					removeItem(ItemList.ironItem, 5);
					removeItem(ItemList.copperItem, 5);
					removeItem(ItemList.solidfuelItem, 2);
					return true;
				}
			}


		}

		return false;
	}

	public void removeItem(Item item, int costo){
		for(Item i : TempinventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() - costo);
				return;
			}

		}
	}

	//Questa funzione controlla che nel tuo inventario tu abbia le risorse necessarie per craftare. Se ritorna true le hai
	public boolean checkResources(int id1, int id2,int id3, int costo1,int costo2,int costo3) {
		boolean craft1=false;
		boolean craft2=false;
		boolean craft3=false;

		for(Item i : TempinventoryItems){
			if(i.getId()==id1 && i.getCount()>=costo1){
				craft1 =true;
			}
			if(i.getId()==id2 && i.getCount()>=costo2){
				craft2 =true;
			}
			if(i.getId()==id3 && i.getCount()>=costo3){
				craft3 =true;
			}		
		}
		if(craft1==true && craft2==true &&craft3 ==true) {
			return true;
		}
		else {
			return false;
		}

	}


	private boolean containItem=false;
	boolean stop=false;

	//Qui Controlla se hai le risorse per craftare un item e se true te lo fa vedere nel menu craft
	public void checkCraftable() {
		if(stop==false) {

			//ammo
			craftableItems.add(ItemList.bulletItem);
			craftableItems.add(ItemList.rocketItem);
			craftableItems.add(ItemList.incendiarytItem);

			//armi
			craftableItems.add(ItemList.gunItem);
			craftableItems.add(ItemList.rocketLauncherItem);
			craftableItems.add(ItemList.flameThrowerItem);
			craftableItems.add(ItemList.pickAxeItem);

			//Oggetti
			craftableItems.add(ItemList.wallItem);
            craftableItems.add(ItemList.turretItem);

			stop = true;
		}



	}





	//	public void removeItem(Item item) {
	//		ArrayList<Item> tempInventory;
	//		tempInventory=World.entityManager.getPlayer().getInventory().getInventoryItems();
	//		for(Item i : tempInventory){
	//			if(i.getId() == item.getId()){
	//				i.setCount(i.getCount() - 1);
	//			}
	//		}
	//		inventoryItems=tempInventory;	
	//	}

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

	public ArrayList<Item> getCraftableItems() {
		return craftableItems;
	}

	public void setCraftableItems(ArrayList<Item> craftableItems) {
		this.craftableItems = craftableItems;
	}

	public int getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}

	public static Item getItem() {
		return Item;
	}

	public boolean isCraftObW() {
		return craftObW;
	}

	public void setCraftObW(boolean craftObW) {
		this.craftObW = craftObW;
	}

	public boolean isCraftObT() {
		return craftObT;
	}

	public void setCraftObT(boolean craftObT) {
		this.craftObT = craftObT;
	}








}