
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
import java.util.ArrayList;
import javax.swing.JFrame;

public class OuterSpace extends Canvas implements KeyListener, Runnable {
  private Ship ship;
  private AlienHorde aliens;
  private Bullets shots;

  /*
   * uncomment once you are ready for this part
   *
   * private AlienHorde horde;
   * private Bullets shots;
   */

  private boolean[] keys;
  private BufferedImage back;

  public OuterSpace(JFrame par) {
    setBackground(Color.black);

    keys = new boolean[5];

    // instantiate other instance variables
    // Ship, Alien
    ship = new Ship(0, 0, 50, 50, 5);
    shots = new Bullets();
    aliens = new AlienHorde(3);
    // aliens.add(new Alien(100, 40, 50, 50, 1));
    // aliens.add(new Alien(200, 40, 50, 50, 1));
    // aliens.add(new Alien(300, 40, 50, 50, 1));
    // aliens.add(new Alien(400, 40, 50, 50, 1));
    // alien1 = new Alien(100, 40, 50, 50, 10);
    // alien2 = new Alien(200, 40, 50, 50, 10);
    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

  public void update(Graphics window) {
    paint(window);
  }

  public void paint(Graphics window) {
    // sets up the double buffering to make the game animation nice and smooth
    Graphics2D twoDGraph = (Graphics2D) window;

    // take a snap shop of the current screen and same it as an image
    // that is the exact same width and height as the current screen
    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));

    // create a graphics reference to the back ground image
    // we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();

    graphToBack.setColor(Color.BLUE);
    window.drawString("StarFighter ", 25, 50);
    graphToBack.setColor(Color.BLACK);
    graphToBack.fillRect(0, 0, 800, 600);
    graphToBack.setColor(Color.BLUE);
    graphToBack.drawString("Points: " + aliens.getPoints(), 730, 20);

    if (keys[0] == true) {
      ship.move("LEFT");
    }
    if (keys[1] == true) {
      ship.move("RIGHT");
    }
    if (keys[2] == true) {
      ship.move("UP");
    }
    if (keys[3] == true) {
      ship.move("DOWN");
    }
    if(keys[4] == true) {
      shots.add(new Ammo(ship.getX(),ship.getY(),5));
      System.out.println("FIRE");
      keys[4] = false;
    }
    // add code to move Ship, Alien, etc.
    
    // add in collision detection to see if Bullets hit the Aliens and if Bullets
    // hit the Ship
   
    ship.draw(graphToBack);
    aliens.SpawnRandom();
    aliens.drawEmAll(graphToBack);
    shots.drawEmAll(graphToBack); 
    shots.moveEmAll();
    shots.cleanEmUp();
    aliens.moveEmAll();
    aliens.removeDeadOnes(shots.getList());
    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[4] = true;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      keys[3] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[4] = false;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e) {
    // no code needed here
  }

  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(5);
        repaint();
      }
    } catch (Exception e) {
    }
  }
}
