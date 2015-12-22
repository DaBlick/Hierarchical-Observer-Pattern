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
 *
 * @author jonathanodgis
 * @param <T>
 */
public class ListObservable<T> extends AbstractCollectionObservable implements List   //list looks at collection interface
{   
    public ListObservable(String observableID, BaseHObservable parentObservable, Collection<T> collection)   
    {
        super(observableID, parentObservable, POST, AUTO, collection);
    }    
    
    public ListObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, Collection<T> collection)  
    {
        super(observableID, parentObservable, order, AUTO, collection);
    }    

    public ListObservable(String observableID, BaseHObservable parentObservable, SetChangedMode setChangedMode, Collection<T> collection)   
    {
        super(observableID, parentObservable, POST, setChangedMode, collection);
    }        
    
    public ListObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedMode setChangedMode, Collection<T> collection)
    {
        super(observableID, parentObservable, order, setChangedMode, collection);   
    }
    /**
     * @see com.barfly.hobservable.BaseHObservable
     */
    
    /**
     *
     * @return
     * @see com.barfly.hobservable.BaseHObservable
     */
    @Override
    public int size() {
        return collection.size();
    }

    /**
     * 
     * @return 
     */
    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    /**
     * 
     * @return 
     */
    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    /**
     * 
     * @return 
     */
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    /**
     * 
     */
    @Override
    public void clear() {
        collection.clear();
    }

    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        return collection.equals(o);
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() 
    {
        return super.toString();
    }

    /**
     * 
     * @param index
     * @param c
     * @return 
     */
    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param index
     * @return 
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
     */
    @Override
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param index
     * @param element 
     */
    @Override
    public void add(int index, Object element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public Object remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param index
     * @return 
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
     */
    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
