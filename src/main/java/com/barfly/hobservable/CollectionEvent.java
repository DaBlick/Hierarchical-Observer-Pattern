package com.barfly.hobservable;
import com.barfly.hobservable.collections.AbstractCollectionObservable;
/**
 * This class represents the CollectionEvent object which extends the Event class.
 * The object is used as a event pertaining to the AbstractCollectionObservable in the collections package. The events then stored as CollectionEventDataEnums and stored in
 * an ArrayList of events in the TestCollectionObserver class.
 * @author jonathanodgis
 */
public class CollectionEvent extends Event
{
    private final CollectionEventDataEnum eventDataEnum;
    
    /**
     * Creates a new CollectionEvent object using an AbstractCollectionObservable observable and an Object eventData.
     * @param observable
     * @param eventData 
     */
    public CollectionEvent(AbstractCollectionObservable observable, Object eventData)
    {
        super(observable, eventData);
        this.eventDataEnum = observable.getMostRecentChange();
    }

    /**
     * Returns the event data enumerator
     * @return the event data enumerator
     */
    public CollectionEventDataEnum getEventDataEnum()
    {
        return this.eventDataEnum;
    }    

}
