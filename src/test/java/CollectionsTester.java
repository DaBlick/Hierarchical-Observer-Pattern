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
    
    @Test
    public void collectionObservableRemoving()
    {
        List<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
       
        final String str = "Some String";
        
        collectionObservable.add("Some Other String");  
        
        collectionObservable.remove(str);
        assert(!(collectionObservable.contains(str)));        
    }
    
    @Test
    public void notifyingAnObserverOfAnEvent()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
       
        final String str = "Some String";
        
        collectionObservable.add("Some Other String");   
        
        TestHObserver observer = new TestHObserver("Observer");
        
        collectionObservable.addObserver(observer);
        collectionObservable.notifyObservers(collectionObservable.get(0));
        
        assert(observer.getEvents().get(0).getEventData().equals(collectionObservable.get(0).toString()));
    }

    @Test
    public void notifyingAnObserverOfManyEvents()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
        
        final String str1 = "Some String 1";
        final String str2 = "Some String 2";
        final String str3 = "Some String 3";
        
        collectionObservable.add(str1);  
        collectionObservable.add(str2);
        collectionObservable.add(str3);
        
        TestHObserver observer = new TestHObserver("Observer");
        
        collectionObservable.addObserver(observer);
        collectionObservable.notifyObservers();
        
        assert(observer.getEvents().size() == collectionObservable.size());
    }    

    @Test
    public void notifyingManyObserverOfManyEvents()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
        
        final String str1 = "Some String 1";
        final String str2 = "Some String 2";
        final String str3 = "Some String 3";
        
        collectionObservable.add(str1);  
        collectionObservable.add(str2);
        collectionObservable.add(str3);
        
        TestHObserver observer1 = new TestHObserver("Observer 1");
        TestHObserver observer2 = new TestHObserver("Observer 2");

        
        collectionObservable.addObserver(observer1);
        collectionObservable.addObserver(observer2);
       
        collectionObservable.notifyObservers();
        
        assert(observer1.getEvents().containsAll(observer2.getEvents()));
    }    
}
