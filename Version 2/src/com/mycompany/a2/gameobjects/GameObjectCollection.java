package com.mycompany.a2.gameobjects;

import java.util.ArrayList;

/**
 * The GameObjectCollection class serves to hide the implementation of the
 * collection data structure of the GameObjects from the client class Game. This
 * class serves as a structure for GameObjects and returns an iterator to allow
 * clients to iterate through the collection.
 * 
 * @author Mickey Huang
 *
 */
public class GameObjectCollection implements ICollection {
	/**
	 * ArrayList of type GameObject represents the collection which stores
	 * GameObjects
	 */
	private ArrayList<GameObject> gameObjectList;

	/**
	 * This constructor instantiates and initializes gameObjectList
	 */
	public GameObjectCollection() {
		this.gameObjectList = new ArrayList<GameObject>();
	}

	/**
	 * This method allows GameObjects to be added to the gameObjectList
	 */
	@Override
	public void add(GameObject gameObject) {
		this.gameObjectList.add(gameObject);
	}

	/**
	 * This method returns an IIterator to the caller which allows for iteratation
	 * of the gameObjectList
	 */
	@Override
	public IIterator getIterator() {
		return new GameObjectCollectionIterator();

	}

	/**
	 * This private class represents the an iterator for the gameObjectList which
	 * keep tracks of the current index and provides methods for iterating through a
	 * list of GameObjects.
	 * 
	 * @author Mickey Huang
	 *
	 */
	private class GameObjectCollectionIterator implements IIterator {
		/**
		 * This int variable keeps track of the index of the iterator
		 */
		private int currObjectIndex;

		/**
		 * Constructor sets iterator to -1
		 */
		public GameObjectCollectionIterator() {
			this.currObjectIndex = -1;
		}

		/**
		 * This method returns the test value of whether or not the gameObjectList still
		 * has an element in the next index
		 */
		@Override
		public boolean hasNext() {
			if (gameObjectList.size() <= 0 || currObjectIndex + 1 == gameObjectList.size())
				return false;
			return true;
		}

		/**
		 * This method returns the next element in the gameObjectList and increases the
		 * current index by 1.
		 */
		@Override
		public GameObject getNext() {
			return gameObjectList.get(++currObjectIndex);
		}

	}
}
