package com.barfly.hobservable;

/**
 * Set event data functions used to define the functions in the set observables and assign the mostRecentChange field in BaseHObservable an enum value representing the last function on the set.
 * @author jonathanodgis
 */
public enum SetEventDataEnum 
{
    /**
     * Enum constant representing the add function of the set.
     */
    ADD,
    /**
     * Enum constant representing the add all function of the set.
     */
    ADDALL,
    /**
     * Enum constant representing the remove function of the set.
     */
    REMOVE,
    /**
     * Enum constant representing the remove all function of the set.
     */
    REMOVEALL,
    /**
     * Enum constant representing the retain function of the set.
     */
    RETAIN,
    /**
     * Enum constant representing the retain all function of the set.
     */
    RETAINALL,
    /**
     * Enum constant representing the clear function of the set.
     */
    CLEAR,
     ;  
}
