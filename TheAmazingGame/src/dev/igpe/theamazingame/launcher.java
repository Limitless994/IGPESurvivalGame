package dev.igpe.theamazingame;

import dev.igpe.theamazingame.utils.VideoPlayer;

public class launcher {
	
	public final static int width=1920;
	public final static int height=1080;
	private static Game game= new Game("TheAmazingGame",width,height);
	private static VideoPlayer p=new VideoPlayer();
	
	public static void main(String[] args) {
		p.play();
		game.start();
		
	}
	
}
