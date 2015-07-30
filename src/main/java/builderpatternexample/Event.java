/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builderpatternexample;

import com.barfly.hobservable.CollectionEventDataEnum;
import com.barfly.hobservable.HObservable;

/**
 *
 * @author jonathanodgis
 */
public class Event 
{
    private final HObservable observable;   //required
    private final Object eventData;         //required
    private final CollectionEventDataEnum eventDataEnum;  //optional (to be used with CollectionObservable instances)

    Event(EventBuilder eventBuilder)
    {
        this.observable = eventBuilder.observable;
        this.eventData = eventBuilder.eventData;
        this.eventDataEnum = eventBuilder.eventDataEnum;
    }
                                                                            
 
    
    //All getters here. No setters
    
    public HObservable getObservable() 
    {
        return observable;
    }
 
    public Object getEventData() 
    {
        return eventData;
    }
 
    public CollectionEventDataEnum getEventDataEnum() 
    {
        return eventDataEnum;
    }

    public static class EventBuilder 
    {
        final HObservable observable;
        final Object eventData;
        CollectionEventDataEnum eventDataEnum;
    
        public EventBuilder(HObservable observable, Object eventData) 
        {
            this.observable = observable;
            this.eventData = eventData;
        }

        public EventBuilder eventDataEnum(CollectionEventDataEnum eventDataEnum) 
        {
            this.eventDataEnum = eventDataEnum;
            return this;
        }

        public Event build() 
        {
            return new Event(this);
        }
 
}  
}



