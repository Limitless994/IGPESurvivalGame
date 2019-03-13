package dev.igpe.theamazingame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.launcher;
import dev.igpe.theamazingame.display.Display;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.ui.ClickListener;
import dev.igpe.theamazingame.ui.UIImageButton;
import dev.igpe.theamazingame.ui.UIManager;
import dev.igpe.theamazingame.utils.ResourceManager;
import dev.igpe.theamazingame.utils.SaveData;
import dev.igpe.theamazingame.worlds.World;
import dev.igpe.theamazingame.display.*;
public class ExitState extends State {

	static UIManager uiManager3;

	public ExitState (Handler handler) {
		super(handler);
		uiManager3 = new UIManager(handler);

		handler.getMouseManager().setUIManager(uiManager3);


		uiManager3.addObject(new UIImageButton(launcher.width/2,launcher.height/2+400,200,100,Assets.btn_continue,
				new ClickListener(){


			@Override
			public void onClick() {

				State.setState(handler.getGame().gameState);			}
		}));
		uiManager3.addObject(new UIImageButton(launcher.width/2-400,launcher.height/2+400,200,100,Assets.btn_menu,
				new ClickListener(){


			@Override
			public void onClick() {

				State.setState(handler.getGame().menuState);	
				handler.getMouseManager().setUIManager(MenuState.uiManager);
	
			}
		}));
		uiManager3.addObject(new UIImageButton(launcher.width/2 +400,launcher.height/2+400,200,100,Assets.btn_save,
				new ClickListener(){


			@Override
			public void onClick() {

				saveWorld(handler.getWorld());
			}
		}));

	}
	public void saveWorld(World world) {
		SaveData data = new SaveData();
		data.setSave(world);
		try {
			ResourceManager.save(data, "1.save");
			System.out.println("Salvataggio Completato");
		}
		catch (Exception e) {
			System.out.println("IMPOSSIBILE SALVARE "+ e.getMessage());
		}
	}

	@Override
	public void tick() {
		uiManager3.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.pauseBg, 0, 0, null);
		uiManager3.render(g);
	}

}
