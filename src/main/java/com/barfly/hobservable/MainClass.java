/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

/**
 *
 * @author jonathanodgis
 */
public class MainClass 
{
   
    public static void main(String[] args)
    {        
        TestHObservable testObservable = new TestHObservable("Child", TestObservableEnum.OA);
        TestHObserver testObserver1 = new TestHObserver("Observer 1");
        TestHObserver testObserver2 = new TestHObserver("Observer 2");
        PrefabHObserverA testObserver3 = new PrefabHObserverA("Observer 3");
        
        testObserver3.setDateDisplayMode(true);
        testObservable.setConsoleDisplayMode(false);
        
        System.out.println("this is the " + testObservable);
        System.out.println("this is the " + testObservable.getParentObservable());
        System.out.println("this is the " + testObserver1);
        System.out.println("this is the " + testObserver2);
        
        testObservable.addObserver(testObserver1);         
        testObservable.addObserver(testObserver2);
        testObservable.addObserver(testObserver3);
        testObservable.getParentObservable().addObserver(testObserver1);
        
        testObservable.notifyObservers("A");       
        testObservable.getParentObservable().notifyObservers("B"); 
        
        System.out.println("=================================");
        for (int i = 0; i < testObserver1.getEvents().size(); i++)
        {
            System.out.println(testObserver1 + ": The index " + i + " event is " + testObserver1.getEvents().get(i).getEventData() + " from the " + testObserver1.getEvents().get(i).getObservable());
       
        }

        System.out.println("=================================");        
        for (int i = 0; i < testObserver2.getEvents().size(); i++)
        {
            System.out.println(testObserver2 + ": The index " + i + " event is " + testObserver2.getEvents().get(i).getEventData() + " from the " + testObserver2.getEvents().get(i).getObservable()); 
        }

        System.out.println("=================================");           
        System.out.println(testObserver3 + ": The most recent event is " + testObserver3.getEvents().peek().getEventData() + " from the " + testObserver3.getEvents().peek().getObservable()); 

        testObserver3.removeEvents();    

        System.out.println("Now " + testObserver3 + " has " + testObserver3.getEvents().size() + " events after being cleared");
        System.out.println("=================================");        
        System.out.println(testObservable + " has " +testObservable.countObservers() + " observers");
        System.out.println(testObservable.getParentObservable() + " has " + testObservable.getParentObservable().countObservers() + " observers");
    }

}
