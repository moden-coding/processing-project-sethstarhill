import java.util.Random;

import processing.core.*;

public class App extends PApplet {

    float rectX = 300;
    float rectY = 300;
    float rectSizeX = 20;
    float rectSizeY = -50;
    float appleRadius = 20;
    float randomCircleXY = random(600);
    float randomCircleXY2 = random(600);
    int redAppleCount = 0;
    int greenAppleCount = 0;
    int backroundColor = 211;
    int snakegreenColor = 255;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void settings() {
        size(600, 600);
    }

    public void draw() {

        background(backroundColor);
        fill(0, snakegreenColor, 0);
        stroke(0, snakegreenColor, 0);
        rect(rectX, rectY, rectSizeX, rectSizeY);
        fill(255, 0, 0);
        stroke(0, 0, 0);
        strokeWeight(2);
        ellipse(randomCircleXY, randomCircleXY, appleRadius, appleRadius);
        fill(0, 255, 0);
        ellipse(randomCircleXY2, randomCircleXY2, appleRadius, appleRadius);
        collisionDectection();
        windowDeath();
        fill(0, 0, 0);
        textSize(60);
        
        backroundShift();
        
    }

    public void keyPressed() {
        if (keyCode == UP) {
            rectY -= 40;
        }
        if (keyCode == DOWN) {
            rectY += 40;
        }
        if (keyCode == LEFT) {
            rectX -= 40;
        }
        if (keyCode == RIGHT) {
            rectX += 40;
        }
    }

    public void collisionDectection() {

        if (dist(randomCircleXY, randomCircleXY, rectX, rectY) < 15
                || dist(randomCircleXY, randomCircleXY, rectSizeX + rectX, rectSizeY + rectY) < 15
                || dist(randomCircleXY, randomCircleXY, rectX, rectSizeY + rectY) < 15
                || dist(randomCircleXY, randomCircleXY, rectSizeX + rectX, rectY) < 15) {
            randomCircleXY = random(600);
            redAppleCount++;
            System.out.println(redAppleCount);
        } else if (dist(randomCircleXY2, randomCircleXY2, rectX, rectY) < 15
                || dist(randomCircleXY2, randomCircleXY2, rectSizeX + rectX, rectSizeY + rectY) < 15
                || dist(randomCircleXY2, randomCircleXY2, rectX, rectSizeY + rectY) < 15
                || dist(randomCircleXY2, randomCircleXY2, rectSizeX + rectX, rectY) < 15) {
            gameOver();

            System.out.println(greenAppleCount);
        }
    }

    public void gameOver() {

        rectX = 300;
        rectY = 300;
        randomCircleXY = random(600);
        randomCircleXY2 = random(600);
        redAppleCount = 0;
        snakegreenColor = 0;
        fill(0);
        text("Game Over", 170, 100);
        
        text("Play again?", 170, 500);
        if (mousePressed)

        }
    

    

    public void windowDeath() {
        if (rectX < 10 || rectX > 550 || rectY < 10 || rectY > 550) {
            gameOver();
        }
    }

    public void backroundShift() {
        if (redAppleCount > 3) {
            snakegreenColor -= 100;
            backroundColor = 40;
        }

    }
    public void mousePressed(){
        
    }

}

