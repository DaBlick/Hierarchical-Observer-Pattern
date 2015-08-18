package com.barfly.hobservable.collectionsexample;

import com.barfly.hobservable.HObserver;
import com.barfly.hobservable.LoggingHObserver;
import java.util.Observable;

/**
 *
 * @author jonathanodgis
 */
public class PhoneApp extends LoggingHObserver implements HObserver 
{  
    private int updateCount;
    
    public PhoneApp(String appID)
    {
        super(appID);
        this.updateCount = 0;
    }
      
    @Override
    public void update(Observable observable, Object event) 
    {
        System.out.println(event + " in the " + getAppID() + " " + observable);
        updateCount++;
    }
    
    public String getAppID()
    {
        return super.toString();
    }
    
    public int getUpdateCount()
    {
        return this.updateCount;
    }
    
}
