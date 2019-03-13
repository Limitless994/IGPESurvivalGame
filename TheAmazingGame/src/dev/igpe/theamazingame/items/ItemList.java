package dev.igpe.theamazingame.items;

import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.weapons.Gun;

public class ItemList {

	//tipi di oggetti
	
	//RIsorse Grezze
	public static Item woodItem = new Item(Assets.gatWood, "Wood", 0);
	public static Item rockItem = new Item(Assets.gatRock, "Rock", 1);
	public static Item solidfuelItem = new Item(Assets.solidfuel, "Fuel", 2);
	public static Item copperItem = new Item(Assets.gatCopper, "Copper Ore", 3);
	public static Item ironItem = new Item(Assets.gatIron, "Iron Ore", 4);
	
	//munizioni
	public static Item bulletItem = new Item(Assets.bulletAmmo, "Bullet", 10);
	public static Item rocketItem = new Item(Assets.rocketAmmo, "Rocket", 11);
	public static Item incendiarytItem = new Item(Assets.fuelAmmo, "Incendiary Gas", 12);
	public static Item gattoAmmoItem = new Item(Assets.bulletAmmo, "Gatti Volanti", 13);
	
	
	//Armi
	public static Item gunItem = new Item(Assets.gun, "Gun", 20);
	public static Item rocketLauncherItem = new Item(Assets.rocketLauncher, "Rocket Launcher", 21);
	public static Item flameThrowerItem = new Item(Assets.flamethrower, "Flame Thrower", 22);
	public static Item assaultRifleItem = new Item(Assets.assaultRifle, "Assault Rifle", 23);
	public static Item pickAxeItem = new Item(Assets.pickAxe, "Piccone", 24);
	public static Item sparaGattiItem = new Item(Assets.gun, "Spara Gatti", 25);
	
	//Oggetti
	public static Item wallItem = new Item(Assets.wall, "Wall", 30);
	public static Item turretItem = new Item(Assets.assaultRifle, "Turret-Gun", 31);
	
	
	
	
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

	public static Item getRocketItem() {
		return rocketItem;
	}

	public static void setRocketItem(Item rocketItem) {
		ItemList.rocketItem = rocketItem;
	}

	public static Item getIncendiarytItem() {
		return incendiarytItem;
	}

	public static void setIncendiarytItem(Item incendiarytItem) {
		ItemList.incendiarytItem = incendiarytItem;
	}

	public static Item getGunItem() {
		return gunItem;
	}

	public static void setGunItem(Gun gunItem) {
		ItemList.gunItem = gunItem;
	}

	
	
	
	
	
}
