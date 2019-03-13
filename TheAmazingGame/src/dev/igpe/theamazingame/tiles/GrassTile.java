package dev.igpe.theamazingame.tiles;

import java.util.Random;

import dev.igpe.theamazingame.gfx.Assets;

public class GrassTile extends Tile {

	private static Random rnd = new Random();	
	public GrassTile(int id) {
		super(Assets.grass, id);
		
	}

}
