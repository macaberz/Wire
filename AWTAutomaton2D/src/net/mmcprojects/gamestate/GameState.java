package net.mmcprojects.gamestate;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public abstract class GameState {
	protected GameStateManager gameStateManager;
	protected JPanel panel;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);
	public abstract void mouseClicked(MouseEvent me);
	public abstract void mousePressed(MouseEvent me);
	public abstract void mouseReleased(MouseEvent me);
	public abstract void dispose();
	protected boolean isDisposed = false;
	
	public GameState(GameStateManager gameStateManager, JPanel panel) {
		this.gameStateManager = gameStateManager;
		this.panel = panel;
	}
}
