package net.mmcprojects.gamestate.states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import net.mmcprojects.gamestate.GameState;
import net.mmcprojects.gamestate.GameStateManager;

public class MainMenuState extends GameState {
	
	public MainMenuState(GameStateManager gameStateManager, JPanel panel) {
		super(gameStateManager, panel);
	}

	@Override
	public void init() {
		isDisposed = false;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		if (!isDisposed) {
			
		} else {
			
		}
	}

	@Override
	public void keyPressed(int key) {
		
	}

	@Override
	public void keyReleased(int key) {
		
	}

	@Override
	public void dispose() {
		isDisposed = true;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}
}