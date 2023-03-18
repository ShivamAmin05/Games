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


		keys = new boolean[18];

    
    	setBackground(Color.BLACK);
		setVisible(true);
		
		new Thread(this).start();
		addKeyListener(this);		//starts the key thread to log key strokes
	}
	public void update(Graphics window){
		paint(window);
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
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, 800, 600);
		graphToBack.setColor(Color.CYAN);
	    graphToBack.drawString(""+p1Score,340,20);
	    graphToBack.drawString(""+p2Score,440,20);
	    graphToBack.setColor(Color.WHITE);
		graphToBack.drawRect(390, 0, 10, 600);


		ball.moveAndDraw(graphToBack);
		leftPaddle.draw(graphToBack);
		rightPaddle.draw(graphToBack);

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
			ball.setXSpeed(-ball.getXSpeed());
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
		if(keys[4] == true && keys[5] == true)
		{
			leftPaddle.setHeight(250);
		}
		if(keys[6] == true && keys[7] == true)
		{
			rightPaddle.setHeight(250);
		}
		if(p2Score - p1Score >= 5)
		{
			ball.setHeight(10);
			ball.setWidth(10);
			if(ball.getX() <= 400)
			{
				ball.setHeight(75);
				ball.setWidth(75);
			}
		}
		if(p1Score - p2Score >= 5)
		{
			ball.setHeight(10);
			ball.setWidth(10);
			if(ball.getX() >= 400)
			{
				ball.setHeight(75);
				ball.setWidth(75);
			}
		}

		if(keys[8] == true)
		{
			ball.setColor(Color.BLUE);
		}
		if(keys[9] == true)
		{
			ball.setColor(Color.RED);
		}
		if(keys[10] == true)
		{
			ball.setColor(Color.CYAN);
		}
		if(keys[11] == true)
		{
			ball.setColor(Color.PINK);
		}
		if(keys[12] == true)
		{
			ball.setColor(Color.GREEN);
		}
		if(keys[13] == true)
		{
			ball.setColor(Color.ORANGE);
		}
		if(keys[14] == true)
		{
			ball.setColor(Color.YELLOW);
		}
		if(keys[15] == true)
		{
			ball.setColor(Color.WHITE);
		}
		if(keys[16] == true)
		{
			ball.setColor(Color.YELLOW);
		}
		if(keys[17] == true)
		{
			ball.setColor(Color.GRAY);
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
			case 'A' : keys[4]=true; break;
			case 'D' : keys[5]=true; break;
			case 'J' : keys[6]=true; break;
			case 'L' : keys[7]=true; break;
			case '1' : keys[8]=true; break;
			case '2' : keys[9]=true; break;
			case '3' : keys[10]=true; break;
			case '4' : keys[11]=true; break;
			case '5' : keys[12]=true; break;
			case '6' : keys[13]=true; break;
			case '7' : keys[14]=true; break;
			case '8' : keys[15]=true; break;
			case '9' : keys[16]=true; break;
			case '0' : keys[17]=true; break;
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
			case 'A' : keys[4]=true; break;
			case 'D' : keys[5]=true; break;
			case 'J' : keys[6]=true; break;
			case 'L' : keys[7]=true; break;
			case '1' : keys[8]=true; break;
			case '2' : keys[9]=true; break;
			case '3' : keys[10]=true; break;
			case '4' : keys[11]=true; break;
			case '5' : keys[12]=true; break;
			case '6' : keys[13]=true; break;
			case '7' : keys[14]=true; break;
			case '8' : keys[15]=true; break;
			case '9' : keys[16]=true; break;
			case '0' : keys[17]=true; break;
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
         }
      }catch(Exception e)
      {
      }
  	}	
}