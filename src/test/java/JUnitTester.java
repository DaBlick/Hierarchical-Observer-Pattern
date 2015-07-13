/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.LogLevel;
import com.barfly.hobservable.LoggingHObserver;
import com.barfly.hobservable.NotificationOrder;
import static com.barfly.hobservable.NotificationOrder.POST;
import static com.barfly.hobservable.NotificationOrder.PRE;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */
public class JUnitTester 
{   
    @Test
    public void nameIsCorrect()
    {	
        TestHObservable testObservable = new TestHObservable("TOURNAMENT", TestObservableEnum.OA);
        assertEquals("TOURNAMENT", testObservable.getObservableID());
    }	
    
    @Test
    public void correctConsoleDisplayMode()
    {
        TestHObservable testObservable = new TestHObservable("TOURNAMENT", TestObservableEnum.OA);
        testObservable.setConsoleDisplayMode(true);
        assertEquals(true, testObservable.getConsoleDisplayMode());
    }
    
    @Test
    public void numberOfObserversIsCorrect()
    {
        TestHObserver obs1 = new TestHObserver("Observer 1"); 
        TestHObserver obs2 = new TestHObserver("Observer 2");
        TestHObservable testObservable = new TestHObservable("Child 1", TestObservableEnum.OA);
        testObservable.setConsoleDisplayMode(false);        
        testObservable.addObserver(obs1);
        testObservable.addObserver(obs2);        
        assertEquals(2, testObservable.countObservers());
    }
    
    //Are all the events being logged into the observer
 
    @Test 
    public void parentOfChildObservableRemovesAnObserver()
    {
        TestHObservable testObservable = new TestHObservable("Child 1", TestObservableEnum.OA);
        TestHObserver obs1 = new TestHObserver("Observer 1");      
        testObservable.getParentObservable().addObserver(obs1);
        testObservable.getParentObservable().deleteObserver(obs1);
        
        assertEquals(0 , testObservable.getParentObservable().countObservers());  
    }
    
    @Test
    public void correctOrderDEFAULT()
    {
        TestHObservable testObservable = new TestHObservable("Child 1", TestObservableEnum.OA);
        assertEquals(POST, testObservable.getNotificationOrder());
    }
    
    @Test
    public void CorrectOrderPRE()
    {
        TestHObservable testObservable = new TestHObservable("Child 1", TestObservableEnum.OA, NotificationOrder.PRE);
        assertEquals(PRE, testObservable.getNotificationOrder());
    }
    
    @Test
    public void correctOrderPOST()
    {
        TestHObservable testObservable = new TestHObservable("Child1", TestObservableEnum.OA, NotificationOrder.POST);
        assertEquals(POST, testObservable.getNotificationOrder());
    }        
    
    @Test     
    //Case 1: send some events to P, and make sure only observers of P get those
    public void sendEventsToParentOnly()
    {
        TestHObservable testObservableC1 = new TestHObservable("Child 1", TestObservableEnum.OA);    
        TestHObservable testObservableC2 = new TestHObservable("Child 2", TestObservableEnum.OA);
        
        TestHObserver obs1 = new TestHObserver("Observer 1");  //Parent of C1/C2
        TestHObserver obs2 = new TestHObserver("Observer 2");  //Parent of C1/C2
        TestHObserver obs3 = new TestHObserver("Observer 3");  //C1 
        TestHObserver obs4 = new TestHObserver("Observer 4");  //C1 
        TestHObserver obs5 = new TestHObserver("Observer 5");  //C2
        TestHObserver obs6 = new TestHObserver("Observer 6");  //C2 
        
        testObservableC1.getParentObservable().addObserver(obs1);
        testObservableC1.getParentObservable().addObserver(obs2); 
        testObservableC1.addObserver(obs3);
        testObservableC1.addObserver(obs4); 
        testObservableC2.addObserver(obs5);
        testObservableC2.addObserver(obs6); 
        
        testObservableC1.getParentObservable().setChanged();
        testObservableC1.getParentObservable().notifyObservers("test");   //should notify the parent's observers
     
        assert(obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs3.getEvents().isEmpty() && obs4.getEvents().isEmpty() && obs5.getEvents().isEmpty() && obs6.getEvents().isEmpty());
        
       
    }

    @Test     
    public void sendEventsToParentOnlyAlt()
    {
        TestHObservable testObservableC1 = new TestHObservable("Child 1", TestObservableEnum.OA);    
        TestHObservable testObservableC2 = new TestHObservable("Child 2", TestObservableEnum.OA);
        
        TestHObserver obs1 = new TestHObserver("Observer 1");  //Parent of C1/C2
        TestHObserver obs2 = new TestHObserver("Observer 2");  //Parent of C1/C2
        TestHObserver obs3 = new TestHObserver("Observer 3");  //C1 
        TestHObserver obs4 = new TestHObserver("Observer 4");  //C1 
        TestHObserver obs5 = new TestHObserver("Observer 5");  //C2
        TestHObserver obs6 = new TestHObserver("Observer 6");  //C2 
        
        testObservableC1.getParentObservable().addObserver(obs1);
        testObservableC1.getParentObservable().addObserver(obs2); 
        testObservableC1.addObserver(obs3);
        testObservableC1.addObserver(obs4); 
        testObservableC2.addObserver(obs5);
        testObservableC2.addObserver(obs6); 
        

        testObservableC1.getParentObservable().notifyObservers();   //should notify the parent's observers

        
        assert(obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs3.getEvents().isEmpty() && obs4.getEvents().isEmpty() && obs5.getEvents().isEmpty() && obs6.getEvents().isEmpty());        
       
    }    

    @Test     
    //Case 2: send some events to C1 and make sure only observers of P and C1 get events
    public void sendEventsToC1()   //notifyObservers with no param
    {
        TestHObservable testObservableC1 = new TestHObservable("Child 1", TestObservableEnum.OA);    
        TestHObservable testObservableC2 = new TestHObservable("Child 2", TestObservableEnum.OA);
        
        TestHObserver obs1 = new TestHObserver("Observer 1");  //Parent of C1/C2
        TestHObserver obs2 = new TestHObserver("Observer 2");  //Parent of C1/C2
        TestHObserver obs3 = new TestHObserver("Observer 3");  //C1 
        TestHObserver obs4 = new TestHObserver("Observer 4");  //C1 
        TestHObserver obs5 = new TestHObserver("Observer 5");  //C2
        TestHObserver obs6 = new TestHObserver("Observer 6");  //C2 
        
        testObservableC1.getParentObservable().addObserver(obs1);
        testObservableC1.getParentObservable().addObserver(obs2); 
        testObservableC1.addObserver(obs3);            //registerObserver
        testObservableC1.addObserver(obs4);            //registerObserver
        testObservableC2.addObserver(obs5);            //registerObserver
        testObservableC2.addObserver(obs6);            //registerObserver
        
        testObservableC1.notifyObservers("Test");      //should notify the C1 and the parent's observers
        
        //needs to be fixed
        assert(obs3.getEvents().size() > 0 && obs4.getEvents().size() > 0 && obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs5.getEvents().isEmpty() && obs6.getEvents().isEmpty());
 
        
    }

    @Test     
    //Case 2: send some events to C1 and make sure only observers of P and C1 get events
    public void sendEventsToC1Alt()   //notifyObservers with no param
    {
        TestHObservable testObservableC1 = new TestHObservable("Child 1", TestObservableEnum.OA);    
        TestHObservable testObservableC2 = new TestHObservable("Child 2", TestObservableEnum.OA);
        
        TestHObserver obs1 = new TestHObserver("Observer 1");  //Parent of C1/C2
        TestHObserver obs2 = new TestHObserver("Observer 2");  //Parent of C1/C2
        TestHObserver obs3 = new TestHObserver("Observer 3");  //C1 
        TestHObserver obs4 = new TestHObserver("Observer 4");  //C1 
        TestHObserver obs5 = new TestHObserver("Observer 5");  //C2
        TestHObserver obs6 = new TestHObserver("Observer 6");  //C2 
        
        testObservableC1.getParentObservable().addObserver(obs1);
        testObservableC1.getParentObservable().addObserver(obs2); 
        testObservableC1.addObserver(obs3);            //registerObserver
        testObservableC1.addObserver(obs4);            //registerObserver
        testObservableC2.addObserver(obs5);            //registerObserver
        testObservableC2.addObserver(obs6);            //registerObserver
        
        testObservableC1.notifyObservers();      //should notify the C1 and the parent's observers
        
        assert(obs3.getEvents().size() > 0 && obs4.getEvents().size() > 0 && obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs5.getEvents().isEmpty() && obs6.getEvents().isEmpty());
        
    }    
    
    @Test 
    //Case 3: send some events to C2 and make sure only observers of P and C2 get those events
    public void sendEventsToC2()
    {
        TestHObservable testObservableC1 = new TestHObservable("Child 1", TestObservableEnum.OA);    
        TestHObservable testObservableC2 = new TestHObservable("Child 2", TestObservableEnum.OA);
        
        TestHObserver obs1 = new TestHObserver("Observer 1");  //Parent of C1/C2
        TestHObserver obs2 = new TestHObserver("Observer 2");  //Parent of C1/C2
        TestHObserver obs3 = new TestHObserver("Observer 3");  //C1 
        TestHObserver obs4 = new TestHObserver("Observer 4");  //C1 
        TestHObserver obs5 = new TestHObserver("Observer 5");  //C2
        TestHObserver obs6 = new TestHObserver("Observer 6");  //C2 
        
        testObservableC2.getParentObservable().addObserver(obs1);
        testObservableC2.getParentObservable().addObserver(obs2); 
        testObservableC1.addObserver(obs3);
        testObservableC1.addObserver(obs4); 
        testObservableC2.addObserver(obs5);
        testObservableC2.addObserver(obs6); 
        
        testObservableC2.setChanged();
        testObservableC2.getParentObservable().setChanged();
        
        testObservableC2.notifyObservers();      //should notify the C2 and the parent's observers
        

        assert(obs5.getEvents().size() > 0 && obs6.getEvents().size() > 0 && obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs3.getEvents().isEmpty() && obs4.getEvents().isEmpty());
        
    }
  
    @Test     
    //Case 3: send some events to C2 and make sure only observers of P and C2 get those events
    public void sendEventsToC2Alt()
    {
        TestHObservable testObservableC1 = new TestHObservable("Child 1", TestObservableEnum.OA);    
        TestHObservable testObservableC2 = new TestHObservable("Child 2", TestObservableEnum.OA);
        
        TestHObserver obs1 = new TestHObserver("Observer 1");  //Parent of C1/C2
        TestHObserver obs2 = new TestHObserver("Observer 2");  //Parent of C1/C2
        TestHObserver obs3 = new TestHObserver("Observer 3");  //C1 
        TestHObserver obs4 = new TestHObserver("Observer 4");  //C1 
        TestHObserver obs5 = new TestHObserver("Observer 5");  //C2
        TestHObserver obs6 = new TestHObserver("Observer 6");  //C2 
        
        testObservableC1.getParentObservable().addObserver(obs1);
        testObservableC1.getParentObservable().addObserver(obs2); 
        testObservableC1.addObserver(obs3);
        testObservableC1.addObserver(obs4); 
        testObservableC2.addObserver(obs5);
        testObservableC2.addObserver(obs6); 
        
        testObservableC2.setChanged();
        testObservableC2.getParentObservable().setChanged();
        testObservableC2.notifyObservers();      //should notify the C2 and the parent's observers

        assert(obs5.getEvents().size() > 0 && obs6.getEvents().size() > 0 && obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs3.getEvents().isEmpty() && obs4.getEvents().isEmpty());       
 
    }

    @Test     
    public void countingTheObservers()
    {
        TestHObservable testObservableC1 = new TestHObservable("Child 1", TestObservableEnum.OA);    
        
        TestHObserver obs1 = new TestHObserver("Observer 1"); 
        TestHObserver obs2 = new TestHObserver("Observer 2");  
        TestHObserver obs3 = new TestHObserver("Observer 3");   
        
        testObservableC1.addObserver(obs1);
        testObservableC1.addObserver(obs2);
        testObservableC1.getParentObservable().addObserver(obs1);
        
        assert(testObservableC1.countObservers() < testObservableC1.countAllObservers());
    }
    
    @Test
    public void deletionOfObserver()
    {
        TestHObservable testObservableC1 = new TestHObservable("Child 1", TestObservableEnum.OA);         
        TestHObserver obs1 = new TestHObserver("Observer 1"); 

        testObservableC1.addObserver(obs1);      
        testObservableC1.deleteObserver(obs1);
        
        testObservableC1.setChanged();
        testObservableC1.notifyObservers("Test");
        
        assertEquals(0, obs1.getEvents().size());  
    }
    
    @Test
    public void getFullPath()
    {
        BaseHObservable observableI = new BaseHObservable("Observable I", null);
        BaseHObservable observableII = new BaseHObservable("Observable II", observableI);
        assert("Observable: Observable I/Observable: Observable II".equals(observableII.getFullPath()));
    }
    
    

    @Test
    public void LogObserverLogLevel()
    {
        BaseHObservable observableParent = new BaseHObservable("Parent Observable", null);
        BaseHObservable observable = new BaseHObservable("Child Observable", observableParent);
        
        LoggingHObserver observerA = new LoggingHObserver("Observer A");

        observable.addObserver(observerA);

        observable.setChanged();
        observable.getParentObservable().setChanged();
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

        observableA.notifyObservers(new EventData("Event A", LogLevel.WARN));
        observableB.notifyObservers("Event B");
        
        assert(!(observerA.toString().equals(observerB.toString())));
    }        
    
}

