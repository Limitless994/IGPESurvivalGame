package dev.igpe.theamazingame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.igpe.theamazingame.entities.creatures.PlayerMP;
import dev.igpe.theamazingame.Handler;


public class EntityManager {
	
	private Handler handler;
//	private Player player;
	private ArrayList<Entity> entities;
	

	
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getX() + a.getY() + a.getHeight() < b.getX() + b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler){
		this.handler = handler;
		entities = new ArrayList<Entity>();

			}
	
	public void tick(){
		Iterator<Entity>it = entities.iterator();
	
		while(it.hasNext()){
			Entity e = it.next();
			e.tick();
			
			//controlla se e' viva o morta e in caso la rimuove
			if(!e.isActive())
				it.remove();
				
		}
	
		
		entities.sort(renderSorter);
	}
	
    private int getPlayerMPIndex(String username) {
        int index = 0;
        for (Entity e : entities) {
            if (e instanceof PlayerMP && ((PlayerMP) e).getUsername().equals(username)) {
                break;
            }
            index++;
        }
        return index;
    }
    
    public synchronized void removePlayerMP(String username) {
        int index = 0;
        for (Entity e : entities) {
            if (e instanceof PlayerMP && ((PlayerMP) e).getUsername().equals(username)) {
                break;
            }
            index++;
        }
        this.getEntities().remove(index);
    }
	
    public synchronized void movePlayer(String username, float x, float y, boolean isMoving) {
        int index = getPlayerMPIndex(username);
        PlayerMP player = (PlayerMP)entities.get(index);
        player.setX(x);
        player.setY(y);
        player.setMoving(isMoving);
    }
    
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
		
	}
	
	public void addEntity(Entity e){
		synchronized (e) {
			entities.add(e);
		}
		
		
	
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}