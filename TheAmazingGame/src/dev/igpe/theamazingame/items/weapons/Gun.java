package dev.igpe.theamazingame.items.weapons;

import java.awt.image.BufferedImage;

import dev.igpe.theamazingame.audio.AudioPlayer;
import dev.igpe.theamazingame.items.ItemList;

public class Gun extends Weapon{

	

	private static int DEFAULTDAMAGE = 5;
    private static long DEFAULTFIRERATE = 350;
    private int DEFAULTCALIBRO = 1;
    private int id = ItemList.gunItem.getId();
    private static int DEFAULTFIRERANGE = 400;
    private static AudioPlayer DEFAULTSHOOTSOUND = new AudioPlayer("/sounds/9m.wav");
    
	public Gun(BufferedImage texture, String name, int id) {
		super(texture, name, id, DEFAULTDAMAGE, DEFAULTFIRERATE, DEFAULTFIRERANGE,DEFAULTSHOOTSOUND);
		this.calibro=DEFAULTCALIBRO;
	    this.damage =DEFAULTDAMAGE;
	    this.fireRate = DEFAULTFIRERATE;
	    this.id= id;
	    this.fireRange = DEFAULTFIRERANGE;
	    this.shootSound =DEFAULTSHOOTSOUND ;
	}

	//get e set vari
	

	public static AudioPlayer getDEFAULTSHOOTSOUND() {
		return DEFAULTSHOOTSOUND;
	}

	public static void setDEFAULTSHOOTSOUND(AudioPlayer dEFAULTSHOOTSOUND) {
		DEFAULTSHOOTSOUND = dEFAULTSHOOTSOUND;
	}

	public static int getDEFAULTDAMAGE() {
		return DEFAULTDAMAGE;
	}

	public void setDEFAULTDAMAGE(int dEFAULTDAMAGE) {
		DEFAULTDAMAGE = dEFAULTDAMAGE;
	}

	public static long getDEFAULTFIRERATE() {
		return DEFAULTFIRERATE;
	}

	public static void setDEFAULTFIRERATE(long dEFAULTFIRERATE) {
		DEFAULTFIRERATE = dEFAULTFIRERATE;
	}

	public int getDEFAULTCALIBRO() {
		return DEFAULTCALIBRO;
	}

	public void setDEFAULTCALIBRO(int dEFAULTCALIBRO) {
		DEFAULTCALIBRO = dEFAULTCALIBRO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getDEFAULTFIRERANGE() {
		return DEFAULTFIRERANGE;
	}

	public void setDEFAULTFIRERANGE(int dEFAULTGITTATA) {
		DEFAULTFIRERANGE = dEFAULTGITTATA;
	}

	
	
	
}
