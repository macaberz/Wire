package net.mmcprojects.tilemap;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Tileset {
	
	private Map<Byte, Tile> tileset = new HashMap<Byte, Tile>();
	
	public Tileset() {
	}
	
	public void addTile(Tile tile) {
		tileset.put(tile.getId(), tile);
	}
	
	public BufferedImage getImageById(byte id) {
		return tileset.get(id).getImage();
	}
}
