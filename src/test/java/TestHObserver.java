

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.barfly.hobservable.Event;
import com.barfly.hobservable.HObservable;
import com.barfly.hobservable.HObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author jonathanodgis
 */
public class TestHObserver implements HObserver 
{   
    private final List<Event> events = new ArrayList<>();
    private final String observerID;
    private HObservable observable;  //originally testhobservable
     
    public TestHObserver(String observerID)   
    {
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
        if (this.observable != null)
        {
            this.events.add(new Event((HObservable) observable, eventData));  
            System.out.println("Hey, Observer got an event: " + eventData);     
        }
        else
        {
            System.out.println("Observer was deleted and will not get future events");
        }
    }                                                                         
    
    
    /**
     * Sets the observable of the observer. 
     * @param observable 
     */
    public void setObservable(TestHObservable observable)
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

    /**
     * Returns the list of events received by the observer.
     * @return the list of events received by the observer
     */ 
    public List<Event> getEvents()
    {
        return events;
    }

    /**
     * Returns true or false depending if the list contains the event specified in the parameter. 
     * @param check
     * @return true or false 
     */
    public boolean isEventPresent(Event check) 
    {
        return events.contains(check);
    }

}
