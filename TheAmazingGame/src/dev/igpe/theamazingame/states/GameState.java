package dev.igpe.theamazingame.states;

import java.awt.Graphics;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.launcher;
import dev.igpe.theamazingame.entities.creatures.Creature;
import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.ui.HealthBar;
import dev.igpe.theamazingame.worlds.World;

public class GameState extends State{
	private HealthBar playerHealth = new HealthBar(Player.DEFAULT_HEALTH);

	private World world;

	public static int numberOfEnemyes=20,numberOfStatics=300;

	public GameState(Handler handler) {
		super(handler);
		if(launcher.width==1920) {
			world=new World(GameState.handler, "res/worlds/world1.txt");
			//			System.out.println("CARICO LA MAPPA1");
			numberOfEnemyes*=2;
			numberOfStatics*=8;
		}
		else { 	
			world=new World(GameState.handler, "res/worlds/world2.txt");
			numberOfStatics*=4;
		}
		handler.setWorld(world);

	}

	@Override
	public void tick() {
		world.tick();
		if(World.entityManager.getPlayer().getHealth()<=0) {
			State.setState(handler.getGame().menuState);
			World.entityManager.getPlayer().setHealth(Creature.DEFAULT_HEALTH);
			handler.getMouseManager().setUIManager(MenuState.uiManager); //setta di nuovo l'ui a quello del menu

			world=new World(handler, "res/worlds/world1.txt");
			handler.setWorld(world); 									//resetta il gioco dopo la morte
		}
		playerHealth.tick(World.entityManager.getPlayer().getHealth());
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		playerHealth.paint(g);
	}

}
