package net.mmcprojects.automaton.textures;

import java.io.File;
import java.io.FileInputStream;

import net.mmcprojects.automaton.Core;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public final class TextureAtlas {
	private int TextureWidth, TextureHeight;
	private String TextureName;
	private Texture Atlas;
	
	public TextureAtlas(String filename) {
		this.TextureName = filename;
		String[] fn = filename.split("\\.(?=[^\\.]+$)");
		File file = new File(Core.getTextureFolder()+fn[0]+"."+fn[1]);
		try {
			FileInputStream fis = new FileInputStream(file);
			this.Atlas = TextureLoader.getTexture(fn[1], fis);
			this.TextureWidth = this.Atlas.getImageWidth();
			this.TextureHeight = this.Atlas.getImageHeight();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTextureWidth() {
		return this.TextureWidth;
	}
	
	public int getTextureHeight() {
		return this.TextureHeight;
	}
	
	public String getTextureName() {
		return this.TextureName;
	}
	
	public void bind() {
		this.Atlas.bind();
	}
}
