package com.barfly.hobservable;

/**
 *
 * @author jonathanodgis
 */
public interface LoggingEventData 
{
    /**
     * Returns the event data string. 
     * @param hObservable The observable passing the event data.
     * @return the event data. 
     */
    public String logString(HObservable hObservable);
    
    /**
     * Returns the log level of the event.
     * @return the log level.
     */
    public LogLevel getLogLevel();     
}
