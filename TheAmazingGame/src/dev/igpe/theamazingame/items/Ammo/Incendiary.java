package dev.igpe.theamazingame.items.Ammo;

import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.items.ItemList;

public class Incendiary extends Ammo{


	private int speed;
	private int id = ItemList.incendiarytItem.getId();

	public static final int DEFAULT_CALIBRO=4;
	
	public Incendiary(BufferedImage texture, String name, int id) {
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
