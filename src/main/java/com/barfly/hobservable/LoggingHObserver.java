/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

import java.util.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        }
        else
        {
            eventDataStr = eventData.toString();      //Type casting
        }
        //TODO Write that String variable to the log
        getLogLevel((HObservable) observable);
        //TODO Set the log to either of the modes/levels
    }                                                                         
    
    /**
     * Displays the log with the event data based on the current mode of logging which can be debug, info, trace, error, or warn levels of the logger.
     * @param eventData 
     */
    /*
    private void displayLog(Object eventData)
    {
        if (log.isDebugEnabled())
        {
            log.debug("Logged the event data debug: " + eventData);
        }
        else if (log.isInfoEnabled())
        {
            log.info("Logged the event data info: " + eventData);              
        }
        else if (log.isTraceEnabled())
        {
            log.trace("Logged the event data info: " + eventData);              
        }        
        else if (log.isErrorEnabled())
        {
            log.error("Logged the event data error: " + eventData);              
        } 
        else if (log.isWarnEnabled())
        {
            log.warn("Logged the event data warn: " + eventData);
        }        
    }
    */
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

    private void getLogLevel(HObservable hObservable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
