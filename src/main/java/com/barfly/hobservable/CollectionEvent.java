/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

import com.barfly.hobservable.collections.AbstractCollectionObservable;

/**
 *
 * @author jonathanodgis
 */
public class CollectionEvent extends Event
{
    private final EventDataEnum eventDataEnum;
    
    public CollectionEvent(AbstractCollectionObservable observable, Object eventData)
    {
        super(observable, eventData);
        this.eventDataEnum = observable.getMostRecentChange();
    }

    public EventDataEnum getEventDataEnum()
    {
        return this.eventDataEnum;
    }    

}
