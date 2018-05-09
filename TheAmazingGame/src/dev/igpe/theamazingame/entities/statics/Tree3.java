package dev.igpe.theamazingame.entities.statics;

import java.awt.Graphics;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.tiles.Tile;

public class Tree3 extends StaticEntity {

	public Tree3(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);
		bounds.x= 90;
		bounds.y = (int) (height*2.5);
		bounds.width=width-50;
		bounds.height=(int) (height/40f);
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
		g.drawImage(Assets.tree3, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width*3, height*3
				, null);
		
	}
	

}
