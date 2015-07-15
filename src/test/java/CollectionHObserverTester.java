/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.barfly.hobservable.BaseHObservable;
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
        BaseHObservable observableI = new BaseHObservable("Observable I", null);
        BaseHObservable observableII = new BaseHObservable("Observable II", observableI);
        
        ArrayList events = new ArrayList();
        
        CollectionHObserver obs = new CollectionHObserver("Observer General", events);
        
        observableII.addObserver(obs);
        
        observableII.setChanged();
        observableII.notifyObservers("Hi");
        
        assert(obs.getEvents().size() > 0 && obs.getEvents().contains(new Event((HObservable) observableII, "Hi")));
    }
    
    
}
