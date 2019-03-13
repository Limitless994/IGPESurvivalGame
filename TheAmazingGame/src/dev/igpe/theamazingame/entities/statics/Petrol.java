package dev.igpe.theamazingame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.tiles.Tile;

public class Petrol extends StaticEntity{

	public Petrol(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		

	}

	@Override
	   public void die() {
			handler.getWorld().getItemManager().addItem(ItemList.solidfuelItem.createNew((int)x,(int) y));
			
		}

	@Override
	public void tick() {
	
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.petrol, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), (int)(width*0.8),(int) (height*0.8), null);
//		g.setColor(Color.red);
//		g.fillRect((int)(x +bounds.x - handler.getGameCamera().getxOffset()),(int) (y +bounds.y - handler.getGameCamera().getyOffset()), (int)(width*0.8),(int) (height*0.8));

		
	}
}
