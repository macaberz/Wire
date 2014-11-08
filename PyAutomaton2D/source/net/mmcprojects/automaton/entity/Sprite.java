package net.mmcprojects.automaton.entity;

import net.mmcprojects.automaton.IUpdateRender;
import net.mmcprojects.automaton.components.TextureComponent;
import net.mmcprojects.automaton.components.TransformComponent;
import net.mmcprojects.automaton.textures.TextureManager;
import net.mmcprojects.automaton.textures.TextureRegion;

public class Sprite extends Entity implements IUpdateRender {

	private TextureComponent tex;
	public TransformComponent transform;
	
	public Sprite(String tag, String textureAtlasFilename, TextureRegion textureAtlasRegion, TransformComponent transform) {
		super(tag);
		tex = new TextureComponent(TextureManager.getTextureAtlas(textureAtlasFilename), textureAtlasRegion);
		this.transform = transform;
	}

	@Override
	public void update(int delta) {
		
	}

	@Override
	public void render() {
		//TODO: Somehow make it so that Sprites that use the same Atlas are batched.
		this.tex.render(this.transform);
	}
}