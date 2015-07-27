/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

/**
 *
 * @author jonathanodgis
 */
public class CollectionEvent extends Event
{
    private final EventDataEnum eventDataEnum;
    public CollectionEvent(HObservable observable, Object eventData, EventDataEnum eventDataEnum) 
    {
        super(observable, eventData);
        this.eventDataEnum = eventDataEnum;
    }
    
    public CollectionEvent(HObservable observable, Object eventData)
    {
        super(observable, eventData);
        this.eventDataEnum = null;
    }

    public EventDataEnum getEventDataEnum()
    {
        return this.eventDataEnum;
    }    

}
