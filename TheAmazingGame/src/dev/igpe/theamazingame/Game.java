package dev.igpe.theamazingame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import dev.igpe.theamazingame.display.Display;
import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.entities.creatures.PlayerMP;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.gfx.GameCamera;
import dev.igpe.theamazingame.input.KeyManager;
import dev.igpe.theamazingame.input.MouseManager;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.mapgenerator.AssignedEnemy;
import dev.igpe.theamazingame.mapgenerator.AssignedTile;
import dev.igpe.theamazingame.mapgenerator.LayerGenerator;
import dev.igpe.theamazingame.mapgenerator.LayerMap;
import dev.igpe.theamazingame.mapgenerator.LayerSetting;
import dev.igpe.theamazingame.mapgenerator.LayerType;
import dev.igpe.theamazingame.mapgenerator.ScenarioGenerator;
import dev.igpe.theamazingame.net.GameClient;
import dev.igpe.theamazingame.net.GameServer;
import dev.igpe.theamazingame.net.packets.Packet00Login;
import dev.igpe.theamazingame.scenario.Scenario;
import dev.igpe.theamazingame.scenario.ScenarioManager;
import dev.igpe.theamazingame.states.CreditsState;
import dev.igpe.theamazingame.states.ExitState;
import dev.igpe.theamazingame.states.GameState;
import dev.igpe.theamazingame.states.MenuState;
import dev.igpe.theamazingame.states.State;
import dev.igpe.theamazingame.utils.Scoreboard;
import dev.igpe.theamazingame.worlds.World;

public class Game implements Runnable {


	private static ScenarioManager scenarioManager = new ScenarioManager();
	private Display display;
	private int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private boolean isServer=false;
	
	private static Scoreboard score;
	
	private BufferStrategy bs;
	private Graphics g;
	public boolean isApplet = false;

	//Stati
	public static State gameState;
	public static State menuState;
	public static State creditsState;
	public static State exitState;
	//input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	//Camera
	private GameCamera gameCamera;

	//Handler
	private Handler handler;

	//Multiplayer
	private GameClient socketClient;
	private GameServer socketServer;
	private static Player player;

	public Game(String title, int width, int height) {
		this.width=width;
		this.height=height;
		this.title=title;
		keyManager=new KeyManager();
		mouseManager=new MouseManager();
	}

	private void init() {
		display=new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();



		if (socketServer != null) {
			LayerSetting groundLayerSetting = new LayerSetting(128, 128, 16, 0, new Random(System.currentTimeMillis()));
			LayerGenerator groundGenerator = new LayerGenerator(groundLayerSetting);
			LayerMap layerMap = groundGenerator.doCreate(LayerType.GROUND);
			ScenarioGenerator scenarioGenerator = new ScenarioGenerator(layerMap);
			List<AssignedTile> assignedTileList = scenarioGenerator.doAssignTiles();
			List<AssignedEnemy> assignedEnemyList = scenarioGenerator.doAssignEnemies();
			scenarioManager.setScenario(new Scenario(layerMap.getWidth(), layerMap.getHeight(), layerMap.getMapData(), assignedTileList, assignedEnemyList));
			scenarioManager.setIsLoaded(Boolean.TRUE);
			score = new Scoreboard("score.txt");

		}

		handler = new Handler(this);
		World world = new World(handler);
		handler.setWorld(world);
		player = new PlayerMP(handler, 100, 100, JOptionPane.showInputDialog(this, "Please enter a username"),null, -1);
		Packet00Login loginPacket = new Packet00Login(player.getUsername(), player.getX(), player.getY());
		loginPacket.writeClientData(socketClient);
		while (!scenarioManager.getIsLoaded()) {
			try {
				Thread.sleep(1000); //Attesa 1 secondo
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
		gameCamera = new GameCamera(handler,0,0);
		creditsState = new CreditsState(handler);
		gameState=new GameState(handler, world); //State dichiarato come uno State normale ma specializzato su Gamestate
		exitState= new ExitState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
		this.getHandler().getWorld().getEntityManager().addEntity(player);

		//		if (!isApplet) {
		//			Packet00Login loginPacket = new Packet00Login(player.getUsername(), player.getX(), player.getY());
		//			if (socketServer != null) {
		//				socketServer.addConnection((PlayerMP) player, loginPacket);
		//				System.out.println("ADD MULTI");
		//			}
		//			loginPacket.writeData(socketClient);
		//		}

handler.getGame().getPlayer().getInventory().addItem(ItemList.woodItem);
	}


	private void tick() {
		keyManager.tick();
//		if(State.getState().equals(gameState))MenuState.bgMusic.stop();
		if(State.getState()!=null)
			State.getState().tick();

	}

	public static State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	private void render() {
		bs=display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g =bs.getDrawGraphics();
		//clear
		g.clearRect(0, 0, width, height);
		//draw
		if(State.getState()!=null)
			State.getState().render(g);

		//update buffer

		bs.show();
		g.dispose();
		player.postRender(g);
	}

	public void run() {

		init();

		int fps=60;
		double timePerTick =1000000000/fps;
		double delta =0;
		long now;
		long lastTime=System.nanoTime();
		long timer=0;
		int ticks=0;

		while(running) {
			now=System.nanoTime();
			delta+=(now-lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime=now;

			if(delta>= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				//	System.out.println("FPS " + ticks);
				ticks=0;
				timer =0;
			}
		}

		stop();

	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	public int getWidth() {
		return width;	
	}
	public int getHeight() {
		return height;	
	}


	public GameClient getSocketClient() {
		return socketClient;
	}

	public void setSocketClient(GameClient socketClient) {
		this.socketClient = socketClient;
	}

	public GameServer getSocketServer() {
		return socketServer;
	}

	public void setSocketServer(GameServer socketServer) {
		this.socketServer = socketServer;
	}

	public Handler getHandler() {
		return handler;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public synchronized void start() {
		if(running)
			return;
		running=true;

		//Server
		if (!isApplet) {
			if(JOptionPane.showConfirmDialog(null,this, "Do you want to run the server", 1) == 0) {
				isServer=true;
				socketServer=new GameServer(this);
				System.out.println("SOCKET START");
				socketServer.start();
			}
		}
		socketClient = new GameClient(this, "localhost");
		socketClient.start();

		thread=new Thread(this);
		thread.start();

	}

	public synchronized void stop() {
		if(!running)
			return;
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	public boolean isServer() {
		return isServer;
	}

	public void setServer(boolean isServer) {
		this.isServer = isServer;
	}

	public static ScenarioManager getSenarioManager() {
		return scenarioManager;
	}
	
	public static Player getPlayer() {
		return player;
	}

	public static Scoreboard getScore() {
		return score;
	}

	public void setScore(Scoreboard score) {
		this.score = score;
	}

}

