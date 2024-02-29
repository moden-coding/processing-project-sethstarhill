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
    
    

    


    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void settings() {
        size(600, 600);
    }

    public void draw() {

        background(backroundColor);
        fill(0, 255, 30);
        stroke(0, 255, 30);
        rect(rectX, rectY, rectSizeX, rectSizeY);
        fill(255, 0, 0);
        stroke(0, 0, 0);
        strokeWeight(2);
        ellipse(randomCircleXY, randomCircleXY, appleRadius, appleRadius);
        fill(0, 255, 0);
        ellipse(randomCircleXY2, randomCircleXY2, appleRadius, appleRadius);
        collisionDectection();
        windowDeath();
        
        

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

if (dist(randomCircleXY, randomCircleXY, rectX, rectY) < 15 || dist(randomCircleXY, randomCircleXY, rectSizeX + rectX, rectSizeY + rectY) < 15 || dist(randomCircleXY, randomCircleXY, rectX, rectSizeY + rectY) < 15 || dist(randomCircleXY, randomCircleXY, rectSizeX + rectX, rectY) < 15) {
            randomCircleXY = random(600);
            redAppleCount++;
            System.out.println(redAppleCount);
        }
        else if (dist(randomCircleXY2, randomCircleXY2, rectX, rectY) < 15 || )

    }

    public void gameOver() {
        rectX = 300;
        rectY = 300;
        randomCircleXY = random(600);
        redAppleCount = 0;
    }

    public void windowDeath(){
        if(rectX < 10 || rectX > 550 || rectY < 10 || rectY > 550){
            gameOver();
        }
    }
    
    
    
    public void backroundShift() {
       
        
        
       }
       
       
       
        
       
    
    public void borderCollision(){
        
        
    }
    
}
