package dev.igpe.theamazingame.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.entities.creatures.PlayerMP;
import dev.igpe.theamazingame.net.packets.Packet;
import dev.igpe.theamazingame.net.packets.Packet.PacketTypes;
import dev.igpe.theamazingame.net.packets.Packet00Login;
import dev.igpe.theamazingame.net.packets.Packet01Disconnect;
import dev.igpe.theamazingame.net.packets.Packet02Move;
import dev.igpe.theamazingame.net.packets.Packet03Scenario;

public class GameServer extends Thread {

	private DatagramSocket socket;
	private Game game;
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();

	public GameServer(Game game) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
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
		char c1 = bb.getChar();
		char c2 = bb.getChar();
		char[] codeChar = new char[2];
		codeChar[0] = c1;
		codeChar[1] = c2;
		String code = new String(codeChar);
		byte[] payload = new byte[data.length-4];
		bb.get(payload, 0, data.length-4);
		//      String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(code);
		Packet packet = null;
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(payload);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] "
					+ ((Packet00Login) packet).getUsername() + " has connected...");
			handleLogin((Packet00Login) packet, address, port);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(payload);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] "
					+ ((Packet01Disconnect) packet).getUsername() + " has left...");
			this.removeConnection((Packet01Disconnect) packet);
			break;
		case MOVE:
			packet = new Packet02Move(payload);
			this.handleMove(((Packet02Move) packet));
		}
	}

	public void addConnection(PlayerMP player, Packet00Login packet) {
		boolean alreadyConnected = false;
		for (PlayerMP p : this.connectedPlayers) {
			if (player.getUsername().equalsIgnoreCase(p.getUsername())) {
				if (p.ipAddress == null) {
					p.ipAddress = player.ipAddress;
				}
				if (p.port == -1) {
					p.port = player.port;
				}
				alreadyConnected = true;
			} else {
				// relay to the current connected player that there is a new
				// player
				sendData(packet.getData(), p.ipAddress, p.port);

				// relay to the new player that the currently connect player
				// exists
				Packet packetUser = new Packet00Login(p.getUsername(), p.getX(), p.getY());
				sendData(packetUser.getData(), player.ipAddress, player.port);
			}
		}
		if (!alreadyConnected) {
			this.connectedPlayers.add(player);
		}
	}

	public void removeConnection(Packet01Disconnect packet) {
		this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername()));
		packet.writeServerData(this);
	}

	public PlayerMP getPlayerMP(String username) {
		for (PlayerMP player : this.connectedPlayers) {
			if (player.getUsername().equals(username)) {
				return player;
			}
		}
		return null;
	}

	public int getPlayerMPIndex(String username) {
		int index = 0;
		for (PlayerMP player : this.connectedPlayers) {
			if (player.getUsername().equals(username)) {
				break;
			}
			index++;
		}
		return index;
	}

	public void sendData(byte[] data, InetAddress ipAddress, int port) {

		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendDataToClientsExceptPlayer(byte[] data, Player pl) {
		for (PlayerMP p : connectedPlayers) {
			if (!p.getUsername().equals(pl.getUsername()))
				sendData(data, p.ipAddress, p.port);
		}
	}

	public void sendDataToAllClients(byte[] data) {
		for (PlayerMP p : connectedPlayers) {
			sendData(data, p.ipAddress, p.port);
		}
	}


	private void handleMove(Packet02Move packet) {
		if (getPlayerMP(packet.getUsername()) != null) {
			int index = getPlayerMPIndex(packet.getUsername());
			PlayerMP player = this.connectedPlayers.get(index);
			player.setX(packet.getX()) ;
			player.setY(packet.getY()) ;
			//         player.setMoving(packet.isMoving());
			//            player.setMovingDir(packet.getMovingDir());
			//          player.setNumSteps(packet.getNumSteps());
			packet.writeServerData(this);
		}
	}

	public void handleLogin(Packet00Login packet, InetAddress address, int port) {
		PlayerMP player = new PlayerMP(game.getHandler(), 100, 100, ((Packet00Login) packet).getUsername(), address, port);
		this.addConnection(player, (Packet00Login) packet);
		//Risposta con Scenario
		Packet03Scenario packetScenario = new Packet03Scenario(Game.getSenarioManager().getScenario());
		sendData(packetScenario.getData(), player.ipAddress, player.port);
//		packet.writeServerDataExceptPlayer(this, player);
	}

	public List<PlayerMP> getConnectedPlayers() {
		return connectedPlayers;
	}

	public void setConnectedPlayers(List<PlayerMP> connectedPlayers) {
		this.connectedPlayers = connectedPlayers;
	}
}
