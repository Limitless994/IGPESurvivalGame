package dev.igpe.theamazingame.ui;
import java.awt.Graphics;

import dev.igpe.theamazingame.launcher;

import java.awt.Color;
import java.awt.Font;

public class HealthBar {



	int drawHealth;

	public HealthBar(int i) {
		drawHealth = i;
	}

	public void paint(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 23)); 
		String s="LIFE";
		
		g.setColor(Color.RED);
		g.drawString(s,(launcher.width/2)+5,launcher.height-60);
		g.setColor(Color.white);
		g.fillRect((launcher.width/2)+3,launcher.height-60+3, drawHealth+4, 24); //draws healthbar outline
		g.setColor(Color.red);
		g.fillRect((launcher.width/2)+5, launcher.height-60+5, drawHealth, 20); //draws health
	}

	public void tick(int i) {
		drawHealth=i;
	}

}
