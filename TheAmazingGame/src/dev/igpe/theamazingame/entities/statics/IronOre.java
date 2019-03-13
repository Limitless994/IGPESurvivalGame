package dev.igpe.theamazingame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.Item;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.tiles.Tile;

public class IronOre extends StaticEntity {

	public IronOre(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 16;
		bounds.height = (int) (height - height / 2f-14);
	}

	@Override
	   public void die() {
			handler.getWorld().getItemManager().addItem(ItemList.ironItem.createNew((int)x,(int) y));
			
		}

	@Override
	public void tick() {
	
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.ironOre, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), (int)(width*0.8),(int) (height*0.8), null);
//		g.setColor(Color.red);
//		g.fillRect((int)(x +bounds.x - handler.getGameCamera().getxOffset()),(int) (y +bounds.y - handler.getGameCamera().getyOffset()), (int)(width*0.8),(int) (height*0.8));

		
	}
	

}
