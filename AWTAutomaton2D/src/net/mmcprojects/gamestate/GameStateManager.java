package net.mmcprojects.gamestate;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import net.mmcprojects.gamestate.states.EditorState;
import net.mmcprojects.gamestate.states.LevelState;

public class GameStateManager {
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int EDITOR_STATE = 0;
	public static final int IN_GAME_STATE = 1;
	public static final int MAIN_MENU_STATE = 2;
	public static final int IN_GAME_PAUSED_STATE = 3;
	public static final int LOADING_STATE = 4;
	public static final int SAVING_STATE = 5;
	private boolean allowedToDraw = true;
	
	public GameStateManager(JPanel panel) {
		gameStates = new ArrayList<GameState>();
		gameStates.add(new EditorState(this, panel));
		gameStates.add(new LevelState(this, panel));
		setGameState(EDITOR_STATE);
	}
	
	/**
	 * 
	 * @param state
	 * Sets the game state
	 */
	private void setGameState(int state) {
		this.currentState = state;
		gameStates.get(this.currentState).init();
	}
	
	public void switchGameState(int state) {
		allowedToDraw = false;
		gameStates.get(this.currentState).dispose();
		this.currentState = state;
		gameStates.get(this.currentState).init();
		allowedToDraw = true;
	}

	public void update() {
		gameStates.get(this.currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		if (allowedToDraw)
			gameStates.get(this.currentState).draw(g);
	}
	
	public void keyPressed(int key) {
		gameStates.get(this.currentState).keyPressed(key);
	}
	
	public void keyReleased(int key) {
		gameStates.get(this.currentState).keyReleased(key);
	}
	
	public void mouseClicked(MouseEvent me) {
		gameStates.get(this.currentState).mouseClicked(me);
	}
	
	public void mousePressed(MouseEvent me) {
		gameStates.get(this.currentState).mousePressed(me);
	}

	public void mouseReleased(MouseEvent me) {
		gameStates.get(this.currentState).mouseReleased(me);
	}
	
}