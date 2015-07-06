package com.barfly.hobservable;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.barfly.hobservable.HObservable;
import java.util.Observable;

/**
 *
 * @author jonathanodgis
 */
public class Event 
{
    private HObservable observable;
    private Object eventData;
    
    public Event(HObservable observable, Object eventData)
    {
        this.eventData = eventData;
        this.observable = observable;
    }
    
    /**
     * Returns the event data of the Event object
     * @return the eventData
     */
    public Object getEventData()
    {
        return this.eventData;
    }
    
    /**
     * Returns the observable that notified the observer of the event data.
     * @return the observable 
     */
    public HObservable getObservable()
    {
        return this.observable;
    }
    
    /**
     * Returns the string of the observable.
     * @return the string of the observable
     */
    @Override
    public String toString()
    {
        //return "Observable " + this.getObservable().toString();
        return this.eventData + " | " + this.observable.toString();
    }    

    @Override
    public boolean equals(Object obj) 
    {
        if (!(obj instanceof Event))
        {
            return false;
        }
        Event event = (Event) obj;
        return this.observable == event.observable && this.eventData == event.eventData;
    }
    
    
}
