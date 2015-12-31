package com.barfly.hobservable;
/**
 * This Interface represents the HObservable object and contains all the methods to be used by all of the classes that implement it. 
 * @author dblickstein
 */
public interface HObservable 
{
    /**
     * implements the same functionality as java.util.Observable.setChanged().
     * @see java.util.Observable#setChanged() 
     */
    public void setChanged();

    /**
     * implements the same functionality as java.util.Observable.notifyObservers().
     * @see java.util.Observable#notifyObservers() 
     */    
    public void notifyObservers();
    
    /**
     * implements the same functionality as java.util.Observable.notifyObservers(java.lang.Object).
     * @param o The object that is fired to the observers
     * @see java.util.Observable#notifyObservers(java.lang.Object) 
     */    
    public void notifyObservers(Object o);

    /**
     * Adds an observer to the observable's known list of observers.
     * @param observer The observer that is added to the observable.
     * @see java.util.Observable#addObserver(java.util.Observer) 
     */    
    public void addObserver(HObserver observer);    
  
    /**
     * Removes an observer from the observable's known list of observers.
     * @param observer The observer that is removed from the observable.
     * @see java.util.Observable#deleteObserver(java.util.Observer)
     */    
    public void deleteObserver(HObserver observer);

    /**
     * Returns a string of the observable's name
     * @return the string of the observable's name
     */
    public String getObservableID();

    /**
     * Returns the parent observable of the observable
     * @return the parent observable 
     */
    public BaseHObservable getParentObservable();

    /**
     * Sets the value of the parentObservable
     * @param parentObservable The parent of the observable.
     */
    public void setParentObservable(BaseHObservable parentObservable);

    /**
     * Returns the number of observers of an observable
     * @return the number of the observers of an observable
     * @see java.util.Observable#countObservers() 
     */    
    public int countObservers();

    /**
     * Returns the number of observers of both the observable and parent
     * @return the number of observers of the observable and parent
     */    
    public int countAllObservers();
    
}
