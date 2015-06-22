/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

/**
 *
 * @author jonathanodgis
 */
public enum TestObservableEnum implements HObservable
{
    OA(),
    OB(OA),
    OC(OA),
    OD(OA),
    OE(OD),
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
    

    public TestObservableEnum getTestEnum() 
    {
        return this;
    }
    
    public BaseHObservable getObservableObject() 
    {
        return observableObject;
    }

    @Override    
    public void registerObserver(HObserver obs) 
    {
        this.observableObject.registerObserver(obs);
    }

    @Override
    public void notifyObservers() {
        this.observableObject.notify();
    }

    @Override    
    public void notifyObservers(Object eventData) 
    {
        this.observableObject.notifyObservers(eventData);
    }

    @Override
    public void deleteObserver(HObserver observer) 
    {
        this.observableObject.deleteObserver(observer);
        System.out.println("Observer " + observer + " has been deleted.");
    }

    @Override
    public String getObservableID() 
    {
        return this.observableObject.getObservableID();
    }

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

    @Override
    public int countObservers() 
    {
        return this.observableObject.countObservers();
    }
}
