package net.mmcprojects.automaton.components;

import net.mmcprojects.automaton.textures.TextureAtlas;
import net.mmcprojects.automaton.textures.TextureRegion;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glScalef;

public final class TextureComponent {
	public TextureRegion region;
	public TextureAtlas atlas;

	public TextureComponent(TextureAtlas atlas, TextureRegion region) {
		this.region = region;
		this.atlas = atlas;
	}
	
	public void render(TransformComponent transform) {
		this.atlas.bind();
		glPushMatrix();
		{
			glScalef(transform.getScale().getX(), transform.getScale().getY(), 0);
			glTranslatef(transform.getPosition().getX(), transform.getPosition().getY(), 0);
			
			glBindBuffer(GL_ARRAY_BUFFER, region.getVertexHandle());
			glVertexPointer(2, GL_FLOAT, 0, 0L);
			
			glBindBuffer(GL_ARRAY_BUFFER, region.getTextureHandle());
			glTexCoordPointer(2, GL_FLOAT, 0, 0);
			
			glEnableClientState(GL_VERTEX_ARRAY);
			glEnableClientState(GL_TEXTURE_COORD_ARRAY);
			glDrawArrays(GL_QUADS, 0, 4);
			glDisableClientState(GL_VERTEX_ARRAY);
			glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		}
		glPopMatrix();
	}
}