import sys.path as path
path.append("D:\Workspaces\java\PyAutomaton2D\\bin")
path.append("D:\Workspaces\java\PyAutomaton2D\\assets\scripts")

from net.mmcprojects.automaton import Game
from net.mmcprojects.automaton.entity import Entity
from net.mmcprojects.automaton.entity import Sprite
from net.mmcprojects.automaton.entity import Text
from net.mmcprojects.automaton.textures import TextureManager
from net.mmcprojects.automaton.textures import TextureRegion
from net.mmcprojects.automaton.components import TransformComponent
from net.mmcprojects.automaton.math import Vector2f
from net.mmcprojects.automaton import Input
from org.lwjgl.input import Keyboard;

from Assets import TextureUnpacker

#GLOBAL variables, do NOT remove these.
global width
global height
global title
global targetFrameRate

#Add additional global variables below this line
global sprite
global text

def init():
	#DO NOT change the contents of this method.
	#only adjust the values assigned to the variables
	#to your liking.
	global width
	global height
	global title
	global targetFrameRate
	width = 800
	height = 600
	title = "Python Rocks!"
	targetFrameRate = 60


def onGameStart():
	global sprite
	global text

	textPosition = Vector2f(100.0, 100.0)
	textScale = Vector2f(1.0, 1.0)
	textTransform = TransformComponent(textPosition, textScale, 0)
	text = Text("texttag", "Hallo", "Times New Roman", textTransform)

	tp = TextureUnpacker("D:\Workspaces\java\PyAutomaton2D\\assets\\textures\output.xml")
 	sprite = tp.getSprite("HUD_corner_bottom_left.png")
 	Game.addEntity(sprite)
 	Game.addEntity(text)

def update():
	global sprite
	global text
	if (Input.getKey(Keyboard.KEY_D)):
		sprite.transform.translate(0.5, 0.0)
	elif (Input.getKey(Keyboard.KEY_A)):
		sprite.transform.translate(-0.5, 0.0)
	elif (Input.getKey(Keyboard.KEY_W)):
		sprite.transform.translate(0.0, -0.5)
	elif (Input.getKey(Keyboard.KEY_S)):
		sprite.transform.translate(0.0, 0.5)
	elif (Input.getKey(Keyboard.KEY_P)):
		print("Hallo Rens")
		
		
		
#DIT STOND ER EERST NIET IN!!!