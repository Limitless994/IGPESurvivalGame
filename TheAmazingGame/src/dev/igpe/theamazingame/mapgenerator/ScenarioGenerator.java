package dev.igpe.theamazingame.mapgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.igpe.theamazingame.entities.creatures.Enemy;
import dev.igpe.theamazingame.gfx.Assets;
import dev.igpe.theamazingame.states.GameState;
import dev.igpe.theamazingame.tiles.Tile;

public class ScenarioGenerator {

	private LayerMap layerMap;
	private List<AssignedTile> assignedTileList;
	private List<AssignedEnemy> assignedEnemyList;
	
	public ScenarioGenerator(LayerMap layerMap) {
		this.layerMap = layerMap;
		this.assignedTileList = new ArrayList<AssignedTile>();
		this.assignedEnemyList = new ArrayList<AssignedEnemy>();
	}
	
	public List<AssignedEnemy> doAssignEnemies() {
		
		Random random = new Random();
		int randomEnemyNumber =random.nextInt(GameState.numberOfEnemies);
		for(int i=0; i<=randomEnemyNumber; i++) {
			int spawnEnemyX = random.nextInt((Assets.getWidth())* 128); //assets.getHeight= 32* (token[0] del file World.txt) 100 poich� le tile sono comprese tra 
			int spawnEnemyY = random.nextInt((Assets.getHeight())* 128 );  //100*32 pixels, sia per x che per y.
			AssignedEnemy assignedEnemy = new AssignedEnemy(spawnEnemyX, spawnEnemyY);
			assignedEnemyList.add(assignedEnemy);
		}
		
		return assignedEnemyList;
		
	}
	
	public List<AssignedTile> doAssignTiles() {
		
		Random random = new Random();
		byte[] mapData = layerMap.getMapData();
		int width = layerMap.getWidth();
		int height = layerMap.getHeight();
		
		for(int y = 0;y < width;y++){
			for(int x = 0;x < height;x++){
				int i = x + y * width;
				if (mapData[i] == TileType.TREE.getID()) {
					int k = random.nextInt(2);
					if(k==1) {
						assignedTileList.add(new AssignedTile(x, y, 1));
					}
				}
				else if(mapData[i] == TileType.ROCK.getID()) {
					int k = random.nextInt(3);
					if(k==0) {
						assignedTileList.add(new AssignedTile(x, y, 2));
					}
					else if(k==1) {
						assignedTileList.add(new AssignedTile(x, y, 3));
					}
					else if(k==2) {
						assignedTileList.add(new AssignedTile(x, y, 4));
					}
				}
				else if(mapData[i] == TileType.SAND.getID()) {
					int k = random.nextInt(7);
					if(k==1) {
						assignedTileList.add(new AssignedTile(x, y, 5));
					}
				}
			}
		}
		return assignedTileList;
	}
	
	public static int[][] generateTiles(int width, int height, byte[] mapData) {
		int[][] tiles = new int[width][height];
		for(int y = 0;y < width;y++){
			for(int x = 0;x < height;x++){
				int i = x + y * width;
				if (mapData[i] == TileType.GRASS.getID()) 
					tiles[x][y] = Tile.grassTile.getId();
				if (mapData[i] == TileType.ROCK.getID())
					tiles[x][y] = Tile.rockTile.getId();
				if (mapData[i] == TileType.DIRT.getID())
					tiles[x][y] = Tile.dirtTile.getId();
				if (mapData[i] == TileType.WATER.getID()) 
					tiles[x][y] = Tile.waterTile.getId();
				if (mapData[i] == TileType.SAND.getID())
					tiles[x][y] = Tile.sandTile.getId();
				if (mapData[i] == TileType.TREE.getID())
					tiles[x][y] = Tile.grassTile.getId();
			}	
		}
		return tiles;
	}
	
}
