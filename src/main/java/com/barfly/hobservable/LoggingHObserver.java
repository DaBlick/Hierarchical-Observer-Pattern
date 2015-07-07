/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

import java.util.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger;
/**
 *
 * @author jonathanodgis
 */
public class LoggingHObserver implements HObserver
{
    static Logger log = LoggerFactory.getLogger(LoggingHObserver.class);
    
    private final String observerID;
    private HObservable observable;  //originally testhobservable
     
    public LoggingHObserver(String observerID)   
    {
        this.observerID = observerID;  
    }      
    
    public LoggingHObserver()      //Need to choose the right parameter
    {
        this("DEFAULT");     //The default level
    }
    
    /**
     * Updates Observer with eventData and the observable in the form of an event
     * @param observable
     * @param eventData 
     */
    @Override
    public void update(Observable observable, Object eventData) 
    {
        String eventDataStr;
        if (eventData instanceof LoggingEventData)
        {
            eventDataStr = observable.toString();  
            System.out.println("This eventData is an instance!");
            eventData.getLogLevel((HObservable) observable);
            eventData.logString((HObservable) observable);
        }
        else
        {
            eventDataStr = eventData.toString();     //call eventData.toString()
            System.out.println("This eventData is NOT an instance!");
        }
    }                                                                         

    public XLogger getEvents()   //TODO get the events from the XLogger
    {
        return null;
    }
    
    /**
     * Sets the observable of the observer. 
     * @param observable 
     */
    public void setObservable(BaseHObservable observable)
    {
        this.observable = observable; 
    }
    
    public HObservable getObservable()
    {
        return this.observable;
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
}
