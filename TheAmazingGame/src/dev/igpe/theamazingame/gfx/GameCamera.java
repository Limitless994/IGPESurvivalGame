package dev.igpe.theamazingame.gfx;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.entities.Entity;
import dev.igpe.theamazingame.tiles.Tile;

public class GameCamera {

	private Handler handler; //serve per focussare la camera sul player
	private float yOffset, xOffset;
	
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler=handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
	}
	
	public void checkBlackSpace() {
		if(xOffset< 0) {
			xOffset=0;
		}else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
	if(yOffset< 0) {
		yOffset=0;
	}else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
		yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
	}
}

	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth()/2 + e.getWidth()/2;
		yOffset = e.getY() - handler.getHeight()/2 + e.getHeight() /2;
		checkBlackSpace();
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlackSpace();
	}
	
	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}
	
}
