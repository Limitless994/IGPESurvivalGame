package dev.igpe.theamazingame.gfx;

import java.awt.image.BufferedImage;
import java.awt.Font;


public class Assets { //permette ai metodi di essere chiamati solo lo stretto necessario 

	private static final int width=32, height =32;

	public static Font font28;

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	//tile
	public static BufferedImage grass, dirt, stone, sand ,monster,player,tree,tree2,tree3,rock1,menuBg;

	//risorse gaterate
	public static BufferedImage gatRock,gatWood;

	//inventario
	public static BufferedImage inventoryScreen;
	
	//Ammo
	public static BufferedImage bulletAmmo;

	public static BufferedImage[] player_right;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_up;
	public static BufferedImage[] playerStatic;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] btn_quit;
	public static BufferedImage[] btn_credits;
	public static BufferedImage[] enemy_right;
	public static BufferedImage[] enemy_left;
	public static BufferedImage[] enemy_down;
	public static BufferedImage[] enemy_up;
	public static BufferedImage[] enemyStatic;

	public static void init() {

		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet stone2 = new SpriteSheet(ImageLoader.loadImage("/textures/stone.jpg"));
		SpriteSheet grass2 = new SpriteSheet(ImageLoader.loadImage("/textures/testMap.png"));
		SpriteSheet dirt2 = new SpriteSheet(ImageLoader.loadImage("/textures/dirt.jpg"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet.png"));
		SpriteSheet enemySheet = new SpriteSheet(ImageLoader.loadImage("/textures/enemySheet.png"));
		SpriteSheet tree1 = new SpriteSheet(ImageLoader.loadImage("/textures/Tree1.png"));
		SpriteSheet tree2a = new SpriteSheet(ImageLoader.loadImage("/textures/Tree2.png"));
		SpriteSheet tree3a = new SpriteSheet(ImageLoader.loadImage("/textures/Tree3.png"));
		SpriteSheet rock1a = new SpriteSheet(ImageLoader.loadImage("/textures/Rock1.png"));
		SpriteSheet bg1 = new SpriteSheet(ImageLoader.loadImage("/textures/menuBG.jpg"));
		SpriteSheet buttonStart = new SpriteSheet(ImageLoader.loadImage("/textures/btnStart.png"));
		SpriteSheet buttonQuit = new SpriteSheet(ImageLoader.loadImage("/textures/btnQuit.png"));
		SpriteSheet buttonCredits = new SpriteSheet(ImageLoader.loadImage("/textures/btnCredits.png"));

		//inventario
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

		//ammo
		bulletAmmo = ImageLoader.loadImage("/textures/bulletammo.png");
		
		menuBg=bg1.crop(0, 0, 1280, 720);


		btn_credits=new BufferedImage[2];
		btn_credits[0]=buttonCredits.crop(0, 0, 150, 75);
		btn_credits[1]=buttonCredits.crop(0, 75, 150, 75);


		btn_quit=new BufferedImage[2];
		btn_quit[0]=buttonQuit.crop(0, 0, 150, 75);
		btn_quit[1]=buttonQuit.crop(0, 75, 150, 75);

		btn_start=new BufferedImage[2];
		btn_start[0]=buttonStart.crop(0, 0, 150, 75);
		btn_start[1]=buttonStart.crop(0, 75, 150, 75);


		player_right= new BufferedImage[3];
		player_right[0]=playerSheet.crop(0, height, width, height);
		player_right[1]=playerSheet.crop(width, height, width, height);
		player_right[2]=playerSheet.crop(width*2, height, width, height);

		player_left= new BufferedImage[3];
		player_left[0]=playerSheet.crop(0, 0, width, height);
		player_left[1]=playerSheet.crop(width, 0, width+1, height+1);
		player_left[2]=playerSheet.crop(width*2, 0, width+1, height+1);

		player_down= new BufferedImage[3];
		player_down[0]=playerSheet.crop(0, height*2, width, height);
		player_down[1]=playerSheet.crop(width, height*2, width+1, height+1);
		player_down[2]=playerSheet.crop(width*2, height*2, width+1, height+1);

		player_up= new BufferedImage[3];
		player_up[0]=playerSheet.crop(0, height*3, width, height);
		player_up[1]=playerSheet.crop(width,height*3, width+1, height+1);
		player_up[2]=playerSheet.crop(width*2, height*3, width+1, height+1);

		playerStatic= new BufferedImage[1];
		playerStatic[0]=playerSheet.crop(0, height*3, width, height);//da ottimizzare

		/////////////////////////////////////////enemySheetAnimation///////////////////////////////////////////////////
		enemy_right= new BufferedImage[3];
		enemy_right[0]=enemySheet.crop(0, height, width, height);
		enemy_right[1]=enemySheet.crop(width, height, width, height);
		enemy_right[2]=enemySheet.crop(width*2, height, width, height);

		enemy_left= new BufferedImage[3];
		enemy_left[0]=enemySheet.crop(0, 0, width, height);
		enemy_left[1]=enemySheet.crop(width, 0, width+1, height+1);
		enemy_left[2]=enemySheet.crop(width*2, 0, width+1, height+1);

		enemy_down= new BufferedImage[3];
		enemy_down[0]=enemySheet.crop(0, height*2, width, height);
		enemy_down[1]=enemySheet.crop(width, height*2, width+1, height+1);
		enemy_down[2]=enemySheet.crop(width*2, height*2, width+1, height+1);

		enemy_up= new BufferedImage[3];
		enemy_up[0]=enemySheet.crop(0, height*3, width, height);
		enemy_up[1]=enemySheet.crop(width,height*3, width+1, height+1);
		enemy_up[2]=enemySheet.crop(width*2, height*3, width+1, height+1);

		enemyStatic= new BufferedImage[3];
		enemyStatic[0]=enemySheet.crop(0, height*3, width, height);//da ottimizzare
		enemyStatic[1]=enemySheet.crop(width,height*3, width+1, height+1);
		enemyStatic[2]=enemySheet.crop(width*2, height, width, height);


		grass=grass2.crop(0, 0, width, height);
		dirt=dirt2.crop(width, 0, width+1, height+1);
		stone=stone2.crop(0,0,width,height);
		sand=sheet.crop(width*3, 0, width, height+1);
		monster=sheet.crop(width*2, height, width+1, height+1);
		tree=tree1.crop(0, 0, width*2, height*2);
		tree2=tree2a.crop(0, 0, width*2, height*2);
		tree3=tree3a.crop(0, 0, width*2, height*2);
		rock1=rock1a.crop(0, 0, width,height);
		gatRock=ImageLoader.loadImage("/textures/gatStone.png");
		gatWood=ImageLoader.loadImage("/textures/gatWood.png");

	}

}
