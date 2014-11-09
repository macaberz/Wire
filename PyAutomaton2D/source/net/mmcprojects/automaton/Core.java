package net.mmcprojects.automaton;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_REPLACE;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_ENV;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_ENV_MODE;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexEnvi;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.mmcprojects.automaton.entity.Entity;
import net.mmcprojects.automaton.textures.TextureManager;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.python.core.PyObject;
import org.python.core.PyString;


/**
 * @author Matthias Calis
 * @version 0.1a
 * <h1>The Core Class</b>
 * <b>Core.java</b> will assume the following folder structure for a new or existing game project:
*-->assets
*----> [audio]
*----> [scripts]
*------> setup.py
*----> [textures]
* The only permitted deviation from this is the top level folder. If it is <b>not</b> named "assets" one must
* specify the top-level folder name by starting Core.java through the commandline as such:
* <i>java Core.jar {setupScriptFileName.py} {topLevelFolderPath}</i>
*/
public final class Core {
	private static Logger CoreLogger = Logger.getLogger(Core.class.getName());
	private static Automaton2D engineInstance;
	private static Game gameInstance;
	public static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
	public static ScriptEngine python = scriptEngineManager.getEngineByName("jython");
	public static CoreState coreState;
	public static Invocable pythonInvoke = (Invocable) python;
	
	public static String assetFolder, audioFolder, scriptsFolder, texturesFolder;
	
	public enum CoreState {
		STARTED,
		SKIP_SETUP_SCRIPT,
		STOPPED;
	}
	
	public static void main(String[] args) {		
		//Obligatory logger
		CoreLogger.log(Level.INFO, "Core.java has started.");
		Core.coreState = CoreState.STARTED;
		
		//The values we'll be trying to get from the python setup script
		int pyWidth = 640;
		int pyHeight = 480;
		int pyTargetFrameRate = 60;
		String pyTitle = "Core Default Title";
		
		assetFolder = "assets/";
		audioFolder = assetFolder+"audio/";
		scriptsFolder = assetFolder+"scripts/";
		texturesFolder = assetFolder+"textures/";
		
		//TODO: Implement folder renaming through the commandline (see javadoc at the top)
		try {
			File setPathScript;
			String os = System.getProperty("os.name").substring(0,7);
			
			if (os.equalsIgnoreCase("windows")) {
				setPathScript = new File(scriptsFolder+"set_path_windows.py");
			}
			else {
				setPathScript = new File(scriptsFolder+"set_path_mac.py");
			}
			
			Reader setPathScriptReader = new FileReader(setPathScript);
			python.eval(setPathScriptReader);
			setPathScriptReader.close();
			
			File setupScript = new File(scriptsFolder+"setup.py"); //or get args[1]
			Reader setupScriptReader = new FileReader (setupScript);
			python.eval(setupScriptReader);
			setupScriptReader.close();
		} catch (IOException ioex) {
			CoreLogger.log(Level.WARNING, String.format(
					"Couldn't access the script %s. Please make sure it is located in the specified directory. Automaton will proceed loading using default values.",
					"setup.py"));
			Core.coreState = CoreState.SKIP_SETUP_SCRIPT;
		} catch (ScriptException se) {
			CoreLogger.log(Level.SEVERE, String.format(
					"The script %s contains errors. Please make sure the script uses correct syntax and isn't using illegal operations.",
					"setup.py"));
			CoreLogger.log(Level.SEVERE, "Core.java has encountered a severe error and has stopped.");
			Core.coreState = CoreState.STOPPED;
			se.printStackTrace();
			System.exit(1);
		}
		
		if (Core.coreState != CoreState.SKIP_SETUP_SCRIPT) {
			try {
				pythonInvoke.invokeFunction("init");
				pyWidth = (int) python.get("width");
				pyHeight = (int) python.get("height");
				pyTitle = (String) python.get("title");
				pyTargetFrameRate = (int) python.get("targetFrameRate");
			} catch (NoSuchMethodException nsme) {
				CoreLogger.log(Level.WARNING, "The method init() doesn't exist in the current setup script. Will try to continue with default values...");
				pyWidth = 640;
				pyHeight = 480;
				pyTargetFrameRate = 60;
				pyTitle = "Core Default Title";
			} catch (ScriptException se) {
				CoreLogger.log(Level.SEVERE, "A script exception occured in the setup script, see stacktrace. Stopping NOW.");
				se.printStackTrace();
				System.exit(1);
			}
			
		}
		
		DisplayMode displayMode = new DisplayMode(pyWidth, pyHeight);
		
		try {
			Display.setDisplayMode(displayMode);
			Display.create();
			Display.setTitle(pyTitle);
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			CoreLogger.log(Level.SEVERE, String.format(
					"Unable to setup the display. Make sure the classpath for the native part of the LWJGL library is set to the host OS. See stacktrace:\n%s",
					e.getStackTrace().toString()));
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,Display.getWidth(),Display.getHeight(),0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		glDisable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
		glClearColor(0, 0, 0, 0);
		
		
		gameInstance = new Game();
		engineInstance = new Automaton2D(gameInstance, pyTargetFrameRate);
		Automaton2D.getDelta();
		engineInstance.engineLoop();
		engineInstance.cleanup();
		Core.cleanup();
	}
	
	public static String getAssetFolder() {
		return Core.assetFolder;
	}
	
	public static String getAudioFolder() {
		return Core.audioFolder;
	}
	
	public static String getScriptFolder() {
		return Core.scriptsFolder;
	}
	
	public static String getTextureFolder() {
		return Core.texturesFolder;
	}
	
	private static void cleanup() {
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
		System.exit(0);
	}
	
	public static String getWorkingDirectory() {
		return System.getProperty("user.dir");
	}
	
}