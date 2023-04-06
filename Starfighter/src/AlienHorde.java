//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
	private List<Alien> aliens;

	public AlienHorde(int size)
	{
		aliens = new ArrayList<>();
	}

	public void add(Alien al)
	{
		aliens.add(al);
	}

	public void drawEmAll( Graphics window )
	{
		for(Alien enemy: aliens)
		{
			enemy.draw(window);
		}
	}

	public void moveEmAll()
	{
		for(Alien enemy: aliens)
		{
			enemy.move("UP");
		}
	}

	public void removeDeadOnes(List<Ammo> shots)
	{
		for(Alien enemy: aliens)
		{
			for(Ammo a: shots)
			{
				if(Math.abs(a.getX()-enemy.getX()) < 20 && Math.abs(a.getY()-enemy.getY()) < 20)
				{
					aliens.remove(enemy);
					shots.remove(a);
				}
			}
		}
	}

	public String toString()
	{
		return "";
	}
}
			