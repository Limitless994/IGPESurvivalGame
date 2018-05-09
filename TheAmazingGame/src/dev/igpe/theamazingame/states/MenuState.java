package dev.igpe.theamazingame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.display.Display;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.ui.ClickListener;
import dev.igpe.theamazingame.ui.UIImageButton;
import dev.igpe.theamazingame.ui.UIManager;
import dev.igpe.theamazingame.worlds.World;
import dev.igpe.theamazingame.display.*;
public class MenuState extends State {

	static UIManager uiManager;
	
	public MenuState (Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
		handler.getMouseManager().setUIManager(uiManager);
		
		
		uiManager.addObject(new UIImageButton(600,450,128,64,Assets.btn_start,new ClickListener(){
			

			@Override
			public void onClick() {
				State.setState(handler.getGame().gameState);			}
			}));
		
	uiManager.addObject(new UIImageButton(600,650,128,64,Assets.btn_quit,new ClickListener(){
			

			@Override
			public void onClick() {
				 System.exit(0);			}
			}));
	
	uiManager.addObject(new UIImageButton(600,550,128,64,Assets.btn_credits,new ClickListener(){
		

		@Override
		public void onClick() {
			handler.getMouseManager().setUIManager(CreditsState.uiManager2); //evita che il tasto del menù venga premuto dopo essere stato premuto 1 volta
			
			State.setState(handler.getGame().creditsState);			}
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

}
