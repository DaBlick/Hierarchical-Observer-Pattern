
import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.HObservable;
import com.barfly.hobservable.StackHObserver;
import com.barfly.hobservable.LoggingHObserver;
import com.barfly.hobservable.eventData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


/**
 *
 * @author jonathanodgis
 */
public class MainClass 
{
   
    public static void main(String[] args)
    {        
        BaseHObservable observableParent = new BaseHObservable("Parent Observable", null);
        BaseHObservable observable = new BaseHObservable("Child Observable", observableParent);
        
        StackHObserver observerA = new StackHObserver("Observer A");
        TestHObserver observerB = new TestHObserver("Observer B");
        LoggingHObserver observerC = new LoggingHObserver("Observer C");
        LoggingHObserver observerD = new LoggingHObserver("Observer D");
        observable.addObserver(observerA);
        observable.addObserver(observerB);
        observable.addObserver(observerC);
        observable.addObserver(observerD);
        
        observable.notifyObservers("Here's an Event!");
        
        observable.notifyObservers(new eventData("Hello"));
        
        System.out.println("Observer A: " + observerA.getEvents().peek());
        System.out.println("Observer B: " + observerB.getEvents().get(0));
        //System.out.println("Observer C: " + observerC.getEvents().get(0));
    }
}
