package net.mmcprojects.automaton;

/**
 * 
 * @author Matthias
 * @category Core
 * The IUpdateRender interface contracts the implementing class to implement an update() and render() function.
 * All classes that implement this interface will be run during the main engine loop.
 */
public interface IUpdateRender {
	public void update(int delta);
	public void render();
}
