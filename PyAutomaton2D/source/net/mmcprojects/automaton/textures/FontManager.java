package net.mmcprojects.automaton.textures;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public final class FontManager {
	private static Logger FontManagerLogger = Logger.getLogger(FontManager.class.getName());
	private static FontManager instance = null;
	private static Map<String, TrueTypeFont> fonts = new HashMap<String, TrueTypeFont>();
	
	public synchronized static FontManager getInstance() {
		if (instance == null) {
			instance = new FontManager();
			addFont(new Font("Times New Roman", Font.BOLD, 24), true);
			addFont(new Font("Tahoma", Font.BOLD, 24), true);
		}
		return instance;
	}
	
	public static void addFont(Font font, boolean antiAliased) {
		if (!fonts.containsKey(font.getName())) {
			TrueTypeFont ttfont = new TrueTypeFont(font, antiAliased);
			fonts.put(font.getName(), ttfont);
		} else {
			FontManagerLogger.log(Level.WARNING, String.format("Fontmanager already has a font called %s"), font.getName());
		}
	}
	
	public static void addFont(String trueTypeFontResource, float fontSize, boolean antiAliased) {
		if (!fonts.containsKey(trueTypeFontResource)) {
			try {
				InputStream inputStream	= ResourceLoader.getResourceAsStream(trueTypeFontResource);
				Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
				awtFont = awtFont.deriveFont(fontSize); // set font size
				TrueTypeFont ttfont = new TrueTypeFont(awtFont, antiAliased);
				fonts.put(trueTypeFontResource, ttfont);
			} catch (Exception e) {
				FontManagerLogger.log(Level.WARNING, "Failed to load the requested font, stopping NOW.");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	public static TrueTypeFont getFont(String fontName) {
		TrueTypeFont ttfont = fonts.get(fontName);
		if (ttfont != null) {
			return ttfont;
		} else {
			FontManagerLogger.log(Level.WARNING, String.format("Couldn't get the font called %s", fontName));
			//TODO: Try to avoid returning NULL
			return null;
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
} 
