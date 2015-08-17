/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable.collectionsexample;

import com.barfly.hobservable.collections.ListObservable;

/**
 *
 * @author jonathanodgis
 */
public class phoneAppObservesPhoneContacts 
{

    public static void main(String[] args)
    {
        PhoneApp appA = new PhoneApp("System App");  //Most important app that really needs the contacts
        PhoneApp appB = new PhoneApp("Email App");
        PhoneApp appC = new PhoneApp("Social Media App");        
        ContactsList contactsList = new ContactsList("User");
        ListObservable mainObservable = contactsList.getMainObservable();
        ListObservable addObservable = contactsList.getAddObservable();
        ListObservable editObservable = contactsList.getEditObservable();
        Contact contact1 = new Contact("Jon", "111-111-1111");
        Contact contact2 = new Contact("Mike", "222-222-2222");
        Contact contact3 = new Contact("Alex", "333-333-3333");
        Contact contact4 = new Contact("Tom", "444-444-4444");     
        
        mainObservable.addObserver(appA);
        addObservable.addObserver(appB);
        editObservable.addObserver(appC);   
        contactsList.addContact(contact1);
        contactsList.addContact(contact2);        
        contactsList.addContact(contact3);        
        contactsList.addContact(contact4);  
        contactsList.editContact(contact1, "Jonathan", "222-222-2222");
        System.out.println(mainObservable.size());
        System.out.println(addObservable.size());
        System.out.println(editObservable.size());
        System.out.println(contactsList);
        
    }
}
