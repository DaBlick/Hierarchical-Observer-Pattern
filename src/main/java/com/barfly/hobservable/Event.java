/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

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
    
    public Object getEventData()
    {
        return this.eventData;
    }
    
    public HObservable getObservable()
    {
        return this.observable;
    }
    
    @Override
    public String toString()
    {
        //return "Observable " + this.getObservable().toString();
        return "Observable " + this.observable;
    }    
}
