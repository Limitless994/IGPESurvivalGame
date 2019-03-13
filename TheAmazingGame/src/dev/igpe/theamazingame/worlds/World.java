package dev.igpe.theamazingame.worlds;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import dev.igpe.theamazingame.Game;
import dev.igpe.theamazingame.Handler;
import dev.igpe.theamazingame.entities.Entity;
import dev.igpe.theamazingame.entities.EntityManager;
import dev.igpe.theamazingame.entities.creatures.Enemy;
import dev.igpe.theamazingame.entities.statics.CopperOre;
import dev.igpe.theamazingame.entities.statics.IronOre;
import dev.igpe.theamazingame.entities.statics.Petrol;
import dev.igpe.theamazingame.entities.statics.Rock;
import dev.igpe.theamazingame.entities.statics.Tree;
import dev.igpe.theamazingame.entities.statics.Wall;
import dev.igpe.theamazingame.entities.statics.turret;
import dev.igpe.theamazingame.items.ItemManager;
import dev.igpe.theamazingame.mapgenerator.AssignedEnemy;
import dev.igpe.theamazingame.mapgenerator.AssignedTile;
import dev.igpe.theamazingame.mapgenerator.ScenarioGenerator;
import dev.igpe.theamazingame.states.GameState;
import dev.igpe.theamazingame.tiles.Tile;

public class World {
	private Handler handler;

	//	private static final Random mapSeed = new Random(System.currentTimeMillis());

	//	LayerSetting groundLayerSetting = new LayerSetting(256, 256, 16, 0, mapSeed);
	//	LayerGenerator groundGenerator = new LayerGenerator(groundLayerSetting);
	//	private LayerMap groundMap = groundGenerator.doCreate(LayerType.GROUND);


	private int width, height;
	private int spawnX=100, spawnY=100;
	private int k=2; //numero entit� da randomizzare
	long lastTurn = System.currentTimeMillis();
	private int[][] tiles;
//	private Enemy e;
	//Entities
	public EntityManager entityManager;
	Random random = new Random();

	//ITEM
	private ItemManager itemManager;

	int randomEnemyNumber =random.nextInt(GameState.numberOfEnemies);
	public World(Handler handler){
		this.handler = handler;

		entityManager = new EntityManager(handler);

		//item
		itemManager = new ItemManager(handler);


		//randomspawn

		//spawnEnemyes(randomEnemyNumber);
		//		loadWorld();
		//		Game.getPlayer().setX(spawnX);
		//		Game.getPlayer().setY(spawnY);

	}


	private void spawnEnemies() {
		List<AssignedEnemy> assignedEnemyList = Game.getSenarioManager().getScenario().getAssignedEnemyList();
		for (AssignedEnemy assignedEnemy : assignedEnemyList) {
			if (getTile(assignedEnemy.getX(), assignedEnemy.getY()) != Tile.rockTile) {
				Enemy enemy = new Enemy(handler, assignedEnemy.getX(), assignedEnemy.getY());
				entityManager.addEntity(enemy);
			}
		}
		
		
		//da implementare il resto


//		if(numberOfEnemyes!=0) {
//			for(int i=0;i<=numberOfEnemyes;i++) {
//				int spawnEnemyX = random.nextInt((Assets.getWidth())* 128); //assets.getHeight= 32* (token[0] del file World.txt) 100 poich� le tile sono comprese tra 
//				int spawnEnemyY = random.nextInt((Assets.getHeight())* 128 );  //100*32 pixels, sia per x che per y.
//				if((this.getTile(spawnEnemyX, spawnEnemyY))!=Tile.rockTile
//						//						&&Game.getPlayer().getX()!=spawnEnemyX
//						//						&&Game.getPlayer().getY()!=spawnEnemyY
//						) 
//					e= new Enemy(handler, spawnEnemyX, spawnEnemyY);
//				entityManager.addEntity(e);
//
//			}	
//
//		}

	}



	public void tick(){
		itemManager.tick();
		entityManager.tick();

		if (System.currentTimeMillis() - lastTurn >= 60000) {
			spawnEnemies();
			lastTurn = System.currentTimeMillis();
		}

		if(handler.getGame().getPlayer().getCraftMenu().isCraftObW()) {
			craftWall();

		}
		else if(handler.getGame().getPlayer().getCraftMenu().isCraftObT()) {
			craftTurret();

		}

		//Funzione Mondo Rotondo
		//				if(handler.getGame().getPlayer().getX()>Tile.TILEWIDTH*Game.getSenarioManager().getScenario().getWidth()/2) handler.getGame().getPlayer().setX(0); //Tile.TILEWIDTH*getGroundMap().getWidth() massima dimensione della mappa
		//				if(handler.getGame().getPlayer().getX()<0) handler.getGame().getPlayer().setX(Tile.TILEWIDTH*Game.getSenarioManager().getScenario().getWidth()/2);
		//				if(handler.getGame().getPlayer().getY()>Tile.TILEWIDTH*Game.getSenarioManager().getScenario().getHeight()/2) handler.getGame().getPlayer().setY(0);
		//				if(handler.getGame().getPlayer().getY()<0) handler.getGame().getPlayer().setY(Tile.TILEWIDTH*Game.getSenarioManager().getScenario().getHeight()/2);


	}

	public void craftWall() {

		int x=(handler.getMouseManager().getMouseX()+(int)handler.getGameCamera().getxOffset())/Tile.TILEWIDTH;
		int y = (handler.getMouseManager().getMouseY()+(int)handler.getGameCamera().getyOffset())/Tile.TILEHEIGHT;
		entityManager.addEntity(new Wall(handler,x*48, y*48));
	}
	public void craftTurret() {
		int x=(handler.getMouseManager().getMouseX()+(int)handler.getGameCamera().getxOffset())/Tile.TILEWIDTH;
		int y = (handler.getMouseManager().getMouseY()+(int)handler.getGameCamera().getyOffset())/Tile.TILEHEIGHT;
		entityManager.addEntity(new turret(handler,x*48, y*48));
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
		for(Entity e: entityManager.getEntities()) {
			if(Math.abs(e.getX()-handler.getGame().getPlayer().getX())<=1750 && Math.abs(e.getY()-handler.getGame().getPlayer().getY())<=1750) {
				e.render(g);
			}

		}

		//Inventari
		handler.getGame().getPlayer().postRender(g);
	}

	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;

		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.grassTile;
		return t;
	}
	public void loadWorld(){

		width = 128;
		height = 128;
		spawnX = 100;
		spawnY = 100;

		tiles = ScenarioGenerator.generateTiles(Game.getSenarioManager().getScenario().getWidth(), 
				Game.getSenarioManager().getScenario().getHeight(), Game.getSenarioManager().getScenario().getMap());

		List<AssignedTile> assignedTileList = Game.getSenarioManager().getScenario().getAssignedTileList();
		for (AssignedTile assignedTile : assignedTileList) {
			switch(assignedTile.getTile()) {
			case 1:
				entityManager.addEntity(new Tree(handler, assignedTile.getX()*Tile.TILEWIDTH-32, assignedTile.getY()*Tile.TILEHEIGHT-64));
				break;
			case 2:
				entityManager.addEntity(new Rock(handler, assignedTile.getX()*Tile.TILEWIDTH, assignedTile.getY()*Tile.TILEHEIGHT));
				break;
			case 3:
				entityManager.addEntity(new IronOre(handler, assignedTile.getX()*Tile.TILEWIDTH, assignedTile.getY()*Tile.TILEHEIGHT));
				break;
			case 4:
				entityManager.addEntity(new CopperOre(handler, assignedTile.getX()*Tile.TILEWIDTH, assignedTile.getY()*Tile.TILEHEIGHT));
				break;
			case 5:
				entityManager.addEntity(new Petrol(handler, assignedTile.getX()*Tile.TILEWIDTH, assignedTile.getY()*Tile.TILEHEIGHT));
				break;
			}
		}
		
		spawnEnemies();

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

	public int[][] getTiles() {
		return tiles;
	}


	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
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