package dev.igpe.theamazingame.items.Ammo;

import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.items.Item;

public  abstract class Ammo extends Item{


	// 0 proiettile, 1 razzo, 2 lanciafiamme

	
protected int calibro;
protected int speed;

public Ammo(BufferedImage texture, String name, int id) {
	super(texture, name, id);
	this.speed = speed;
	this.calibro= calibro;
}

public int getCalibro() {
	return calibro;
}

public void setCalibro(int calibro) {
	this.calibro = calibro;
}

	
	}


