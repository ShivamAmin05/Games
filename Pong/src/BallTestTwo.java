//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BallTestTwo extends Canvas implements Runnable
{
	private Ball ball;

	public BallTestTwo()
	{
		setBackground(Color.WHITE);
		setVisible(true);

		//instantiate a new Ball
		ball = new Ball();

		//test the Ball thoroughly

		//test all constructors
		// ball = new Ball(100,90);
		
		// ball = new Ball(100,100,30,50);
		
		// ball = new Ball(100,100,30,50,Color.BLUE);
		
		// ball = new Ball(100,100,30,50,Color.BLUE,5,6);
				//x, y, wid, ht, color, xSpd, ySpd
		// ball = new Ball(100,100,30,50,Color.blue,5,6);		

		new Thread(this).start();
	}
	
	public void update(Graphics window)
	{
		paint(window);
	}

	public void paint(Graphics window)
	{
		ball.moveAndDraw(window);

		if(!(ball.getX()>=10 && ball.getX()<=550))
		{
			ball.setXSpeed(-ball.getXSpeed());
		}

		if(!(ball.getY()>=10 && ball.getY()<=450))
		{
			ball.setYSpeed(-ball.getYSpeed());
		}
	}
	
   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(19);
		   System.out.println(ball);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}	
}