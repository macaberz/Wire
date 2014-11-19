package net.mmcprojects.tilemap;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public enum Tilemanager {
	INSTANCE;
	private Map<String, BufferedImage> resources = new HashMap<String, BufferedImage>(255);

	public void addTile(String resource) {
		if (!resources.containsKey(resource) && resources.size() < 255) {
			try {
				FileInputStream fis = new FileInputStream("resources/"+resource);
				BufferedImage image = ImageIO.read(fis);
				fis.close();
				resources.put(resource, image);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(String.format("Either the tile is already loaded or the tileset is full! Tileset size: %d", resources.size()));
		}
	}
	
	public BufferedImage getTile(String resource) {
		if (resources.containsKey(resource)) {
			return resources.get(resource);
		} else {
			addTile(resource);
			return resources.get(resource);
		}
	}
}
