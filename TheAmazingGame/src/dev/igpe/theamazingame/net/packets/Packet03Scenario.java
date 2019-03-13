package dev.igpe.theamazingame.net.packets;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import dev.igpe.theamazingame.mapgenerator.AssignedEnemy;
import dev.igpe.theamazingame.mapgenerator.AssignedTile;
import dev.igpe.theamazingame.scenario.Scenario;

public class Packet03Scenario extends Packet {
	
	private Scenario scenario;

	public Packet03Scenario(byte[] data) {
		super(03);
		
		ByteBuffer bb = ByteBuffer.wrap(data);
		try {
		  int width = bb.getInt();
		  int height = bb.getInt();
		  int mapLength = bb.getInt();
		  byte[] map = new byte[mapLength];
		  bb.get(map, 0, mapLength);
		  int assignedTileLength = bb.getInt();
		  List<AssignedTile> assignedTileList = new ArrayList<AssignedTile>();
		  for (int i = 0; i<assignedTileLength; i++) {
			  assignedTileList.add(new AssignedTile(bb.getInt(), bb.getInt(), bb.getInt()));
		  }
		  int assignedEnemyLength = bb.getInt();
		  List<AssignedEnemy> assignedEnemyList = new ArrayList<AssignedEnemy>();
		  for (int i = 0; i<assignedEnemyLength; i++) {
			  assignedEnemyList.add(new AssignedEnemy(bb.getInt(), bb.getInt()));
		  }
		  scenario = new Scenario(width, height, map, assignedTileList, assignedEnemyList);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Packet03Scenario(Scenario scenario) {
		super(03);
		this.scenario = scenario;
	}

	@Override
	public byte[] getData() {
		ByteBuffer bb = ByteBuffer.allocate(16+scenario.getMap().length+4+12*scenario.getAssignedTileList().size()+4+8*scenario.getAssignedEnemyList().size()); //int = 4 byte + char = 2 byte
		bb.putChar('0');
		bb.putChar('3');
		bb.putInt(scenario.getWidth());
		bb.putInt(scenario.getHeight());
		bb.putInt(scenario.getMap().length);
		bb.put(scenario.getMap());
		bb.putInt(scenario.getAssignedTileList().size());
		for (AssignedTile assignedTile : scenario.getAssignedTileList()) {
			bb.putInt(assignedTile.getX());
			bb.putInt(assignedTile.getY());
			bb.putInt(assignedTile.getTile());
		}
		bb.putInt(scenario.getAssignedEnemyList().size());
		for (AssignedEnemy assignedEnemy : scenario.getAssignedEnemyList()) {
			bb.putInt(assignedEnemy.getX());
			bb.putInt(assignedEnemy.getY());
		}
		return bb.array();
	}
	
	public Scenario getScenario() {
		return scenario;
	}

}
