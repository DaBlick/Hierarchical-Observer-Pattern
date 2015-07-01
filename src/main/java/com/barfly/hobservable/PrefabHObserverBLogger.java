/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

import java.util.Observable;
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author jonathanodgis
 */
public class PrefabHObserverBLogger implements HObserver
{
    static Logger log = LoggerFactory.getLogger(PrefabHObserverBLogger.class);
    
    private final String observerID;
    private HObservable observable;  //originally testhobservable
     
    public PrefabHObserverBLogger(String observerID)   
    {
        log.info("LOGGER TEST CONSTRUCTOR");
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
        log.debug("LOGGER TEST UPDATE");
        System.out.println("Hey, Observer got an event: " + eventData); 
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
