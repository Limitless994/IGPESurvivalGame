package dev.igpe.theamazingame.tiles;

import dev.igpe.theamazingame.gfx.Assets;

public class RockTile extends Tile{

	public RockTile(int id) {
		super(Assets.stone, id);
	
	}
	@Override
	public boolean isSolid() {
		return false;//CONTROLLARE INTERSECT
	}
	
}
