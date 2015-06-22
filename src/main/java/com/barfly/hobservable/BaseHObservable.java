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
    
    
    //private Set<HObserver> observers;
    
    private BaseHObservable parentObservable;
    
    private String observableID;
    
    private TestObservableEnum observableEnum;  //added by Netbeans as a recommended fix for the enum call for event
    
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

    
    public void addObserver(HObserver observer)
    {
        printConsoleDisplay("Registering observer....");
        super.addObserver(observer);
    }    
    
    @Override
    public void deleteObserver(HObserver observer)
    {
        printConsoleDisplay("Deleting observer....");
        super.deleteObserver(observer);
    }

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
       
    @Override
    public void notifyObservers(Object eventData) 
    {

        printConsoleDisplay("Broadcasting event.....");
        if (order.equals(PRE))
        {
            super.setChanged();
            super.notifyObservers(eventData);
            if (parentObservable != null)
            {
                //parentObservable.setChanged();
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
    
    
    @Override
    public BaseHObservable getParentObservable() 
    {
        printConsoleDisplay("Getting the parentObservable....");        
        return parentObservable;
    }

    @Override
    public void setParentObservable(BaseHObservable parentObservable) 
    {
        printConsoleDisplay("Setting the parentObservable....");
        this.parentObservable = parentObservable;
    }

    @Override
    public String getObservableID()        
    {
        return observableID;
    }

    public String getFullPath() 
    {
        String result = this.toString();
        while (parentObservable != null) 
        {
            result += "/";
            result += parentObservable.toString();
        }
        return result;
    }
    
    public NotificationOrder getNotificationOrder()
    {
        return this.order;
    }
    
    public boolean getConsoleDisplayMode()
    {
        return consoleDisplayMode;
    }
    public void setConsoleDisplayMode(boolean value)
    {
        consoleDisplayMode = value;
    }
    
    public void printConsoleDisplay(String msg)
    {
        if (consoleDisplayMode == true)
        {
            System.out.println(msg);
        }
    }
    
    @Override
    public String toString()
    {
        return "Observable: " + this.observableID;
    }
}
