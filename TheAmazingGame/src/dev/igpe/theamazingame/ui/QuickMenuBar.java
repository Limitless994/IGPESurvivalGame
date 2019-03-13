package dev.igpe.theamazingame.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dev.igpe.theamazingame.launcher;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.inventory.Inventory;
import dev.igpe.theamazingame.items.Item;

public class QuickMenuBar {
	private Item currentItem=null;


	public void paint(Graphics g) {

		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		String s="SELECTED";
		g.setColor(Color.BLACK);
		g.drawString(s,25,25);
		g.drawImage(Assets.selectedItem,45,30,null );
		if(currentItem!=null)
			g.drawImage(Inventory.activeWeapon.getTexture(),50,35,null); 
	}

	public void tick() {
		currentItem=Inventory.activeWeapon;
	}

}