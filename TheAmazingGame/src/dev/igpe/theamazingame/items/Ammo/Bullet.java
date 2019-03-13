package dev.igpe.theamazingame.items.Ammo;

import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;

import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.tiles.Tile;

public class Bullet extends Ammo{

	//0 proiettile
	
	
	public static final int DEFAULT_CALIBRO=1;
	
	private int speed;
	private int id = ItemList.bulletItem.getId();
	
	public Bullet(BufferedImage texture, String name, int id) {
		super(texture, name, id);
		this.speed=speed;
		this.id=id;
		this.calibro= DEFAULT_CALIBRO;
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
