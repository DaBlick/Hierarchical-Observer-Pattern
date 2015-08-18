package com.barfly.hobservable;
/**
 *
 * @author dblickstein
 */
public interface HObservable 
{
    /**
     * implements the same functionality as java.util.Observable#setChanged()
     * @see java.util.Observable#setChanged() 
     */
    public void setChanged();

    /**
     * implements the same functionality as java.util.Observable#notifyObservers()
     * @see java.util.Observable#notifyObservers() 
     */    
    public void notifyObservers();
    
    /**
     * @see java.util.Observable#notifyObservers(java.lang.Object) 
     */    
    public void notifyObservers(Object o);

    /**
     * @see java.util.Observable#addObserver(java.util.Observer) 
     */    
    public void addObserver(HObserver observer);    

    
    /**
     * @see java.util.Observable#deleteObserver(java.util.Observer)
     */    
    public void deleteObserver(HObserver observer);

    /**
     * Returns a string of the observable's name
     * @return the string of the observable's name
     */
    public String getObservableID();

    /**
     * Returns the parentObservable
     * @return the parentObservable
     */
    public BaseHObservable getParentObservable();

    /**
     * Sets the value of the parentObservable
     * @param parentObservable 
     */
    public void setParentObservable(BaseHObservable parentObservable);

    /**
     * Returns the number of observers of an observable
     * @return the number of the observers
     * @see java.util.Observable#countObservers() 
     */    
    public int countObservers();

    /**
     * Returns the number of observers of an observable and its parent
     */    
    public int countAllObservers();
    
}
