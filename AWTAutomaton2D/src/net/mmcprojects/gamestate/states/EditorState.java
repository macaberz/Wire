package net.mmcprojects.gamestate.states;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import net.mmcprojects.Automaton2D;
import net.mmcprojects.Main;
import net.mmcprojects.editor.actionCreateWorld;
import net.mmcprojects.editor.Console;
import net.mmcprojects.editor.Console.SEVERITY;
import net.mmcprojects.gamestate.GameState;
import net.mmcprojects.gamestate.GameStateManager;
import net.mmcprojects.tilemap.Tilemap;
import net.mmcprojects.tilemap.Tileset;

public class EditorState extends GameState{

	private BufferedImage editorBG;
	private Button goToLevel, createWorld;
	private JComboBox<String> tileBrush;
	private Console console;
	private actionCreateWorld actionCreateWorld;
	
	//store all the tilemaps/layers we generate. First we must create a world, this will generate the first byte array;
	//after this we can add new layers that will make tilemaps of the same size;
	private ArrayList<Tilemap> tilemaps;
	
	public EditorState(GameStateManager gameStateManager, JPanel panel) {
		super(gameStateManager, panel);
	}

	@Override
	public void init() {
		console = new Console(10);
		this.tilemaps = new ArrayList<Tilemap>();
		ActionListener switchToLevel = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Automaton2D.getGameStateManager().switchGameState(GameStateManager.IN_GAME_STATE);
			}
		};
		
		goToLevel = new Button("Level...");
		goToLevel.addActionListener(switchToLevel);
		panel.add(goToLevel);
		
		actionCreateWorld = new actionCreateWorld();
		
		createWorld = new Button("New world");
		createWorld.addActionListener(actionCreateWorld);
		panel.add(createWorld);
		
		try {
			editorBG = ImageIO.read(new FileInputStream("resources/Automaton2D.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		console.log("Automaton2D Game Engine (2014) created by Matthias Calis (Programming, Art) & Rens Kloosterhuis (Scripting).", SEVERITY.INFO);
		console.log("Initialized the Automaton2D engine in editor mode...", SEVERITY.INFO);
		this.isDisposed = false;
	}

	@Override
	public void update() {
		if (actionCreateWorld.hasPerformedAction()) {
			String[] vals = actionCreateWorld.getValue();
			console.log(String.format("World %s created with width: %s height: %s tilesize: %s", vals[0], vals[1], vals[2], vals[3]), SEVERITY.WARNING);
			
			byte[][] newmap = new byte[Integer.parseInt(vals[1])][Integer.parseInt(vals[2])];
			for (int i=0; i<Integer.parseInt(vals[1]); i++) {
				for (int j=0; j<Integer.parseInt(vals[2]); j++) {
					newmap[i][j] = 0;
				}
			}
			Tilemap t = new Tilemap(Integer.parseInt(vals[3]), newmap, new Tileset());
			this.tilemaps.add(t);
			console.log(t.toString(), SEVERITY.INFO);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (!this.isDisposed) {
			g.drawImage(editorBG, 0, 0, null);
			console.draw(g);
			goToLevel.setLocation(Main.window.getWidth() - 100, 0);
			goToLevel.setSize(100, 40);
			createWorld.setLocation(Main.window.getWidth() - 100, 40);
			createWorld.setSize(100, 40);
		} else {
			g.setColor(Color.black);
			g.fillRect(0, 0, Main.window.getContentPane().getWidth(), Main.window.getContentPane().getHeight());
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
		this.isDisposed = true;
		//de-reference all loaded variables
		panel.remove(goToLevel);
		panel.remove(createWorld);
		editorBG = null;
		goToLevel = null;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1) {
			console.log("Click!", SEVERITY.ERROR);
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1) {
			console.log("Pressed!", SEVERITY.INFO);
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}
}
