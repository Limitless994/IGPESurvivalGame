package dev.igpe.theamazingame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.launcher;
import dev.igpe.theamazingame.audio.AudioPlayer;
import dev.igpe.theamazingame.display.Display;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.ui.ClickListener;
import dev.igpe.theamazingame.ui.UIImageButton;
import dev.igpe.theamazingame.ui.UIManager;
import dev.igpe.theamazingame.utils.ResourceManager;
import dev.igpe.theamazingame.utils.SaveData;
import dev.igpe.theamazingame.utils.ScoreboardViewer;
import dev.igpe.theamazingame.worlds.World;
import dev.igpe.theamazingame.display.*;
public class MenuState extends State {
	public static AudioPlayer bgMusic;
	public static AudioPlayer bgMusic1;
	
	static UIManager uiManager;
	public MenuState (Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		bgMusic = new AudioPlayer("/sounds/menumusic1.wav");
		bgMusic1 = new AudioPlayer("/sounds/gamemusic1.wav");
		bgMusic.play();
		handler.getMouseManager().setUIManager(uiManager);
		
uiManager.addObject(new UIImageButton(launcher.width/2-100,launcher.height/2+150,200,100,Assets.btn_start,new ClickListener(){
			
			@Override
			public void onClick() {
				State.setState(handler.getGame().gameState);		
				bgMusic.stop();
	
				bgMusic1.play();
				}
			}));
		
	uiManager.addObject(new UIImageButton(launcher.width/2-100,launcher.height/2+350,200,100,Assets.btn_quit,new ClickListener(){
			

			@Override
			public void onClick() {
				 System.exit(0);			}
			}));
	uiManager.addObject(new UIImageButton(launcher.width/2-100,launcher.height/2+250,200,100,Assets.btn_scores,new ClickListener(){


		@Override
		public void onClick() {
			//			handler.getMouseManager().setUIManager(CreditsState.uiManager2); //evita che il tasto del menù venga premuto dopo essere stato premuto 1 volta
			ScoreboardViewer.show(handler.getGame().getScore());

		}
	}));

	}
	
	@Override
	public void tick() {
		uiManager.tick();
	
	
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.menuBg, 0, 0, null);
		uiManager.render(g);
	}

public void loadWorld() {
		
		try {
			SaveData data = (SaveData) ResourceManager.load("1.save");
			handler.setWorld(data.getSave());
		}
		catch (Exception e) {
			System.out.println("IMPOSSIBILE CARICARE"+ e.getMessage());
		}
	}
}
