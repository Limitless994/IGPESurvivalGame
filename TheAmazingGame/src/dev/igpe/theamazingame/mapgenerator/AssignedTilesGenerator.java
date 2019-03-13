package dev.igpe.theamazingame.mapgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.igpe.theamazingame.tiles.Tile;

public class AssignedTilesGenerator {

	private LayerMap layerMap;
	private List<AssignedTile> assignedTileList;

	public AssignedTilesGenerator(LayerMap layerMap) {
		this.layerMap = layerMap;
		this.assignedTileList = new ArrayList<AssignedTile>();
	}

	public List<AssignedTile> doAssign() {

		Random random = new Random();
		byte[] mapData = layerMap.getMapData();
		int width = layerMap.getWidth();
		int height = layerMap.getHeight();

		for(int y = 0;y < width;y++){
			for(int x = 0;x < height;x++){
				int i = x + y * width;
				//				////////////////////////////////////RIEMPIMENTO MAPPA//////////////////////////////////
				//				if (mapData[i] == TileType.GRASS.getID()) { tiles[x][y] = Tile.grassTile.getId();
				//				}
				//				if (mapData[i] == TileType.ROCK.getID()) tiles[x][y] = Tile.rockTile.getId();
				//				if (mapData[i] == TileType.DIRT.getID())tiles[x][y] = Tile.dirtTile.getId();
				//				if (mapData[i] == TileType.WATER.getID())tiles[x][y] = Tile.waterTile.getId();
				//				if (mapData[i] == TileType.SAND.getID()) {
				//					//					entityManager.addEntity(new Petrol(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT));
				//
				//					tiles[x][y] = Tile.sandTile.getId();
				//
				//				}
				//				//	                if (mapData[i] == TileType.TREE.getID()) pixels[i] = TileType.TREE.getHexColor();
				//				//	                if (mapData[i] == TileType.LAVA.getID()) pixels[i] = TileType.LAVA.getHexColor();
				if (mapData[i] == TileType.TREE.getID()) {
					int k = random.nextInt(2);
					if(k==1) {
						assignedTileList.add(new AssignedTile(x, y, 1));
					}
				}
				else if(mapData[i] == TileType.ROCK.getID()) {
					int k = random.nextInt(6);
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
					int k = random.nextInt(17);
					if(k==1) {
						assignedTileList.add(new AssignedTile(x, y, 5));
					}
				}
			}
		}
		return assignedTileList;
		//
		//		for(int y = 0;y < width;y++){
		//			for(int x = 0;x < height;x++){
		//				int i = x + y * width;
		//				if(mapData[i] == TileType.ROCK.getID()) {
		//					int k = random.nextInt(3);
		//					if(k==0) {
		//						assignedTileList.add(new AssignedTile(x, y, 2));
		//					}
		//					else if(k==1) {
		//						assignedTileList.add(new AssignedTile(x, y, 3));
		//					}
		//					else if(k==2) {
		//						assignedTileList.add(new AssignedTile(x, y, 4));
		//					}
		//
		//
		//				}
		//				else if(tiles[x][y]==Tile.sandTile.getId()) {
		//					int k = random.nextInt(7);
		//					if(k==1) {
		//						assignedTileList.add(new AssignedTile(x, y, 5));
		//					}
		//				}
		//			}
		//		}
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
