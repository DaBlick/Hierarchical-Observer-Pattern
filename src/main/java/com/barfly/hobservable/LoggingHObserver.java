package com.barfly.hobservable;

import java.util.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jonathanodgis
 */
public class LoggingHObserver implements HObserver {

    private static final LogLevel DEFAULT_LOGLEVEL = LogLevel.WARN;

    private Logger log;

    /**
     * Creates a LoggingObserver that generates its own Logger using
     * LoggerFactor.getLogger(name)
     *
     * @param name
     */
    public LoggingHObserver(String name) {
        log = LoggerFactory.getLogger(name);
    }

    /**
     * Create a LoggingObserver that generates its own Logger using
     * LoggingFactory.getLogger(clazz).
     *
     * @param clazz Class marker to use for all log events
     */
    public LoggingHObserver(Class<? extends Class> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

    /**
     * Create an Observer with provided Logger
     *
     * @param logger The Logger
     *
     */
    public LoggingHObserver(Logger logger) {
        log = logger;
    }

    /**
     * Receives events from the Observer and writes them to the log
     *
     * @param observable The Observable
     * @param eventData The event context
     */
    @Override
    public void update(Observable observable, Object eventData) {
        String eventDataStr;
        LogLevel level = DEFAULT_LOGLEVEL;
        if (eventData instanceof LoggingEventData) {
            LoggingEventData ed = (LoggingEventData) eventData;
            eventDataStr = observable.toString();
            System.out.println("This eventData is an instance!");
            ed.getLogLevel();
            eventDataStr = ed.logString((HObservable) observable);
        } else {
            eventDataStr = eventData.toString();     //call eventData.toString()
            System.out.println("This eventData is NOT an instance!");
        }

        switch (level) {
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
}

