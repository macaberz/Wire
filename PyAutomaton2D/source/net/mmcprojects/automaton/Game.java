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

/**
 * 
 * @author Matthias
 * @category Core
 * The Game class manages the game's update and render loop.
 */
public class Game implements ICleanup {
	private static Logger GameLogger = Logger.getLogger(Game.class.getName());
	private static ArrayList<Integer> vertexBufferObjectHandles = new ArrayList<Integer>(1024);
	private ArrayList<Entity> entities = new ArrayList<>(); 
	private static ArrayList<Entity> entityAddQueue = new ArrayList<Entity>(8);
	private static ArrayList<Entity> entityRemoveQueue = new ArrayList<Entity>(8);
	
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
		//Dynamically allocate/de-allocate entities
		if (entityRemoveQueue.size() != 0) {
			entities.removeAll(entityRemoveQueue);
			entityRemoveQueue.clear();
		}
		
		if (entityAddQueue.size() != 0) {
			entities.addAll(entityAddQueue);
			entityAddQueue.clear();
		}
		
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
		
		for(Entity e: entities) {
			e.update(delta);
			e.render();
		}
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
	
	public synchronized static void addEntity(Entity e) {
		entityAddQueue.add(e);
	}
	
	public synchronized static void removeEntity(Entity e) {
		entityRemoveQueue.add(e);
	}
}