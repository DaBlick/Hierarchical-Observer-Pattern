/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable.collections;

import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.NotificationOrder;
import static com.barfly.hobservable.NotificationOrder.POST;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author jonathanodgis
 */

public abstract class AbstractCollectionObservable<E> extends BaseHObservable implements Collection<E> 
{
    protected final Collection<E> collection;   //arraylist, stack, list (Concrete types) 
    
    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, Collection<E> collection)
    {
        super(observableID, parentObservable, POST);
        this.collection = collection;
    }
   
    public AbstractCollectionObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, Collection<E> collection)  
    {
        super(observableID, parentObservable, order);
        this.collection = collection;
    }
    

    //TODO Add comments to each of these

    /**
     * 
     * @return 
     * @see java.util.Collection
     */
    @Override
    public int size() {
        return collection.size();
    }

    /**
     * 
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     * 
     * @param o
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    /**
     * 
     * @return 
     * @see java.util.Collection
     */
    @Override
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    /**
     * 
     * @return 
     * @see java.util.Collection
     */
    @Override
    public Object[] toArray() {
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
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    /**
     * 
     * @param o
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    /**
     * 
     * @param c
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    /**
     * 
     * @param c
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return collection.addAll(c);
    }

    /**
     * 
     * @param c
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return collection.removeAll(c);
    }

    /**
     * 
     * @param c
     * @return 
     * @see java.util.Collection
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    /**
     * 
     * @param o
     * @return
     * @see java.util.Collection
     */
    @Override
    public boolean equals(Object o) {
        return collection.equals(o);
    }

    /**
     * 
     * @return 
     * @see java.util.Collection
     */
    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    /**
     * 
     * @return 
     * @see java.util.Collection
     */
    @Override
    public String toString() {
        return collection.toString();
    }
            
    /**
     * @see java.util.Collection
     */    
    @Override
    public void clear() 
    {
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
        return collection.removeAll((Collection<?>) e);
    }
    
    
    
    
    
}
