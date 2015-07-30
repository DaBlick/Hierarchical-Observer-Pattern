# Hierarchical Observer

HObserver is an extension of the `java.util.Observable` class and it's supporting framework that allows Observables to 
be part of an hierarchy such that events fired to any observable are also fired to its ancestors.

#Observer Pattern

`Observable` is an implementation of the GOF (Gang of Four) "Observer" pattern.   An "Observable" provides an API with which
"Observers" can register themselves for notification whenever the Observable is changed.   Since Observers register with 
Observable, the Observer is unaware of and thus decouple from its observers.

For example, in a typical AJAX web application changing the name of some domain entity may require re-rendering in another 
part of the DOM where the name is displayed.   Let's say we have a web app that maintains a contact list.   Whenever the 
name of  a contact is changed, we might need to update any place where names are used.   Such places would register
themselves as observers of the the contact list.

#What is an Hierarchical Observer?

Suppose we wanted finer grained control over the update notification.   For example, if we have a component that only
displays the name, we don't need (nor "want") the expense of re-rendering if the thing that changed was the contact's 
phone number.

HObserver exapnds on the design pattern by introducing the notion of Observables that are part of a tree of Observables.  
The tree reflects the logical structure of the domain objects.   Each Observable in the tree C might have a parent Observable
P.   Anytime a notification is sent to observers of C, notifications are also sent to observers of all ancestors of C.

It might be best to explain this in terms of our contact list example.  

Here's a potential organization of the tree of oberservables:

- Contact List
   - Contact
      - Name
      - Phone numbers
        - Work Phone
        - Home Phone
      - Address
         - Country

Every time a contact's Home Phone is changed, all observers of the "Home Phone" Observable are notified.   But an observer 
who registered as an observer of "Phone Numbers" will also get a notification when the "Home Phone" is changed, or any
phone number.

Observers can also register for notification of changes to ANY part of a contact by registering with "Contact".   And if 
you have something that wants to be notified whenever any part of a contact changes, or whenever the list itself 
changes (via addition and deletion) there is that opiion as well.


This example is admittedly contrived and overkill but contrived overkill examples are usually the most helpful. 

#What is the Logging Observer?

In the Hierarchical Observer Pattern, there are multiple types of observers that handle events differenty. Thg logging observer uses the Logger API in Java in order to handle incoming events at different levels of the log. The LoggingObserver instantiates a Logger using the built in Logger Factory and then events are processed by the LoggingObserver class and the proper log message is outputed through the log. The loggingObserver can write the data to different log levels such as DEBUG and INFO. 


#What is the Collection Observable

Suppose the user wants to have an observable consisting of a specific type of collection and they want to have observers notified when there is a change made to the observable collection. Collection Observable allows for observers to be fired an event that not only provides information about the data changed but also the operation executed on the list.

For example, a collection observable that uses an ArrayList, a type of collection for its collection can add or remove data from its list. If data is removed from the list, the most recent event done on the observable's collection is set to Add and then the observers of this observable are notified of the recent change in the form of an enum. Examples of EventDataEnums are for ADD, REMOVE, ADDALL, REMOVEALL.