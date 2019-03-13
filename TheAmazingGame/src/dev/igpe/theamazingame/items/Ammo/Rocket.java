package dev.igpe.theamazingame.items.Ammo;

import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.items.ItemList;

public class Rocket extends Ammo{

	//5 rocket
	
	public static final int DEFAULT_CALIBRO=5;

	private int speed;
	private int id = ItemList.rocketItem.getId();
	
	public Rocket(BufferedImage texture, String name, int id) {
		super(texture, name, id);
		this.speed=speed;
		this.id=id;
		this.calibro= DEFAULT_CALIBRO;
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

	public void setId(int id) {
		this.id = id;
	}

	public static int getDefaultCalibro() {
		return DEFAULT_CALIBRO;
	}
	
	


}
