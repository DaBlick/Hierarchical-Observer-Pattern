/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable.collectionsexample;

/**
 *
 * @author jonathanodgis
 */
public class Contact 
{
    private String contactID;
    private String contactNumber;
    public Contact(String contactID, String contactNumber)
    {
        this.contactID = contactID;
        this.contactNumber = contactNumber;
    }
    
    public String getID()
    {
        return this.contactID;
    }
    
    public void setID(String contactID) 
    {
        this.contactID = contactID;
    }

    String getNumber() 
    {
        return this.contactNumber;
    }
        
    
    public void setNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }
    
    @Override
    public String toString()
    {
        return this.contactID + " | " + this.contactNumber;
    }


    
    
}
