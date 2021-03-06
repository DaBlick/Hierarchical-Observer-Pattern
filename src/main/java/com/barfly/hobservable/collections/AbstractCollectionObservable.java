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
 *
 * @author jonathanodgis
 * @param <E>
 */

public abstract class AbstractCollectionObservable<E> extends BaseHObservable implements Collection<E>, HObservable
{
    protected final Collection<E> collection;   //arraylist, stack, list (Concrete types) 

    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, Collection<E> collection)   
    {
        super(observableID, parentObservable, POST, AUTO);
        this.collection = collection;
    }    
    
    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, Collection<E> collection)  
    {
        super(observableID, parentObservable, order, AUTO);
        this.collection = collection;
    }    

    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, SetChangedMode setChangedMode, Collection<E> collection)   
    {
        super(observableID, parentObservable, POST, setChangedMode);
        this.collection = collection;
    }        
    
    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedMode setChangedMode, Collection<E> collection)
    {
        super(observableID, parentObservable, order, setChangedMode);
        this.collection = collection;    
    }
    
    /**
     * Returns the size of the collection 
     * @return 
     * @see java.util.Collection
     */ 
   
    
    @Override
    public int size() 
    {
        return collection.size();
    }

    /**
     * Returns boolean value if the collection is empty or not empty
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean isEmpty() 
    {
        return collection.isEmpty();
    }

    /**
     * Returns boolean value if the collection contains the object specified in the parameter
     * @param o
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean contains(Object o) 
    {
        return collection.contains(o);
    }

    /**
     * Returns the iterator of the collection
     * @return 
     * @see java.util.Collection
     */
    @Override
    public Iterator<E> iterator() 
    { 
        return collection.iterator();
    }

    /**
     * Returns the collection as an array
     * @return the collection as an array
     * @see java.util.Collection
     */
    @Override
    public Object[] toArray() 
    {
        return collection.toArray();
    }

    /**
     * 
     * @param <T>
     * @param a
     * @return 
     * @see java.util.Collection
     */
    @Override
    public <T> T[] toArray(T[] a) 
    {
        return collection.toArray(a);
    }

    /**
     * 
     * @param o
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean remove(Object o) 
    {
        /*
        if (this.getSetChangedMode().equals(AUTO))
        {
            super.setChanged();
        }
        */
        return collection.remove(o);
    }

    /**
     * 
     * @param c
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean containsAll(Collection<?> c) 
    {
        return collection.containsAll(c);
    }

    /**
     * 
     * @param c
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean addAll(Collection<? extends E> c) 
    {
        /*
        if (this.getSetChangedMode().equals(AUTO))
        {
            super.setChanged();
        }
        */      
        return collection.addAll(c);
    }

    /**
     * 
     * @param c
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean removeAll(Collection<?> c) 
    {
        /*
        if (this.getSetChangedMode().equals(AUTO))
        {
            super.setChanged();
        } 
        */
        return collection.removeAll(c);
    }

    /**
     * 
     * @param c
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean retainAll(Collection<?> c) 
    {
        /*
        if (this.getSetChangedMode().equals(AUTO))
        {
            super.setChanged();
        } 
        */
        return collection.retainAll(c);
    }

    /**
     * 
     * @param o
     * @return
     * @see java.util.Collection
     */
    @Override
    public boolean equals(Object o) 
    {
        return collection.equals(o);
    }

    /**
     * 
     * @return 
     * @see java.util.Collection
     */
    @Override
    public int hashCode() 
    {
        return collection.hashCode();
    }

    /**
     * 
     * @return 
     * @see java.util.Collection
     */
    @Override
    public String toString() 
    {
        return "Observable: " + super.getObservableID();
    }
            
    /**
     * @see java.util.Collection
     */    
    @Override
    public void clear() 
    {
        /*
        if (this.getSetChangedMode().equals(AUTO))
        {
            super.setChanged();
        } 
        */
        collection.clear();
    }
    
    /**
     * 
     * @param e
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean add(E e)
    {
        /*
        if (this.getSetChangedMode().equals(AUTO))
        {
            super.setChanged();
        }
        */
        return collection.add(e);
    }
   
    /**
     * 
     * @param e
     * @return 
     * @see java.util.Collection
     */
    public boolean removeAll(E e)
    {
        /*
        if (this.getSetChangedMode().equals(AUTO))
        {
            super.setChanged();
        } 
        */
        return collection.removeAll((Collection<?>) e);
    }
}
