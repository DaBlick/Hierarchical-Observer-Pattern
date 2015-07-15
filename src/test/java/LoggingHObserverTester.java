/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.LogLevel;
import com.barfly.hobservable.LoggingHObserver;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */
public class LoggingHObserverTester 
{
    
  @Test
    public void LogObserverLogLevel()
    {
        BaseHObservable observableParent = new BaseHObservable("Parent Observable", null);
        BaseHObservable observable = new BaseHObservable("Child Observable", observableParent);
        
        LoggingHObserver observerA = new LoggingHObserver("Observer A");

        observable.addObserver(observerA);

        observable.setChanged();
        observable.notifyObservers(new EventData("Hello", LogLevel.DEBUG));
        
//        assert(observerA.getLogLevel().equals("DEBUG")); // TODO this test is wrong.   
    }    

    @Test
    public void LogObserverEventData()
    {
        BaseHObservable observableA = new BaseHObservable("Child Observable", null);
        BaseHObservable observableB = new BaseHObservable("Child Observable", null);
        
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
