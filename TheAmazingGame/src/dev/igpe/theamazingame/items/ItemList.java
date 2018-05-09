package dev.igpe.theamazingame.items;

import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.Item;

public class ItemList {

	//tipi di oggetti
	public static Item woodItem = new Item(Assets.gatWood, "Wood", 0);
	public static Item rockItem = new Item(Assets.gatRock, "Rock", 1);
	
	//munizioni
	public static Item bulletItem = new Item(Assets.bulletAmmo, "Bullet", 2);
	public static Item rocketItem = new Item(Assets.bulletAmmo, "Rocket", 3);
	public static Item incendiarytItem = new Item(Assets.bulletAmmo, "Incendiary Gas", 4);
	
	
	
	
	
	//GET E SET VARI
	public static Item getWoodItem() {
		return woodItem;
	}

	public static void setWoodItem(Item woodItem) {
		ItemList.woodItem = woodItem;
	}

	public static Item getRockItem() {
		return rockItem;
	}

	public static void setRockItem(Item rockItem) {
		ItemList.rockItem = rockItem;
	}

	public static Item getBulletItem() {
		return bulletItem;
	}

	public static void setBulletItem(Item bulletItem) {
		ItemList.bulletItem = bulletItem;
	}
	
	
	
	
	
}
