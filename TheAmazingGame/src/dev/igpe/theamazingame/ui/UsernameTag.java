package dev.igpe.theamazingame.ui;
import java.awt.Graphics;

import dev.igpe.theamazingame.launcher;

import java.awt.Color;
import java.awt.Font;

public class UsernameTag {

	String name;
	float width, height;

	public UsernameTag(float width,float height,String name) {
	
		this.width=width;
		this.height=height;
		this.name=name;
		
	}
	public void tick(float x, float y) {
		width=x;
		height=y;
	}
	public void paint(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 17));
		g.setColor(Color.BLUE);
		g.drawString(name,(int)(width),(int)height);
	}

	public void setHeight(int i) {
		this.height = height;		
	}

	public void setWidth(int i) {
		this.width = width;		
	}

}
