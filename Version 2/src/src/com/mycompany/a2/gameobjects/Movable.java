package com.mycompany.a2.gameobjects;

/**
 * This abstract class Movable inherits the GameObject class and extends the
 * attributes/behaviours. This class is representative of game world object that
 * have a "movable" location and is allowed for its location to be moved around
 * the game world.
 * 
 * @author Mickey
 */
public abstract class Movable extends GameObject {
	/**
	 * This integer attribute represents the heading (direction of movement).
	 */
	private int heading;
	/**
	 * This integer attribute represents the speed of the Movable object.
	 */
	private int speed;

	/**
	 * This constructor takes in parameters heading and speed which is initialized
	 * to an instance of Movable's attributes.
	 * 
	 * @param heading is an integer value representing the heading (direction) of
	 *                the object
	 * @param speed   is an integer value representing the speed of the object.
	 */
	public Movable(int heading, int speed) {
		this.heading = heading;
		this.speed = speed;
	}

	/**
	 * This constructor allows for a Movable object to be instantiated and
	 * initialized with a custom size value instead of the random generated size
	 * value in the GameObject class.
	 * 
	 * @param size is an integer value representing the size of the object.
	 */
	public Movable(int size) {
		super(size);
	}

	/**
	 * This method "moves" a Movable object on the game world map. This method uses
	 * a formula to update the object's new change in x and y values derived from
	 * the object's heading and speed.
	 */
	public void move() {
		float changeInX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
		float changeInY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);

		this.getLocation().setX(this.getLocation().getX() + changeInX);
		this.getLocation().setY(this.getLocation().getY() + changeInY);
	}

	/**
	 * This method returns the speed of the object.
	 * 
	 * @return an integer value representing the speed of the object.
	 */
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * This method returns the heading of the object.
	 * 
	 * @return an integer value that represents the direction of movement of the
	 *         object.
	 */
	public int getHeading() {
		return this.heading;
	}

	/**
	 * This method allows for a Movable object to set its heading attribute.
	 * 
	 * @param speed represents the integer value to be replaced with in the speed
	 *              attribute.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * This method allows for a Movable object to set its heading attribute.
	 * 
	 * @param heading represents the integer value to be replaced with in the
	 *                heading attribute.
	 */
	public void setHeading(int heading) {
		this.heading = heading;
	}

	/**
	 * This method overrides its' parent toString() method and extends it by adding
	 * in String descriptions of its' attributes, heading and speed.
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(super.toString()).append("heading=").append(this.getHeading())
				.append(" speed=").append(this.getSpeed()).append(" ").toString();
	}
}
