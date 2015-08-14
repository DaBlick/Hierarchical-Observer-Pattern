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

Consider a tournament application that contains a list of the players competing in a tournament but we also have a score keeper component that changes scores based on the changes in the list of players. 

The `PlayerList` class contains functions such as adding players, removing players, and editing existing players in the list to provide updated information.

For each of these functions, there is an observable designed to notify its observers of events. The `addObservable`, the `removeObservable`, and the `editObservable` are all children of the `mainObservable`.  If a player is added to the list, then the  `addObservable` will fire an event to its observers and also the parent, `mainObservable` will notify its observers of the event as well. 

- `Player List`
	- `mainObservable`
		- `ScoreKeeper`
	- `AddObservable`
		- `ScoreKeeper`
	- `RemoveObservable`
		- `ScoreKeeper`
	- `EditingObservable`
		- `ScoreKeeper`

First, we create the `ScoreKeeper`Object which will be the observer. 

        ScoreKeeper scoreKeeper = new ScoreKeeper("Score Keeper");

The `ScoreKeeper` object extends the `LoggingHObserver` class and implements `Observer`. This allows the `ScoreKeeper` to act as an observer that can be updated by observables. In this case, we are going to have `ScoreKeeper` observe the `addObservable` and `editObservable`. Let's take a look at a block of the `ScoreKeeper` class. 

	public class ScoreKeeper extends LoggingHObserver implements Observer 
	{
	    private PlayerList playerList;
    
	    public ScoreKeeper(String observerID)
	    {    
	        super(observerID);
	        playerList = new PlayerList();
	    }

	    @Override
	    public void update(Observable observable, Object eventData) 
	    {
	        System.out.println("Updating...");
	        String observableID = observable.toString();
	        if (observableID.contains("add observable"));          
	        {
	            respondToAdd();
	        }
	        if (observableID.contains("remove observable"))
	        {
	            respondToRemove();
	        }
	        if (observableID.contains("edit observable"))
	        {
	            respondToEdit();           
	        }
	        super.update(observable, eventData);
	    }   

The `ScoreKeeper` contains a reference to the `PlayerList` class and an observerID. It also an log information using its superclass the `LoggingHObserver`. `ScoreKeeper` implements `Observer` and can receive an update each time the observable makes a notifyObservers() call. The update() method is an overridden method of the Observer interface and provides it's own definition of updating. In this case, if the observable of the the `ScoreKeeper` contains a  certain String value, then the `ScoreKeeper` will execute the right method in response to the `PlayerList`'s changes. 

Next, we initialize a `PlayerList` and also populate the list with `Player` objects. 

        PlayerList playerList = scoreKeeper.getPlayerList();
        Player player = new Player("Jon", 21, "M", "jonathan@gmail.com");   

`Player` objects contain fields such as the player's name, age, gender, and email. The information can be changed and accessed via setters and getters. 

Next we create references to the observables in the `PlayerList`.

        BaseHObservable mainObservable = playerList.getMainObservable();
        BaseHObservable addObservable = playerList.getAddObservable();
        BaseHObservable editObservable = playerList.getEditObservable();
        
Now we must add our `ScoreKeeper` object to the observables. Since `ScoreKeeper` implements the `Observer` interface, we can easily add this object to the observables.

        System.out.println("Starting...");
        
        mainObservable.addObserver(scoreKeeper);
        addObservable.addObserver(scoreKeeper);
        editObservable.addObserver(scoreKeeper);

Our ScoreKeeper is now an observer of the `mainObservable`, the `addObservable`, and the `editObservable`. When a player is added, removed, edited, the observables will fire events to the `ScoreKeeper`.

Next, let's make some changes to the `PlayerList`. 

        playerList.addPlayer(player);
        playerList.editPlayer(player, "Jonathan", player.getAge(), player.getGender(), "jonathan@gmail.com");
        
The PlayerList will add the `Player` object and then edit the Player's name and email. As these two operations are happening, the observers are being notified of these events. Let's take a look at the addPlayer() method in PlayerList.

    public void addPlayer(Player player)
    {
        players.add(player);
        addObservable.notifyObservers(player + " was added to the list");
    }
The player is being added to the list of players and then the addObservable is notifying all the observers of the event. Our `ScoreKeeper` is an observer of `addObservable` and will be updated via the update() method which is conveniently called by notifyObservers().

Finally, we run a test assertion to see if the code works and provides the correct results. We test to see if there is 1 player in the `PlayerList` and also if the player information that was changed is correct.   

        String testName = "Jonathan";
        String testEmail = "jonathan@gmail.com";
        int testIndex = playerList.getPlayers().indexOf(player);
        Player testPlayer = playerList.getPlayers().get(testIndex);
        
        assert(playerList.size() == 1 && testPlayer.getName().equals(testName) && testPlayer.getEmail().equals(testEmail)); 

The output of this code is presented in log form and looks like this. 


-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running PlayerListObservablesTest
This eventData [Jon was added to the list] is NOT an instance!
18:03:56.239 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was added to the list
This eventData [Jon was added to the list] is NOT an instance!
18:03:56.243 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was added to the list
This eventData [Jon was added to the list] is NOT an instance!
18:03:56.243 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was added to the list
This eventData [Jon was added to the list] is NOT an instance!
18:03:56.243 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was added to the list
This eventData [Mike was added to the list] is NOT an instance!
18:03:56.244 [main] WARN  c.b.hobservable.LoggingHObserver - Mike was added to the list
This eventData [Mike was added to the list] is NOT an instance!
18:03:56.244 [main] WARN  c.b.hobservable.LoggingHObserver - Mike was added to the list
This eventData [Jon was removed from the list] is NOT an instance!
18:03:56.244 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was removed from the list
This eventData [Jon was removed from the list] is NOT an instance!
18:03:56.244 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was removed from the list
This eventData [Jon was added to the list] is NOT an instance!
18:03:56.244 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was added to the list
This eventData [Mike was added to the list] is NOT an instance!
18:03:56.244 [main] WARN  c.b.hobservable.LoggingHObserver - Mike was added to the list
This eventData [Jonathan was edited in the list] is NOT an instance!
18:03:56.244 [main] WARN  c.b.hobservable.LoggingHObserver - Jonathan was edited in the list
This eventData [Michael was edited in the list] is NOT an instance!
18:03:56.244 [main] WARN  c.b.hobservable.LoggingHObserver - Michael was edited in the list
Starting...
Updating...
adding...
This eventData [Jon was added to the list] is NOT an instance!
18:03:56.245 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was added to the list
Updating...
adding...
This eventData [Jon was added to the list] is NOT an instance!
18:03:56.245 [main] WARN  c.b.hobservable.LoggingHObserver - Jon was added to the list
Updating...
adding...
This eventData [Jonathan was edited in the list] is NOT an instance!
18:03:56.245 [main] WARN  c.b.hobservable.LoggingHObserver - Jonathan was edited in the list
Updating...
adding...
editing...
This eventData [Jonathan was edited in the list] is NOT an instance!
18:03:56.246 [main] WARN  c.b.hobservable.LoggingHObserver - Jonathan was edited in the list
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.137 sec

Results :

Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

-------------------------------------------------------

`LoggingHObserver` objects display information in the console related to the event it receives. When the observables use their built-in notifyObservers() method, these observers will be updated with the information and display it in the console. 

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


