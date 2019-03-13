package dev.igpe.theamazingame.tiles;

import java.awt.image.BufferedImage;
import java.util.Random;

import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.states.GameState;

public class WaterTile extends Tile{
	
	private static Random rnd = new Random();
	
	static Random random = new Random();
	
	private static int n=random.nextInt(3);
	public WaterTile(int id) {
		super(Assets.water[n], id);
		
	}
	
	

	
		
		
}
