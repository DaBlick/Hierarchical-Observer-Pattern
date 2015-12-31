package com.barfly.hobservable;
/**
 * Collection event data functions used to define the functions in the collection observables and assign the mostRecentChange field in BaseHObservable an enum value representing the last function on the collection.
 * @author jonathanodgis
 */

public enum CollectionEventDataEnum 
{
    /**
     * Enum constant representing the add function of the collection.
     */
    ADD,
    /**
     * Enum constant representing the add all function of the collection 
     */
    ADDALL,
    /**
     * Enum constant representing the remove function of the collection
     */
    REMOVE,
    /**
     * Enum constant representing the remove all function of the collection
     */
    REMOVEALL,
    /**
     * Enum constant representing the retain function of the collection
     */
    RETAIN,
    /**
     * Enum constant representing the retain all function of the collection
     */
    RETAINALL,
    /**
     * Enum constant representing the clear function of the collection
     */
    CLEAR,
     ;  
}
