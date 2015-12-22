package com.barfly.hobservable;

import java.util.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jonathanodgis
 */
public class LoggingHObserver implements HObserver {

    private final String observerID;
    private final LogLevel DEFAULT_LOGLEVEL = LogLevel.WARN;
    private static final Logger log = LoggerFactory.getLogger(LoggingHObserver.class);

    /**
     * Creates a LoggingObserver that generates its own Logger using
     * LoggerFactor.getLogger(name)
     * @param observerID
     */
    public LoggingHObserver(String observerID) 
    {
        this.observerID = observerID;
    }

    /**
     * Receives events from the Observable and writes them to the log.
     * @param observable The observable.
     * @param eventData The event data.
     */
    @Override
    public void update(Observable observable, Object eventData) 
    {
        String eventDataStr;
        LogLevel logLevel = DEFAULT_LOGLEVEL;
        if (eventData instanceof LoggingEventData) 
        {

            LoggingEventData ed = (LoggingEventData) eventData;
            logLevel = ed.getLogLevel();
            eventDataStr = ed.logString((HObservable) observable);
            System.out.println("This eventData [" + eventData + "] is an instance!");
        } 
        else 
        {
            eventDataStr = eventData.toString();
            System.out.println("This eventData [" + eventData + "] is NOT an instance!");
        }

        switch (logLevel) 
        {
            case TRACE:
                log.trace(eventDataStr);
                break;
            case DEBUG:
                log.debug(eventDataStr);
                break;
            case INFO:
                log.info(eventDataStr);
                break;
            case WARN:
                log.warn(eventDataStr);
                break;
            case ERROR:
                log.error(eventDataStr);
                break;
        }
    }
 
    /**
     * Returns the name of the observer.
     * @return the name of the observer.
     */
    @Override
    public String toString()
    {
        return this.observerID;
    }

}
