package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * This concrete class rerpresents the Ant object which the player controls. The
 * Ant object inherits from the Movable class and also implements the ISteerable
 * interface to allow for steerable movement.
 */
public class Ant extends Movable implements ISteerable {
	/**
	 * This integer attribute represents the maximum speed the Ant object can
	 * achieve
	 */
	private int maximumSpeed;
	/**
	 * This integer attribute represents the Ant object's food level
	 */
	private int foodLevel = 50;
	/**
	 * This integer attribute represents the Ant object's food consumption rate on a
	 * clock tick
	 */
	private int foodConsumptionRate = 10;
	/**
	 * This integer attribute represents the Ant object's health level.
	 */
	private int healthLevel = 10;
	/**
	 * This integer attribute represents the latest flag that the Ant object has
	 * collided with.
	 */
	private int lastFlagReached = 1;

	/**
	 * This constructor takes in parameters for the attributes of location, speed,
	 * size, and then initializes them. The constructor also initializes the
	 * maximumSpeed attribute by double the amount of the set speed and the color of
	 * the Ant object.
	 * 
	 * @param location is a Point type that is used to set the Ant object's location
	 *                 in the game world.
	 * @param speed    is a integer value that represents the speed of the Ant
	 *                 object
	 * @param size     is a integer value that represents the size of the Ant object
	 */
	public Ant(Point location, int speed, int size) {
		super(size);
		this.setHeading(0);
		this.setSpeed(speed);
		this.maximumSpeed = speed * 2;
		this.setColor(ColorUtil.rgb(255, 0, 0));
		// Set location for Ant with the (x,y) values instead of reference to Point
		// object
		this.setLocation(new Point(location.getX(), location.getY()));
	}

	/**
	 * This method returns the Ant object's attribute maximumSpeed
	 * 
	 * @return a integer value of attribute maximumSpeed
	 */
	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	/**
	 * This method returns the Ant object's attribute foodLevel
	 * 
	 * @return a integer value of attribute foodLevel
	 */
	public int getFoodLevel() {
		return foodLevel;
	}

	/**
	 * This method returns the Ant object's attribute foodConsumptionRate
	 * 
	 * @return a integer value of attribute foodConsumptionRate
	 */
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}

	/**
	 * This method returns the Ant object's attribute healthLevel
	 * 
	 * @return a integer value of attribute healthLevel
	 */
	public int getHealthLevel() {
		return healthLevel;
	}

	/**
	 * This method returns the Ant object's attribute lastFlagReached
	 * 
	 * @return a integer value of attribute lastFlagReached
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}

	/**
	 * This method sets the Ant object's attribute lastFlagReached
	 * 
	 * @param lastFlagReached is a integer value that represents the last flag the
	 *                        Ant object has collided with
	 */
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}

	/**
	 * This method sets the Ant object's attribute foodLevel
	 * 
	 * @param foodLevel is a integer value that represents the Ant object's food
	 *                  level
	 */
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	/**
	 * This method sets the Ant object's attribute healthLevel
	 * 
	 * @param healthLevel is a integer value that represents teh Ant object's health
	 */
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}

	/**
	 * This method overrides and implements the method from the interface ISteerable
	 * to allow a representation of the Ant object steering through changing its'
	 * heading attribute.
	 */
	@Override
	public void changeHeading(int heading) {
		this.setHeading(this.getHeading() + heading);
	}

	/**
	 * This method overrides to toString() method and combines the parent's toString
	 * method along with Strings that contains the Ant object attributes of maxSpeed
	 * and foodConsumptionRate.
	 */
	@Override
	public String toString() {
		return new StringBuilder().append("Ant: ").append(super.toString()).append("maxSpeed=")
				.append((int) (((double) this.getHealthLevel() / 10) * this.getMaximumSpeed()))
				.append(" foodConsumptionRate=").append(this.getFoodConsumptionRate()).toString();
	}

}
