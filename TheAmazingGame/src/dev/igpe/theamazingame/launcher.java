package dev.igpe.theamazingame;

import java.awt.Dimension;
import java.awt.Toolkit;

import dev.igpe.theamazingame.states.GameState;
import dev.igpe.theamazingame.worlds.World;

public class launcher {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final static int width=(int) screenSize.getWidth();;
	public final static int height=(int) screenSize.getHeight();;
	
	
	public static void main(String[] args) {
		
		
		Game game= new Game("TheAmazingGame",width,height);
		System.out.println("LA tua risoluzione e' " +width + " x " + height);
		game.start();
	}
	
}
