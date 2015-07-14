package com.barfly.hobservable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
/**
 *
 * @author jonathanodgis
 */
public class CollectionHObserver implements HObserver, Collection
{   
    private final Collection events;
    private final String observerID;
    private HObservable observable;
    
    public CollectionHObserver(String observerID, Collection events)   
    {
        this.observerID = observerID;
        this.events = events;
    }    
    
    /**
     * Updates Observer with eventData and the observable in the form of an event
     * @param observable
     * @param eventData 
     */
    @Override
    public void update(Observable observable, Object eventData) 
    {
        this.events.add(new Event((HObservable) observable, eventData));
        System.out.println("Hey, Observer got an event: " + eventData);
    }                                                                         
    
    /**
     * Removes all the events of the observer by popping the events off the stack until empty
     */
    public void removeEvents()
    {
        int i = 0;
        while (!events.isEmpty())
        {
            this.events.remove(i);
            i++;
        }
    }
    
    /**
     * Sets the observable of the observer. 
     * @param observable 
     */
    public void setObservable(BaseHObservable observable)
    {
        this.observable = observable; 
    }
    
    /**
     * Returns the name of the observer.
     * @return the name of the observer
     */
    @Override
    public String toString()
    {
        return "Observer: " + this.observerID;
    }

    /**
     * Returns true or false depending if the list contains the event specified in the parameter. 
     * @param check
     * @return true or false 
     */
    public boolean isEventPresent(Event check) 
    {
        return events.contains(check);
    }

    @Override
    public int size() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Object o) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator iterator() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray(Object[] a) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Object e) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
