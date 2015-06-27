
import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.HObservable;
import com.barfly.hobservable.PrefabHObserverA;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jonathanodgis
 */
public class MainClass 
{
   
    public static void main(String[] args)
    {        

        BaseHObservable realObservable = new BaseHObservable("ROOT", TestObservableEnum.OA.getObservableObject());
        TestHObserver realObserverA = new TestHObserver("Observer A");
        
        realObservable.addObserver(realObserverA);
        realObservable.addObserver(realObserverA);
        
        realObservable.notifyObservers("test 1");
        realObservable.notifyObservers("test 2");
        realObservable.notifyObservers("test 3");
        realObservable.notifyObservers("test 4");
        
        System.out.println("NUMBER OF OBSERVERS:" +realObservable.countObservers());    
        System.out.println("NUMBER OF OBSERVERS:" + realObservable.countAllObservers());
        
        for (int i = 0; i < realObserverA.getEvents().size(); i++)
        {
            System.out.println(realObserverA.getEvents().get(i).getEventData());            
        }

        
        System.out.println("==================================================");
        
        TestHObservable testObservable = new TestHObservable("Child", TestObservableEnum.OA);
        TestHObserver testObserver1 = new TestHObserver("Observer 1");
        TestHObserver testObserver2 = new TestHObserver("Observer 2");
        PrefabHObserverA testObserver3 = new PrefabHObserverA("Observer 3");
        
        testObserver3.setDateDisplayMode(false);
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
        System.out.println(testObserver1.getEvents().size());
  
        System.out.println(testObservable + " has " +testObservable.countObservers() + " observers");
        System.out.println(testObservable.getParentObservable() + " has " + testObservable.getParentObservable().countObservers() + " observers");
    
        System.out.println("NUMBER OF OBSERVERS:" +testObservable.countObservers());
     
        System.out.println("NUMBER OF OBSERVERS:" + testObservable.countAllObservers());
        
        System.out.println("========================================================");
        
        testObservable.deleteObserver(testObserver1);
        
        testObservable.notifyObservers("new event to see if deleted observers get events");
        
        for (int i = 0; i < testObserver1.getEvents().size(); i++)
        {
            System.out.println(testObserver1.getEvents().get(i).getEventData());
        }
    }

}
