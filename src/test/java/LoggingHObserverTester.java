/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.barfly.hobservable.LogLevel;
import com.barfly.hobservable.LoggingHObserver;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */
public class LoggingHObserverTester 
{
    
    /**
     * Tests if the log level is equal to the enum string value
     */
    @Test
    public void LogObserverLogLevel()
    {
        TestHObservable observableParent = new TestHObservable("Parent Observable");
        TestHObservable observable = new TestHObservable("Child Observable", observableParent);
        
        LoggingHObserver observerA = new LoggingHObserver("Observer A");

        observable.addObserver(observerA);

        observable.setChanged();
        observable.notifyObservers(new EventData("Hello", LogLevel.DEBUG));
        
        //assert(observerA.getLog.equals("DEBUG")); // TODO this test is wrong.   
    }    

    /**
     * Tests if the event data of observerA is equal to the event data of observerB
     */
    @Test
    public void LogObserverEventData()
    {
        TestHObservable observableA = new TestHObservable("Child Observable");
        TestHObservable observableB = new TestHObservable("Child Observable", observableA);
        
        LoggingHObserver observerA = new LoggingHObserver("Observer A");
        LoggingHObserver observerB = new LoggingHObserver("Observer B");
        
        observableA.addObserver(observerA);
        observableB.addObserver(observerB);

        observableA.setChanged();
        observableB.setChanged();
        observableA.notifyObservers(new EventData("Event A", LogLevel.WARN));
        observableB.notifyObservers("Event B");
        
        assert(!(observerA.toString().equals(observerB.toString())));
    }        
}
