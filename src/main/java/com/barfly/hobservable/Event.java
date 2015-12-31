package com.barfly.hobservable;

/**
 * This class represents the Event object.
 * The object is used as a representation of an event and its data. When Update is called in the TestHObserver class, a new instance of an Event object is created using 
 * the event's data and a reference to the HObservable that notified the observer. The events then stored as CollectionEventDataEnums and stored in
 * an ArrayList of events in the TestCollectionObserver class.
 * @author jonathanodgis
 */
public class Event 
{
    private final HObservable observable;
    private final Object eventData;
    
    /**
     * Creates a new Event using a HObservable observable and an Object eventData.
     * @param observable
     * @param eventData 
     */
    public Event(HObservable observable, Object eventData)
    {
        this.eventData = eventData;
        this.observable = observable;
    }
    
    /**
     * Returns the event data of the Event object
     * @return the eventData
     */
    public Object getEventData()
    {
        return this.eventData;
    }
    
    /**
     * Returns the observable that notified the observer of the event data.
     * @return the observable 
     */
    public HObservable getObservable()
    {
        return this.observable;
    }
    
    /**
     * Returns the name of the observable.
     * @return the name of the observable
     */
    @Override
    public String toString()
    {
        return this.eventData + " | " + this.observable.toString();
    }    

    /**
     * Returns boolean value if the object is an instance of the event interface
     * @param obj The object 
     * @return false if the object passed is not an instance of the Event class; otherwise true if the event and objects's observables and eventData are equal to each other.
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (!(obj instanceof Event))
        {
            return false;
        }
        Event event = (Event) obj;
        return this.observable == event.observable && this.eventData == event.eventData;
    }   
}
