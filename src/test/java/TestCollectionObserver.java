
import com.barfly.hobservable.CollectionEvent;
import com.barfly.hobservable.HObserver;
import com.barfly.hobservable.collections.AbstractCollectionObservable;
import java.util.ArrayList;
import java.util.Observable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonathanodgis
 */
public class TestCollectionObserver implements HObserver
{
    private final String observerID;
    
    private ArrayList<CollectionEvent> events = new ArrayList<CollectionEvent>();
    
         
    
    public TestCollectionObserver(String observerID)
    {
        this.observerID = observerID;
    }

    @Override
    public void update(Observable observable, Object eventData) 
    {
        
        if (observable instanceof AbstractCollectionObservable)   
        {
            this.events.add(new CollectionEvent((AbstractCollectionObservable) observable, eventData));  //ADD is a placeholder TODO get the operations to be read into the collectionEvent  
            System.out.println("Hey, Observer got an event: " + eventData); 
        }
    }
    
    public ArrayList<CollectionEvent> getEvents()
    {
        return this.events;
    }
}
