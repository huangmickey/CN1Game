package com.mycompany.a2.gameobjects;

/**
 * Interface which is needed for the iterator design pattern and provides the
 * blueprint for a custom collection that allows for adding GameObjects and
 * accessing the iterator.
 * 
 * @author Mickey Huang
 *
 */
public interface ICollection {
	public void add(GameObject gameObject);
	public IIterator getIterator();
}
