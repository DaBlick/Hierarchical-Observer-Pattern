package com.barfly.hobservable;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author dblickstein
 */
public interface HObserver extends Observer
{
    /**
     * implements the same functionality as java.util.Observer#update(java.util.Observable, java.lang.Object) 
     * @param observable
     * @param eventData 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object) 
     */
    public void update(Observable observable, Object eventData);     

    /**
     * implements the same functionality as java.util.Observer#toString() 
     * @return the name of the observer 
     * @see java.util.Observer#toString() 
     */
    public String toString();
    
    
}
