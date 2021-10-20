package com.mycompany.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;

/**
 * This concrete class represents a Food Station within the game world that an
 * the player can interact with. This class serves to represent a object that
 * allows for the player to collide with and replenish food levels based on the
 * capacity attribute of this class. The FoodStation object has a food capacity
 * that is proportional to its size therefore if the size is 10, then the
 * FoodStation's attribute capacity, will also automatically be initialized as
 * 10.
 * 
 * @author Mickey
 */
public class FoodStation extends Fixed {
	/**
	 * The capacity integer attribute represents how much "food" that this
	 * FoodStation carries in the game world.
	 */
	private int capacity;

	/**
	 * The FoodStation default constructor allows for the instantiation of a
	 * Foodstation object and it initializes the capacity to its size proportionally
	 * and also sets a green color.
	 */
	public FoodStation() {
		this.capacity = this.getSize();
		this.setColor(ColorUtil.rgb(12, 107, 12));
	}

	/**
	 * This method returns the capacity value of the FoodStation object.
	 * 
	 * @return an integer value representative of the capacity of food.
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * This method sets the capacity of the FoodStation object.
	 * 
	 * @param capacity is the parameter that will be set to for this FoodStation
	 *                 object.
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * This method overrides the toString() method which extends it's parents
	 * toString() method by adding the returned String with another String that
	 * contains this object's unique attribute capacity.
	 */
	@Override
	public String toString() {

		return new StringBuilder().append("FoodStation: ").append(super.toString()).append("capacity=")
				.append(this.capacity).toString();
	}
}
