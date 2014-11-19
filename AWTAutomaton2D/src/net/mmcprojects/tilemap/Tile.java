package net.mmcprojects.tilemap;

import java.awt.image.BufferedImage;

public class Tile {
	private byte tileID;
	private BufferedImage image;
	
	public Tile(String resource, byte id) {
		Tilemanager.INSTANCE.addTile(resource);
		image = Tilemanager.INSTANCE.getTile(resource);
		this.tileID = id;
	}
	
	public byte getId () {
		return this.tileID;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
}
