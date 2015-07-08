package com.barfly.hobservable;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author dblickstein
 */
public interface HObserver extends Observer
{
    public void update(Observable observable, Object eventData);     

    public String toString();
    
    
}
