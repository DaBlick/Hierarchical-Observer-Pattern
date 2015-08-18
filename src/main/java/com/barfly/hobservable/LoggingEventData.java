package com.barfly.hobservable;

/**
 *
 * @author jonathanodgis
 */
public interface LoggingEventData 
{
    public String logString(HObservable hObservable);
    public LogLevel getLogLevel();     
}
