package net.mmcprojects.gamestate.states;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import net.mmcprojects.Automaton2D;
import net.mmcprojects.Main;
import net.mmcprojects.gamestate.GameState;
import net.mmcprojects.gamestate.GameStateManager;
import net.mmcprojects.tilemap.Tile;
import net.mmcprojects.tilemap.Tilemap;
import net.mmcprojects.tilemap.Tileset;

public class LevelState extends GameState {

	private Tilemap tilemap;
	private Tilemap tilemap2;
	private Button goToEditor;
	public LevelState(GameStateManager gameStateManager, JPanel panel) {
		super(gameStateManager, panel);
	}

	@Override
	public void init() {
		ActionListener switchToEditor = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Automaton2D.getGameStateManager().switchGameState(GameStateManager.EDITOR_STATE);
			}
		};
		goToEditor = new Button("Editor... ");
		goToEditor.addActionListener(switchToEditor);
		panel.add(goToEditor);
		
		Tileset tileset = new Tileset();
		tileset.addTile(new Tile("red.png", (byte) 1));
		tileset.addTile(new Tile("blue.png", (byte) 2));
		tileset.addTile(new Tile("alpha.png", (byte) 3));
		
		this.tilemap = new Tilemap(64, new byte[][] {
				{1,2,1,2},
				{2,1,2,1}
		}, tileset);
		
		this.tilemap2 = new Tilemap(64, new byte[][] {
				{3,3,3,3},
				{3,3,3,3}
		}, tileset);
		this.isDisposed = false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		if (!this.isDisposed) {
			this.tilemap.draw(g);
			this.tilemap2.draw(g);
			goToEditor.setLocation(Main.window.getWidth() - 100, 0);
			goToEditor.setSize(100, 40);
		} else {
			g.setColor(Color.black);
			g.fillRect(0, 0, Main.window.getContentPane().getWidth(), Main.window.getContentPane().getHeight());
		}
	}

	@Override
	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int key) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		this.isDisposed = true;
		//de-referenced the allocated objects
		panel.remove(goToEditor);
		tilemap = null;
		tilemap2 = null;
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