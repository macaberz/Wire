package net.mmcprojects.automaton.tilemap;

import java.util.ArrayList;

import net.mmcprojects.automaton.entity.Entity;

public class Layer {
	//depth 0 is the lowest layer, n>0 is the highest.
	private ArrayList<Entity> entities = new ArrayList<>(); 
	private static ArrayList<Entity> entityAddQueue = new ArrayList<Entity>(8);
	private static ArrayList<Entity> entityRemoveQueue = new ArrayList<Entity>(8);
	private boolean render = true;
	private int depth;
	
	public Layer(int depth) {
		this.depth = depth;
	}

	public void update(int delta) {
		//Dynamically allocate/de-allocate entities
		if (entityRemoveQueue.size() != 0) {
			entities.removeAll(entityRemoveQueue);
			entityRemoveQueue.clear();
		}
		
		if (entityAddQueue.size() != 0) {
			entities.addAll(entityAddQueue);
			entityAddQueue.clear();
		}
		//Identify which entities on the layer are visible and should be rendered using the Camera.isInView(Entity e) method (not yet created).
		//Gather a list of indices for those entities and render only those in the Layer.render() method. Don't render at all when rendering is disabled.
		//Perhaps the size of this list can be pre-calculated if the width and height of the screen and of the tiles are known.
		if (this.render) {
			//TODO: Right now we're rendering EVERY entity/tile, regardless of whether they are in view or not. Needs to be fixed.
			for (Entity e: this.entities) {
				e.update(delta);
				e.render();
			}
		}
	}

	public synchronized static void addEntity(Entity e) {
		entityAddQueue.add(e);
	}
	
	public synchronized static void removeEntity(Entity e) {
		entityRemoveQueue.add(e);
	}
	
	public final int getDepth() {
		return this.depth;
	}
	
}
