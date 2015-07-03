/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;
import org.slf4j.ext.XLogger;

/**
 *
 * @author jonathanodgis
 */
public interface LoggingEventData 
{
    public String logString(HObservable hObserver);
    public XLogger.Level getLogLevel(HObservable hObserver);  //TODO import XLogger into project
    
    
}
