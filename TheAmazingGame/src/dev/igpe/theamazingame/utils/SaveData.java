package dev.igpe.theamazingame.utils;

import dev.igpe.theamazingame.states.State;
import dev.igpe.theamazingame.worlds.World;

public class SaveData implements java.io.Serializable {

	private static final long serialVersionUID=1L;
	
	private World currentState;

	public World getSave() {
		return getSave() ;
	}

	public void setSave(World world) {
		this.currentState = world;
	}

}
