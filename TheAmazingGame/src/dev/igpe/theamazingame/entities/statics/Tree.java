package dev.igpe.theamazingame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.tiles.Tile;

public class Tree extends StaticEntity {


	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, 88,88);
		
		
		bounds.x = 38;
		bounds.y = 64;
		bounds.width = 12;
		bounds.height = 12;
	}

	@Override
    public void die() {
		handler.getWorld().getItemManager().addItem(ItemList.woodItem.createNew((int)x,(int) y));
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//g.setColor(Color.red);
//g.fillRect((int)(x +bounds.x - handler.getGameCamera().getxOffset()),(int) (y +bounds.y - handler.getGameCamera().getyOffset()), 15, 15);

	}

}

////public class Tree extends StaticEntity {
////
////	public Tree(Handler handler, float x, float y) {
////		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);
////		bounds.x= 32;
////		bounds.y = (int) (height*2.3); //margine inferiore
////		bounds.width=width-50;
////		bounds.height=(int) (height/30f);
////	}
