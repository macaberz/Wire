package net.mmcprojects.automaton.textures;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class TextureManager {
	private static Logger TextureManagerLogger = Logger.getLogger(TextureManager.class.getName());
	private static TextureManager instance = null;
	private static Map<String, TextureAtlas> textures = new HashMap<String, TextureAtlas>();
	
	public synchronized static TextureManager getInstance() {
		if (instance == null) {
			instance = new TextureManager();
			addTextureAtlas("default.png");
		}
		return instance;
	}
	
	public static void addTextureAtlas(String textureName) {
		if (!textures.containsKey(textureName)) {
			textures.put(textureName, new TextureAtlas(textureName));
		} else {
			TextureManagerLogger.log(Level.INFO, String.format("TextureAtlas %s already loaded!", textureName));
		} 
	}
	
	public static TextureAtlas getTextureAtlas(String textureName) {
		TextureAtlas tex = textures.get(textureName);
		if (tex != null) {
			return tex;
		} else {
			TextureManagerLogger.log(Level.WARNING, String.format("Couldn't get the texture called %s", textureName));
			//TODO: Try to avoid returning null
			return null;
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
} 