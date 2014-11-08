from xml.dom import minidom
from net.mmcprojects.automaton.entity import Sprite
from net.mmcprojects.automaton.textures import TextureRegion
from net.mmcprojects.automaton.textures import TextureManager
from net.mmcprojects.automaton.components import TransformComponent
from net.mmcprojects.automaton.math import Vector2f

class TextureUnpacker:
	def getSprite(self, spriteTag):
		spr = [s for s in self._sprites if s.getTag() == spriteTag]
		return spr[0]

	def __init__(self, textureXMLFile):
		self.spriteNames = []
		self.spriteWidth = []
		self.spriteHeight = []
		self.spriteTexCoordX = []
		self.spriteTexCoordY = []
		self._sprites = []

		self.textureXMLFile = textureXMLFile
		self.xmldoc = minidom.parse(textureXMLFile)
		lst = self.xmldoc.getElementsByTagName('TextureAtlas')
		self.textureFile = lst[0].attributes['imagePath'].value
		self.textureFileWidth = int(lst[0].attributes['width'].value)
		self.textureFileHeight = int(lst[0].attributes['height'].value)
		lst = self.xmldoc.getElementsByTagName('sprite')

		TextureManager.addTextureAtlas(self.textureFile)

		for elem in lst:
			self.spriteNames.append(elem.attributes['n'].value)
			self.spriteTexCoordX.append(int(elem.attributes['x'].value))
			self.spriteTexCoordY.append(int(elem.attributes['y'].value))
			self.spriteWidth.append(int(elem.attributes['w'].value))
			self.spriteHeight.append(int(elem.attributes['h'].value))
		for i, sprname in enumerate(self.spriteNames):
			print(i, sprname, self.spriteTexCoordX[i], self.spriteTexCoordY[i], self.spriteWidth[i], self.spriteHeight[i], self.textureFileWidth, self.textureFileHeight)
			region = TextureRegion(self.spriteTexCoordX[i], self.spriteTexCoordY[i], self.spriteWidth[i], self.spriteHeight[i], self.textureFileWidth, self.textureFileHeight)
			position = Vector2f(0.0, 0.0)
			scale = Vector2f(1.0, 1.0)
			transform = TransformComponent(position, scale, 0)
			sprite = Sprite(sprname, self.textureFile, region, transform)
			self._sprites.append(sprite)