//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Block
{
   //instance variables
   private int speed;

   public Paddle()
   {
		super(10,10);
      speed =5;
   }


   //add the other Paddle constructors
   public Paddle(int xPos, int yPos)
   {
      super(xPos,yPos,10,10);
      speed = 5;
   }
   public Paddle(int xPos, int yPos, int width)
   {
      super(xPos,yPos, width,10);
      speed = 5;
   }
   public Paddle(int xPos, int yPos, int width, int height, int speed)
   {
      super(xPos,yPos, width, height);
      this.speed = speed;
   }
   public Paddle(int xPos, int yPos, int width, int height,Color color, int speed)
   {
      super(xPos,yPos, width, height, color);
      this.speed = speed;
   }









   public void moveUpAndDraw(Graphics window)
   {
      draw(window, Color.BLACK); 
	   setY(getY()-speed);
		//draw the ball at its new location
	   draw(window);
   }

   public void moveDownAndDraw(Graphics window)
   {
      draw(window, Color.BLACK); 
	   setY(getY()+speed);
		//draw the ball at its new location
	   draw(window);
   }

   //add get methods
   public int getSpeed()
   {
      return speed;
   }
   
   //add a toString() method
   public String toString()
   {
      return getX() + " " + getY() + " " + getWidth() + " " + getHeight() + " " + getColor() + " " + speed + "\n";
   }
}