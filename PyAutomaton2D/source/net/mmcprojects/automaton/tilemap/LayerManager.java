package net.mmcprojects.automaton.tilemap;

import java.util.ArrayList;


public final class LayerManager {
	private ArrayList<Layer> layers;
	private static ArrayList<Layer> layerAddQueue = new ArrayList<Layer>(4);
	private static ArrayList<Layer> layerRemoveQueue = new ArrayList<Layer>(4);
	private static LayerManager instance = null;
	
	public LayerManager() {
		
	}
	
	public synchronized static LayerManager getInstance() {
		if (instance == null) {
			instance = new LayerManager();
		}
		return instance;
	}

	public void update(int delta) {
		// TODO update all the layers
		//Dynamically allocate/de-allocate layers
		if (layerRemoveQueue.size() != 0) {
			layers.removeAll(layerRemoveQueue);
			layerRemoveQueue.clear();
		}
		
		if (layerAddQueue.size() != 0) {
			layers.addAll(layerAddQueue);
			layerAddQueue.clear();
		}
		
		//render all the layers.
		for (Layer l : layers) {
			l.update(delta); //update also renders
		}
		
	}

	
	public static void addLayer(Layer newLayer) {
		layerAddQueue.add(newLayer);
	}
	
	public static void removeLayer(Layer layer) {
		layerRemoveQueue.add(layer);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
}
