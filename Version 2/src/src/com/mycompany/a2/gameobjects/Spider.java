package com.mycompany.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a2.RandGen;

/**
 * This concrete class represents a spider in the game world. The Spider class
 * inherits Movable and symbolizes as an enemy in the game where when collision
 * happens between the Spider and the player, the player loses health value.
 * 
 * @author Mickey
 */
public class Spider extends Movable {

	/**
	 * This constructor instantiates and initializes a Spider object with a random
	 * heading from 0-400 and speed from (5-10) along with a integer color value of
	 * black.
	 */
	public Spider() {
		super(RandGen.randFromToRange(0, 400), RandGen.randFromToRange(5, 10)); // inclusive (a,b)
		super.setColor(ColorUtil.rgb(0, 0, 0));
	}

	/**
	 * This method overrides the parent method move() and represents the movement of
	 * a spider where at every tick the heading of the Spider object changes.
	 */
	@Override
	public void move() {
		super.setHeading(this.getHeading() + RandGen.randFromToRange(-5, 5)); // inclusive (a,b)
		super.move();
	}

	/**
	 * This method overrides the parent method with an empty implementation to deny
	 * access to setting a new color attribute value.
	 */
	@Override
	public void setColor(int color) {
	}

	/**
	 * This method overrides the parent method with an empty implementation to deny
	 * access to setting a new speed attribute value.
	 */
	@Override
	public void setSpeed(int speed) {

	}

	/**
	 * This method overrides the parent method with an empty implementation to deny
	 * access to setting a new header attribute value.
	 */
	@Override
	public void setHeading(int heading) {

	}

	/**
	 * This method overrides the toString() method and returns a String description
	 * of the object.
	 */
	@Override
	public String toString() {
		return new StringBuilder().append("Spider: ").append(super.toString()).toString();
	}
}
