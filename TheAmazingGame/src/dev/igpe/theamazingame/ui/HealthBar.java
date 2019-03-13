package dev.igpe.theamazingame.ui;
import java.awt.Graphics;

import dev.igpe.theamazingame.launcher;

import java.awt.Color;
import java.awt.Font;

public class HealthBar {


	int drawHealth, s;
	float width,height;
	String name;

	public HealthBar(int health,float width,float height,int s,String name) {
		drawHealth = health;
		this.width=width;
		this.height=height;
		this.s=s;
		this.name=name;
		
	}

	public void paint(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 23)); 
		g.setColor(Color.RED);
		g.drawString(name,(int)(width)+25,(int)height-30);
		g.setColor(Color.white);
		g.fillRect((int)(width)-2,(int)height-20-2, drawHealth+4, s+4); //draws healthbar outline
		g.setColor(Color.red);
		g.fillRect((int)(width),(int) height-20, drawHealth, s); //draws health
	}

	public void tick(int i) {
		drawHealth=i;
	}


	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
