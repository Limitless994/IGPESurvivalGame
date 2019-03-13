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
	public static BufferedImage grass,dirt, stone, sand ,monster,player,
	tree,tree2,tree3,rock1,menuBg,pauseBg,terrainTextures,topscoresbg;

	//risorse grezze
	public static BufferedImage petrol,ironOre,copperOre,wall;

	//risorse gaterate
	public static BufferedImage gatRock,gatWood,solidfuel,gatIron,gatCopper;

	//inventario
		public static BufferedImage inventoryScreen;
		public static BufferedImage craftScreen;
		public static BufferedImage selectedItem;
		
	//Ammo
	public static BufferedImage bulletAmmo,fuelAmmo,rocketAmmo;

	//weapons
	public static BufferedImage gun,rocketLauncher,flamethrower,assaultRifle, pickAxe,quickBarBg;
	
	//Oggetti

	//animazioni
	public static BufferedImage bullet;  //animazione proiettile
	public static BufferedImage[]  turret_right, turret_left, turret_down, turret_up;  //animazione torretta
	public static BufferedImage[] water;
	public static BufferedImage[] player_right;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_gun_right;
	public static BufferedImage[] player_gun_left;
	public static BufferedImage[] player_gun_down;
	public static BufferedImage[] player_gun_up;
	public static BufferedImage[] player_right_swimming;
	public static BufferedImage[] player_left_swimming;
	public static BufferedImage[] player_down_swimming;
	public static BufferedImage[] player_up_swimming;
	public static BufferedImage[] player_RL_right;
	public static BufferedImage[] player_RL_left;
	public static BufferedImage[] player_RL_down;
	public static BufferedImage[] player_RL_up;
	public static BufferedImage[] player_AR_right;
	public static BufferedImage[] player_AR_left;
	public static BufferedImage[] player_AR_down;
	public static BufferedImage[] player_AR_up;
	public static BufferedImage[] playerStatic;
	public static BufferedImage[] playerStatic_swimming;
	public static BufferedImage[] player_AR_Static;
	public static BufferedImage[] player_RL_Static;
	public static BufferedImage[] player_gun_Static;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] btn_quit;
	public static BufferedImage[] btn_credits;
	public static BufferedImage[] btn_continue;
	public static BufferedImage[] btn_load;
	public static BufferedImage[] btn_save;
	public static BufferedImage[] btn_menu;
	public static BufferedImage[] btn_scores;
	public static BufferedImage[] enemy_right;
	public static BufferedImage[] enemy_left;
	public static BufferedImage[] enemy_down;
	public static BufferedImage[] enemy_up;
	public static BufferedImage[] enemy_right_swimming;
	public static BufferedImage[] enemy_left_swimming;
	public static BufferedImage[] enemy_down_swimming;
	public static BufferedImage[] enemy_up_swimming;
	public static BufferedImage[] enemyStatic_swimming;
	public static BufferedImage[] enemyStatic;
	public static BufferedImage[] grassMesh;

	public static void init() {

		font28 = FontLoader.loadFont("fonts/slkscr.ttf", 28);

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

		SpriteSheet grass2 = new SpriteSheet(ImageLoader.loadImage("/textures/grass1.PNG"));
		SpriteSheet dirt2 = new SpriteSheet(ImageLoader.loadImage("/textures/dirt.jpg"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet.png"));
		SpriteSheet playerSheetSwimming = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheetSwimming.png"));
		SpriteSheet player_gun_Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet_gun.png"));
		SpriteSheet player_RL_Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet_RL.png"));
		SpriteSheet player_AR_Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet_AR.png"));
		SpriteSheet enemySheet = new SpriteSheet(ImageLoader.loadImage("/textures/enemySheet.png"));
		SpriteSheet enemySheetSwimming = new SpriteSheet(ImageLoader.loadImage("/textures/enemySheetSwimming.png"));
		SpriteSheet turret = new SpriteSheet(ImageLoader.loadImage("/textures/turret.png"));

		SpriteSheet water2 = new SpriteSheet(ImageLoader.loadImage("/textures/water2.png"));
		SpriteSheet sand2 = new SpriteSheet(ImageLoader.loadImage("/textures/sand.PNG"));
		SpriteSheet stoneT = new SpriteSheet(ImageLoader.loadImage("/textures/concrete.png"));
		SpriteSheet terrainTextures =new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheetTerrain.png"));
		
		SpriteSheet bg1 = new SpriteSheet(ImageLoader.loadImage("/textures/menubg.jpg"));
		SpriteSheet bg2 = new SpriteSheet(ImageLoader.loadImage("/textures/bgHELP.jpg"));
		SpriteSheet bg3 = new SpriteSheet(ImageLoader.loadImage("/textures/bgTopScores.jpg"));

		SpriteSheet buttonStart = new SpriteSheet(ImageLoader.loadImage("/textures/btnStart.png"));
		SpriteSheet buttonQuit = new SpriteSheet(ImageLoader.loadImage("/textures/btnQuit.png"));
		SpriteSheet buttonCredits = new SpriteSheet(ImageLoader.loadImage("/textures/btnCredits.png"));
		SpriteSheet buttonContinue = new SpriteSheet(ImageLoader.loadImage("/textures/btnContinue.png"));
		SpriteSheet buttonLoad = new SpriteSheet(ImageLoader.loadImage("/textures/btnLoad.png"));
		SpriteSheet buttonSave = new SpriteSheet(ImageLoader.loadImage("/textures/btnSave.png"));
		SpriteSheet buttonMenu = new SpriteSheet(ImageLoader.loadImage("/textures/btnMenu.png"));
		SpriteSheet buttonScores = new SpriteSheet(ImageLoader.loadImage("/textures/btnScores.png"));
		
		
		//risorse gaterate
		solidfuel = ImageLoader.loadImage("/textures/risorse/solidfuel.png");
		gatRock=ImageLoader.loadImage("/textures/risorse/gatStone.png");
		gatWood=ImageLoader.loadImage("/textures/risorse/gatWood.png");
        gatIron=ImageLoader.loadImage("/textures/risorse/ironplate.png");
        gatCopper=ImageLoader.loadImage("/textures/risorse/copper-plate.png");
		wall=ImageLoader.loadImage("/textures/wall.PNG");


		//risorse
		petrol = ImageLoader.loadImage("/textures/risorse/petrol.png");
		ironOre = ImageLoader.loadImage("/textures/risorse/IronOre.png");
		copperOre = ImageLoader.loadImage("/textures/risorse/CopperOre.png");
		SpriteSheet stone2 = new SpriteSheet(ImageLoader.loadImage("/textures/risorse/stone.jpg"));
		SpriteSheet tree1 = new SpriteSheet(ImageLoader.loadImage("/textures/risorse/Tree1.png"));
		SpriteSheet tree2a = new SpriteSheet(ImageLoader.loadImage("/textures/risorse/Tree2.png"));
		SpriteSheet tree3a = new SpriteSheet(ImageLoader.loadImage("/textures/risorse/Tree3.png"));
		SpriteSheet rock1a = new SpriteSheet(ImageLoader.loadImage("/textures/risorse/Rock1.png"));
		SpriteSheet gmesh=new SpriteSheet(ImageLoader.loadImage("/textures/mesh1.png"));

		//inventario
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		selectedItem =ImageLoader.loadImage("/textures/square.png");
		quickBarBg=ImageLoader.loadImage("/textures/rectangle.png");
		craftScreen = ImageLoader.loadImage("/textures/craftScreen.png");

		//ammo
		bulletAmmo = ImageLoader.loadImage("/textures/ammo/bulletammo.png");
		fuelAmmo = ImageLoader.loadImage("/textures/ammo/flamethrower-ammo.png");
		rocketAmmo = ImageLoader.loadImage("/textures/ammo/rocket.png");

		//terreno


		//weapons
		gun = ImageLoader.loadImage("/textures/weapons/pistol.png");
		rocketLauncher = ImageLoader.loadImage("/textures/weapons/rocket-launcher.png");
		flamethrower = ImageLoader.loadImage("/textures/weapons/flamethrower.png");
		assaultRifle = ImageLoader.loadImage("/textures/weapons/assaultRIfle.png");
		pickAxe = ImageLoader.loadImage("/textures/weapons/pickaxe.png");
		
		//animazioni
		bullet = ImageLoader.loadImage("/textures/animations/bullet.png");

		menuBg=bg1.crop(0, 0, 1920, 1080);
		pauseBg=bg2.crop(0, 0, 1920, 1080);
		topscoresbg=bg3.crop(0, 0, 800, 800);
		
		turret_right=new BufferedImage[1];
		turret_right[0]=turret.crop(0, height, width, height);
		turret_left=new BufferedImage[1];
		turret_left[0]=turret.crop(0, 0, width, height);
		turret_down=new BufferedImage[1];
		turret_down[0]=turret.crop(0, height*2, width, height);
		turret_up=new BufferedImage[1];
		turret_up[0]=turret.crop(0, height*3, width, height);
		

		btn_scores=new BufferedImage[2];
		btn_scores[0]=buttonScores.crop(0, 0, 150, 75);
		btn_scores[1]=buttonScores.crop(0, 75, 150, 75);
		
		btn_credits=new BufferedImage[2];
		btn_credits[0]=buttonCredits.crop(0, 0, 150, 75);
		btn_credits[1]=buttonCredits.crop(0, 75, 150, 75);

		btn_load=new BufferedImage[2];
		btn_load[0]=buttonLoad.crop(0, 0, 150, 75);
		btn_load[1]=buttonLoad.crop(0, 75, 150, 75);
		
		btn_save=new BufferedImage[2];
		btn_save[0]=buttonSave.crop(0, 0, 150, 75);
		btn_save[1]=buttonSave.crop(0, 75, 150, 75);
		
		btn_quit=new BufferedImage[2];
		btn_quit[0]=buttonQuit.crop(0, 0, 150, 75);
		btn_quit[1]=buttonQuit.crop(0, 75, 150, 75);
		
		btn_menu=new BufferedImage[2];
		btn_menu[0]=buttonMenu.crop(0, 0, 150, 75);
		btn_menu[1]=buttonMenu.crop(0, 75, 150, 75);


		btn_start=new BufferedImage[2];
		btn_start[0]=buttonStart.crop(0, 0, 150, 75);
		btn_start[1]=buttonStart.crop(0, 75, 150, 75);

		btn_continue=new BufferedImage[2];
		btn_continue[0]=buttonContinue.crop(0, 0, 150, 75);
		btn_continue[1]=buttonContinue.crop(0, 75, 150, 75);

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
		
		player_right_swimming= new BufferedImage[3];
		player_right_swimming[0]=playerSheetSwimming.crop(0, height, width, height);
		player_right_swimming[1]=playerSheetSwimming.crop(width, height, width, height);
		player_right_swimming[2]=playerSheetSwimming.crop(width*2, height, width, height);

		player_left_swimming= new BufferedImage[3];
		player_left_swimming[0]=playerSheetSwimming.crop(0, 0, width, height);
		player_left_swimming[1]=playerSheetSwimming.crop(width, 0, width+1, height+1);
		player_left_swimming[2]=playerSheetSwimming.crop(width*2, 0, width+1, height+1);

		player_down_swimming= new BufferedImage[3];
		player_down_swimming[0]=playerSheetSwimming.crop(0, height*2, width, height);
		player_down_swimming[1]=playerSheetSwimming.crop(width, height*2, width+1, height+1);
		player_down_swimming[2]=playerSheetSwimming.crop(width*2, height*2, width+1, height+1);

		player_up_swimming= new BufferedImage[3];
		player_up_swimming[0]=playerSheetSwimming.crop(0, height*3, width, height);
		player_up_swimming[1]=playerSheetSwimming.crop(width,height*3, width+1, height+1);
		player_up_swimming[2]=playerSheetSwimming.crop(width*2, height*3, width+1, height+1);

		playerStatic_swimming= new BufferedImage[1];
		playerStatic_swimming[0]=playerSheetSwimming.crop(0, height*3, width, height);//da ottimizzare

		/////////////////////////////////////////////////////////////////////////////////////////////

		player_gun_right= new BufferedImage[3];
		player_gun_right[0]=player_gun_Sheet.crop(0, height, width, height);
		player_gun_right[1]=player_gun_Sheet.crop(width, height, width, height);
		player_gun_right[2]=player_gun_Sheet.crop(width*2, height, width, height);

		player_gun_left= new BufferedImage[3];
		player_gun_left[0]=player_gun_Sheet.crop(0, 0, width, height);
		player_gun_left[1]=player_gun_Sheet.crop(width, 0, width+1, height+1);
		player_gun_left[2]=player_gun_Sheet.crop(width*2, 0, width+1, height+1);

		player_gun_down= new BufferedImage[3];
		player_gun_down[0]=player_gun_Sheet.crop(0, height*2, width, height);
		player_gun_down[1]=player_gun_Sheet.crop(width, height*2, width+1, height+1);
		player_gun_down[2]=player_gun_Sheet.crop(width*2, height*2, width+1, height+1);

		player_gun_up= new BufferedImage[3];
		player_gun_up[0]=player_gun_Sheet.crop(0, height*3, width, height);
		player_gun_up[1]=player_gun_Sheet.crop(width,height*3, width+1, height+1);
		player_gun_up[2]=player_gun_Sheet.crop(width*2, height*3, width+1, height+1);

		player_gun_Static= new BufferedImage[1];
		player_gun_Static[0]=player_gun_Sheet.crop(0, height*3, width, height);//da ottimizzare

		//////////////////////////////////////////////////////////////////////////////////////

		player_RL_right= new BufferedImage[3];
		player_RL_right[0]=player_RL_Sheet.crop(0, height, width, height);
		player_RL_right[1]=player_RL_Sheet.crop(width, height, width, height);
		player_RL_right[2]=player_RL_Sheet.crop(width*2, height, width, height);

		player_RL_left= new BufferedImage[3];
		player_RL_left[0]=player_RL_Sheet.crop(0, 0, width, height);
		player_RL_left[1]=player_RL_Sheet.crop(width, 0, width+1, height+1);
		player_RL_left[2]=player_RL_Sheet.crop(width*2, 0, width+1, height+1);

		player_RL_down= new BufferedImage[3];
		player_RL_down[0]=player_RL_Sheet.crop(0, height*2, width, height);
		player_RL_down[1]=player_RL_Sheet.crop(width, height*2, width+1, height+1);
		player_RL_down[2]=player_RL_Sheet.crop(width*2, height*2, width+1, height+1);

		player_RL_up= new BufferedImage[3];
		player_RL_up[0]=player_RL_Sheet.crop(0, height*3, width, height);
		player_RL_up[1]=player_RL_Sheet.crop(width,height*3, width+1, height+1);
		player_RL_up[2]=player_RL_Sheet.crop(width*2, height*3, width+1, height+1);

		player_RL_Static= new BufferedImage[1];
		player_RL_Static[0]=player_RL_Sheet.crop(0, height*3, width, height);//da ottimizzare

		//////////////////////////////////////////////////////////////////////////////////////////

		player_AR_right= new BufferedImage[3];
		player_AR_right[0]=player_AR_Sheet.crop(0, height, width, height);
		player_AR_right[1]=player_AR_Sheet.crop(width, height, width, height);
		player_AR_right[2]=player_AR_Sheet.crop(width*2, height, width, height);

		player_AR_left= new BufferedImage[3];
		player_AR_left[0]=player_AR_Sheet.crop(0, 0, width, height);
		player_AR_left[1]=player_AR_Sheet.crop(width, 0, width+1, height+1);
		player_AR_left[2]=player_AR_Sheet.crop(width*2, 0, width+1, height+1);

		player_AR_down= new BufferedImage[3];
		player_AR_down[0]=player_AR_Sheet.crop(0, height*2, width, height);
		player_AR_down[1]=player_AR_Sheet.crop(width, height*2, width+1, height+1);
		player_AR_down[2]=player_AR_Sheet.crop(width*2, height*2, width+1, height+1);

		player_AR_up= new BufferedImage[3];
		player_AR_up[0]=player_AR_Sheet.crop(0, height*3, width, height);
		player_AR_up[1]=player_AR_Sheet.crop(width,height*3, width+1, height+1);
		player_AR_up[2]=player_AR_Sheet.crop(width*2, height*3, width+1, height+1);

		player_AR_Static= new BufferedImage[1];
		player_AR_Static[0]=player_AR_Sheet.crop(0, height*3, width, height);//da ottimizzare





		/////////////////////////////////////////enemySheetAnimation///////////////////////////////////////////////////
		enemy_left= new BufferedImage[3];
		enemy_left[0]=enemySheet.crop(0, height, width, height);
		enemy_left[1]=enemySheet.crop(width, height, width, height);
		enemy_left[2]=enemySheet.crop(width*2, height, width, height);

		enemy_up= new BufferedImage[3];
		enemy_up[0]=enemySheet.crop(0, 0, width, height);
		enemy_up[1]=enemySheet.crop(width, 0, width+1, height+1);
		enemy_up[2]=enemySheet.crop(width*2, 0, width+1, height+1);

		enemy_right= new BufferedImage[3];
		enemy_right[0]=enemySheet.crop(0, height*2, width, height);
		enemy_right[1]=enemySheet.crop(width, height*2, width+1, height+1);
		enemy_right[2]=enemySheet.crop(width*2, height*2, width+1, height+1);

		enemy_down= new BufferedImage[3];
		enemy_down[0]=enemySheet.crop(0, height*3, width, height);
		enemy_down[1]=enemySheet.crop(width,height*3, width+1, height+1);
		enemy_down[2]=enemySheet.crop(width*2, height*3, width+1, height+1);

		enemyStatic= new BufferedImage[3];
		enemyStatic[0]=enemySheet.crop(0, height*3, width, height);//da ottimizzare
		enemyStatic[1]=enemySheet.crop(width,height*3, width+1, height+1);
		enemyStatic[2]=enemySheet.crop(width*2, height, width, height);
		
		enemy_left_swimming= new BufferedImage[3];
		enemy_left_swimming[0]=enemySheetSwimming.crop(0, height, width, height);
		enemy_left_swimming[1]=enemySheetSwimming.crop(width, height, width, height);
		enemy_left_swimming[2]=enemySheetSwimming.crop(width*2, height, width, height);

		enemy_up_swimming= new BufferedImage[3];
		enemy_up_swimming[0]=enemySheetSwimming.crop(0, 0, width, height);
		enemy_up_swimming[1]=enemySheetSwimming.crop(width, 0, width+1, height+1);
		enemy_up_swimming[2]=enemySheetSwimming.crop(width*2, 0, width+1, height+1);

		enemy_right_swimming= new BufferedImage[3];
		enemy_right_swimming[0]=enemySheetSwimming.crop(0, height*2, width, height);
		enemy_right_swimming[1]=enemySheetSwimming.crop(width, height*2, width+1, height+1);
		enemy_right_swimming[2]=enemySheetSwimming.crop(width*2, height*2, width+1, height+1);

		enemy_down_swimming= new BufferedImage[3];
		enemy_down_swimming[0]=enemySheetSwimming.crop(0, height*3, width, height);
		enemy_down_swimming[1]=enemySheetSwimming.crop(width,height*3, width+1, height+1);
		enemy_down_swimming[2]=enemySheetSwimming.crop(width*2, height*3, width+1, height+1);

		enemyStatic_swimming= new BufferedImage[3];
		enemyStatic_swimming[0]=enemySheetSwimming.crop(0, height*3, width, height);//da ottimizzare
		enemyStatic_swimming[1]=enemySheetSwimming.crop(width,height*3, width+1, height+1);
		enemyStatic_swimming[2]=enemySheetSwimming.crop(width*2, height, width, height);

		//terreno
		grassMesh= new BufferedImage[120];
		int k=0;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				grassMesh[k]=gmesh.crop((32*i),(16*j),width,height);
				k++;
			}
		}
		//		grassMesh[0]=
		//		grassMesh[1]=gmesh.crop(0,height,width,height);
		//		grassMesh[2]=gmesh.crop(0,height*2,width,height);
		//		grassMesh[3]=gmesh.crop(0,height*3,width,height);




		sand  = terrainTextures.crop(0, 0, width, height);
		//water =terrainTextures.crop(width, 0, width, height);
		 grass	=terrainTextures.crop(width*2+1,0,width,height);
		stone	=terrainTextures.crop(width*3, 0, width, height);
		
		tree=tree1.crop(0, 0, width*2, height*2);
		dirt=dirt2.crop(width*3, 0, width, height);
		rock1=rock1a.crop(0, 0, width,height);
		
		water= new BufferedImage[3];
		water[0]=water2.crop(0, 0, width, height);//da ottimizzare
		water[1]=water2.crop(width,0, width, height);
		water[2]=water2.crop(width*2, 0, width, height);

	}

}
