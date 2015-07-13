package com.barfly.hobservable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Observable;
import java.util.Stack;
/**
 *
 * @author jonathanodgis
 */
public class StackHObserver implements HObserver 
{   
    private final Stack<Event> events = new Stack<>();
    private final String observerID;
    private HObservable observable;
    
    public StackHObserver(String observerID)   
    {
        this.observerID = observerID;
    }    
    
    /**
     * Updates Observer with eventData and the observable in the form of an event
     * @param observable
     * @param eventData 
     */
    @Override
    public void update(Observable observable, Object eventData) 
    {
        this.events.push(new Event((HObservable) observable, eventData));
        System.out.println("Hey, Observer got an event: " + eventData);
    }                                                                         
    
    /**
     * Removes all the events of the observer by popping the events off the stack until empty
     */
    public void removeEvents()
    {
        while (!events.empty())
        {
            this.events.pop();
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
     * Returns the list of events received by the observer.
     * @return the list of events received by the observer
     */
    public Stack getEvents()
    {
        return events;
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
    

}
