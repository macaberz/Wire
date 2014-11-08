1. Support colored text
2. Sprite object factory pattern. Right now making a sprite in python takes 5 lines of code, I want it to be one.
Perhaps the best, long-term solution is to write/use a TexturePacker that also generates a TextureRegion files for each atlas.
Sprites would still be adressed by their individual file names, but the textures would be read from the atlas using the associated
texture region file.
3. Figure out where to multiply by deltaTime in the engine code (at TransformComponent?)
4. FOR SOME REASON the TextureRegion REQUIRES all atlasses to be 1024x1024 or 512x512. They have to be SQUARE. Probably has to do
with how the percentages are calculated but I don't have the brains right now to figure that out.

TEMPORARY HACK:
1. Pack textures with TP
2. Resize to a square ^2 format in Photoshop
3. UPDATE the associated .xml file to set the width and height to the new, square, ^2 values
4. ???
5. Profit

A NEW COMMIT