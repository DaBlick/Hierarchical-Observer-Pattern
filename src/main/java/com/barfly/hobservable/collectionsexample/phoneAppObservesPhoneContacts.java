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
        PhoneApp appC = new PhoneApp("Garbage Bin App");
        ContactsList contactsList = new ContactsList("User");
        ListObservable mainObservable = contactsList.getMainObservable();
        ListObservable addObservable = contactsList.getAddObservable();
        ListObservable removeObservable = contactsList.getRemoveObservable(); 

        Contact contact1 = new Contact("Jon", "111-111-1111");
        Contact contact2 = new Contact("Mike", "222-222-2222");
        Contact contact3 = new Contact("Alex", "333-333-3333");
        Contact contact4 = new Contact("Tom", "444-444-4444");     
               
        mainObservable.addObserver(appA);
        addObservable.addObserver(appB);
        removeObservable.addObserver(appC);
        contactsList.addContact(contact1);
        contactsList.addContact(contact2);        
        contactsList.addContact(contact3);        
        contactsList.addContact(contact4); 
        contactsList.removeContact(contact3);

        
    }
}
