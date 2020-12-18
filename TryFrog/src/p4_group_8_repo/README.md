## Main Menu

![Alt Text](https://raw.githubusercontent.com/kongksi2011/TryFrog/master/TryFrog/src/p4_group_8_repo/MainMenu.png)

Main Menu with 3 options. <br /> **Refer to Main.java**
#####START.(Press to Start Game)
#####GUIDE. (Press to Read Guide)
#####QUIT. (Press to Quit Game)




## Screenshot of Game
![alt text](https://raw.githubusercontent.com/kongksi2011/TryFrog/master/TryFrog/src/p4_group_8_repo/GameSS.png)

## Main.java
Contains needs for main menu

## GameView.java class created
Contains needs for game view.


#### Background added
Road and river is added for a better view.
<br />**Refer to GameView.java Line 318 - 366**

#### Life Count added
Player is given 5 lives to complete each stage. Once they lose all the lives, the game will end.
<br />**Refer to Animal.java Line 332 - 351, methods regarding to life are added to each death method. **
<br />**Refer to GameView.java Line 79 - 82, 234 - 248, 277 - 287**

#### Additional Level
A total levels of 5 is added to the game, with speed increased and extra obstacle added.
<br />**Refer to GameView.java Line 299 - 313, 389 - 627**

#### Stage level mentioned
Stage level is written at top left corner.
<br />**Refer to GameView.java Line 368 - 387**

#### SubScreen created
A sub screen will pop up once player finished each stage. It contains buttons for player to choose either to proceed or leave the game, score obtained from the current stage, and lastly score for each stage will be recorded here. 
<br />**Refer to GameView.java Line 66 - 232**

#### Alert created
An alert screen will pop up once player loses all the lives.
<br />**Refer to GameView.java Line 234 - 248**

#### Methods to increase speed
Methods to increase speed of obstacles, logs, turtles and wet turtles are added in each java file, and apply them to each insertingObject method in Animal.java.

#### JUnit Tests
