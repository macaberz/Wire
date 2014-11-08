package net.mmcprojects.automaton.textures;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import net.mmcprojects.automaton.Game;

import org.lwjgl.BufferUtils;

public class TextureRegion {
	private int x,y,width,height,textureWidth,textureHeight;
	private int TextureCoordinatesHandle, TextureVertexHandle;
	private float xPercent, yPercent, hPercent, wPercent;
	private boolean flipped = false;
	
	public TextureRegion(int x, int y, int width, int height, TextureAtlas atlas) {
		this(x,y,width,height,atlas,false);
	}
	
	public TextureRegion(int x, int y, int width, int height, TextureAtlas atlas, boolean flipped) {
		this(x,y,width,height,atlas.getTextureWidth(),atlas.getTextureHeight(),flipped);
	}
	
	public TextureRegion(int x, int y, int width, int height, int texturewidth, int textureheight) {
		this(x,y,width,height,texturewidth,textureheight,false);
	}
	
	public TextureRegion(int x, int y, int width, int height, int texturewidth, int textureheight, boolean flipped) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		TextureCoordinatesHandle = glGenBuffers();
		TextureVertexHandle = glGenBuffers();
		this.textureWidth = texturewidth;
		this.textureHeight = textureheight;
		this.flipped = flipped;
		prepareBuffers();
	}
	
	public TextureRegion(int[] rect, TextureAtlas atlas) {
		this(rect[0], rect[1], rect[2], rect[3], atlas);
	}
	
	private void prepareBuffers() {
		FloatBuffer VertexData = BufferUtils.createFloatBuffer(8); 
		VertexData.put(new float[] {
			0, 0,
			0, height,
			width, height,
			width, 0
		});
		VertexData.flip();
		glBindBuffer(GL_ARRAY_BUFFER, TextureVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, VertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		Game.addVertexBufferObjectHandle(TextureVertexHandle);
		
		FloatBuffer TextureCoordinatesData = BufferUtils.createFloatBuffer(8);
		
		float _y = (float) y;
		float _x = (float) x;
		float _w = (float) width;
		float _h = (float) height;
		float _texWidth = (float) this.textureWidth;
		float _texHeight = (float) this.textureHeight;
		
		xPercent = _x / _texWidth;
		yPercent = _y / _texHeight;
		wPercent = (_x + _w) / _texWidth;
		hPercent = (_y + _h) / _texHeight;
		
		if (!this.flipped) {
			TextureCoordinatesData.put(new float[] {
					xPercent, yPercent,
					xPercent, hPercent,
					wPercent, hPercent,
					wPercent, yPercent
			});
		} else {
			TextureCoordinatesData.put(new float[] {
					wPercent, yPercent,
					wPercent, hPercent,
					xPercent, hPercent,
					xPercent, yPercent
			});	
		}
		
		TextureCoordinatesData.flip();
		glBindBuffer(GL_ARRAY_BUFFER,TextureCoordinatesHandle);
		glBufferData(GL_ARRAY_BUFFER, TextureCoordinatesData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		Game.addVertexBufferObjectHandle(TextureCoordinatesHandle);
	}
	
	public int getTextureHandle() {
		return this.TextureCoordinatesHandle;
	} 
	
	public int getVertexHandle() {
		return this.TextureVertexHandle;
	}

	public float getxPercent() {
		return xPercent;
	}

	public float getyPercent() {
		return yPercent;
	}

	public float gethPercent() {
		return hPercent;
	}

	public float getwPercent() {
		return wPercent;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}