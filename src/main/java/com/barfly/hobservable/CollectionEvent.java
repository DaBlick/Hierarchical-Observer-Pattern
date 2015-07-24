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
    public CollectionEvent(HObservable observable, Object eventData, EventDataEnum eventDataEnum) 
    {
        super(observable, eventData);
        super.setEventDataEnum(eventDataEnum);
    }
    

}
