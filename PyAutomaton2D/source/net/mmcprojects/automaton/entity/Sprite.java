package net.mmcprojects.automaton.entity;

import net.mmcprojects.automaton.IUpdateRender;
import net.mmcprojects.automaton.components.TextureComponent;
import net.mmcprojects.automaton.components.TransformComponent;
import net.mmcprojects.automaton.textures.TextureAtlas;
import net.mmcprojects.automaton.textures.TextureManager;
import net.mmcprojects.automaton.textures.TextureRegion;

public class Sprite extends Entity implements IUpdateRender {

	public TextureComponent texture;
	public TransformComponent transform;
	
	public Sprite(String tag, String textureAtlasFilename, TextureRegion textureAtlasRegion, TransformComponent transform) {
		super(tag);
		this.texture = new TextureComponent(TextureManager.getTextureAtlas(textureAtlasFilename), textureAtlasRegion);
		this.transform = transform;
	}
	
	public Sprite(String tag, TextureAtlas atlas, TextureRegion region, TransformComponent transform) {
		super(tag);
		this.texture = new TextureComponent(atlas, region);
		this.transform = transform;
	}

	@Override
	public void update(int delta) {
		
	}

	@Override
	public void render() {
		//TODO: Somehow make it so that Sprites that use the same Atlas are batched.
		this.texture.render(this.transform);
	}
}