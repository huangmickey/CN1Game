package com.mycompany.a2.gameobjects;

/**
 * Interface which represents an iterator design pattern and provides the
 * blueprint for an iterator.
 * 
 * @author Mickey Huang
 *
 */
public interface IIterator {
	public boolean hasNext();
	public GameObject getNext();
}
