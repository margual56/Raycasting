# Raycasting
A simple Raycasting project made in Java using the swing and awt libraries.<br/>
This is a modified version, since the original was made using Processing's libraries.

## Explanation
[Here](https://en.wikipedia.org/wiki/Ray_casting) is a good Wikipedia article about the topic. To summarize, we have a series of rays projected from a point in all directions and onto walls, which then are processed as a function of the distance and angle to give the impression of depth.
This project diverges and evolves from the Wikipedia article in that I don't use "cells", I use math to define the walls, so they can have any shape and resolution I want. 
For example, this code creates a wall of a random size, in a random location and at a random angle:
```Java
Wall wall = new Wall(Math.random(Main.width-1), Math.random(Main.height-1), Math.random(Main.width-1), Math.random(Main.height-1));
```

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
