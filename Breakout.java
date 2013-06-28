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

public class Breakout extends GraphicsProgram {

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
	
/** Width of a row */
	private static final int ROW_WIDTH =
	  (BRICK_WIDTH * NBRICKS_PER_ROW) + (BRICK_SEP * (NBRICKS_PER_ROW - 1));

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
/** Pause duration in milliseconds */
	private static final int PAUSE_TIME = 2;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	
//Don't forget to set the brick counter and decrease when bricks are hit.
	public void init() {
		addMouseListeners();
		drawBricks();
		getPaddle();
		play();
		gameOver();
		// if isInPlay is false - on click: play
		// Set a isInPlay flag to true.	
	}
	public void play() {
		while ((NBricks > 0) && (NBalls > 0)) {
			getBall();
			NBalls -= 1;
			//wait for click?
			vx = rgen.nextDouble (1.0,3.0);
			if (rgen.nextBoolean(0.5)) vx = -vx;
			vy = 1;
			
			while (true) {
				ball.move(vx,vy);
				pause(PAUSE_TIME);
				double x = ball.getX();
				double y = ball.getY();
				// Tests the location of the ball to check for
				// the following conditions:
				// hits right hand wall:
				if (x >= (getWidth() - BALL_RADIUS)) {
					vx=-vx;
				}
				// hits left hand wall:
				if (x <= 0) vx=-vx;
				// hits top wall:
				if (y <= 0) vy=-vy;
				// hits bottom wall (ends round):
				if (y >= HEIGHT) {
					//Add sound
					break;
				}
			}
			//break if hits bottom or bricks = 0
		}
		
	}
/* Draws 10 rows of bricks of the following colors:
 * RED, ORANGE, YELLOW, GREEN, CYAN.
 */
	private void drawBricks() {
		setBackground(Color.BLACK);
		int y = BRICK_Y_OFFSET;
		drawRow(y,Color.RED);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.RED);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.ORANGE);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.ORANGE);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.YELLOW);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.YELLOW);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.GREEN);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.GREEN);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.CYAN);
		y += BRICK_SEP + BRICK_HEIGHT;
		drawRow(y,Color.CYAN);		
	}
	
	private void getPaddle() {
	paddleY = HEIGHT - (PADDLE_HEIGHT + PADDLE_Y_OFFSET);
	paddle = new GRect ((WIDTH - PADDLE_WIDTH)/2,paddleY,PADDLE_WIDTH,PADDLE_HEIGHT);
	paddle.setColor(Color.WHITE);
	paddle.setFilled(true);
	add(paddle);
	}
	
	private void getBall() {
		ball = new GOval((WIDTH/2-BALL_RADIUS/2),HEIGHT/2,BALL_RADIUS,BALL_RADIUS);
		ball.setFilled(true);
		ball.setColor(Color.WHITE);
		add(ball);
	}
	private void gameOver() {
		GLabel label = new GLabel("GAME OVER");
		label.setFont("Courier-28");
		label.setColor(Color.RED);
		double startX = (getWidth()/2 - label.getWidth()/2);
		double startY = (getHeight()/2);
		label.setLocation(startX,startY);
		
		add(label);
		
		
	}
	private void drawRow(int y, Color c) {
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {
			int x = (WIDTH - ROW_WIDTH) / 2;
			int dx = (BRICK_WIDTH + BRICK_SEP) * i;
			x = x + dx;
			GRect brick = new GRect (x,y,BRICK_WIDTH,BRICK_HEIGHT);
			brick.setColor(c);
			brick.setFilled(true);
			add(brick);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if ((e.getX() > (0 + PADDLE_WIDTH/2)) && (e.getX() < (getWidth() - PADDLE_WIDTH/2))) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH/2,paddleY);
		}
	}
	private GRect paddle;
	private int paddleY;
	private GOval ball;
	private int NBricks = NBRICKS_PER_ROW + NBRICK_ROWS;
	private int NBalls = 3;
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	

	//DEFINE METHODS:
	//private void play()
	//private void hitBrick()
	
} // end class.
