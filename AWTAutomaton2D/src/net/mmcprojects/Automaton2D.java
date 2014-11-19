package net.mmcprojects;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import net.mmcprojects.gamestate.GameStateManager;

public class Automaton2D extends JPanel implements Runnable, KeyListener, MouseListener{
	private static final long serialVersionUID = 4946396475225194473L;
	
	// dimensions
	private int width;
	private int height;
	
	//game thread
	private Thread thread;
	private boolean running;
	private int targetFPS;
	private long targetTime;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	//game state manager
	private static GameStateManager gameStateManager;

	public Automaton2D() {
		this(640, 480, 60);
	}
	
	public Automaton2D(int width, int height, int targetFPS) {
		super();
		this.targetFPS = targetFPS;
		this.width = width;
		this.height = height;
		this.targetTime = 1000 / this.targetFPS;
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		addKeyListener(this);
		addMouseListener(this);
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	private void init() {
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		gameStateManager = new GameStateManager(this);
		this.running = true;
	}

	@Override
	public void run() {
		init();
		long start, elapsed, wait;
		
		while (running) {
			start = System.nanoTime();
			
			update();
			draw();
			Graphics g2 = getGraphics();
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
			
			elapsed = (System.nanoTime() - start);
			wait = targetTime - elapsed / 1000000;
			
			try {
				if (wait < 0)
					wait = 1;
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		gameStateManager.update();
	}
	
	private void draw() {
		gameStateManager.draw(g);
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		gameStateManager.keyPressed(key.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent key) {
		gameStateManager.keyReleased(key.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent key) {
		
	}
	
	public static GameStateManager getGameStateManager() {
		return gameStateManager;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		gameStateManager.mouseClicked(me);
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		gameStateManager.mousePressed(me);
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		gameStateManager.mouseReleased(me);
	}
}
