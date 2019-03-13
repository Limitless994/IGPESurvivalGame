package dev.igpe.theamazingame.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.entities.creatures.PlayerMP;
import dev.igpe.theamazingame.net.packets.Packet;
import dev.igpe.theamazingame.net.packets.Packet.PacketTypes;
import dev.igpe.theamazingame.net.packets.Packet00Login;
import dev.igpe.theamazingame.net.packets.Packet01Disconnect;
import dev.igpe.theamazingame.net.packets.Packet02Move;
import dev.igpe.theamazingame.net.packets.Packet03Scenario;
import dev.igpe.theamazingame.worlds.World;

public class GameClient extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;

	public GameClient(Game game, String ipAddress) {
		this.game = game;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace(); 
		}
	}

	public void run() {
		while (true) {
			byte[] data = new byte[65535];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}

	private void parsePacket(byte[] data, InetAddress address, int port) {
		ByteBuffer bb = ByteBuffer.wrap(data);
		char[] charCode = new char[2];
		charCode[0] = bb.getChar();
		charCode[1] = bb.getChar();
		String code = new String(charCode);
		PacketTypes type = Packet.lookupPacket(code);
		//		String message = new String(data).trim();
		//		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		byte[] payload = new byte[data.length-4];
		bb.get(payload, 0, data.length-4);
		Packet packet = null;
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(payload);
			handleLogin((Packet00Login) packet, address, port);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(payload);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] "
					+ ((Packet01Disconnect) packet).getUsername() + " has left the world...");
			//  game.level.removePlayerMP(((Packet01Disconnect) packet).getUsername());
			break;
		case MOVE:
			packet = new Packet02Move(payload);
			handleMove((Packet02Move) packet);
			break;
		case SCENARIO:
			packet = new Packet03Scenario(payload);
			handleScenario((Packet03Scenario) packet);
			break;
		}
	}

	public void sendData(byte[] data) {
		if (!game.isApplet) {
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleLogin(Packet00Login packet, InetAddress address, int port) {
		System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUsername()
		+ " has joined the game...");
		PlayerMP player = new PlayerMP(game.getHandler(), packet.getX(), packet.getY(), packet.getUsername(), address, port);
		game.getHandler().getWorld().getEntityManager().addEntity(player);
	}

	private void handleMove(Packet02Move packet) {
		game.getHandler().getWorld().getEntityManager().movePlayer(packet.getUsername(), packet.getX(), packet.getY(),packet.isMoving());
	}

	private void handleScenario(Packet03Scenario packet) {
		Game.getSenarioManager().setScenario(packet.getScenario());
		Game.getSenarioManager().setIsLoaded(Boolean.TRUE);	
	}
}
