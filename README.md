# Raycasting
A simple Raycasting project made in Java using the Processing core library. 

![raycasting](https://user-images.githubusercontent.com/30444886/158455908-736cd6ac-afdc-4ae5-9805-bb22e8c3a033.png)

There are a couple bugs related to the world rotation of the 1st person camera... oh well. This was a sideproject and it is now abandoned.

## Explanation
[Here](https://en.wikipedia.org/wiki/Ray_casting) is a good Wikipedia article about the topic. To summarize, we have a series of rays projected from a point in all directions and onto walls, which then are processed as a function of the distance and angle to give the impression of depth.
This project diverges and evolves from the Wikipedia article in that I don't use "cells", I use math to define the walls, so they can have any shape and resolution I want. 
For example, this code creates a wall of a random size, in a random location and at a random angle:
```Java
Wall wall = new Wall(random(width-1), random(height-1), random(width-1), random(height-1));
```

## How to import into eclipse
Download processing from the [Processing page](https://processing.org/download/) and go into the folder where you extracted it. Then, "core > library > core.jar". You will need to add the core.jar into the external libraries of the project. After that, everything will work.

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
