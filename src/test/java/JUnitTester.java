/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.barfly.hobservable.Event;
import com.barfly.hobservable.NotificationOrder;
import static com.barfly.hobservable.NotificationOrder.POST;
import static com.barfly.hobservable.NotificationOrder.PRE;
import com.barfly.hobservable.TestHObservable;
import com.barfly.hobservable.TestHObserver;
import com.barfly.hobservable.TestObservableEnum;
import org.junit.Test;
import static org.junit.Assert.*;

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
        

        testObservableC1.getParentObservable().notifyObservers("test");   //should notify the parent's observers
              
        assertEquals(true, obs1.isEventPresent(new Event(testObservableC1.getParentObservable(), "test")) 
                && obs2.isEventPresent(new Event(testObservableC1.getParentObservable(), "test")) 
        );
       
    }
    
    //Case 2: send some events to C1 and make sure only observers of P and C1 get events
    public void sendEventsToC1()
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
        
        testObservableC1.notifyObservers("test");      //should notify the C1 and the parent's observers
        
        assertEquals(true, obs3.isEventPresent(new Event(testObservableC1,"test")) 
                && obs4.isEventPresent(new Event(testObservableC1, "test")) 
                && obs1.isEventPresent(new Event(testObservableC1.getParentObservable(), "test"))
                && obs2.isEventPresent(new Event(testObservableC1.getParentObservable(), "test"))
        );
        
    }
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
        
        testObservableC1.getParentObservable().addObserver(obs1);
        testObservableC1.getParentObservable().addObserver(obs2); 
        testObservableC1.addObserver(obs3);
        testObservableC1.addObserver(obs4); 
        testObservableC2.addObserver(obs5);
        testObservableC2.addObserver(obs6); 
        
        testObservableC2.notifyObservers("test");      //should notify the C2 and the parent's observers
        
        assertEquals(true, obs5.isEventPresent(new Event(testObservableC2,"test")) 
                && obs6.isEventPresent(new Event(testObservableC2, "test")) 
                && obs1.isEventPresent(new Event(testObservableC2.getParentObservable(), "test"))
                && obs2.isEventPresent(new Event(testObservableC2.getParentObservable(), "test"))
        );     
    }
    

}

