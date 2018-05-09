package dev.igpe.theamazingame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.tiles.Tile;

public class Tree2 extends StaticEntity {

	public Tree2(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 84;
		bounds.y = 32;
		bounds.width = 12;
		bounds.height = 78;
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
		g.drawImage(Assets.tree2, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width*3, height*3
				, null);
		g.setColor(Color.red);
		g.fillRect((int)(x +bounds.x - handler.getGameCamera().getxOffset()),(int) (y +bounds.y - handler.getGameCamera().getyOffset()), 15, 15);

		
	}
	

}
