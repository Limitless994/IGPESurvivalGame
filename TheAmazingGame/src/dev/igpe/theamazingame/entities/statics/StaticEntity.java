package dev.igpe.theamazingame.entities.statics;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
	super(handler, x, y, width, width);
	}

}
