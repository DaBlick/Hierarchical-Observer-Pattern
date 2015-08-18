import com.barfly.hobservable.NotificationOrder;
import static com.barfly.hobservable.NotificationOrder.POST;
import static com.barfly.hobservable.NotificationOrder.PRE;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */
public class HObservableTester 
{
    /**
     * Tests if the testObservable's name is the same as the String value "TOURNAMENT"
     */
    @Test
    public void nameIsCorrect()
    {	
        TestHObservable testObservable = new TestHObservable("TOURNAMENT", TestObservableEnum.OA);
        assertEquals("TOURNAMENT", testObservable.getObservableID());
    }	
    
    /**
     * Tests if the testObservable's console display mode is toggled to the boolean value true
     */
    @Test
    public void correctConsoleDisplayMode()
    {
        TestHObservable testObservable = new TestHObservable("TOURNAMENT", TestObservableEnum.OA);
        testObservable.setConsoleDisplayMode(true);
        assertEquals(true, testObservable.getConsoleDisplayMode());
    }
    
    /**
     * Tests if the number of observers of a testObservable is equal to the int value of 2
     */
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
    
    
    /**
     * Tests if the parent observable of an observable can remove an observer from its list of observers
     */
    @Test 
    public void parentOfChildObservableRemovesAnObserver()
    {
        TestHObservable testObservableParent = new TestHObservable("Parent");
        TestHObservable testObservable = new TestHObservable("Child", testObservableParent);
        
        TestHObserver obs1 = new TestHObserver("Observer 1");      
        TestHObserver obs2 = new TestHObserver("Observer 2");  
        
        testObservable.getParentObservable().addObserver(obs1);
        testObservable.addObserver(obs2);
        
        testObservable.getParentObservable().deleteObserver(obs1);
        
        assertEquals(0 , testObservable.getParentObservable().countObservers());  
    }
    
    /**
     * Tests if the NotificationOrderEnum is set to POST in the default constructor of the observable.
     */
    @Test
    public void correctOrderDEFAULT()
    {
        TestHObservable testObservable = new TestHObservable("Child 1", TestObservableEnum.OA);
        assertEquals(POST, testObservable.getNotificationOrder());
    }
    
    /**
     * Tests if the NotificationOrderEnum is set to PRE in the other constructor of the observable where one of the parameters is a NotificationOrderEnum value
     */
    @Test
    public void CorrectOrderPRE()
    {
        TestHObservable testObservable = new TestHObservable("Child 1", TestObservableEnum.OA, NotificationOrder.PRE);
        assertEquals(PRE, testObservable.getNotificationOrder());
    }
    
    /**
     * Tests if the NotificationOrderEnum is set to POST in the other constructor of the observable where one of the parameters is a NotificationOrderEnum value 
     */
    @Test
    public void correctOrderPOST()
    {
        TestHObservable testObservable = new TestHObservable("Child1", TestObservableEnum.OA, NotificationOrder.POST);
        assertEquals(POST, testObservable.getNotificationOrder());
    }        
    
    /**
     * Test to send events to P, and make sure only observers of P get those events
     */
    @Test     
    
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

    /**
     * Test to send a few events to P, and make sure only observers of P get those events
     */    
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
        
        testObservableC1.getParentObservable().setChanged();
        testObservableC1.getParentObservable().notifyObservers();   //should notify the parent's observers

        
        assert(obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs3.getEvents().isEmpty() && obs4.getEvents().isEmpty() && obs5.getEvents().isEmpty() && obs6.getEvents().isEmpty());        
       
    }    
    /**
     * Test to send some events to C1 and make sure only observers of P and C1 get events 
     */
    @Test     
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
        
        testObservableC1.setChanged();
        testObservableC1.notifyObservers("Test");      //should notify the C1 and the parent's observers
        
        //needs to be fixed
        assert(obs3.getEvents().size() > 0 && obs4.getEvents().size() > 0 && obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs5.getEvents().isEmpty() && obs6.getEvents().isEmpty());
 
        
    }
    /**
     * Test to send some events to C1 and make sure only observers of P and C1 get events
     */
    @Test     
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
        
        testObservableC1.setChanged();
        testObservableC1.notifyObservers();      //should notify the C1 and the parent's observers
        
        assert(obs3.getEvents().size() > 0 && obs4.getEvents().size() > 0 && obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs5.getEvents().isEmpty() && obs6.getEvents().isEmpty());
        
    }    
    
    /**
     * Test to send some events to C2 and make sure only observers of P and C2 get those events
     */
    @Test 
    
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
        testObservableC2.notifyObservers();      //should notify the C2 and the parent's observers
        

        assert(obs5.getEvents().size() > 0 && obs6.getEvents().size() > 0 && obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs3.getEvents().isEmpty() && obs4.getEvents().isEmpty());
        
    }
    /**
     * Test to send some events to C2 and make sure only observers of P and C2 get those events
     */
    @Test     
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
        testObservableC2.notifyObservers();      //should notify the C2 and the parent's observers

        assert(obs5.getEvents().size() > 0 && obs6.getEvents().size() > 0 && obs1.getEvents().size() > 0 && obs2.getEvents().size() > 0 && obs3.getEvents().isEmpty() && obs4.getEvents().isEmpty());       
 
    }

    /**
     * Test to check the countObservers() which counts only the observable and not its parent, is less than countAllObservers() which counts the observers among the observable and its parent observable
     */
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
 
    /**
     * Test to check the setChanged() method of observable
     */
    @Test
    public void setChanged()
    {
        TestHObservable observable = new TestHObservable("Observable II", TestObservableEnum.OA);
        
        TestHObserver obs1 = new TestHObserver("Observer 1");
        TestHObserver obs2 = new TestHObserver("Observer 2");
        
        observable.addObserver(obs1);
        observable.addObserver(obs2);
        
        observable.setChanged();
        
        observable.notifyObservers("Hello");
        

        assert(obs1.getEvents().get(0).getEventData() == obs2.getEvents().get(0).getEventData());
    }
    
    /**
     * Test to check if the observer is deleted from the observable
     */
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
    
    /**
     * Tests to see if a path of the observable hierarchy is properly written compared to the String value in the assertion
     */
    @Test
    public void getFullPath()
    {
        TestHObservable observableI = new TestHObservable("Observable I");
        TestHObservable observableII = new TestHObservable("Observable II", observableI);
        assert("Observable: Observable I/Observable: Observable II".equals(observableII.getFullPath()));
    }
}