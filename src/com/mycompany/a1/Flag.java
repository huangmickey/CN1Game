package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * This concrete class Flag inherits the Fixed class and is representative of
 * flags in the game world which act as checkpoints.
 * 
 * @author Mickey
 */
public class Flag extends Fixed {
	/**
	 * This integer attribute represents the flag number in sequence
	 */
	private int sequenceNumber;
	/**
	 * This static integer attribute act as a counter to autoincrement the
	 * sequenceNumber attribute on instantiaton from the constructor.
	 */
	private static int sequenceCounter;

	/**
	 * This constructor takes in the size and location to be set for the Flag object
	 * on instantiation. The constructor sets a blue color for the flags and also
	 * increment the static variable sequenceCounter.
	 * 
	 * @param size     represents the size of the Flag object.
	 * @param location represents the location of the Flag object.
	 */
	public Flag(int size, Point location) {
		super(size, location);
		super.setColor(ColorUtil.rgb(0, 0, 255));
		this.sequenceNumber = ++sequenceCounter;
	}

	/**
	 * This method returns the sequence number of the Flag object.
	 * 
	 * @return an integer value that represents the flag number.
	 */
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}

	/**
	 * This method resets the sequenceCounter attribute for the beginning of new
	 * rounds within the game.
	 */
	public void resetSequenceCounter() {
		sequenceCounter = 0;
	}

	/**
	 * This method overrides the parent setColor method with an empty implementation
	 * to deny access to the setter of the color attribute.
	 */
	@Override
	public void setColor(int color) {

	}

	/**
	 * This method overrides the toString() method and extends its' parent
	 * toString() method with String value of its attribute, sequenceNumber.
	 */
	@Override
	public String toString() {

		return new StringBuilder().append("Flag: ").append(super.toString()).append("seqNum=")
				.append(this.sequenceNumber).toString();
	}
}
