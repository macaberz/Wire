package net.mmcprojects.automaton.components;

import net.mmcprojects.automaton.Game;
import net.mmcprojects.automaton.Input;

public class InputComponent {
	private Input input;
	public InputComponent() {
		input = Game.getInput();
	}
}
