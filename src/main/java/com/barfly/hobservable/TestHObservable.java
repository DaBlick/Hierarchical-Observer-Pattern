package com.barfly.hobservable;

import java.util.ArrayList;

public class TestHObservable extends BaseHObservable 
{
    
    public TestHObservable(String observableID, BaseHObservable observable)   //observable being barflyenum tournament.getobservableobject
    {
        super(observableID, observable); 
    }
    
    public TestHObservable(String observableID, BaseHObservable observable, NotificationOrder order)
    {
        super(observableID, observable, order);
    }
    
    public TestHObservable(String observableID, TestObservableEnum e)
    {
        super(observableID, e.getObservableObject());
    }
    
    public TestHObservable(String observableID, TestObservableEnum e, NotificationOrder order)
    {
        super(observableID, e.getObservableObject(), order);
    }   
    
}
