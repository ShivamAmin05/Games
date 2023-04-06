//Name -

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;

	public Ship()//default constructor
	{
		this(0, 0, 50, 50,10);
	}

	public Ship(int x, int y)//overloaded constructor
	{
	   //add code here (look at the above constructor)
	   this(x, y, 50, 50,10);
	}

	public Ship(int x, int y, int s)
	{
	   //add code here
	   this(x, y, 50, 50,s);
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			URL url = getClass().getResource("/images/ship.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			//feel free to do something here
		}
	}


	public void setSpeed(int s)
	{
	   //add code (set the speed)
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed; //get the speed
	}

	public void move(String direction)
	{
		//add code here
		if(direction.equals("LEFT"))
		{
			setX(getX()-getSpeed());
		}
		else if(direction.equals("RIGHT"))
		{
			setX(getX()+getSpeed());
		}
		else if(direction.equals("UP"))
		{
			setY(getY()-getSpeed());
		}
		else if(direction.equals("DOWN"))
		{
			setY(getY()+getSpeed());
		}
	}

	public void draw( Graphics window )//this is done
	{
   		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()//this is done
	{
		return super.toString() + getSpeed();
	}
}
