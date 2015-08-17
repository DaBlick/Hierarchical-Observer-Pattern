/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public void update(Observable o, Object arg) 
    {
        System.out.println(arg + " in the " + getAppID() + " " + o);
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
