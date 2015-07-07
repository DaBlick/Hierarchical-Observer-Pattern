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
public class eventData implements LoggingEventData
{
    
    private Object data;
    
    public eventData(Object data)
    {
        this.data = data;
    }
    
    @Override
    public String toString()
    {
        return this.data.toString();
    }
    
    @Override
    public String logString(HObservable hObservable)    //TODO log this string to the log. 
    {
        return hObservable.getObservableID();      
    }

    @Override
    public XLogger.Level getLogLevel(HObservable hObservable)
    {
        switch (hObservable.getObservableID()) 
        {
            case "DEBUG":
                return XLogger.Level.DEBUG;
            case "INFO":
                return XLogger.Level.INFO;
            case "TRACE":
                return XLogger.Level.TRACE;
            case "WARN":
                return XLogger.Level.WARN;
            case "ERROR":
                return XLogger.Level.ERROR;
            default:
                return XLogger.Level.valueOf("DEFAULT");
        }
    }
}
