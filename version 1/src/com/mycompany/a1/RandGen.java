package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.models.Point;

/**
 * This class has static methods that serve as helper methods to generate random
 * values for game object attributes and also generate values that have bounds
 * derived from the game world dimensions and origins.
 * 
 * @author Mickey
 */
public class RandGen {
	/**
	 * This static attribute represents the world height
	 */
	private static int worldHeight = 1000;
	/**
	 * This static attribute represents the world length
	 */
	private static int worldLength = 1000;
	/**
	 * This static attribute represents the origin of the world (bottom left =
	 * (0,0))
	 */
	private static Point origin = new Point(0, 0);
	/**
	 * This static object Random is instantiated for ease of use across static
	 * methods
	 */
	private static Random rand = new Random();

	/**
	 * This method takes in an integer value that represents the "size" of
	 * gameObjects and assigns a (x,y) point that is directly in the middle of the
	 * bound in length and height.
	 * 
	 * @param bound represents the collison box "size"
	 * @return Point object that represents the location of GameObject objects
	 */
	public static Point randLocWithBound(int bound) {

		float xPos = rand.nextFloat() * 1000;
		float yPos = rand.nextFloat() * 1000;

		// Example: Bound size = 10 meaning rectangle collision box is 10 by 10.
		// The (x,y) point in the middle for height is 5 and the point in the middle is
		// also 5 for length
		// Therefore the (x,y) point is extended (x + bound/2, y + bound/2) to create
		// the collision
		// box around the point.

		float boundLengthFromMiddle = (float) bound / 2;

		// Checks if the (x,y) point is within the game world
		// if not then redo until (x,y) point is within game world boundary height and
		// length
		while (xPos - boundLengthFromMiddle < origin.getX() || xPos + boundLengthFromMiddle > worldLength) {
			xPos = rand.nextFloat() * worldLength;
		}

		while (yPos - boundLengthFromMiddle < origin.getY() || yPos + boundLengthFromMiddle > worldHeight) {
			yPos = rand.nextFloat() * worldHeight;
		}

		return new Point(xPos, yPos);

	}

	/**
	 * This method returns a random integer between parameter from and to
	 * (inclusive)
	 * 
	 * @param from represents the range a in (a,b) inclusive
	 * @param to   represents the range b in (a,b) inclusive
	 * @return a random integer value between the range of parameters (from,to)
	 */
	public static int randFromToRange(int from, int to) {
		if (from == to) {
			return from;
		} else if (to > from) {
			return rand.nextInt((to + 1) - from) + from;
		}
		return rand.nextInt((from + 1) - to) + to;

	}
}
