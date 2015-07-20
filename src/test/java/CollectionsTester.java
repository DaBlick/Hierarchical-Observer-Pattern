/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.barfly.hobservable.collections.ListObservable;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */

public class CollectionsTester
{   
    @Test
    public void collectionObservableAdding()
    {
        List<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
       
        final String str = "Some String";
        
        collectionObservable.add(str);
        collectionObservable.add("Some Other String");  
        
        assert(collectionObservable.contains(str) && collectionObservable.contains("Some String"));
    }
    
    // Tests
    // We got an event for the add
    @Test
    public void collectionObservableRemoving()
    {
        List<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
       
        final String str = "Some String";
        
        collectionObservable.add("Some Other String");  
        
        collectionObservable.remove(str);
        assert(!(collectionObservable.contains(str)));        
    }
    
    // We got an event for the 2nd add
    // We got an event for the remove
    // The list ended up with size 1
    // etc
    
    @Test
    public void notifyingAnObserver()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
       
        final String str = "Some String";
        
        collectionObservable.add("Some Other String");   
        
        TestHObserver obs = new TestHObserver("Observer");
        
        collectionObservable.addObserver(obs);
        
        collectionObservable.notifyObservers(collectionObservable.get(0));
        
        assert(obs.getEvents().get(0) != null);
    }
    
}
