//Name -

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Block
{
	private int xSpeed;
	private int ySpeed;

	public Ball()
	{
		super(200,200);
		xSpeed = 3;
		ySpeed = 1;
	}

	//add the other Ball constructors
	public Ball(int xPos, int yPos)
	{
		super(xPos,yPos);
		xSpeed = 3;
		ySpeed = 1;
	}
	public Ball(int xPos, int yPos, int width, int height)
	{
		super(xPos,yPos,width,height);
		xSpeed = 3;
		ySpeed = 1;
	}
	public Ball(int xPos, int yPos, int width, int height, Color color)
	{
		super(xPos,yPos,width,height,color);
	}
	public Ball(int xPos, int yPos, int width, int height, Color color, int xSpeed, int ySpeed)
	{
		super(xPos,yPos,width,height,color);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	
	
	
	
	
	
	
	
	
	
	   
   //add the set methods
   public void setXSpeed(int xSpeed)
   {
      this.xSpeed = xSpeed;
   }

   public void setYSpeed(int ySpeed)
   {
      this.ySpeed = ySpeed;
   }

   public void moveAndDraw(Graphics window)
   {
   	//draw a white ball at old ball location
	draw(window, Color.WHITE);
	setX(getX()+xSpeed);
		//setY
	setY(getY()+ySpeed);
		//draw the ball at its new location
	draw(window);
   }
   
	// public boolean equals(Object obj)
	// {
	// 	return false;
	// }   

   //add the get methods
	public int getXSpeed()
	{
		return xSpeed;
	}
	public int getYSpeed()
	{
		return ySpeed;
	}
   //add a toString() method
   public String toString()
   {
      return getX() + " " + getY() + " " + getWidth() + " " + getHeight() + " " + getColor() + " " + xSpeed + " " + ySpeed + "\n";
   }
}