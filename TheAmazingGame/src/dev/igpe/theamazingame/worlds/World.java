package dev.igpe.theamazingame.worlds;

import java.awt.Graphics;
import java.util.Random;

import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.launcher;
import dev.igpe.theamazingame.entities.EntityManager;
import dev.igpe.theamazingame.entities.creatures.Enemy;
import dev.igpe.theamazingame.entities.creatures.Player;
import dev.igpe.theamazingame.entities.statics.Rock;
import dev.igpe.theamazingame.entities.statics.Tree;
import dev.igpe.theamazingame.entities.statics.Tree2;
import dev.igpe.theamazingame.entities.statics.Tree3;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.items.ItemManager;
import dev.igpe.theamazingame.states.GameState;
import dev.igpe.theamazingame.tiles.Tile;
import dev.igpe.theamazingame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int k=2; //numero entità da randomizzare
	
	
	private int[][] tiles;
	//Entities
	public static EntityManager entityManager;
	Random random = new Random();

	//ITEM
	private ItemManager itemManager;

	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));

		//item
		itemManager = new ItemManager(handler);

		//randomspawn
		spawnRandom(path);

		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);


	}

	public void spawnRandom(String path) {

		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");

		//da implementare il resto
		int randomStaticENumber = random.nextInt(GameState.numberOfStatics); //randomizza il numero di statics
		int randomEnemyNumber =random.nextInt(GameState.numberOfEnemyes);

		for(int i=0;i<=randomStaticENumber;i++) {

			int spawnTreeX = random.nextInt((Assets.getWidth())* (Utils.parseInt(tokens[2])));
			int spawnTreeY = random.nextInt((Assets.getHeight())* (Utils.parseInt(tokens[2])));
			//			System.out.println("TOKENS1 = " + tokens[1]);

			if(!(getTile(spawnTreeX, spawnTreeY).isSolid())) {
				k=random.nextInt(3);

				if(k==1)	entityManager.addEntity(new Rock(handler, spawnTreeX, spawnTreeY));
				else entityManager.addEntity(new Tree(handler, spawnTreeX, spawnTreeY));
			}
		}
		if(randomEnemyNumber!=0) {
			for(int i=0;i<=randomEnemyNumber;i++) {
				int spawnEnemyX = random.nextInt((Assets.getWidth())* (Utils.parseInt(tokens[2]))); //assets.getHeight= 32* (token[0] del file World.txt) 100 poiché le tile sono comprese tra 
				int spawnEnemyY = random.nextInt((Assets.getHeight())* (Utils.parseInt(tokens[3])));  //100*32 pixels, sia per x che per y.
				if((this.getTile(spawnEnemyX, spawnEnemyY))!=Tile.rockTile
						&&this.entityManager.getPlayer().getX()!=spawnEnemyX
						&&this.entityManager.getPlayer().getY()!=spawnEnemyY) 
					entityManager.addEntity(new Enemy(handler, spawnEnemyX, spawnEnemyY));
			}	

		}

	}
	public void tick(){
		itemManager.tick();
		entityManager.tick();

	}

	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Item
		itemManager.render(g);

		//Entities
		entityManager.render(g);
	}

	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;

		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}

	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}