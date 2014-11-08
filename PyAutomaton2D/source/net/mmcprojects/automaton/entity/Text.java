package net.mmcprojects.automaton.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import net.mmcprojects.automaton.components.TransformComponent;
import net.mmcprojects.automaton.textures.FontManager;

public class Text extends Entity {

	private TrueTypeFont ttfont;
	private TransformComponent transform;
	private String text;
	
	public Text(String tag, String text, String fontName, TransformComponent transform) {
		super(tag);
		this.text = text;
		this.ttfont = FontManager.getFont(fontName);
		this.transform = transform;
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		this.ttfont.drawString(transform.getPosition().getX(), transform.getPosition().getY(), this.text, Color.white);
	}
}