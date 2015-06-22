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
        TestHObservable testObservable = new TestHObservable("Root", TestObservableEnum.OA);
        TestHObserver testObserver1 = new TestHObserver("Observer 1");
        TestHObserver testObserver2 = new TestHObserver("Observer 2");
        
        testObservable.setConsoleDisplayMode(true);
        
        System.out.println("this is the " + testObservable);
        
        System.out.println("this is the " + testObserver1);
        System.out.println("this is the " + testObserver2);
        
        testObservable.addObserver(testObserver1);         //Works
        testObservable.addObserver(testObserver2); 
       
        testObservable.notifyObservers("The test event");         //Doesn't Work (Related to notifyObservers or the overriden update() method in HObserver
        
        System.out.println(testObserver1);
        System.out.println(testObserver2);
        
        System.out.println("EVENT " + testObserver1.getEvents().get(0).getEventData());
        System.out.println("EVENT " + testObserver2.getEvents().get(0).getEventData());
    }
}
