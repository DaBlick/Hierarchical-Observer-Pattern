import com.barfly.hobservable.CollectionEvent;
import static com.barfly.hobservable.CollectionEventDataEnum.ADD;
import static com.barfly.hobservable.SetChangedMode.AUTO;
import static com.barfly.hobservable.SetChangedMode.MANUAL;
import com.barfly.hobservable.collections.AbstractCollectionObservable;
import com.barfly.hobservable.collections.ListObservable;
import com.barfly.hobservable.collections.SetObservable;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
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
    
    /**
     * Tests if a ListObservable can notify an observer of an event in the form of a string
     */
    @Test
    public void notifyingAnObserverOfAnEvent()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
        TestHObserver observer = new TestHObserver("Observer");       
        final String str = "Some String";
        
        collectionObservable.add("Some Other String");   
        collectionObservable.addObserver(observer);
        collectionObservable.notifyObservers(collectionObservable.get(0));
        
        assert(observer.getEvents().get(0).getEventData().equals(collectionObservable.get(0).toString()));
    }

    /**
     * Tests if a ListObservable can notify an observer of many events from its collection
     */
    @Test
    public void notifyingAnObserverOfManyEvents()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), AUTO, new ArrayList<String>());
        TestHObserver observer = new TestHObserver("Observer");        
        final String str1 = "Some String 1";
        final String str2 = "Some String 2";
        final String str3 = "Some String 3";
        
        collectionObservable.add(str1);  
        collectionObservable.add(str2);
        collectionObservable.add(str3);
        collectionObservable.addObserver(observer);
        collectionObservable.notifyObservers(collectionObservable.get(0));
        collectionObservable.notifyObservers(collectionObservable.get(1));
        collectionObservable.notifyObservers(collectionObservable.get(2));
        
        assertEquals(collectionObservable.size(), observer.getEvents().size());
    }    

    /**
     * Tests if the many observers of a ListObservable get the same events broadcast to them
     */
    @Test
    public void notifyingManyObserverOfManyEvents()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", TestObservableEnum.OA.getObservableObject(), new ArrayList<String>());
        TestHObserver observer1 = new TestHObserver("Observer 1");
        TestHObserver observer2 = new TestHObserver("Observer 2");         
        final String str1 = "Some String 1";
        final String str2 = "Some String 2";
        final String str3 = "Some String 3";
     
        collectionObservable.add(str1);  
        collectionObservable.add(str2);
        collectionObservable.add(str3);      
        collectionObservable.addObserver(observer1);
        collectionObservable.addObserver(observer2);     
        collectionObservable.notifyObservers();
        
        assert(observer1.getEvents().containsAll(observer2.getEvents()));
    }
    
    /**
     * Tests if the notifyObservers updates observers of the parent and child ListObservables and also checks the fullPath string with a base case string of "Observable: Parent/Observable: Child"
     */
    @Test
    public void HierarchalObserverPattern()
    {
        ListObservable<String> collectionObservableParent = new ListObservable<>("Parent", null, new ArrayList<String>());
        ListObservable<String> collectionObservableChild = new ListObservable<>("Child", collectionObservableParent, new ArrayList<String>());    
    
        TestHObserver obs1 = new TestHObserver("Observer 1");
        TestHObserver obs2 = new TestHObserver("Observer 2");
        
        collectionObservableChild.addObserver(obs1);
        collectionObservableParent.addObserver(obs2);
        collectionObservableChild.add("OK");
        collectionObservableChild.notifyObservers("OK");
        
        assert(obs1.getEvents().get(0).getEventData() == obs2.getEvents().get(0).getEventData() && "Observable: Parent/Observable: Child".equals(collectionObservableChild.getFullPath()));
    }
    
    @Test
    public void CollectionEventNotifyEnum()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("Observable", null, new ArrayList<String>());
        TestHObserver obs = new TestHObserver("Observer");
        
        collectionObservable.addObserver(obs);
        collectionObservable.add("Hello Test");   
        collectionObservable.notifyObservers(new CollectionEvent((AbstractCollectionObservable) collectionObservable, collectionObservable.get(0)));
        
        //assertEquals(EventDataEnum.ADD, obs.getEvents().get(0).);      
    }

    /**
     * Tests if the default constructor which accepts no parameters for the SetChangedMode is AUTO and the user doesn't need to call setChanged()
     */
    @Test
    public void setChangedModeDefault()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", null, new ArrayList<String>());
        TestHObserver obs = new TestHObserver("Observer");
        
        collectionObservable.addObserver(obs);
        collectionObservable.add("HELLO");    

        collectionObservable.notifyObservers(collectionObservable.get(0));
        
        assert(obs.getEvents().get(0) != null && collectionObservable.getSetChangedMode().equals(AUTO));        
    }        

    /**
     * Tests if the SetChangedMode is AUTO and the user doesn't need to call setChanged()
     */
    @Test
    public void setChangedModeAuto()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", null, AUTO, new ArrayList<String>());
        TestHObserver obs = new TestHObserver("Observer");
        
        collectionObservable.addObserver(obs);
        collectionObservable.add("HELLO");    

        collectionObservable.notifyObservers(collectionObservable.get(0));
        
        assert(obs.getEvents().get(0) != null && collectionObservable.getSetChangedMode().equals(AUTO));        
    }            
    /**
     * Tests if the Manuel Mode for the observable works and setChanged only will be called when the user calls it
     */
    @Test
    public void setChangedModeManual()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", null, MANUAL, new ArrayList<String>());
        TestHObserver obs = new TestHObserver("Observer");
        
        collectionObservable.addObserver(obs);
        collectionObservable.add("HELLO");   //ADD ENUM    
        collectionObservable.setChanged();
        collectionObservable.notifyObservers(collectionObservable.get(0));
        
        assert(obs.getEvents().get(0) != null && collectionObservable.getSetChangedMode().equals(MANUAL));        
    }
    
    /**
     * Tests if the collectionEvent can be read into the list of events and the ENUM says the right operation from the event
     * TODO Get the enums to be read into the collectionEvent when updating the observer. 
     */

    @Test
    public void listObservable()
    {
        ListObservable<String> collectionObservable = new ListObservable<>("List Observable", null, AUTO, new ArrayList<String>());
        TestCollectionObserver collectionObserver = new TestCollectionObserver("Observer");
        
        collectionObservable.add("Event Data");  
        collectionObservable.addObserver(collectionObserver);
        collectionObservable.notifyObservers(collectionObservable.get(0));
      
        
        assert(collectionObserver.getEvents().get(0).getEventDataEnum() == ADD);
    }    


    @Test
    public void setObservable()
    {
        SetObservable<String> collectionObservable = new SetObservable<>("List Observable", null, AUTO, new ArrayList<String>());
        TestCollectionObserver collectionObserver = new TestCollectionObserver("Observer");
        
        collectionObservable.add("Event Data");  
        collectionObservable.addObserver(collectionObserver);
        collectionObservable.notifyObservers(null);
      
        
        assert(collectionObserver.getEvents().get(0).getEventDataEnum() == ADD);
    }        
}
