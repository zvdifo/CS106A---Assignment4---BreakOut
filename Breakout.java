/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram{

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	public void run() {
		/* You fill this in, along with any subsidiary methods */
		GRect paddle = makePaddle();
		/*GObject bricks = makeBricks();*/
		GOval ball = makeBall();
		add(paddle);
		/*add(bricks);*/
		add(ball);
		moveBall(ball,paddle);
		addMouseListeners();
	}		
	
	
	/* make a paddle which can be controlled by the mouse*/
	public GRect makePaddle(){
		double x = (APPLICATION_WIDTH - PADDLE_WIDTH)/2; 
		double y = APPLICATION_HEIGHT - PADDLE_Y_OFFSET;
		paddle = new GRect(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		add(paddle);
	}	
		
	public void mouseMoved(MouseEvent e){
		paddle.setLocation(e.getX() - PADDLE_WIDTH/2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT); 	    
	}
	
	private GRect paddle;
	  
	

	
	
	

	/* @return A ball that can be bounced.
	 */
	public GOval makeBall(){
		int x = (APPLICATION_WIDTH - BALL_RADIUS )/2; 
		int y = (APPLICATION_HEIGHT - BALL_RADIUS)/2;
		GOval ball = new GOval(x,y,BALL_RADIUS,BALL_RADIUS);
		return ball;
	}
	
	/*set vx,vy to make ball move*/
	private void moveBall(GOval ball,GRect paddle){
		double vy = 3.0;
		RandomGenerator rgen = RandomGenerator.getInstance();
		double vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)){
			vx = -vx;
		}	
		GObject collider = getCollidingObject(ball);
		while (true){
			if (ball.getX() == 0.0){
				ball.move(-vx, vy);
			}
			if (ball.getY() == 0.0){
				ball.move(vx, -vy);
			}
			if (ball.getX() == APPLICATION_WIDTH-BALL_RADIUS){
				ball.move(-vx, vy);
			}
			if (ball.getY() == APPLICATION_HEIGHT-BALL_RADIUS){
				ball.move(vx, -vy);
			}
			if (collider == paddle){
				ball.move(vx, -vy);
			}
			 
			
		}
		
	}
	/*
	 * check if there is a collision.
	 */
	private GObject getCollidingObject(GOval ball){
		if (getElementAt(ball.getX(),ball.getY())!= null){
			/**strange*/
			GObject collider = getElementAt(ball.getX(),ball.getY());
			return collider;
		}
		else if (getElementAt(ball.getX()+ 2*BALL_RADIUS,ball.getY())!= null){
			GObject collider = getElementAt(ball.getX()+ 2*BALL_RADIUS,ball.getY());	
			return collider;
		}
		else if (getElementAt(ball.getX(),ball.getY()+ 2*BALL_RADIUS)!= null){
			GObject collider = getElementAt(ball.getX(),ball.getY()+ 2*BALL_RADIUS);
			return collider;
		}
		else if (getElementAt(ball.getX()+ 2*BALL_RADIUS,ball.getY()+ 2*BALL_RADIUS)!= null){
			GObject collider = getElementAt(ball.getX()+ 2*BALL_RADIUS,ball.getY()+ 2*BALL_RADIUS);	
			return collider;
		}
		else{
			return null;
		}
	}
	
	
	
	
	


}











