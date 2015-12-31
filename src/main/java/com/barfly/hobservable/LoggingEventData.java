package com.barfly.hobservable;

/**
 * This Interface represents the LoggingEventData object. 
 * Instances of this interface are associated with the LoggingHObserver class and are logged as events in the console. 
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
