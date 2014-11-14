package net.mmcprojects.automaton;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;

import javax.script.ScriptException;

import org.lwjgl.opengl.GL11;

import net.mmcprojects.automaton.entity.Entity;
import net.mmcprojects.automaton.textures.FontManager;
import net.mmcprojects.automaton.textures.TextureManager;
import net.mmcprojects.automaton.tilemap.Layer;
import net.mmcprojects.automaton.tilemap.LayerManager;

/**
 * 
 * @author Matthias
 * @category Core
 * The Game class manages the game's update and render loop.
 */
public class Game implements ICleanup {
	private static Logger GameLogger = Logger.getLogger(Game.class.getName());
	private static ArrayList<Integer> vertexBufferObjectHandles = new ArrayList<Integer>(1024);
	
	private LayerManager layerManager = LayerManager.getInstance();
	
	private static Input input;
	private GameState gameState = GameState.GAME_NOT_YET_STARTED;
	
	private static TextureManager textureManager;
	private static FontManager fontManager;
	
	
	public Game() {
		textureManager = TextureManager.getInstance();
		fontManager = FontManager.getInstance();
		try {
			Core.pythonInvoke.invokeFunction("onGameStart");
		} catch (NoSuchMethodException nsme) {
			GameLogger.log(Level.WARNING, "Couldn't invoke the python atGameStart() method. Skipping it...");
		} catch (ScriptException se) {
			GameLogger.log(Level.SEVERE, "A scripting error occured while executing python's atGameStart() function! See stacktrace for details. Stopping NOW.");
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	public enum GameState {
		GAME_NOT_YET_STARTED,
		IN_GAME,
		IN_MENU,
		LOADING,
		SAVING;
	}
	
	public void update(int delta) {

		
		//Clear the screen
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		Input.update();
		
		try {
			Core.pythonInvoke.invokeFunction("update");
		} catch (NoSuchMethodException nsme) {
			GameLogger.log(Level.WARNING, "Couldn't invoke the python update() method. Skipping it...");
		} catch (ScriptException se) {
			GameLogger.log(Level.SEVERE, "A scripting error occured while executing python's update() function! See stacktrace for details. Stopping NOW.");
			se.printStackTrace();
			System.exit(1);
		}
		
		layerManager.update(delta);
	}
	
	public GameState getGameState() {
		return this.gameState;
	}
	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public static Input getInput() {
		return input;
	}
	
	@Override
	public void cleanup() {
		removeAllVertexBufferObjects();
	}
	
	public static void addVertexBufferObjectHandle(int handle) {
		vertexBufferObjectHandles.add(handle);
	}
	
	public static void removeVertexBufferObjectHandle(int handle) {
		vertexBufferObjectHandles.remove((Object) handle);
	}
	
	private static void removeAllVertexBufferObjects() {
		for (int handle : vertexBufferObjectHandles) {
			glDeleteBuffers(handle);
		}
	}
}