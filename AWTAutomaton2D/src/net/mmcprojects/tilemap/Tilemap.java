package net.mmcprojects.tilemap;

import java.awt.*;

public class Tilemap {
	private byte[][] map;
	private int tilesize;
	private Tileset tileset;
	
	public Tilemap(int tilesize, byte[][] map, Tileset set) {
		this.tilesize = tilesize;
		this.map = map;
		this.tileset = set;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		for (int i=0; i<map.length; i++) {
			for (int j=0; j<map[i].length; j++) {
				if (map[i][j] != 0)
					g.drawImage(this.tileset.getImageById(map[i][j]), j*tilesize, i*tilesize, null);
			}
		}
	}
}
