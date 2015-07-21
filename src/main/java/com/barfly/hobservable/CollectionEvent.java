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
    private EventDataEnum eventDataEnum;
    
    public CollectionEvent(HObservable observable, Object eventData) 
    {
        super(observable, eventData);
    }

    public void setEventDataEnum(EventDataEnum eventDataEnum)
    {
        this.eventDataEnum = eventDataEnum;
    }
    
    public EventDataEnum getEventDataEnum()
    {
        return this.eventDataEnum;
    }
    
    
}
