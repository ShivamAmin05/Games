//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

public class Ammo extends MovingThing
{
	private int speed;

	public Ammo()
	{
		this(0,0,0);
	}

	public Ammo(int x, int y)
	{
		//add code
		this(x,y,0);
	}

	public Ammo(int x, int y, int s)
	{
		setPos(x, y);
		setSpeed(s);
	}

	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Graphics window )
	{
		//add code to draw the ammo
		window.setColor(Color.orange);
		window.fillRect(getX() + 19, getY() - 10, 10, 10);
	}
	
	
	public void move( String direction )
	{
		setY(getY() - getSpeed());
	}

	public String toString()
	{
		return "";
	}
}
