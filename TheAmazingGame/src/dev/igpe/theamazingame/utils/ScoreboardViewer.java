package dev.igpe.theamazingame.utils;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.gfx.Assets;
@SuppressWarnings("serial")
public class ScoreboardViewer extends JPanel{
	private	String[] names =Game.getScore().getTopNames();
	private int[] scores = Game.getScore().getTopScores();
	public void paint(Graphics g){
		g.drawImage(Assets.topscoresbg, 0, 0, null);
		g.setColor(Color.yellow);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 80));
		for(int i = 0; i<5; i++) {
			g.drawString((i+1)+names[i]+" : "+ scores[i], 300, 150+i*100);
		}

	}

	public static void show(Scoreboard gamers) {
		JFrame frame= new JFrame("SURVIVAL_INSTICT ~ TOP PLAYER");	

		frame.getContentPane().add(new ScoreboardViewer());
		frame.setSize(800, 800);
		frame.setBackground(Color.yellow);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);		
	}
}