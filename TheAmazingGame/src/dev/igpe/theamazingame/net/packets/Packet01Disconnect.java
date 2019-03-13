package dev.igpe.theamazingame.net.packets;

import java.nio.ByteBuffer;

public class Packet01Disconnect extends Packet {

    private String username;

    public Packet01Disconnect(byte[] data) {
        super(01);
        this.username = new String(data).trim();
    }

    public Packet01Disconnect(String username) {
        super(01);
        this.username = username;
    }

    @Override
    public byte[] getData() {
    	byte[] payload = this.username.getBytes();
    	ByteBuffer bb = ByteBuffer.allocate(payload.length+4);
    	bb.putChar('0');
    	bb.putChar('1');
    	bb.put(payload);
        return bb.array();
    }

    public String getUsername() {
        return username;
    }

}
