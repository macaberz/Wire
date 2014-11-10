package net.mmcprojects.automaton;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * 
 * @author Matthias
 * @category Core
 * This is the main Game Engine class. It contains and manages the global engine state.
 */
public class Automaton2D implements ICleanup {
	private static Logger Automaton2DLogger = Logger.getLogger(Automaton2D.class.getName());
	
	private int targetFrameRate = 60;
	private Game game;
	private EngineState engineState = EngineState.STOPPED;
	
	static long lastFrame = 0;
	static int fps;
	static long lastFps; 

	public enum EngineState {
		AWAKE,
		ASLEEP,
		STOPPED;
	}
	
	public Automaton2D(Game game) {
		this(game, 60);
	}
	
	public Automaton2D(Game game, int targetFrameRate) {
		this.game = game;
		this.engineState = EngineState.AWAKE;
		Automaton2DLogger.log(Level.INFO, "The Automaton2D Engine has woken up.");
		lastFps = getTime();
		this.targetFrameRate = targetFrameRate;
		getDelta();
		System.gc();
	}
	
	public void engineLoop() {
		while(!Display.isCloseRequested() && this.engineState == EngineState.AWAKE) {
			game.update(getDelta());
			updateFPS();

			Display.processMessages();
			Mouse.poll();
			Keyboard.poll();

			Display.update();
			Display.sync(this.targetFrameRate);
		}
	}
	
	@Override
	public void cleanup() {
		this.game.cleanup();
	}
	
	public EngineState getEngineState() {
		return this.engineState;
	}
	
	public static int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}
	
	public static long getTime() {
		return(Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public static void updateFPS() {
		if (getTime() - lastFps > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFps += 1000;
		}
		fps++;
	}
}