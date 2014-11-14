from net.mmcprojects.automaton import Game
from net.mmcprojects.automaton import Core
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
import random

#GLOBAL variables, do NOT remove these.
global width, height, title, targetFrameRate, scriptsFolder, texturesFolder, soundsFolder

#Add additional global variables below this line
global sprites
sprites = []
global text

def init():
	#DO NOT change the contents of this method.
	#only adjust the values assigned to the variables
	#to your liking.
	global width, height, title, targetFrameRate, scriptsFolder, texturesFolder, soundsFolder
	width = 800
	height = 600
	title = "Python Rocks!"
	targetFrameRate = 60
	cwd = Core.getWorkingDirectory().replace("\\", "/")
	scriptsFolder = cwd+"/"+Core.getScriptFolder()
	texturesFolder = cwd+"/"+Core.getTextureFolder()
	soundsFolder = cwd+"/"+Core.getAudioFolder()

def onGameStart():
	global sprites
	global text
	global scriptsFolder
	global texturesFolder
	global soundsFolder
	global width, height

	textPosition = Vector2f(100.0, 100.0)
	textScale = Vector2f(1.0, 1.0)
	textTransform = TransformComponent(textPosition, textScale, 0)
	text = Text("texttag", "Hallo", "Times New Roman", textTransform)
	tp = TextureUnpacker(texturesFolder+"output.xml")
	for i in range(0,25):
 		sprites.append(tp.getNewSprite("HUD_corner_bottom_left.png"))
 		sprites[i].transform.setPosition(random.uniform(1, 400), random.uniform(1, 400))
 		Game.addEntity(sprites[i])
 	Game.addEntity(text)

def update():
	global sprites
#	global text

	tx = 0.2
	ty = 0.2

	#if (Input.getKey(Keyboard.KEY_D)):
	#	tx = 4.0
	#elif (Input.getKey(Keyboard.KEY_A)):
	#	tx = -4.0
	#elif (Input.getKey(Keyboard.KEY_W)):
	#	ty = -4.0
	#elif (Input.getKey(Keyboard.KEY_S)): 
	#	ty = 4.0
	for spr in sprites:
		spr.transform.translate(tx, ty);