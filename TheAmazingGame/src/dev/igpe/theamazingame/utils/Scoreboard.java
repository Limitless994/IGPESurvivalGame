package dev.igpe.theamazingame.utils;
import java.util.*;
import java.io.*;

public class Scoreboard {

	private final int TOP_COUNT = 5;

	private final String[] names;
	private final int[] scores;

	private String filename;


	public Scoreboard() { 

		this("topscores.txt");

	}

	public Scoreboard(String filename) {
		this.filename = filename;
		this.names = new String[TOP_COUNT];
		this.scores = new int[TOP_COUNT];
		for(int i = 0; i < TOP_COUNT; i++) { names[i] = ""; }
		readScores();
	}

	private void readScores() { 

	}

	private void writeScores() { 

	}

	private void shiftValuesDown(int index) { /* your code here */ }
	private int findPosition(int score) {
		return score; /* your code here */ }


	public void playerScore(String name, int newScore) { 
		int pos = findPosition(newScore);
		if (pos<TOP_COUNT) {
			shiftValuesDown(pos);
			names[pos] = name;
			scores[pos] = newScore;
		}

		writeScores();
	}


	public String[] getTopNames(){ return names; } 

	public int[] getTopScores() { return scores; } 


}


