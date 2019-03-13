package dev.igpe.theamazingame.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.igpe.theamazingame.Handler;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items;
	
	public ItemManager(Handler handler){
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	public void tick(){
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			Item i = it.next();
			i.tick();
			if(i.isPickedUp() )
				//lo mette nell'inventario che e' una lista
				it.remove();
		}
	}
	
	public void render(Graphics g){
		for(Item i : items)
			i.render(g);
	}
	
	//Metodo per l'aggiunta di item a terra
	public void addItem(Item i){
		i.setHandler(handler);
		items.add(i);
	}
	
	// Get e Set Vari

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
