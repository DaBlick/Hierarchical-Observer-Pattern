
import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.HObservable;
import com.barfly.hobservable.HObserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jonathanodgis
 */
/**
 * Observables that can be used with other observables in the hierarchy. 
 */
public enum TestObservableEnum implements HObservable
{
    /**
     * Observable OA - has no parent
    */
    OA(),
    /**
     * Observable OB - parent is Observable OA
     */
    OB(OA),
    /**
     * Observable OC - parent is Observable OA
     */
    OC(OA),
    /**
     * Observable OD - parent is Observable OA
     */
    OD(OA),
    /**
     * Observable OE - parent is Observable OD
     */
    OE(OD),
    /**
     * Observable OF - parent is Observable OE
     */
    OF(OE),;

    private TestObservableEnum parent = null;
    private final BaseHObservable observableObject;     //think of as the enum's actual observable object representation to use baseObservable
    
  
    private TestObservableEnum() 
    {
         this.observableObject = new BaseHObservable(this.toString(), null);   
    }
    
    private TestObservableEnum(TestObservableEnum parent) 
    {
        this.parent = parent;
        this.observableObject = new TestHObservable(this.toString(), parent.observableObject);
    }
    
    /**
     * Returns TestObservableEnum
     * @return TestObservableEnum
     */
    public TestObservableEnum getTestEnum() 
    {
        return this;
    }
    
    /**
     * Returns ObservableObject
     * @return ObservableObject
     */
    public BaseHObservable getObservableObject() 
    {
        return observableObject;
    }

    /**
     * Adds an observer to the observable's list of observers
     * @param observer
     */    
    @Override    
    public void addObserver(HObserver obs) 
    {
        this.observableObject.addObserver(obs);
    }

    @Override
    public void setChanged()
    {
        this.observableObject.setChanged();
    }
    
    @Override
    public void notifyObservers() {
        this.observableObject.notifyObservers();
    }

    @Override    
    public void notifyObservers(Object eventData) 
    {
        this.observableObject.notifyObservers(eventData);
    }

    /**
     * Removes an observer from the observable's list of observers
     * @param observer
     */        
    @Override
    public void deleteObserver(HObserver observer) 
    {
        this.observableObject.deleteObserver(observer);
        System.out.println("Observer " + observer + " has been deleted.");
    }

    /**
     * Returns the Observable ID
     * @return ObservableID
     */
    @Override
    public String getObservableID() 
    {
        return this.observableObject.getObservableID();
    }

    /**
     * Returns the Parent Observable
     * @return Parent Observable
     */
    @Override
    public BaseHObservable getParentObservable() 
    {
        return this.observableObject.getParentObservable();
    }

    @Override
    public void setParentObservable(BaseHObservable parentObservable) 
    {
        this.observableObject.setParentObservable(parentObservable);
    }    

    /**
     * Returns the number of observers that a 
     * @return 
     */
    @Override
    public int countObservers() 
    {
        return this.observableObject.countObservers();
    }

    @Override
    public int countAllObservers() 
    {
        return this.observableObject.countAllObservers();
    }

}
