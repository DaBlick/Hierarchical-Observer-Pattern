/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable.collections;

import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.NotificationOrder;
import static com.barfly.hobservable.NotificationOrder.POST;
import com.barfly.hobservable.SetChangedMode;
import static com.barfly.hobservable.SetChangedMode.AUTO;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This class represents the ListObservable object which extends AbstractCollectionObservable and implements the List Interface. 
 * The class uses the constructors in AbstractCollectionObservable to create objects and uses the methods in the super class to modify the list. An instance of this class is able to use a list of data and pass information to attached observers as events.
 * @author jonathanodgis
 * @param <T>
 */
public class ListObservable<T> extends AbstractCollectionObservable implements List   //list looks at collection interface
{   
    /**
     * Creates a new ListObservable object using a String observableID, BaseHObservable parentObservable, and a Collection<T> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param collection The observable's collection
     */
    public ListObservable(String observableID, BaseHObservable parentObservable, Collection<T> collection)   
    {
        super(observableID, parentObservable, POST, AUTO, collection);
    }    
    
    /**
     * Creates a new ListObservable object using a String observableID, BaseHObservable parentObservable, NotificationOrder order, and a Collection<T> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param order The observable's notification order when notifyObservers() is used
     * @param collection The observable's collection
     */
    public ListObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, Collection<T> collection)  
    {
        super(observableID, parentObservable, order, AUTO, collection);
    }    

    /**
     * Creates a new ListObservable object using a String observableID, BaseHObservable parentObservable, SetChangedOrder setChangedMode, and a Collection<T> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param setChangedMode The setting to determine if the observable needs to be setChanged manually by the user via a setChanged() call or automatically done by the class
     * @param collection The observable's collection
     */
    public ListObservable(String observableID, BaseHObservable parentObservable, SetChangedMode setChangedMode, Collection<T> collection)   
    {
        super(observableID, parentObservable, POST, setChangedMode, collection);
    }        
    
    /**
     * Creates a new ListObservable object using a String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedOrder setChangedMode, and a Collection<T> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param order The observable's notification order when notifyObservers() is used
     * @param setChangedMode The setting to determine if the observable needs to be setChanged manually by the user via a setChanged() call or automatically done by the class
     * @param collection The observable's collection
     */
    public ListObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedMode setChangedMode, Collection<T> collection)
    {
        super(observableID, parentObservable, order, setChangedMode, collection);   
    }

    /**
     * Returns the size of the list.
     * @return the size of the list
     * @see java.util.List
     */
    @Override
    public int size() {
        return collection.size();
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if the list is empty; otherwise false
     * @see java.util.List
     */
    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     * Returns boolean value if the list contains the object specified in the parameter.
     * @param o the object
     * @return true if the list contains the object; otherwise false
     * @see java.util.List
     */
    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    /**
     * Returns the iterator of the list.
     * @return the iterator
     * @see java.util.List
     */
    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    /**
     * Returns an array containing all of the elements in this list.
     * @return the array containing elements of the list
     * @see java.util.List
     */
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    /**
     * Removes the specified object from the list.
     * @param o The specified object to be removed.
     * @return true if the object is removed from the list; otherwise false.
     * @see java.util.List
     */
    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    /**
     * Removes all of the elements from the list. 
     * @see java.util.List
     */    
    @Override
    public void clear() {
        collection.clear();
    }

    /**
     * Compares the specified object with the list to check for equality.
     * @param o The specified object to be checked with the list for equality
     * @return true if the specified object and the list are equal
     * @see java.util.List
     */
    @Override
    public boolean equals(Object o) {
        return collection.equals(o);
    }

    /**
     * Returns the hash code value of the list.
     * @return the hash code value of the list
     * @see java.util.List
     */
    @Override
    public int hashCode() 
    {
        return collection.hashCode();
    }

    /**
     * Returns the name of the observable.
     * @return the name of the observable
     * @see com.barfly.hobservable.BaseHObservable
     */
    @Override
    public String toString() 
    {
        return super.toString();
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position.
     * @param index index at which to insert the first element from the specified collection
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @see java.util.List
     */
    @Override
    public boolean addAll(int index, Collection c) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param index
     * @return 
     * @see java.util.List
     */
    @Override
    public Object get(int index) 
    {
        return collection.toArray()[index];
    }
    
    /**
     * 
     * @param index
     * @param element
     * @return 
     * @see java.util.List     
     */
    @Override
    public Object set(int index, Object element) 
    {
        return collection.toArray()[index] = element;
    }

    /**
     * 
     * @param index
     * @param element 
     * @see java.util.List 
     */
    @Override
    public void add(int index, Object element) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param index
     * @return 
     * @see java.util.List
     */
    @Override
    public Object remove(int index) 
    {    
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param o
     * @return 
     * @see java.util.List
     */
    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param o
     * @return 
     * @see java.util.List
     */
    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     * @see java.util.List
     */
    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param index
     * @return 
     * @see java.util.List
     */
    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param fromIndex
     * @param toIndex
     * @return 
     * @see java.util.List
     */
    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
