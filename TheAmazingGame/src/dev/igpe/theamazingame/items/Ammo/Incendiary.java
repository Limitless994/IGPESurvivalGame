package dev.igpe.theamazingame.items.Ammo;

public class Incendiary extends Ammo{






	public static final int DEFAULT_CALIBRO=2;
	
	public Incendiary(int speed, int calibro, int id) {
		super(speed, calibro, id);
		// TODO Auto-generated constructor stub
	}

	
	public static int getDefaultCalibro() {
		return DEFAULT_CALIBRO;
	}


}
