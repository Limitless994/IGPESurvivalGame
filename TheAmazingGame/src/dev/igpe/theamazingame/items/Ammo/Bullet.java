package dev.igpe.theamazingame.items.Ammo;

import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.tiles.Tile;

public class Bullet extends Ammo{
	
	

	//0 proiettile
	
	//id è 2
	
	
	public static final int DEFAULT_CALIBRO=0;
	
	private int speed;
	private int id = ItemList.bulletItem.getId();
	
	//costo craft
	private int costo =2;
	
	public Bullet(int speed, int calibro, int id, int costo) {
		super(speed, calibro, id);
		this.speed=speed;
		this.id=id;
		this.costo=costo;
		
	}
	

	public int getCosto() {
		return costo;
	}


	public void setCosto(int costo) {
		this.costo = costo;
	}


	public static int getDefaultCalibro() {
		return DEFAULT_CALIBRO;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getId() {
		return id;
	}


	
}
