package com.barfly.hobservable;

import java.util.Observer;

/**
 *
 * @author dblickstein
 */
public interface HObserver extends Observer
{
    String getObserverID();
}
