/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.barfly.hobservable.CollectionHObserver;
import com.barfly.hobservable.Event;
import com.barfly.hobservable.HObservable;
import java.util.ArrayList;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */
public class CollectionHObserverTester 
{
   
    @Test
    public void sendEventToCollectionHObserver()
    {
        TestHObservable observable = new TestHObservable("Observable", TestObservableEnum.OA);
        
        ArrayList events = new ArrayList();
        
        CollectionHObserver obs = new CollectionHObserver("Collection Observer", events);
        
        observable.addObserver(obs);
        
        observable.setChanged();
        observable.notifyObservers("Test for event");
        
        assert(obs.getEvents().size() > 0 && obs.getEvents().contains(new Event((HObservable) observable, "Test for event")));
    }
    
    @Test
    public void removeEventFromCollectionHObserver()
    {
        TestHObservable observable = new TestHObservable("Observable", TestObservableEnum.OA);
        
        ArrayList events = new ArrayList();
        
        CollectionHObserver obs = new CollectionHObserver("Collection Observer", events);
        
        observable.addObserver(obs);
        
        observable.setChanged();
        observable.notifyObservers("Test for event");
        
        obs.removeEvents();
        
        assert(obs.getEvents().isEmpty());
    }
    
    
}
