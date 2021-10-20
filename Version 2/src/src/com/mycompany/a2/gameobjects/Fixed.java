package com.mycompany.a2.gameobjects;

import com.codename1.charts.models.Point;

/**
 * This abstract class Fixed inherits the GameObject class and extends the
 * attributes/behaviours. This class is representative of game world objects
 * that have a "fixed" location and do not intend to be moved around in the game
 * world.
 * 
 * @author Mickey
 */
public abstract class Fixed extends GameObject {

	/**
	 * This is a default constructor for the Fixed class
	 */
	public Fixed() {
	}

	/**
	 * This is an overloaded constructor that takes in the size and location for a
	 * GameObject. This constructor serves the purpose for the Flag object to have
	 * its attributes size and location set with the restriction that it does not
	 * have access to the setLocation method in GameObject class and a proper public
	 * setter field for the size attribute.
	 * 
	 * @param size     represents the size of the GameObject.
	 * @param location represents the location of the GameObject.
	 */
	public Fixed(int size, Point location) {
		super(size);
		super.setLocation(location);
	}

	/**
	 * This method overrides the setLocation of the super class, GameObject, which
	 * is meant to deny access to the setter method of the GameObject class for the
	 * attribute, location by having an empty body implementation. This is for the
	 * purpose that "fixed" game objects are not allowed to change their location
	 * after runtime.
	 */
	@Override
	public void setLocation(Point location) {
	}

}
