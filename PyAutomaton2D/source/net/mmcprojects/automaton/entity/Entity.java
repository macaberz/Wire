package net.mmcprojects.automaton.entity;

import net.mmcprojects.automaton.IUpdateRender;

public abstract class Entity implements IUpdateRender {
	private int entityID;
	private static int latestID = 0;
	private String tag;
	
	public Entity(String tag) {
		this.entityID = latestID;
		this.tag = tag;
		latestID++; 
	}
	
	//Can't be made protected as Python then won't be able to access these methods (as they 
	//will remain protected within the lowest java subclass)
	public String getTag() {
		return this.tag;
	}
	
	//Can't be made protected as Python then won't be able to access these methods (as they 
	//will remain protected within the lowest java subclass)
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return String.format("Entity #%d", this.entityID);
	}
	
	public int getId() {
		return this.entityID;
	}
}
