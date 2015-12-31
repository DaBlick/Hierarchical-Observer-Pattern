/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable.collections;

import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.NotificationOrder;
import static com.barfly.hobservable.NotificationOrder.POST;
import com.barfly.hobservable.SetChangedMode;
import static com.barfly.hobservable.SetChangedMode.AUTO;
import java.util.Collection;
import java.util.Set;

/**
 * This class represents the SetObservable object which extends AbstractCollectionObservable and implements the Set Interface. 
 * The class uses the constructors in AbstractCollectionObservable to create objects and uses the methods in the super class to modify the set. An instance of this class is able to use a set of data and pass information to attached observers as events.
 * @author jonathanodgis
 */
public class SetObservable<T> extends AbstractCollectionObservable implements Set//list looks at collection interface
{   
    /**
     * Creates a new SetObservable object using a String observableID, BaseHObservable parentObservable, and a Collection<T> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param collection The observable's collection
     */
    public SetObservable(String observableID, BaseHObservable parentObservable, Collection<T> collection)   
    {
        super(observableID, parentObservable, POST, AUTO, collection);
    }    
    
    /**
     * Creates a new SetObservable object using a String observableID, BaseHObservable parentObservable, NotificationOrder order, and a Collection<T> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param order The observable's notification order when notifyObservers() is used
     * @param collection The observable's collection
     */
    public SetObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, Collection<T> collection)  
    {
        super(observableID, parentObservable, order, AUTO, collection);
    }    

    /**
     * Creates a new SetObservable object using a String observableID, BaseHObservable parentObservable, SetChangedOrder setChangedMode, and a Collection<T> collection.
     * @param observableID The name of the observable
     * @param parentObservable The observable's parent
     * @param setChangedMode The setting to determine if the observable needs to be setChanged manually by the user via a setChanged() call or automatically done by the class
     * @param collection The observable's collection
     */
    public SetObservable(String observableID, BaseHObservable parentObservable, SetChangedMode setChangedMode, Collection<T> collection)   
    {
        super(observableID, parentObservable, POST, setChangedMode, collection);
    }        
    
    /**
     * Creates a new SetObservable object using a String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedOrder setChangedMode, and a Collection<T> collection.
     * @param observableID The name of the observable.
     * @param parentObservable The observable's parent.
     * @param order The observable's notification order when notifyObservers() is used.
     * @param setChangedMode The setting to determine if the observable needs to be setChanged manually by the user via a setChanged() call or automatically done by the class.
     * @param collection The observable's collection.
     */
    public SetObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedMode setChangedMode, Collection<T> collection)
    {
        super(observableID, parentObservable, order, setChangedMode, collection);   
    }
    
}
