package com.barfly.hobservable;

/**
 *
 * @author dblickstein
 */
public interface HObservable 
{

    public void notifyObservers();
    
    public void notifyObservers(Object o);

    public void deleteObserver(HObserver observer);

    public String getObservableID();

    public BaseHObservable getParentObservable();

    public void addObserver(HObserver observer);

    public void setParentObservable(BaseHObservable parentObservable);
    
    public int countObservers();
    
    public int countAllObservers();
}
