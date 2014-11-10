package net.mmcprojects.automaton.components;

import net.mmcprojects.automaton.math.Vector2f;

public class TransformComponent {
	private Vector2f position;
	private float angle = 0.0f;
	private Vector2f scale;
	
	public TransformComponent(Vector2f position, Vector2f scale, float angle) {
		this.position = position;
		this.angle = angle;
		this.scale = scale;
	}
	
	public Vector2f getPosition() {
		return this.position;
	}
	
	public Vector2f getScale() {
		return this.scale;
	}
	
	public float getAngle() {
		return this.angle;
	}
	
	public void translate(float x, float y) {
		this.position.setX(this.position.getX()+x);
		this.position.setY(this.position.getY()+y);
	}
	
	public void translate(Vector2f translation) {
		this.position.add(translation);
	}
	
	//TODO: implement rotation
	public void rotate(float angle) {
		
	}
	
	public void scale(float scalar) {
		//this.scale = this.scale.mul(scalar); NONE of the Vector2f ops are destructive. All return new values.
		this.scale.mul(scalar);
	}
	
	public void scale (Vector2f scalar) {
		this.scale.mul(scalar);
	}
	
	public void set(TransformComponent transform) {
		this.position = transform.getPosition();
		this.scale = transform.getScale();
		this.angle = transform.getAngle();
	}
	
	public TransformComponent get() {
		return this;
	}
	
	public void setPosition(Vector2f position) {
		this.position.set(position);
	}
	
	public void setPosition(float x, float y) {
		this.position.set(x, y);
	}
}
