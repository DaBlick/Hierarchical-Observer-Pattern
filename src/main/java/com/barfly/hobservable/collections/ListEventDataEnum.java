/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable.collections;

/**
 * List event data functions used to define the functions in the list observables and assign the mostRecentChange field in BaseHObservable an enum value representing the last function on the list.
 * @author jonathanodgis
 */
public enum ListEventDataEnum 
{
    /**
     * Enum constant representing the add function of the list.
     */
    ADD,
    /**
     * Enum constant representing the add all function of the list. 
     */
    ADDALL,
    /**
     * Enum constant representing the remove function of the list.
     */
    REMOVE,
    /**
     * Enum constant representing the remove all function of the list.
     */
    REMOVEALL,
    /**
     * Enum constant representing the retain function of the list.
     */
    RETAIN,
    /**
     * Enum constant representing the retain all function of the list.
     */
    RETAINALL,
    /**
     * Enum constant representing the clear function of the list.
     */
    CLEAR,
     ;    
}