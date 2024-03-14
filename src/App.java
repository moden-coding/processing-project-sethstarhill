import java.util.Random;

import processing.core.*;

public class App extends PApplet {

    int windowX = 600; // X size of window
    int windowY = 600; // Y size of window

    float snakeRectX = 300; // x value of rectangle
    float snakeRectY = 300; // y value of rectangle
    float rectSizeX = 20;// size of rectangle
    float rectSizeY = -50; // y size of rectangle

    float randomRedAppleCircle = random(windowX); // red apple random placement
    float randomGreenAppleCircle = random(windowX); // green apple random placement

    int redAppleCount = 0; // red apple counter

    boolean upMove = false; // movement booleans
    boolean downMove = false;
    boolean leftMove = false;
    boolean rightMove = false;

    int finalScore = 0; // final score

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void settings() {
        size(windowX, windowY);
    }

    public void draw() {

        background(211); // setting the backround
        fill(0, 255, 0);
        stroke(0, 0, 0);
        strokeWeight(2);
        rect(snakeRectX, snakeRectY, rectSizeX, rectSizeY); // "snake"
        fill(255, 0, 0);
        stroke(0, 0, 0);
        strokeWeight(2);

        ellipse(randomRedAppleCircle, randomRedAppleCircle, 20, 20); // red apple

        fill(0, 255, 0);
        ellipse(randomGreenAppleCircle, randomGreenAppleCircle, 20, 20); // green apple
        
        
        
        collisionDectection(); // self explanatory
        windowDeath(); // if "snake" touches window, then "gameover"
        windowClose(); // window's getting smaller
        movementMethod();
        highScore();
        appleWindow();
        appleCollision();

        fill(0, 0, 0); 
        text("High Score: " + finalScore, 20, 40); // final score
        text("Score: " + redAppleCount, 20, 68); // current score
    }

    public void movementMethod() {
        if (upMove) { // continued - movement method - if upMove is true snakeRectY moves 4 up
            snakeRectY -= 4; // I chose 4 by trial and error - 4 seemed the perfect speed - not too fast and
            // not too slow
        }
        if (downMove) { // same thing as ^^ - but if downMove is true snakeRectY moves 4 down
            snakeRectY += 4;
        }
        if (leftMove) { // following the same pattern as above
            snakeRectX -= 4;
        }
        if (rightMove) { // ^^
            snakeRectX += 4;
        }
    }

    public void keyPressed() {

        if (keyCode == UP) { // if the key pressed is the down key - identified by the keycode - UP -
            upMove = true;
            downMove = false; // the snake cannot go two distances as once - so once the user presses one
                              // key - any other key being pressed doesn't have any affect on the snake

            leftMove = false; // ^^
            rightMove = false; // ^^
        }

        if (keyCode == DOWN) { // if the key pressed is the down key - identified by the keycode - DOWN -
            upMove = false; // the snake cannot go two distances as once - so once the user presses one
                            // key... any other key being pressed doesn't have any affect on the snake
            downMove = true;
            leftMove = false;
            rightMove = false;
        }
        if (keyCode == LEFT) { // Likewise if the key pressed is the left key... following the trend
            upMove = false;
            downMove = false;
            leftMove = true;
            rightMove = false;
        }
        if (keyCode == RIGHT) { // following the trends started with the three conditions of the previous IF
                                // statements
            upMove = false;
            downMove = false;
            leftMove = false;
            rightMove = true;
        }

    }

    public void collisionDectection() {

        if (dist(randomRedAppleCircle, randomRedAppleCircle, snakeRectX, snakeRectY) < 15 // if any 4 points of the
                                                                                          // snake touches the apple,
                // then the apple resets and redapplecount++
                || dist(randomRedAppleCircle, randomRedAppleCircle, rectSizeX + snakeRectX, rectSizeY + snakeRectY) < 15
                || dist(randomRedAppleCircle, randomRedAppleCircle, snakeRectX, rectSizeY + snakeRectY) < 15
                || dist(randomRedAppleCircle, randomRedAppleCircle, rectSizeX + snakeRectX, snakeRectY) < 15) {
            randomRedAppleCircle = random(windowX); // respawans the apple in a different place in the window
            redAppleCount++; // once the apple is "eaten" the the redAppleCount (how many apples the snake
                             // has eaten) increases

        } else if (dist(randomGreenAppleCircle, randomGreenAppleCircle, snakeRectX, snakeRectY) < 15 // same thing but
                                                                                                     // with the green
                                                                                                     // apple -
                // and gameover
                || dist(randomGreenAppleCircle, randomGreenAppleCircle, rectSizeX + snakeRectX,
                        rectSizeY + snakeRectY) < 15
                || dist(randomGreenAppleCircle, randomGreenAppleCircle, snakeRectX, rectSizeY + snakeRectY) < 15
                || dist(randomGreenAppleCircle, randomGreenAppleCircle, rectSizeX + snakeRectX, snakeRectY) < 15) {
            gameOver();
            upMove = false;
            downMove = false;
            leftMove = false;
            rightMove = false;

        }
    }

    public void gameOver() {

        fill(0);
        redAppleCount = 0;

        if (windowX > 500) {// if the player dies when SizeX or SizeY(they are equal) is bigger and smaller
                            // then some number, then the position of the text below is changed
            textSize(50);
            text("Game Over", windowX / 2 - 100, 100); // I found out where all these texts should go mostly by educated
                                                       // guesses, and trials and error
            text("Press 'f' to play again", windowX / 7, windowY - 50);
        } else if (windowX >= 400 && windowX < 500) { // if the window is a certain size, then "Game Over" and "Press f
                                                      // to
            // play again" are in a certain place
            textSize(40);
            text("Game Over", windowX / 2 - 100, 100); // this just puts Game Over onto the screen
            text("Press 'f' to play again", windowX / 11, windowY - 50); // likewise with "Press 'f' to play again"
        } else if (windowX < 400 && windowX > 250) {
            textSize(30);
            text("Game Over", windowX / 4, 70);
            text("Press 'f' to play again", windowX / 9, windowY - 50);
        } else if (windowX <= 250) {
            textSize(23);
            text("Game Over", windowX / 5 + 10, 40);
            text("Press 'f' to play again", windowX / 10, windowY - 50);
        }

        if (keyCode == 70) {// if the player presses (f) then the variables and therefore the game resets

            windowX = 600; // resetting window size
            windowY = 600; // resetting window size
            surface.setSize(windowX, windowY); // setting the new window sizes

            snakeRectX = 300; // resetting the "snake's" cords
            snakeRectY = 300; // ^^
            randomRedAppleCircle = random(windowX); // reset red apple placement
            randomGreenAppleCircle = random(windowX); // same as ^^ but with green apples
            redAppleCount = 0; // resets applecount
            rectSizeY = -50; // because the snake gradually gets smaller as the window gets smaller - so this
                             // resets

        }

    }

    public void windowDeath() {
        if (snakeRectX < 1 || snakeRectX + rectSizeX > windowX || snakeRectY > windowX || snakeRectY < 40
                || snakeRectY > windowY) { // when the
            // "snake" touches
            // the side of the
            // window, it dies
            upMove = false; // when it dies, it's not able to move anymore
            downMove = false;
            leftMove = false;
            rightMove = false;
            gameOver(); // gameover method
        }
    }

    public void highScore() { // self explanatory
        if (windowX < 400 && windowX > 250) { // controling how big the final score text is based on how big or small
                                              // the
            // window is
            textSize(17);
        } else if (windowX <= 250) { // shrinking the final score text based on how small the window is - ^^
            textSize(13);
        } else if (windowX >= 400) {
            textSize(30);
        }

        if (redAppleCount > finalScore) { // setting the final score - if the apple count is bigger then the old
                                          // finalscore then that redapplecount is the new highscore
            finalScore = redAppleCount;

        }

    }

    public void windowClose() { // changing the window size based on how many apples the snake has ate

        if (redAppleCount > 3 && windowX > 550 && windowY > 550) { // if the snake has eaten more then three apples -
                                                                   // then
            // the window size decreases by 1 until windowX and windowY
            // no longer is > 550
            windowX = windowX - 1;
            windowY = windowY - 1;

            surface.setSize(windowX, windowY); // setting the new window size
        }
        if (redAppleCount > 5 && windowX > 475 && windowY > 475) { // same thing as ^^ but the window decreases until
                                                                   // windowX
            // and SizeY no longer > 475 and only if redAppleCount >
            // 5
            windowX = windowX - 1;
            windowY = windowY - 1;

            surface.setSize(windowX, windowY); // setting the new window size
        }
        if (redAppleCount > 7 && windowX > 400 && windowY > 400) { // same as above, but only if applecount > 7 and
                                                                   // shrinks
            // until SizeX and SizeY is no longer > 400
            windowX = windowX - 1;
            windowY = windowY - 1;

            surface.setSize(windowX, windowY);
        }
        if (redAppleCount > 9 && windowX > 325 && windowY > 325) {
            windowX = windowX - 1;
            windowY = windowY - 1;

            rectSizeY = -40;
            surface.setSize(windowX, windowY);
        }
        if (redAppleCount > 11 && windowX > 250 && windowY > 250) {
            windowX = windowX - 1;
            windowY = windowY - 1;

            rectSizeY = -30; //// making the "snake" shorter because the window size is smaller so the game
                             //// is
            // more manageable to play

            surface.setSize(windowX, windowY);
        }
        if (redAppleCount > 13 && windowX > 175 && windowY > 175) {
            windowX = windowX - 1;
            windowY = windowY - 1;

            rectSizeY = -20;

            surface.setSize(windowX, windowY); // setting the new window size
        }

    }

    public void appleWindow() {
        if (randomRedAppleCircle > windowX) { // if the red apple is not visible in
                                                                                // the frame this
            // re-randomizes the apples location within the frame
            randomRedAppleCircle = random(windowX);
        } else if (randomGreenAppleCircle > windowX || randomGreenAppleCircle > windowY) { // same thing but with the
                                                                                           // green apple
            randomGreenAppleCircle = random(windowX);
        }
    }

    public void appleCollision() {
        if (dist(randomRedAppleCircle, randomRedAppleCircle, randomGreenAppleCircle, randomGreenAppleCircle) < 40) { // if
                                                                                                                     // the
                                                                                                                     // two
                                                                                                                     // apples
                                                                                                                     // are
                                                                                                                     // too
                                                                                                                     // close,
                                                                                                                     // the
                                                                                                                     // red
                                                                                                                     // apple
                                                                                                                     // is
                                                                                                                     // reset

            randomRedAppleCircle = random(windowX);
        }
    }
}
