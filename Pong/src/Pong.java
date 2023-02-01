//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.FontMetrics;

public class Pong extends Canvas implements KeyListener, Runnable
{
	private Ball ball;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private Block topWall;
	private Block bottomWall;
	private boolean[] keys;
	private BufferedImage back;
	private int p1Score;
	private int p2Score;


	public Pong()
	{
		//set up all variables related to the game
		ball = new Ball(400,300,10,10,Color.BLUE);

		leftPaddle = new Paddle(10,300,10,50,Color.WHITE,5);

		rightPaddle = new Paddle(790-10,300, 10,50,Color.WHITE,5);


		keys = new boolean[4];

    
    	setBackground(Color.BLACK);
		setVisible(true);
		
		new Thread(this).start();
		addKeyListener(this);		//starts the key thread to log key strokes
	}
	public void update(Graphics window){
		paint(window);
   		window.setColor(Color.CYAN);
	    window.drawString(""+p1Score,340,20);
	    window.drawString(""+p2Score,440,20);
	    window.setColor(Color.WHITE);
		window.drawRect(390, 0, 10, 600);
   }

   public void paint(Graphics window)
   {
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();


		ball.moveAndDraw(graphToBack);
		leftPaddle.draw(graphToBack);
		rightPaddle.draw(graphToBack);
		// topWall.draw(window, Color.BLUE);
		// bottomWall.draw(window, Color.BLUE);


		// //see if ball hits left wall or right wall
		if(ball.getX()>=back.getWidth() + 20)
		{
			ball.setYSpeed(-ball.getYSpeed());
			ball.setXSpeed(-ball.getXSpeed());
			p1Score += 1;
			ball.setPos(back.getWidth()/2, back.getHeight()/2);
		}
		if(ball.getX() <= 0 - 20)
		{
			ball.setYSpeed(-ball.getYSpeed());
			p2Score += 1;
			ball.setPos(back.getWidth()/2, back.getHeight()/2);
		}

		
		// //see if the ball hits the top or bottom wall 
		if(!(ball.getY()>=0 && ball.getY()<=back.getHeight()))
		{
			ball.setYSpeed(-ball.getYSpeed());
		}


		//see if the ball hits the left paddle
		if((ball.didCollideLeft(leftPaddle)) && ( ball.didCollideTop(leftPaddle) || ball.didCollideBottom(leftPaddle)))
		{
			if(ball.didCollideLeft(leftPaddle))
			{
				ball.setXSpeed(-ball.getXSpeed());
			}
			else
			{
				ball.setYSpeed(-ball.getYSpeed());
			}
		}
		
		//see if the ball hits the right paddle
		if((ball.didCollideRight(rightPaddle)) && (ball.didCollideTop(rightPaddle) || ball.didCollideBottom(rightPaddle)))
		{
			if(ball.didCollideRight(rightPaddle))
			{
				ball.setXSpeed(-ball.getXSpeed());
			}
			else
			{
				ball.setYSpeed(-ball.getYSpeed());
			}
		}

		//see if the paddles need to be moved
		if(keys[0] == true && leftPaddle.getY() - leftPaddle.getSpeed() >= 0)
		{
			//move left paddle up and draw it on the window
			leftPaddle.moveUpAndDraw(graphToBack);
		}
		if(keys[1] == true && leftPaddle.getY() + leftPaddle.getHeight() + leftPaddle.getSpeed() <= back.getHeight())
		{
			//move left paddle down and draw it on the window
			leftPaddle.moveDownAndDraw(graphToBack);

		}
		if(keys[2] == true && rightPaddle.getY() - rightPaddle.getSpeed() >= 0)
		{
			rightPaddle.moveUpAndDraw(graphToBack);
		}
		if(keys[3] == true && rightPaddle.getY() + rightPaddle.getHeight() + rightPaddle.getSpeed() <= back.getHeight())
		{
			rightPaddle.moveDownAndDraw(graphToBack);
		}

		twoDGraph.drawImage(back, null, 0, 0);
	}

	public interface Collidable
	{
		boolean didCollideLeft(Object obj);  

		boolean didCollideRight(Object obj);  

		boolean didCollideTop(Object obj);  

		boolean didCollideBottom(Object obj);  
	}
	public void keyPressed(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
			case 'W' : keys[0]=true; break;
			case 'S' : keys[1]=true; break;
			case 'I' : keys[2]=true; break;
			case 'K' : keys[3]=true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
			case 'W' : keys[0]=false; break;
			case 'S' : keys[1]=false; break;
			case 'I' : keys[2]=false; break;
			case 'K' : keys[3]=false; break;
		}
	}

	public void keyTyped(KeyEvent e){}
	
   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(8);
            repaint();
			System.out.println(leftPaddle.getX());
			System.out.println(rightPaddle.getX());
         }
      }catch(Exception e)
      {
      }
  	}	
}