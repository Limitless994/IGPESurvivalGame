package dev.igpe.theamazingame.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.launcher;
import dev.igpe.theamazingame.audio.AudioPlayer;
import dev.igpe.theamazingame.entities.EntityManager;
import dev.igpe.theamazingame.entities.creatures.Creature;
import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.inventory.Inventory;
import dev.igpe.theamazingame.items.ItemList;
import dev.igpe.theamazingame.mapgenerator.TileMapViewer;
import dev.igpe.theamazingame.ui.HealthBar;
import dev.igpe.theamazingame.ui.QuickMenuBar;
import dev.igpe.theamazingame.worlds.World;

public class GameState extends State{
	private HealthBar playerHealth = new HealthBar(Player.DEFAULT_HEALTH,175,70,20,"Life");
	private QuickMenuBar quickMenu=new QuickMenuBar();
	public static int score=0;
	private int ammo=0;
	private World world;

	
	public static int numberOfEnemies=10,numberOfStatics=1400;

	public GameState(Handler handler, World world) {
		super(handler);
		this.world = world;
//		handler.setWorld(world);
		world.loadWorld();
		
		
	}

	@Override
	public void tick() {

		world.tick();
		if(handler.getKeyManager().esc) {
			//evita che il tasto del menu venga premuto dopo essere stato premuto 1 volta

			State.setState(handler.getGame().exitState);	
			handler.getMouseManager().setUIManager(ExitState.uiManager3);
		}

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)) {


			State.setState(handler.getGame().exitState);	
			handler.getMouseManager().setUIManager(ExitState.uiManager3);
			TileMapViewer.ViewMap("GROUND", 4, 4, Game.getSenarioManager().getScenario());
		}

		if(handler.getGame().getPlayer().getHealth()<=0) {
			resetWorld();
		}
		playerHealth.tick(handler.getGame().getPlayer().getHealth());
		quickMenu.tick();
		ammo=(handler.getGame().getPlayer().getInventory().getAmmoCount());

	}


	public void resetWorld() {
		handler.getGame().setSocketServer(null);

		Game.getPlayer().setX(100);
		Game.getPlayer().setY(100);

		State.setState(handler.getGame().menuState);
		handler.getGame().getPlayer().getInventory().activeWeapon=null;
		handler.getGame().getPlayer().setHealth(Creature.DEFAULT_HEALTH);
		handler.getMouseManager().setUIManager(MenuState.uiManager); //setta di nuovo l'ui a quello del menu
		
		score=0;

	}

	@Override
	public void render(Graphics g) {

		world.render(g);
		g.drawImage(Assets.quickBarBg,0,0, 300, 120,null);
		playerHealth.paint(g);
		quickMenu.paint(g);
		g.setColor(Color.black);

		g.drawString("SCORE: "+score,10,90);
		if(Inventory.activeWeapon==ItemList.pickAxeItem) {
			g.drawString("AMMO: INFINITE",10,107);
		}else {
			g.drawString("AMMO: "+ammo,10,107);
		}
	}

}
