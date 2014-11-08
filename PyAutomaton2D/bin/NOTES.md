The python scripts should be used to create object/entities in the world and manipulate them. It is the task of the Automaton Engine
to handle and manage these entities.

As such, the "entities" ArrayList should reside within the Java code, not within Python. Python will construct individual entities and
add them to the world (maybe approach this differently for level creation/generation).

Using the Entity's built-in tags, Python may retrieve individual entities from the entities ArrayList in order to manipulate them.

Furthermore, Java will expose several powerful functions to Python in order to ease the development of gameplay code.

Manipulating the Player's position fomr python would look as follows:

#Python

player = Game.findEntityWithTag("player")
player.setPosition(10,10);

#Java

public Entity findEntityWithTag(String s) { //apparently I can just return the class I want and python will be able to call it's Java functions
	...
	return (pyObject) Entity blah;
}

public setPostion(PyInteger a, PyInteger b) {
	...set pos
}


#### DEBUG INFO ####

PYTHON can ONLY access PUBLIC functions/methods. It can't access a protected function in a superclass.


#The "Expected 1 arg got 0" occured because Core.SayHi() wasn't declared as static. As such, SayHi() didn't belong to any instance
#that this script could refer to.

#####TEST CASE#####

#Add to bottom of Core:
	//TMP
	public static void SayHi() {
		System.out.println("JAVA says hi to PYTHON");
	}
	
	public static void SayThis(PyString s) {
		System.out.println(s.toString());
	}
	
	public static Entity getEntity() {
		return e;
	}

#Add to core under private static folder declarations:
private static Entity e;

#Add to core in Main(string args):
e = new Tile();

#Add to TILE.JAVA
public void SayHi() {
	syso("tile says hi!");
}

###PY code:

import sys.path as path
path.append("D:\Workspaces\java\PyAutomaton2D\bin")
from net.mmcprojects.automaton import Core

Core.SayHi()

#The "Expected 1 arg got 0" occured because Core.SayHi() wasn't declared as static. As such, SayHi() didn't belong to any instance
#that this script could refer to.

Core.SayThis("Hello JAVA, python here!")
e = Core.getEntity()
e.SayHi()

width = 800
height = 600
title = "Python Rocks!"

def update():
	print("Python Rocks Cause it Updates!");