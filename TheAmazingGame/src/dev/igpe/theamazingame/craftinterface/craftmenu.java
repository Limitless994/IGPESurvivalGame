package dev.igpe.theamazingame.craftinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.gfx.Text;
import dev.igpe.theamazingame.items.Item;

public class craftmenu {

	private Handler handler;
	private boolean active = false;

	private int invX = 64, invY = 48,
			invWidth = 512, invHeight = 384,
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;

	private int invImageX = 452, invImageY = 82,
			invImageWidth = 64, invImageHeight = 64;

	private int invCountX = 484, invCountY = 172;

	private int selectedItem = 0;
	
	public craftmenu(Handler handler){
		this.handler = handler;
		ArrayList<Item> craftmenu = new ArrayList<Item>();
	}
	
	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))
			active = !active;
		if(!active)
			return;
	}
	
	public void render(Graphics g){
		if(!active)
			return;

		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

		
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	
}
