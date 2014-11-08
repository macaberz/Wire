package net.mmcprojects.automaton;

import java.util.ArrayList;

import org.python.core.PyFloat;
import org.python.core.PyInteger;

public class PyTest {
	
	/**
	 * This class is designed to expirement with python -> java and java -> python interaction.
	 */
	
	private ArrayList<Integer> myList = new ArrayList<>();
	
	public PyTest() {
		
	}
	
	public void sayHello() {
		System.out.println("PyTest says hello to python!");
	}
	
	public void addSayHello(float a, float b) {
		float res = a + b;
		System.out.println(String.format("PyTest has received two floats from Python and put them together: %f", res));
	}
	
	public void addToList(int i) {
		myList.add(i);
	}
	
	public void printListContents() {
		for (int i: myList) {
			System.out.println("item: " + i);
		}
	}
	
}
