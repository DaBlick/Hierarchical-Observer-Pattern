package com.barfly.hobservable;

import static com.barfly.hobservable.NotificationOrder.POST;
import static com.barfly.hobservable.NotificationOrder.PRE;
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
    
    private boolean consoleDisplayMode;
    
    public BaseHObservable(String observableID, BaseHObservable parentObservable)   //DEFAULT 
    {
        this(observableID, parentObservable, POST);
    }    
    
    
    public BaseHObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order)   //PRE
    {
        this.observableID = observableID;
        this.parentObservable = parentObservable;  
        this.order = order;
    }       

    /**
     * Adds an observer to the observable's list of observers
     * @param observer
     * @see java.util.Observable
     */    
    @Override
    public void addObserver(HObserver observer)
    {
        printConsoleDisplay("Registering observer....");
        super.addObserver(observer);
        //Should we add a way to set the observable in the observer object in this method?
    }    
    
    /**
     * Removes an observer from the observable's list of observers
     * @param observer 
     */
    @Override
    public void deleteObserver(HObserver observer)
    {
        printConsoleDisplay("Deleting observer....");
        super.deleteObserver(observer);
    }

    /**
     * Toggles the status of an observable before notifying the observers
     */
    @Override
    public void setChanged()    //make a test based on this
    {
        super.setChanged();
        if (this.parentObservable != null)
        {
            this.parentObservable.setChanged();
        }
    }
    
    /**
     * Notifies the observers of an observable and its parent. 
     */
    @Override
    public void notifyObservers() 
    {
        printConsoleDisplay("Broadcasting event.....");
        
        if (order.equals(PRE))
        {
            if (parentObservable != null)
            {
                parentObservable.notifyObservers();   //Notifies this observable's parent's observers         
            }
            super.notifyObservers();            
        }
        if (order.equals(POST))
        {
            super.notifyObservers();
            if (parentObservable != null)
            {
                parentObservable.notifyObservers();   //Notifies this observable's parent's observers         
            }           
        }
    }

    /**
     * Notifies the observers of an observable and its parent. 
     * @param eventData
     */    
    @Override
    public void notifyObservers(Object eventData) 
    {

        printConsoleDisplay("Broadcasting event.....");
        if (order.equals(PRE))
        {
            super.notifyObservers(eventData);
            if (parentObservable != null)
            {
                parentObservable.notifyObservers(eventData);   //Notifies this observable's parent's observers         
            }
//
        }
        if (order.equals(POST))
        {
            if (parentObservable != null)
            {
                parentObservable.setChanged();
                parentObservable.notifyObservers(eventData);    
            }
            super.setChanged();
            super.notifyObservers(eventData);
        }
        
    }
    
    /**
     * Returns the parent observable of this observable
     * @return the parent observable of this observable
     */
    @Override
    public BaseHObservable getParentObservable() 
    {
        printConsoleDisplay("Getting the parentObservable....");        
        return parentObservable;
    }

    /**
     * Sets the parent observable of this observable
     * @param parentObservable
     */    
    @Override
    public void setParentObservable(BaseHObservable parentObservable) 
    {
        printConsoleDisplay("Setting the parentObservable....");
        this.parentObservable = parentObservable;
    }

    /**
     * Returns the name of the observable
     * @return the name of the observable
     */
    @Override
    public String getObservableID()        
    {
        return observableID;
    }

    /**
     * Returns the path of the parent and child observable in string form
     * @return the path of the parent and child observable in string form
     */
    public String getFullPath() 
    {
        String result = this.toString();
        /*
        while (this.parentObservable != null) 
        {
            result += "/";
            result = result + parentObservable.toString();
        }
        */
        if (this.parentObservable != null)
        {
            result = this.parentObservable.getFullPath() + "/" + result;
            //result = result + "/" + this.parentObservable.getFullPath();
        }
        return result;
    }
    
    /**
     * Returns the notification order
     * @return the notification order
     */
    public NotificationOrder getNotificationOrder()
    {
        return this.order;
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
     */
    @Override
    public String toString()
    {
        return "Observable: " + this.observableID;
    }

    /**
     * Returns the number of observers of the observable
     * @return the number of observers
     */
    @Override
    public int countObservers()
    {
        return super.countObservers();
    }
    
    /**
     * Returns the number of observers of the observable and its parent
     * @return the number of observers of the observable and the parent
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
    
    
}
