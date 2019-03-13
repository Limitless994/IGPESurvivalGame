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
import dev.igpe.theamazingame.display.*;
public class CreditsState extends State {

	static UIManager uiManager2;
	
	public CreditsState (Handler handler) {
		super(handler);
		uiManager2 = new UIManager(handler);
		
		handler.getMouseManager().setUIManager(uiManager2);
		
		
	uiManager2.addObject(new UIImageButton(600,650,128,64,Assets.btn_quit,new ClickListener(){
			

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(MenuState.uiManager); //evita che il tasto del menù venga premuto dopo essere stato premuto 1 volta
				
				State.setState(handler.getGame().menuState);			}
			}));

	}
	
	
	@Override
	public void tick() {
		uiManager2.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.menuBg, 0, 0, null);
		uiManager2.render(g);
	}

}
