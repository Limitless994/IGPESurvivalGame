package dev.igpe.theamazingame.net.packets;

import java.nio.ByteBuffer;

import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.net.GameClient;
import dev.igpe.theamazingame.net.GameServer;

public class Packet00Login extends Packet {

    private String username;
    private float x, y;
    

    public Packet00Login(byte[] data) {
        super(00);
        String[] dataArray = new String(data).trim().split(",");
        this.username = dataArray[0];
        this.x = Float.parseFloat(dataArray[1]);
        this.y = Float.parseFloat(dataArray[2]);
    }

    public Packet00Login(String username, float x, float y) {
        super(00);
        this.username = username;
        this.x = x;
        this.y = y;
        
    }

    @Override
    public byte[] getData() {
    	byte[] payload = (this.username + "," + getX() + "," + getY()).getBytes();
    	ByteBuffer bb = ByteBuffer.allocate(payload.length+4);
    	bb.putChar('0');
    	bb.putChar('0');
    	bb.put(payload);
        return bb.array();
    }

    public String getUsername() {
        return username;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
