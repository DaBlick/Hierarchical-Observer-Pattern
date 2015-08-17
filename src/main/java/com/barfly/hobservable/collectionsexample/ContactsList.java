/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable.collectionsexample;

import com.barfly.hobservable.collections.ListObservable;
import java.util.ArrayList;

/**
 *
 * @author jonathanodgis
 */
public class ContactsList
{
    private final String userID;
    private final ArrayList<Contact> contacts;   
    private final  ListObservable mainObservable;
    private final  ListObservable addObservable;
    private final  ListObservable removeObservable;
    private final  ListObservable editObservable;
    
    public ContactsList(String userID)
    {
        this.userID = userID;
        contacts = new ArrayList<>();    
        mainObservable = new ListObservable<>("Main Observable", null, new ArrayList<String>());    
        addObservable = new ListObservable<>("Add ListObservable", mainObservable, new ArrayList<String>());
        removeObservable = new ListObservable<>("Remove ListObservable", mainObservable, new ArrayList<String>());
        editObservable = new ListObservable<>("Edit ListObservable", mainObservable, new ArrayList<String>());
    }
    
    public void addContact(Contact contact)
    {
        Object event = contact + " was added to the list";
        contacts.add(contact);
        addObservable.add(event);
        addObservable.getParentObservable().add(event);
        addObservable.notifyObservers(addObservable.get(addObservable.size()-1));
    }
    
    public void removeContact(Contact contact)
    {
        Object event = contact + " was removed from the list";
        contacts.remove(contact);
        removeObservable.add(event);
        removeObservable.getParentObservable().add(event);
        removeObservable.notifyObservers(removeObservable.get(removeObservable.size()-1));
    }
        
    public void editContact(Contact contact, String name, String number)
    {
        Contact originalContact = new Contact(contact.getID(), contact.getNumber());
        contact.setID(name);
        contact.setNumber(number);
        Object event = contact + " was edited in the list" + " (Originally " + originalContact + ")";        
        editObservable.add(event);
        editObservable.getParentObservable().add(event);
        editObservable.notifyObservers(editObservable.get(editObservable.size()-1));
    } 
    
    public String getUserID()
    {
        return this.userID;
    }
    
    @Override
    public String toString()
    {
        return this.userID + "'s contacts" + " | " + size() + " contacts";
    }
    
    public ArrayList<Contact> getContacts()
    {
        return this.contacts;
    }
    
    public int size()
    {
        return contacts.size();
    }
    
    public ListObservable getMainObservable()
    {
        return this.mainObservable;
    }
    
    public ListObservable getAddObservable()
    {
        return this.addObservable;
    }

    public ListObservable getRemoveObservable()
    {
        return this.removeObservable;
    }    
    
    public ListObservable getEditObservable()
    {
        return this.editObservable;
    }        
    
    
}
