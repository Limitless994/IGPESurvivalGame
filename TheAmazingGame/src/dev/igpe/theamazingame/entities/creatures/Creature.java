package dev.igpe.theamazingame.entities.creatures;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.entities.Entity;
import dev.igpe.theamazingame.tiles.Tile;

public abstract class Creature extends Entity {


	public static final float DEFAULT_SPEED=3.0f;
	public static final int DEFAULT_CREATURE_WIDTH=44,  DEFAULT_CREATURE_HEIGHT=44, DEFAULT_CREATURE_DAMAGE=1;

	protected float startSpeed;
	protected float speed;
	protected float xMove,yMove;
	protected boolean isMoving;
	
	public Creature(Handler handler, float x, float y,int width, int height,int damage) {
		super(handler, x, y, width, height);
		this.startSpeed=DEFAULT_SPEED;
		this.speed=DEFAULT_SPEED;
		xMove=0;
		yMove=0;
		damage=DEFAULT_CREATURE_DAMAGE;
	}

	public Creature(Handler handler, float x, float y,int width, int height,int damage, float startSpeed) {
		super(handler, x, y, width, height);
		this.startSpeed=startSpeed;
		this.speed=startSpeed;
		xMove=0;
		yMove=0;
		damage=DEFAULT_CREATURE_DAMAGE;
	}

	public void move() {
		if(!checkEntityCollisions(xMove,0f))
			moveX();
		if(!checkEntityCollisions(0f,yMove))
			moveY();
	}

	public boolean inWater() {
		if((handler.getWorld().getTile((int)this.x/Tile.TILEWIDTH,(int) this.y/Tile.TILEHEIGHT)).equals(Tile.waterTile)) {
			this.speed=startSpeed-0.5f;
			return true;

		}
		this.speed=startSpeed;
		return false;
	}

	public void moveX() {
		if(xMove>0) { //destra
			int tx=(int)(x+xMove+bounds.x + bounds.width) / Tile.TILEWIDTH;

			if(!collisionWithTile(tx, (int) (y+bounds.y)/ Tile.TILEHEIGHT)&& !collisionWithTile(tx, (int) (y+bounds.y +bounds.height)/Tile.TILEHEIGHT)) {
				x+=xMove;
			}else{
				x = tx * Tile.TILEWIDTH - bounds.x-bounds.width - 1;
			}
		}else if(xMove<0) {//sinistra
			int tx=(int)(x+xMove+bounds.x ) / Tile.TILEWIDTH;

			if(!collisionWithTile(tx, (int) (y+bounds.y)/ Tile.TILEHEIGHT)&& !collisionWithTile(tx, (int) (y+bounds.y +bounds.height)/Tile.TILEHEIGHT)) {
				x+=xMove;
			}else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH -bounds.x;

			}
		}
	}

	public void moveY() {
		if(yMove<0) {//SU
			int ty=(int)(y+yMove + bounds.y)/Tile.TILEHEIGHT;
			if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEWIDTH,ty)&&!collisionWithTile((int)(x+bounds.x + bounds.width)/Tile.TILEWIDTH,ty) ) {
				y+=yMove;
			}else {
				y= ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;

			}
		}if(yMove>0) { //GIU
			int ty=(int)(y+yMove + bounds.y + bounds.height)/Tile.TILEHEIGHT;
			if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEWIDTH,ty)&&!collisionWithTile((int)(x+bounds.x + bounds.width)/Tile.TILEWIDTH,ty) ) {
				y+=yMove;
			}else {

				y= ty * Tile.TILEHEIGHT- bounds.y-bounds.height -1;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	//SET AND GET METHODs

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isEnemy() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}


}
