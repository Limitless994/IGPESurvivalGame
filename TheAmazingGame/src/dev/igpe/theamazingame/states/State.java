package dev.igpe.theamazingame.states;

import java.awt.Graphics;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;

public abstract class State {

	private static State currentState =null;

	public static void setState(State state) {
		currentState =state;
	}

	public static State getState() {
		return currentState;
	}



	//CLASSE
	
	public static Handler handler;
	
	public State (Handler handler) {
		this.handler=handler;
		
	}
	
	public abstract void tick();

	public abstract void render(Graphics g);


}
