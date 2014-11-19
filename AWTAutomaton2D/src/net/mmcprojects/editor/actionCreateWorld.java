package net.mmcprojects.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.mmcprojects.Main;

public class actionCreateWorld implements ActionListener {
	private String[] returnVals;
	private boolean hasPerformedAction = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel dialog = new JPanel();
		String[] tileRes = {"16", "32", "48", "64", "128", "256"};
		returnVals = new String[4];
		JComboBox<String> wTileRes = new JComboBox<String>();
		JComboBox<String> hTileRes = new JComboBox<String>();
		JComboBox<String> tileSize = new JComboBox<String>();
		JLabel lblWorldName = new JLabel("Give world tile width, height, and name.");
		for (String item : tileRes) {
			wTileRes.addItem(item);
			hTileRes.addItem(item);
			tileSize.addItem(item);
		}
		dialog.add(wTileRes);
		dialog.add(hTileRes);
		dialog.add(tileSize);
		dialog.add(lblWorldName);
		returnVals[0] = JOptionPane.showInputDialog(Main.window.getContentPane(), dialog, "Create World...", JOptionPane.QUESTION_MESSAGE);
		returnVals[1] = (String) wTileRes.getSelectedItem();
		returnVals[2] = (String) hTileRes.getSelectedItem();
		returnVals[3] = (String) tileSize.getSelectedItem();
		hasPerformedAction = true;
	}
	
	public String[] getValue() {
		hasPerformedAction = false; //reset so the event may be triggered again.
		return this.returnVals;
	}
	
	public boolean hasPerformedAction() {
		return this.hasPerformedAction;
	}
}
