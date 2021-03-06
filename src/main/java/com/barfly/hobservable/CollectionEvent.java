package com.barfly.hobservable;
import com.barfly.hobservable.collections.AbstractCollectionObservable;
/**
 *
 * @author jonathanodgis
 */
public class CollectionEvent extends Event
{
    private final CollectionEventDataEnum eventDataEnum;
    
    public CollectionEvent(AbstractCollectionObservable observable, Object eventData)
    {
        super(observable, eventData);
        this.eventDataEnum = observable.getMostRecentChange();
    }

    public CollectionEventDataEnum getEventDataEnum()
    {
        return this.eventDataEnum;
    }    

}
