package dev.igpe.theamazingame.scenario;

import java.util.List;

import dev.igpe.theamazingame.mapgenerator.AssignedEnemy;
import dev.igpe.theamazingame.mapgenerator.AssignedTile;

public class Scenario {

	private int width;
	private int height;
	private byte[] map;
	private List<AssignedTile> assignedTileList;
	private List<AssignedEnemy> assignedEnemyList;
	
	public Scenario(int width, int height, byte[] map, List<AssignedTile> assignedTileList, List<AssignedEnemy> assignedEnemyList) {
		super();
		this.width = width;
		this.height = height;
		this.map = map;
		this.assignedTileList = assignedTileList;
		this.assignedEnemyList = assignedEnemyList;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public byte[] getMap() {
		return map;
	}

	public void setMap(byte[] map) {
		this.map = map;
	}
	
	public List<AssignedTile> getAssignedTileList() {
		return assignedTileList;
	}
	
	public void setAssignedTileList(List<AssignedTile> assignedTileList) {
		this.assignedTileList = assignedTileList;
	}
	
	public List<AssignedEnemy> getAssignedEnemyList() {
		return assignedEnemyList;
	}
	
	public void setAssignedEnemyList(List<AssignedEnemy> assignedEnemyList) {
		this.assignedEnemyList = assignedEnemyList;
	}
	
}
