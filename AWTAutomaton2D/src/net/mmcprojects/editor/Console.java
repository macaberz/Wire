package net.mmcprojects.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.mmcprojects.Main;

public class Console {

	private List<String> messages;
	private List<SEVERITY> severityLevels;
	private int maxMessages;
	
	private int messageCount = 0;
	private Color oldColor;
	private Color consoleColor = new Color(0.1f, 0.1f, 0.4f, 0.2f);
	private Font consoleFont;
	private int consoleWidth = Main.window.getContentPane().getWidth();
	private int paneHeight = Main.window.getContentPane().getHeight();
	private float consoleFontSize = 14f;
	private int consoleHeight;
	
	public enum SEVERITY {
		INFO,
		DEBUG,
		WARNING,
		ERROR,
		SEVERE_ERROR,
		FATAL_ERROR;
	}
	
	public Console() {
		this(16);
	}
	
	public Console(int maxMessages) {
		this.maxMessages = maxMessages;
		this.messages = new ArrayList<String>(this.maxMessages);
		this.severityLevels = new ArrayList<SEVERITY>(this.maxMessages);
		this.consoleHeight = ((int)this.consoleFontSize * this.maxMessages) + 20;
		try {
			FileInputStream fis = new FileInputStream("fonts/ERASLGHT.ttf");
			this.consoleFont = Font.createFont(Font.TRUETYPE_FONT, fis);
			this.consoleFont = this.consoleFont.deriveFont(this.consoleFontSize);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void log(String msg, SEVERITY s) {
		//messages are stored in a oldest first fashion. Messages(0) = oldest, Messages(32) = latest message.
		if (messageCount >= this.maxMessages) {
			Collections.rotate(messages, -1);
			Collections.rotate(severityLevels, -1);
			this.messageCount -= 1;
		}
		this.messages.add(this.messageCount, ">>>" + msg);
		this.severityLevels.add(this.messageCount, s);
		this.messageCount++;
	}
	
	public void draw(Graphics2D g) {
		g.setFont(this.consoleFont);
		g.setColor(consoleColor);
		g.fillRect(0, paneHeight-this.consoleHeight, consoleWidth, consoleHeight);
		if (this.messageCount < 10) {
			for (int i=0; i<messageCount; i++) {
				drawColorCodedStrings(i, g);
			}
		} else {
			for (int i=0; i<10; i++) {
				drawColorCodedStrings(i, g);
			}
		}
	}
	
	private void drawColorCodedStrings(int index, Graphics2D g) {
		oldColor = g.getColor();
		switch (severityLevels.get(index)) {
		case INFO:
			g.setColor(Color.white);
			break;
		case DEBUG:
			g.setColor(Color.blue);
			break;
		case WARNING:
			g.setColor(Color.yellow);
			break;
		case ERROR:
			g.setColor(Color.red);
			break;
		case SEVERE_ERROR:
			g.setColor(Color.red);
			break;
		case FATAL_ERROR:
			g.setColor(Color.red);
			break;
		}
		g.drawString(messages.get(index), 0, Main.window.getContentPane().getHeight()-(index*this.consoleFontSize));
		if (oldColor != null)
			g.setColor(oldColor);
	}
}
