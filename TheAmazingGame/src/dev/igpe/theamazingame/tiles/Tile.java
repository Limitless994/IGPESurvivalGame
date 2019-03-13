package dev.igpe.theamazingame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	//STATIC 

	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile= new RockTile(2);  //utilizza la classe RockTile per creare un elemento assegnandolo ad un ID, utilizzando gli assert per disegnare mediante il crop della texture.
	public static Tile sandTile= new SandTile(3);
	public static Tile waterTile = new WaterTile(4);


	//CLASSE

	public static final int TILEWIDTH = 48, TILEHEIGHT = 48;

	protected BufferedImage texture;
	protected  int id;
	public Tile(BufferedImage texture, int id) {
		this.texture=texture;
		this.id=id;

		tiles[id]= this;
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH,TILEHEIGHT, null);

	}

	public boolean isSolid() { //setta se un tile puo essere attraversato
		return false;
	}

	public int getId() {
		return id;
	}

}
