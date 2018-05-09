package dev.igpe.theamazingame.items.Ammo;

public class Rocket extends Ammo{
	




	//1 rocket
	
	public static final int DEFAULT_CALIBRO=1;

	public Rocket(int speed, int calibro, int id) {
		super(speed, calibro, id);
		// TODO Auto-generated constructor stub
	}

	
	
	public static int getDefaultCalibro() {
		return DEFAULT_CALIBRO;
	}

}
