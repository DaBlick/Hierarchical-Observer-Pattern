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
 *
 * @author jonathanodgis
 */
public class SetObservable<T> extends AbstractCollectionObservable implements Set//list looks at collection interface
{   
    public SetObservable(String observableID, BaseHObservable parentObservable, Collection<T> collection)   
    {
        super(observableID, parentObservable, POST, AUTO, collection);
    }    
    
    public SetObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, Collection<T> collection)  
    {
        super(observableID, parentObservable, order, AUTO, collection);
    }    

    public SetObservable(String observableID, BaseHObservable parentObservable, SetChangedMode setChangedMode, Collection<T> collection)   
    {
        super(observableID, parentObservable, POST, setChangedMode, collection);
    }        
    
    public SetObservable(String observableID, BaseHObservable parentObservable, NotificationOrder order, SetChangedMode setChangedMode, Collection<T> collection)
    {
        super(observableID, parentObservable, order, setChangedMode, collection);   
    }
    
}
