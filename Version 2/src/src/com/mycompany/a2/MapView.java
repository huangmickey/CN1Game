package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

/**
 * MapView class is a part of the Observer design pattern where this class
 * serves as a view of the GameWorld.
 * 
 * @author Mickey Huang
 *
 */
public class MapView extends Container implements Observer {

	/**
	 * Default constructor empty for future use.
	 */
	public MapView() {

	}

	/**
	 * This method updates any corresponding changes to the GameWorld and updates
	 * the labels here for view
	 */
	@Override
	public void update(Observable observable, Object data) {
	}

}
