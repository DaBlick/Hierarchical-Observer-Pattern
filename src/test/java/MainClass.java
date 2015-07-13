
import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.LoggingHObserver;
import com.barfly.hobservable.StackHObserver;

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
        BaseHObservable observableI = new BaseHObservable("Obsevable I", null);
        BaseHObservable observableII = new BaseHObservable("Observable II", observableI);
        BaseHObservable observableIII = new BaseHObservable("Observable III", observableII);
        
        StackHObserver observerA = new StackHObserver("Observer A");
        TestHObserver observerB = new TestHObserver("Observer B");
        LoggingHObserver observerC = new LoggingHObserver("Observer C");
        LoggingHObserver observerD = new LoggingHObserver("Observer D");
        observableIII.addObserver(observerA);
        observableIII.addObserver(observerB);
        observableIII.addObserver(observerC);
        observableIII.addObserver(observerD);
        
        observableIII.setChanged();
        observableIII.notifyObservers("Here's an Event!");
        
        //observableIII.notifyObservers(new EventData("Hello"));
        
        System.out.println("THE FULL PATH: " + observableIII.getFullPath());
        
        System.out.println("Observer A: " + observerA.getEvents().peek());
        System.out.println("Observer B: " + observerB.getEvents().get(0));
        
      
//System.out.println("Observer C: " + observerC.getEvents().get(0));
    }
}
