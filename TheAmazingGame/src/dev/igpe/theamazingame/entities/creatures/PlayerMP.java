package dev.igpe.theamazingame.entities.creatures;

import java.net.InetAddress;

import dev.igpe.theamazingame.Handler;

public class PlayerMP extends Player{

	public InetAddress ipAddress;
	public int port;
	
	
	
	public PlayerMP(Handler handler, float x, float y, String Username, InetAddress ipAddress, int port) {
		super(handler, x, y, Username);
		this.ipAddress=ipAddress;
		this.port= port;
	}
	
	public PlayerMP(float x, float y, String Username, InetAddress ipAddress, int port) {
		super(null, x, y, Username);
		this.ipAddress=ipAddress;
		this.port= port;
	}
	
	@Override
	public void tick() {
		super.tick();
	}

}
