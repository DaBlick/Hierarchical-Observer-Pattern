/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author jonathanodgis
 */
public class TestHObserver implements HObserver
{
    //Will include methods of the observer interface or Tournament Observer?
    
    private final List<Event> events = new ArrayList<>();
    private final String observerID;
    private TestHObservable observable;
    
    public TestHObserver(String observerID)   
    {
        this.observerID = observerID;
    }    
   
    @Override
    public void update(Observable observable, Object eventData) 
    {
        this.events.add(new Event((HObservable) observable, eventData));  
        System.out.println("Hey, Observer got an event: " + eventData);     
    }                                                                         
    
    @Override
    public void setObservable(TestHObservable observable)
    {
        this.observable = observable; 
    }
    
    @Override
    public String toString()
    {
        return "Observer: " + this.observerID;
    }

    @Override    
    public List<Event> getEvents()
    {
        return events;
    }

    @Override    
    public boolean isEventPresent(Event check) 
    {
        return events.contains(check);
    }

}
