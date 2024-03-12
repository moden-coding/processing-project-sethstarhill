import java.util.Random;

import processing.core.*;

public class App extends PApplet {

    int sizeX = 600; // X size of window
    int sizeY = 600; // Y size of window

    float rectX = 300; // x value of rectangle
    float rectY = 300; // y value of rectangle
    float rectSizeX = 20;// size of rectangle
    float rectSizeY = -50; // y size of rectangle

    float randomCircleXY = random(sizeX); // red apple random placement
    float randomCircleXY2 = random(sizeX); // green apple random placement

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
        size(sizeX, sizeY);
    }

    public void draw() {

        background(211); // setting the backround
        fill(0, 255, 0); // making the fill color green
        stroke(0, 0, 0); // making the stroke black
        strokeWeight(2); // making the outlines of the shape a little more thick
        rect(rectX, rectY, rectSizeX, rectSizeY); // "snake"
        fill(255, 0, 0); // making the fill color red
        stroke(0, 0, 0); // making the stroke black
        strokeWeight(2); // making the outlines of the shape a little more thick

        if (randomCircleXY2 > sizeX || randomCircleXY2 > sizeY) { // if the green apple if off the window due to the
                                                                  // window getting smaller
            // the location of the green apple resets based off of how big the screen is -
            // the same happens with the redapple later in the code
            randomCircleXY2 = random(sizeX);
        }
        ellipse(randomCircleXY, randomCircleXY, 20, 20); // red apple

        fill(0, 255, 0); // making the fill color green
        ellipse(randomCircleXY2, randomCircleXY2, 20, 20); // green apple
        collisionDectection(); // self explanatory
        windowDeath(); // if "snake" touches window, then "gameover"
        fill(0, 0, 0); // making the fill color black

        if (upMove) { // continued - movement method - if upMove is true rectY moves 4 up
            rectY -= 4; // I chose 4 by trial and error - 4 seemed the perfect speed - not too fast and
                        // not too slow
        }
        if (downMove) { // same thing as ^^ - but if downMove is true rectY moves 4 down
            rectY += 4;
        }
        if (leftMove) { // following the same pattern as above
            rectX -= 4;
        }
        if (rightMove) { // ^^
            rectX += 4;
        }
        windowClose(); // window's getting smaller
        if (dist(randomCircleXY, randomCircleXY, randomCircleXY2, randomCircleXY2) < 40) { // if the two apples are too
                                                                                           // close, reset the red apple
            randomCircleXY = random(sizeX);
        }

        highScore();
        text("High Score: " + finalScore, 20, 40); // final score
        text("Score: " + redAppleCount, 20, 68); // current score
    }

    public void keyPressed() {

        if (keyCode == UP) { // if the key pressed is the down key - identified by the keycode - UP -
            upMove = true;
            downMove = false; // the snake cannot go two distances as once - so once the user presses one
                              // key...
                              // any other key being pressed doesn't have any affect on the snake
            leftMove = false; // ^^
            rightMove = false; // ^^
        }

        if (keyCode == DOWN) { // if the key pressed is the down key - identified by the keycode - DOWN -
            upMove = false; // the snake cannot go two distances as once - so once the user presses one
                            // key...
                            // any other key being pressed doesn't have any affect on the snake
            downMove = true; // ^^
            leftMove = false; // ^^
            rightMove = false; // ^^
        }
        if (keyCode == LEFT) { // Likewise if the key pressed is the left key... following the trend
            upMove = false; // the snake cannot go two distances as once - so once the user presses one
                            // key...
                            // any other key being pressed doesn't have any affect on the snake
            downMove = false; // ^^
            leftMove = true; // ^^
            rightMove = false; // ^^
        }
        if (keyCode == RIGHT) { // following the trends started with the three conditions of the previous IF
                                // statements
            upMove = false; // the snake cannot go two distances as once - so once the user presses one
                            // key...
                            // any other key being pressed doesn't have any affect on the snake
            downMove = false; // ^^
            leftMove = false; // ^^
            rightMove = true; // ^^
        }

    }

    public void collisionDectection() {

        if (dist(randomCircleXY, randomCircleXY, rectX, rectY) < 15 // if any 4 points of the snake touches the apple,
                                                                    // then the apple resets and redapplecount++
                || dist(randomCircleXY, randomCircleXY, rectSizeX + rectX, rectSizeY + rectY) < 15
                || dist(randomCircleXY, randomCircleXY, rectX, rectSizeY + rectY) < 15
                || dist(randomCircleXY, randomCircleXY, rectSizeX + rectX, rectY) < 15) {
            randomCircleXY = random(sizeX); // respawans the apple in a different place in the window
            redAppleCount++; // once the apple is "eaten" the the redAppleCount (how many apples the snake
                             // has eaten) increases

        } else if (dist(randomCircleXY2, randomCircleXY2, rectX, rectY) < 15 // same thing but with the green apple -
                                                                             // and gameover
                || dist(randomCircleXY2, randomCircleXY2, rectSizeX + rectX, rectSizeY + rectY) < 15
                || dist(randomCircleXY2, randomCircleXY2, rectX, rectSizeY + rectY) < 15
                || dist(randomCircleXY2, randomCircleXY2, rectSizeX + rectX, rectY) < 15) {
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

        if (sizeX > 500) {// if the player dies when SizeX or SizeY(they are equal) is bigger and smaller
                          // then some number, then the position of the text below is changed
            textSize(50);
            text("Game Over", sizeX / 2 - 100, 100); // I found out where all these texts should go mostly by educated
                                                     // guesses, and trials and error
            text("Press 'f' to play again", sizeX / 7, sizeY - 50);
        } else if (sizeX >= 400 && sizeX < 500) { // if the window is a certain size, then "Game Over" and "Press f to
                                                  // play again" are in a certain place
            textSize(40);
            text("Game Over", sizeX / 2 - 100, 100); // this just puts Game Over onto the screen
            text("Press 'f' to play again", sizeX / 11, sizeY - 50); // likewise with "Press 'f' to play again"
        } else if (sizeX < 400 && sizeX > 250) { // ^^
            textSize(30);
            text("Game Over", sizeX / 4, 70); // this just puts Game Over onto the screen
            text("Press 'f' to play again", sizeX / 9, sizeY - 50); // likewise with "Press 'f' to play again"
        } else if (sizeX <= 250) { // ^^
            textSize(23);
            text("Game Over", sizeX / 5 + 10, 40); // this just puts Game Over onto the screen
            text("Press 'f' to play again", sizeX / 10, sizeY - 50); // likewise with "Press 'f' to play again"
        }

        if (keyCode == 70) {// if the player presses (f) then the variables and therefore the game resets

            sizeX = 600; // resetting window size
            sizeY = 600; // resetting window size
            surface.setSize(sizeX, sizeY); // setting the new window sizes

            rectX = 300; // resetting the "snake's" cords
            rectY = 300; // ^^
            randomCircleXY = random(sizeX); // reset red apple placement
            randomCircleXY2 = random(sizeX); // same as ^^ but with green apples
            redAppleCount = 0; // resets applecount
            rectSizeY = -50; // because the snake gradually gets smaller as the window gets smaller - so this
                             // resets

        }

    }

    public void windowDeath() {
        if (rectX < 1 || rectX + rectSizeX > sizeX || rectY > sizeX || rectY < 40 || rectY > sizeY) { // when the
                                                                                                      // "snake" touches
                                                                                                      // the side of the
                                                                                                      // window, it dies
            upMove = false; // when it dies, it's not able to move anymore
            downMove = false; // ^^
            leftMove = false; // ^^
            rightMove = false; // ^^
            gameOver(); // gameover method
        }
    }

    public void highScore() { // self explanatory
        if (sizeX < 400 && sizeX > 250) { // controling how big the final score text is based on how big or small the
                                          // window is
            textSize(17);
        } else if (sizeX <= 250) { // shrinking the final score text based on how small the window is - ^^
            textSize(13);
        } else if (sizeX >= 400) {
            textSize(30);
        }

        if (redAppleCount > finalScore) { // setting the final score - if the apple count is bigger then the old
                                          // finalscore then that redapplecount is the new highscore
            finalScore = redAppleCount;

        }

    }

    public void windowClose() { // changing the window size based on how many apples the snake has ate

        if (redAppleCount > 3 && sizeX > 550 && sizeY > 550) { // if the snake has eaten more then three apples - then
                                                               // the window size decreases by 1 until sizeX and sizeY
                                                               // no longer is > 550
            sizeX = sizeX - 1;
            sizeY = sizeY - 1;

            surface.setSize(sizeX, sizeY); // setting the new window size
        }
        if (redAppleCount > 5 && sizeX > 475 && sizeY > 475) { // same thing as ^^ but the window decreases until sizeX
                                                               // and SizeY no longer > 475 and only if redAppleCount >
                                                               // 5
            sizeX = sizeX - 1;
            sizeY = sizeY - 1;

            surface.setSize(sizeX, sizeY); // setting the new window size
        }
        if (redAppleCount > 7 && sizeX > 400 && sizeY > 400) { // same as above, but only if applecount > 7 and shrinks
                                                               // until SizeX and SizeY is no longer > 400
            sizeX = sizeX - 1;
            sizeY = sizeY - 1;

            surface.setSize(sizeX, sizeY); // setting the new window size
        }
        if (redAppleCount > 9 && sizeX > 325 && sizeY > 325) { // following the trend that is started above
            sizeX = sizeX - 1;
            sizeY = sizeY - 1;

            rectSizeY = -40; // making the "snake" shorter because the window size is smaller so the game is
                             // easier to play

            surface.setSize(sizeX, sizeY); // setting the new window size
        }
        if (redAppleCount > 11 && sizeX > 250 && sizeY > 250) { // following the trend that is started above
            sizeX = sizeX - 1;
            sizeY = sizeY - 1;

            rectSizeY = -30; // making the "snake" shorter because the window size is smaller so the game is
                             // easier to play

            surface.setSize(sizeX, sizeY); // setting the new window size
        }
        if (redAppleCount > 13 && sizeX > 175 && sizeY > 175) { // following the trend that is started above
            sizeX = sizeX - 1;
            sizeY = sizeY - 1;

            rectSizeY = -20; // making the "snake" shorter because the window size is smaller so the game is
                             // easier to play

            surface.setSize(sizeX, sizeY); // setting the new window size
        }

    }

}
