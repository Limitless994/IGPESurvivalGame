package dev.igpe.theamazingame.net.packets;

import java.nio.ByteBuffer;

import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.net.GameClient;
import dev.igpe.theamazingame.net.GameServer;

public class Packet02Move extends Packet {

	private String username;
	private float x, y;

	//private int numSteps = 0;
	private boolean isMoving;
	//private int movingDir = 1;

	public Packet02Move(byte[] data) {
		super(02);
		String [] dataArray = new String(data).trim().split(",");
		
		
//		String[] dataArray = readData(data).split(",");
		this.username = dataArray[0];
		this.x = Float.parseFloat(dataArray[1]);
		this.y = Float.parseFloat(dataArray[2]);
		//this.numSteps = Integer.parseInt(dataArray[3]);
		this.isMoving = Integer.parseInt(dataArray[3]) == 1;
	//	this.movingDir = Integer.parseInt(dataArray[5]);
	}

	public Packet02Move(String username, float x, float y, boolean isMoving) {
		super(02);
		this.username = username;
		this.x = x;
		this.y = y;
	//	this.numSteps = numSteps;
		this.isMoving = isMoving;
	//	this.movingDir = movingDir;
	}

	@Override
	public byte[] getData() {
		byte[] payload = (this.username + "," + this.x + "," + this.y + "," + (isMoving ? 1 : 0)).getBytes();
		ByteBuffer bb = ByteBuffer.allocate(payload.length+4);
		bb.putChar('0');
		bb.putChar('2');
		bb.put(payload);
		return bb.array();

	}

	public String getUsername() {
		return username;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public boolean isMoving() {
		return isMoving;
	}

}
