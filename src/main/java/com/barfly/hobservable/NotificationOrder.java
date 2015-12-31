package com.barfly.hobservable;

/**
 * Notification orders used to change whether the parent is notified before or after the child observable. 
 * @author jonathanodgis
 */
public enum NotificationOrder 
{
    /**
     * Enum representing the Pre notification order. Using this enum will have the parent notified before the child observable.
     */
    PRE(),
    /**
     * Enum representing the Post notification order. Using this enum will have the parent notified after the child observable.
     */
    POST();
   
}
