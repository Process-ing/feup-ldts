# LDTS_L01G07 - Timeless Odyssey

2D platformer game where the main character tries to explore a  futuristic world full of obstacles. There are multiple consecutive "levels" that blend through progressive scenery changes.

This project was developed by Bruno Oliveira (up202208700@fe.up.pt), João Mendes (up202208783@fe.up.pt) and Rodrigo Coelho (up202205188@fe.up.pt) for LDTS 2023/24.

## IMPLEMENTED FEATURES

- **Main Menu screen** - simple menu screen when launching the game, allowing for start, exit and a future settings option
- **Screen Resizer** - at the start of the game, the code reads the user's screen size and extends the game to that resolution
- **Basic Level Loader** - class to load levels of written text files with specific characters. This will be used to generate the different scenes/levels and allows for simple layout editions
- **Sprite Image Loader** - a class that loads PNG images into the game and can then represent them pixel by pixel. This will be used for the player, tiles and possibly other things in the program

## PLANNED FEATURES

- **Multiple Levels/Scenes** - consecutive scenes that will blend, similar to different levels but connected
- **Smoother Movement**    
    - **Better walking** - better walking movement with animation, walking at variable speeds
    - **Dashing** - ability to use a speed to the sides (and possibly other directions)
    - **Jump** - better jump control with variable heights, coyote time (jump timer at the end of platforms) and  ledge adjustments (allows jumping around a ledge when only a few pixels are left)
    - **Wall Crawl** - the ability to hang on and slowly fall from walls

## GENERAL STRUCTURE

<img src="resources/timelessOdysseyUML.png"/>

## ARCHITECTURAL PATTERNS

### Model/View/Controller (MVC) Architecture

- Model is used to represent the data
- View displays the model and sends actions to the controller
- Controller provides a model to view and interpret user actions

#### Problem in Context

When creating any piece of software that will have to deal with a user interface it's essential to provide an adequate structural pattern. The code needs to be well-organized and separated so that the Single Responsibility Principle isn't broken.  
Due to this, we must implement a way to deal separately with different components of our game (processing inputs, showing images to the screen, controlling the logic of the game...).

#### The Pattern

Due to this, we decided to use the Model/View/Controller Pattern which is very common in Graphical User Interfaces (GUI). The idea behind this pattern is to use three different sections:
- Model is used to represent the data, rules and game logic
- View displays the model and sends actions to the controller
- Controller provides a model to the view and interprets user actions

Since we had chosen this pattern and had some ideas on how to implement it, each one of us started making the initial classes as methods for each one of the sections. After that, we just merged all the code together, fixed some of the commits and had the main components of the game working.

We have obviously done some changes and tweaks after that initial plan, but since we applied the pattern the components are much more independent and can be altered without causing much conflicts. This is clearly useful when wanting to expand the game further.

#### Implementation

The implementation of this model can be seen by checking the folders inside the source code of our game: 

- [Model](/src/main/java/timelessodyssey/model/menu/Menu.java)
- [View](/src/main/java/timelessodyssey/view/screens/MenuViewer.java)
- [Controller](/src/main/java/timelessodyssey/control/menu/MenuController.java)

An explanation is also provided in the following diagram:


#### Consequences

Just like we mentioned before, this architecture pattern allows for better code organization and segregation so that simple changes don't create much conflicts in other parts of the code.

## DESIGN PATTERNS

### Game Loop Pattern

Game Loop
    - Inside the Game.java (main entry point), there is a game loop that
      updates the frame shown every single iteration
    - Specifying a target FPS and frame time, we can make the main
      thread sleep until it needs to be updated again
    - This allows us to control the overall speed of the game and make
      the graphics smoother

#### Problem in Context

When designing a game we must have a way to update the state of every single entity over the course of time. Initially, we may just want to keep the game running without needing the inputs, but as we develop the game further and add more entities we want to control how fast the game runs and how often we update the images shown to the screen.  
This is also important because code speed depends on the system of the user running the code. In order to make the game run at the same speed for all users, we must have a way to control this.

#### The Pattern

The design pattern frequently used to solve this issue is the Game Loop. A Game Loop is basically a while-loop that runs depending on the state of the game (for instance, it runs while it is not exiting the game).  
On pair with this loop there is also a value of target FPS (frames per second) that represents the number of times the image should be updated to the screen during one second of execution.  
Knowing this value, we can also calculate the time the main execution thread should wait before continuing for the next iteration of the loop.  
This way, our game will run continuously and smoothly across users of different systems.

#### Implementation

The correspondent implementation of this pattern in our project can be found in the main function [Game.java](/src/main/java/timelessodyssey/Game.java)

#### Consequences

Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

### Factory Pattern

Factory
    - The State class is an interface for all the other subclasses
      (GameState and MenuState)
    - This removes the need to anticipate the class
    - Code will only deal with the State interface, therefore, it will
      be able to deal with all the derived States

#### Problem in Context

The application should know if it is currently on the menu or if it is, effectively,  running the game. This could be 

The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.

#### The Pattern

Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.

#### Implementation

Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.

#### Consequences

Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

### State Pattern

State
    - Allow an object to alter its behavior when its internal state
      changes
    - By associating an internal state with the objects, we can make
      them change behavior in response to some user inputs
    - This way our objects are more responsive and can change, for
      example, their representation with inputs

#### Problem in Context

The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.

#### The Pattern

Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.

#### Implementation

Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.

#### Consequences

Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

### Adapter Pattern

#### Problem in Context

The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone other than the original developer should be able to read and understand all the motivations for the decisions made. When referring to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.

#### The Pattern

Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.

#### Implementation

Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.

#### Consequences

Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

### Double Buffer Pattern

#### Problem in Context

## KNOWN CODE SMELLS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation.

## TESTING

- [Unit Testing](/src/test/java/timelessodyssey) for the main section of our code (Model, View, Controller, States) 
- [Mocks](/src/test/java/timelessodyssey) have been used in conjunction with Unit Tests
- Future Testing
    - Test the sections more extensively
    - Test the remaining sections of our code and their interactions
    - Provide test coverage
    - Provide the mutation testing results

## SELF-EVALUATION

- Bruno Oliveira: XX%
- João Mendes: XX%
- Rodrigo Coelho: XX%