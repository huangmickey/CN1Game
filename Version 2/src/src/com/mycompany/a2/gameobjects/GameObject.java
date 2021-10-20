package com.mycompany.a2.gameobjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a2.RandGen;

/**
 * This abstract class GameObject contains basic fields and behaviours of all
 * types of GameObject which include attributes of a size, location, and color.
 * There are two other objects of type GameObject that will inherit from this
 * class which are Fixed and Movable objects.
 * 
 * @author Mickey
 */
public abstract class GameObject {
	/**
	 * This field represents the size of a GameObject.
	 */
	private int size;
	/**
	 * This field represents the location, in the form of a Point type, of a
	 * GameObject.
	 */
	private Point location;
	/**
	 * This field represents the color of the GameObject using CN1's ColorUtil
	 * class.
	 */
	private int color;

	/**
	 * This default constructor initializes the size attribute through the static
	 * method from RandGen class which returns a random integer from 10 - 50. This
	 * constructor also initializes the location attribute through the same static
	 * class with a random Point within the game canvas size of (1000,1000).
	 */
	public GameObject() {
		// Method calls to get random ranges (inclusive) for size and location within
		// map bounds
		this.size = RandGen.randFromToRange(10, 50);
		this.location = RandGen.randLocWithBound(this.size);
	}

	/**
	 * This overloaded constructor takes a parameter size which overrides the random
	 * generation of a size through the default constructor. This constructor serves
	 * the purpose of allowing child classes to access the size attribute without a
	 * proper public setter method for the field size.
	 * 
	 * @param size represents the size of the GameObject.
	 */
	public GameObject(int size) {
		this.size = size;
	}

	/**
	 * This method returns a int value that represents the size of the GameObject.
	 * 
	 * @return int value that represents the size of the object.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * This method returns the Point object of the GameObject.
	 * 
	 * @return a Point type that contains the x and y values of the GameObject.
	 */
	public Point getLocation() {
		return this.location;
	}

	/**
	 * This method sets the location attribute of the GameObject with the parameter.
	 * 
	 * @param location represents the Point object to be set to.
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * This method returns the color value of the GameObject.
	 * 
	 * @return a integer value of the color.
	 */
	public int getColor() {
		return this.color;
	}

	/**
	 * This method sets the color value of the GameObject with the parameter color.
	 * 
	 * @param color represents the color attribute to be set to.
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * This method overrides the default object toString() method and returns a
	 * String that represents the GameObject's attributes of location, size, and
	 * color. The location attribute is formatted with one decimal.
	 */
	@Override
	public String toString() {
		// Returns a String representation of GameObject through appending method with
		// StringBuilder class
		return new StringBuilder().append("loc=").append(Math.round(this.getLocation().getX() * 10.0) / 10.0)
				.append(",").append(Math.round(this.getLocation().getY() * 10.0) / 10.0).append(" color=[")
				.append(ColorUtil.red(this.getColor())).append(",").append(ColorUtil.green(this.getColor())).append(",")
				.append(ColorUtil.blue(this.getColor())).append("] size=").append(this.getSize()).append(" ")
				.toString();
	}
}
