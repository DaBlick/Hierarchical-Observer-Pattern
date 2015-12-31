/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable.collections;

import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.HObservable;
import com.barfly.hobservable.NotificationOrder;
import static com.barfly.hobservable.NotificationOrder.POST;
import com.barfly.hobservable.SetChangedMode;
import static com.barfly.hobservable.SetChangedMode.AUTO;
import java.util.Collection;
import java.util.Iterator;

/**
 * This abstract class represents the AbstractCollectionObservable object which extends BaseHObservable and implements the Collection and HObservable Interfaces. 
 * It has access to the same methods and fields in BaseHObservable. An instance of this class is able to use a collection of data and pass information to attached observers as events.
 * @author jonathanodgis
 * @param <E>
 */

public abstract class AbstractCollectionObservable<E> extends BaseHObservable implements Collection<E>, HObservable
{
    public final Collection<E> collection;   

    /**
     * Creates a new AbstractCollectionObservable object using a String observableID, BaseHObservable parentObservable, and a Collection<E> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param collection The observable's collection
     */
    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, Collection<E> collection)   
    {
        super(observableID, parentObservable, POST, AUTO);
        this.collection = collection;
    }    
    
    /**
     * Creates a new AbstractCollectionObservable object using a String observableID, BaseHObservable parentObservable, NotificationOrder order, and a Collection<E> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param order The observable's notification order when notifyObservers() is used
     * @param collection The observable's collection
     */
    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, Collection<E> collection)  
    {
        super(observableID, parentObservable, order, AUTO);
        this.collection = collection;
    }    

    /**
     * Creates a new AbstractCollectionObservable object using a String observableID, BaseHObservable parentObservable, SetChangedOrder setChangedMode, and a Collection<E> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param setChangedMode The setting to determine if the observable needs to be setChanged manually by the user via a setChanged() call or automatically done by the class
     * @param collection The observable's collection
     */
    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, SetChangedMode setChangedMode, Collection<E> collection)   
    {
        super(observableID, parentObservable, POST, setChangedMode);
        this.collection = collection;
    }        
    
    /**
     * Creates a new AbstractCollectionObservable object using a String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedOrder setChangedMode, and a Collection<E> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param order The observable's notification order when notifyObservers() is used
     * @param setChangedMode The setting to determine if the observable needs to be setChanged manually by the user via a setChanged() call or automatically done by the class
     * @param collection The observable's collection
     */
    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedMode setChangedMode, Collection<E> collection)
    {
        super(observableID, parentObservable, order, setChangedMode);
        this.collection = collection;    
    }
    
    /**
     * Returns the size of the collection.
     * @return the size of the collection
     * @see java.util.Collection
     */ 
    @Override
    public int size() 
    {
        return collection.size();
    }

    /**
     * Returns true if this collection contains no elements.
     * @return true if the collection is empty; otherwise false
     * @see java.util.Collection
     */
    @Override
    public boolean isEmpty() 
    {
        return collection.isEmpty();
    }

    /**
     * Returns true if this collection contains the specified element.
     * @param o the object
     * @return true if the collection contains the object; otherwise false
     * @see java.util.Collection
     */
    @Override
    public boolean contains(Object o) 
    {
        return collection.contains(o);
    }

    /**
     * Returns the iterator of the collection.
     * @return the iterator
     * @see java.util.Collection
     */
    @Override
    public Iterator<E> iterator() 
    { 
        return collection.iterator();
    }

    /**
     * Returns an array containing all of the elements in this collection.
     * @return the array containing elements of the collection
     * @see java.util.Collection
     */
    @Override
    public Object[] toArray() 
    {
        return collection.toArray();
    }

    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     * @param a The specified array in which elements of the collection are stored
     * @return the specified array containing elements of the collection
     * @see java.util.Collection
     */
    @Override
    public <T> T[] toArray(T[] a) 
    {
        return collection.toArray(a);
    }

    /**
     * Removes the specified object from the collection.
     * @param o The specified object to be removed.
     * @return true if the object is removed from the collection; otherwise false.
     * @see java.util.Collection
     */
    @Override
    public boolean remove(Object o) 
    {
        return collection.remove(o);
    }

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     * @param c The specified collection containing elements to be checked with the current collection
     * @return true if the collections contains all the elements in the collection 
     * @see java.util.Collection
     */
    @Override
    public boolean containsAll(Collection<?> c) 
    {
        return collection.containsAll(c);
    }

    /**
     * Adds the elements in a specified collection to this collection. 
     * @param c The specified collection containing elements to be added to the current collection
     * @return true if this collection changed as a result of the call
     * @see java.util.Collection
     */
    @Override
    public boolean addAll(Collection<? extends E> c) 
    {  
        return collection.addAll(c);
    }

    /**
     * Removes all of this collection's elements that are also in the specified collection.
     * @param c The specified collection containing elements to be removed from the current collection
     * @return true if this collection changed as a result of the call
     * @see java.util.Collection
     */
    @Override
    public boolean removeAll(Collection<?> c) 
    {
        return collection.removeAll(c);
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection.
     * @param c The specified collection containing elements to be retained in the current collection
     * @return true if this collection changed as a result of the call
     * @see java.util.Collection
     */
    @Override
    public boolean retainAll(Collection<?> c) 
    {
        return collection.retainAll(c);
    }

    /**
     * Compares the specified object with the collection to check for equality.
     * @param o The specified object to be checked with the collection for equality
     * @return true if the specified object and the collection are equal
     * @see java.util.Collection
     */
    @Override
    public boolean equals(Object o) 
    {
        return collection.equals(o);
    }

    /**
     * Returns the hash code value of the collection.
     * @return the hash code value of the collection
     * @see java.util.Collection
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
        return "Observable: " + super.getObservableID();
    }
            
    /**
     * Removes all of the elements from the collection. 
     * @see java.util.Collection
     */    
    @Override
    public void clear() 
    {
        collection.clear();
    }
    
    /**
     * Ensures that this collection contains the specified element.
     * @param e The specified element that is checked for presence in the collection
     * @return true if the collection changed as a result of this call
     * @see java.util.Collection
     */
    @Override
    public boolean add(E e)
    {
        return collection.add(e);
    }

    /**
     * Removes all of this collection's elements that are equal to the specified element.
     * @param e The specified element to be removed from the current collection
     * @return true if this collection changed as a result of the call
     * @see java.util.Collection
     */ 
    public boolean removeAll(E e)
    {
        return collection.removeAll((Collection<?>) e);
    }
}
