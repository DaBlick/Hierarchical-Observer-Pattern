package com.barfly.hobservable;

/**
 * Set changed modes used to change whether the setChanged() function is called automatically or manually by the user. 
 * @author jonathanodgis
 */
public enum SetChangedMode 
{
    /**
     * Enum representing the automatic setChangedMode. Using this enum allows the observable to call the setChanged() method automatically.
     */
    AUTO(), 
    /**
     * Enum representing the manual setChangedMode. Using this enum allows the observable to call the setChanged ONLY when the user makes the setChanged() calls manually. 
     */
    MANUAL();
}
