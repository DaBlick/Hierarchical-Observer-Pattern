package com.barfly.hobservable;

import static com.barfly.hobservable.NotificationOrder.POST;
import static com.barfly.hobservable.NotificationOrder.PRE;
import static com.barfly.hobservable.SetChangedMode.AUTO;
import java.util.Observable;

/**
 *
 * @author dblickstein
 */
public class BaseHObservable extends Observable implements HObservable
{
    private BaseHObservable parentObservable;
    private String observableID;    
    private NotificationOrder order;
    private SetChangedMode setChangedMode; 
    private boolean consoleDisplayMode; 
    private CollectionEventDataEnum mostRecentChange;
    
    public BaseHObservable(String observableID, BaseHObservable parentObservable)   
    {
        this(observableID, parentObservable, POST, AUTO);
    }     
    
    public BaseHObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order)  
    {
        this(observableID, parentObservable, order, AUTO);
    }    

    public BaseHObservable(String observableID, BaseHObservable parentObservable, SetChangedMode setChangedMode)   
    {
        this(observableID, parentObservable, POST, setChangedMode);
    }        
    
    public BaseHObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedMode setChangedMode)
    {
        this.observableID = observableID;
        this.parentObservable = parentObservable;  
        this.order = order;
        this.setChangedMode = setChangedMode;        
    }
   
    /**
     * Adds an observer to the observable's known list of observers.
     * @param observer The observer to be added.
     * @see java.util.Observable
     */    
    @Override
    public void addObserver(HObserver observer)
    {
        printConsoleDisplay("Registering observer....");
        super.addObserver(observer);
    }    
    
    /**
     * Removes an observer from the observable's known list of observers.
     * @param observer The observer to be deleted.
     * @see java.util.Observable
     */
    @Override
    public void deleteObserver(HObserver observer)
    {
        printConsoleDisplay("Deleting observer....");
        super.deleteObserver(observer);
    }

    /**
     * Toggles the status of an observable before notifying the observers
     * @see java.util.Observable
     */
    @Override
    public void setChanged()   
    {
        super.setChanged();
        if (this.parentObservable != null)
        {
            this.parentObservable.setChanged();
        }
    }
    
    /**
     * Notifies the observers of an observable and the observable's parent of an event. 
     * @see java.util.Observable
     */
    @Override
    public void notifyObservers() 
    {
        printConsoleDisplay("Broadcasting event.....");
        if (this.setChangedMode.equals(AUTO))
        {
            this.setChanged();
        }
        if (order.equals(PRE))
        {
            if (parentObservable != null)
            {
                parentObservable.notifyObservers();         
            }
            super.notifyObservers();            
        }
        if (order.equals(POST))
        {
            super.notifyObservers();
            if (parentObservable != null)
            {
                parentObservable.notifyObservers();        
            }           
        }
    }

    /**
     * Notifies the observers of an observable and the observable's parent of an event.  
     * @param eventData the information being fired to the observers.
     * @see java.util.Observable
     */    
    @Override
    public void notifyObservers(Object eventData) 
    {
        printConsoleDisplay("Broadcasting event.....");
        if (this.setChangedMode.equals(AUTO))
        {
            this.setChanged();
        }
        if (order.equals(PRE))
        {
            super.notifyObservers(eventData);
            if (parentObservable != null)
            {
                parentObservable.notifyObservers(eventData);           
            }
        }
        if (order.equals(POST))
        {
            if (parentObservable != null)
            {
                parentObservable.notifyObservers(eventData);    
            }
            super.notifyObservers(eventData);
        }
        
    }

    /**
     * Returns the parent observable of the observable.
     * @return the parent observable.
     */
    @Override
    public BaseHObservable getParentObservable() 
    {
        printConsoleDisplay("Getting the parentObservable....");        
        return parentObservable;
    }
    
    /**
     * Sets the parent observable of the observable.
     * @param parentObservable The parent of the observable.
     */    
    @Override
    public void setParentObservable(BaseHObservable parentObservable) 
    {
        printConsoleDisplay("Setting the parentObservable....");
        this.parentObservable = parentObservable;
    }

    /**
     * Returns the name of the observable.
     * @return the name of the observable.
     */
    @Override
    public String getObservableID()        
    {
        return observableID;
    }
  
    /**
     * Sets the name of this observable.
     * @param observableID The name of the observable. 
     */
    public void setObservableID(String observableID)
    {
        this.observableID = observableID;
    }
    
    /**
     * Returns the path of the observable and its parent.
     * @return the path of the observable and its parent.
     */
    public String getFullPath() 
    {
        String result = this.toString();

        if (this.parentObservable != null)
        {
            result = this.parentObservable.getFullPath() + "/" + result;
        }
        return result;
    }
    
    /**
     * Returns the notification order of the observable and its parent
     * @return the notification order of the observable and its parent
     */
    public NotificationOrder getNotificationOrder()
    {
        return this.order;
    }
    
    /**
     * Returns the setChangedMode value
     * @return the setChangedMode value
     */
    public SetChangedMode getSetChangedMode()
    {
        return this.setChangedMode;
    }
    /**
     * Returns the current console display value 
     * @return the console display mode value
     */
    public boolean getConsoleDisplayMode()
    {
        return consoleDisplayMode;
    }
    
    /**
     * Sets the console display value
     * @param value 
     */
    public void setConsoleDisplayMode(boolean value)
    {
        consoleDisplayMode = value;
    }
    
    /**
     * Displays the message in the parameters if the consoledisplayMode is set to true
     * @param msg 
     */
    public void printConsoleDisplay(String msg)
    {
        if (consoleDisplayMode == true)
        {
            System.out.println(msg);
        }
    }
    
    /**
     * Returns the name of the observable
     * @return the name of the observable 
     * @see java.lang.Object
     */
    @Override
    public String toString()
    {
        return "Observable: " + this.observableID;
    }

    /**
     * Returns the number of observers of the observable
     * @return the number of observers
     * @see java.util.Observable
     */
    @Override
    public int countObservers()
    {
        return super.countObservers();
    }
    
    /**
     * Returns the number of observers of the observable and its parent
     * @return the number of observers of the observable and its parent
     */
    @Override
    public int countAllObservers()
    {
        if (this.parentObservable != null)
        {
            return super.countObservers() + parentObservable.countObservers();
        }
        else
        {
            return countObservers();
        }
    }

    /**
     * Returns the most recent change to the observable's collection
     * @return the most recent change to the observable's collection
     */
    public CollectionEventDataEnum getMostRecentChange() 
    {
        return this.mostRecentChange;
    }
    
    
}