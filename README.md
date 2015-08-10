# Hierarchical Observer

HObserver is an extension of the `java.util.Observable` class and it's supporting framework that allows Observables to 
be part of an hierarchy such that events fired to any observable are also fired to its ancestors.

#Observer Pattern

`Observable` is an implementation of the GOF (Gang of Four) "Observer" pattern. An "Observable" provides an API with which
"Observers" can register themselves for notification whenever the Observable is changed.   Since Observers register with 
Observable, the Observer is unaware of and thus decouple from its observers.

For example, in a typical AJAX web application changing the name of some domain entity may require re-rendering in another 
part of the DOM where the name is displayed.   Let's say we have a web app that maintains a contact list.   Whenever the 
name of  a contact is changed, we might need to update any place where names are used.   Such places would register
themselves as observers of the the contact list.

#What is an Hierarchical Observer?

Suppose we wanted finer grained control over the update notification.   For example, if we have a component that only displays the name, we don't need (nor "want") the expense of re-rendering if the thing that changed was the contact's 
phone number.

HObserver expands on the design pattern by introducing the notion of Observables that are part of a tree of Observables. The tree reflects the logical structure of the domain objects.   Each Observable in the tree C might have a parent Observable P. Anytime a notification is sent to observers of C, notifications are also sent to observers of all ancestors of C.

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

Every time a contact's Home Phone is changed, all observers of the "Home Phone" Observable are notified.   But an observer who registered as an observer of "Phone Numbers" will also get a notification when the "Home Phone" is changed, or any
phone number.

Observers can also register for notification of changes to ANY part of a contact by registering with "Contact". And if you have something that wants to be notified whenever any part of a contact changes, or whenever the list itself 
changes (via addition and deletion) there is that option as well.

This example is admittedly contrived and overkill but contrived overkill examples are usually the most helpful.

#What is the Logging Observer?

The Logging Observer provides an easy way to create Log File entries in response to changes to an Observable. This is accomplished via a builtin Observer that writes a Log File entry for each change to the Observable via the SLF4J API.

The LoggingObserver instantiates a Logger using the built in Logger Factory and then the observer creates a log which contains information about the events and the time that the event was logged. The `LoggingObserver` can write the data to different log levels such as `DEBUG`, `TRACE`, `WARN`, `ERROR`, and `INFO`. 

Consider a tournament application that contains a list of the players competing in a tournament. 

The Player List class contains functions such as adding players, removing players, and editing existing players in the list to provide updated information.

For each of these functions, there is an observable designed to notify its observers of events. The `addObservable`, the `removeObservable`, and the `editObservable` are all children of the `mainObservable`.  If a player is added to the list, then the  `addObservable` will fire an event to its observers and also the parent, `mainObservable` will notify its observers of the event as well. 

- `Player List`
	- `mainObservable`
		- Observers
	- `AddObservable`
		- Observers
	- `RemoveObservable`
		- Observers
	- `EditingObservable`
		- Observers

The observers in this example are logging observers and display information about the changes to the `PlayerList` pertaining to the observable they observe. In this case, we are going to have observers that observe the `addObservable` and `editObservable`

First, we initialize a `PlayerList` and also populate the list with `Player` objects. 

PlayerList playerList = new PlayerList();
Player player1 = new Player("Jon", 21, "M", "jon@gmail.com");
Player player2 = new Player("Mike", 21, "M", "mike@gmail.com");

`Player` objects contain fields such as the player's name, age, gender, and email. The information can be changed and accessed via setters and getters. 

Next, we create `LoggingHObserver` objects which will observe the observables in the `PlayerList` class. 

	HObserver observer1 = new LoggingHObserver("AddObserver");
	HObserver observer2 = new LoggingHObserver("EditObserver");
	HObserver observer3 = new LoggingHObserver("MainObserver");

`LoggingHObserver` objects display information in the console related to the event it receives. When the observables use their built-in notifyObservers() method, these observers will be updated with the information and display it in the console. 

	playerList.addObservable.addObserver(observer1);
	playerList.editObservable.addObserver(observer2);
	playerList.mainObservable.addObserver(observer3);
	        
This code adds the observers to the observable they will be notified events from. Anytime an event is notified, the parent also notifies its observers of the same event. For example, if `addObservable` calls notifyObservers(), it will also notify the `mainObservable` object's children as well.

	playerList.addPlayer(player1);
	playerList.addPlayer(player2);
	playerList.editPlayer(player1, "Jonathan", player1.getAge(), player1.getGender(), "jonathan@gmail.com");

We add two `Player` objects to the `PlayerList` and then edit one of the existing `Player` objects on the list. Since the addPlayer() method is being called twice, the `addObservable` and `mainObservable`'s observers are both being notified of changes in the PlayerList. The loggers should both display that `player1`  and `player2` were added. Finally, the `editObservable` will notify its observers since the playerList makes a call to the editPlayer() method. Both editObservable and its parent will notify their observers of the edit.

Using the `LoggingHObserver` can serve as a useful form of keeping track of changes of different functions of a component. Like the example above where a list is modified in different ways, the observers can keep track of information of different observables and display information at the console level of the application. 

#What is the Collection Observable?

Collection Observable allows for observers to receive events that provide information about data that was changed in the collection.

Collection Observable is an extension of the `BaseHObservable` class. `AbstractCollectionObservable` is an abstract class that has sub classes that extend it which use different data structures for their collections. The abstract class also implements the `Collection` Interface.

- `HObservable`
	- `BaseHObservable`
      - `AbstractCollectionObservable`
	      - `ListObservable`
	      - `SetObservable`

Suppose a collection observable that uses an `ArrayList`, an instance of `Collection`. If data is added to the list, the observers are notified of the recent change in the form of an enum. Examples of *EventDataEnum* are for `ADD`, `REMOVE`, `ADDALL`, and `REMOVEALL`.

Consider an instant messaging service that holds a list of contacts in a collection such as a list. The service is the observable and the observers are the clients downloaded by the user to use the service on a variety of devices. Observers can receive information in the form of an event. 

This event includes information about the operations on the list. For the end user, the operations are adding and removing contacts from the current list of names. 

As these changes are made, observers of this collection are notified in real time and the client is kept up to date every time the user accesses the service from the client. 

- Instant Messaging Service
	- Client on Desktop
	- Client on Web
	- Client on Mobile


