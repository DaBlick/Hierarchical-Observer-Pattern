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

The Collection Observable is a sub-class of the existing `BaseHObservable` class. `AbstractCollectionObservable` is an abstract class that implements the `Collection` Interface and has sub classes which use other types of collections.

- `HObservable`
	- `BaseHObservable`
      - `AbstractCollectionObservable`
	      - `ListObservable`

Consider a mobile phone which consists of different apps that view a list of contacts. Apps can have access to this list and can be integrated with app's specific functionality. The contacts can be used in a phone app to make calls or in a social media app to get the contact's profile. 

- `ContactsList`
	- `mainObservable`
		- `AppA`
	- `AddObservable`
		- `AppB`
	- `RemoveObservable`
		- `AppC`

The `ContactList` contains a list of `Contact`s which are inserted in an `ArrayList`. The `ContactsList` can have `Contact`s added and removed. When these operations occur, the`ContactsList`'s `mainObservable`, `addObservable`, and `removeObservable` notify their observers of these changes. 

	public class ContactsList
	{
	    private final String userID;
	    private final ArrayList<Contact> contacts;   
	    private final  ListObservable mainObservable;
	    private final  ListObservable addObservable;
	    private final  ListObservable removeObservable;
	    private final  ListObservable editObservable;

	   public ContactsList(String userID, ListObservable parent)
	    {
	        this.userID = userID;
	        contacts = new ArrayList<>();    
	        mainObservable = new ListObservable<>("Main ListObservable", parent, contacts);    
	        addObservable = new ListObservable<>("Add ListObservable", mainObservable, contacts);
	        removeObservable = new ListObservable<>("Remove ListObservable", mainObservable, contacts);
	        editObservable = new ListObservable<>("Edit ListObservable", mainObservable, contacts);
	    }    

	    public ContactsList(String userID)
	    {
	        this.userID = userID;
	        contacts = new ArrayList<>();    
	        mainObservable = new ListObservable<>("Main ListObservable", null, contacts);    
	        addObservable = new ListObservable<>("Add ListObservable", mainObservable, contacts);
	        removeObservable = new ListObservable<>("Remove ListObservable", mainObservable, contacts);
	        editObservable = new ListObservable<>("Edit ListObservable", mainObservable, contacts);
    }

These observables are of type `ListObservable` which is a subclass of the `AbstractCollectionObservable`.  As changes are made to the `ArrayList` named `contacts`, the observables' collections are exactly the same as the `contacts` and will be changed accordingly. 

The observers of these ListObservables are `appA`, `appB`, and `appC` which are all of the type `PhoneApp` and implement the `HObserver` interface. `PhoneApp` also extends the `LoggingHObserver` class so we have the option to integrate logging functionality into our program.  The appA object is an observer of the `mainObservable`, the appB object is an observer of the `addObservable`, and the appC object is an observer of the `removeObservable`.

		public class PhoneApp extends LoggingHObserver implements HObserver 
	{  
	    private int updateCount;
    
	    public PhoneApp(String appID)
	    {
	        super(appID);
	        this.updateCount = 0;
	    }
      
	    @Override
	    public void update(Observable observable, Object event) 
	    {
	        System.out.println(event + " in the " + getAppID() + " " + observable);
        updateCount++;
	    }
    
	    public String getAppID()
	    {
	        return super.toString();
	    }
    
	    public int getUpdateCount()
	    {
	        return this.updateCount;
	    }   
	}

Similar to the observer pattern, when the `ListObservable` calls its notifyObservers() method, the overriding update() method in the `PlayerApp` class will be called and the information will be displayed by the observer. The `PlayerApp` class also allows the user to get the name of the app and also the number of times that the app has been notified by an observable.

The example program involves 3 apps (observers) being notified of changes in the phone's `ContactList`. To begin the implementation we need to first initialize the observers or in this case, the `PhoneApp` objects.

        PhoneApp appA = new PhoneApp("System App");  
        PhoneApp appB = new PhoneApp("Email App");
        PhoneApp appC = new PhoneApp("Garbage Bin App");

The `PhoneApp`'s parameter is a `String` that is the app's name. `AppA` is an example of a phone app that you open when calling a contact, `appB` is an email app example that observes the contacts and can be notified of any emails tied to that phone number. `AppC` is a utility app example that is informed of contacts that are removed from the list.  

Next, we create the `ContactsList` that will store the `Contact`s. We also create reference objects to the different observables in the `ContactsList`.

        ContactsList contactsList = new ContactsList("User");
        ListObservable mainObservable = contactsList.getMainObservable();
        ListObservable addObservable = contactsList.getAddObservable();
        ListObservable removeObservable = contactsList.getRemoveObservable(); 

`ContactsList` contains getters for all of its observables. This keeps the observables private and protected in the class. 

Next we initiate some `Contact`s	 to be added to the contacts.  

        Contact contact1 = new Contact("Jon", "111-111-1111");
        Contact contact2 = new Contact("Mike", "222-222-2222");
        Contact contact3 = new Contact("Alex", "333-333-3333");
        Contact contact4 = new Contact("Tom", "444-444-4444");     

The parameters of `Contact` are the name of the contact and the phone number. Both parameters are of type `String`.         

Next, we add the observers `appA`, `appB`, and `appC` to the different observables so that they can receive events.

        mainObservable.addObserver(appA);
        addObservable.addObserver(appB);
        removeObservable.addObserver(appC);

We now add the previously initialized contacts to the `ContactList`.

        contactsList.addContact(contact1);
        contactsList.addContact(contact2);        
        contactsList.addContact(contact3);        
        contactsList.addContact(contact4); 

To test the remove capabilities, let's take `contact3` off of the list. 

        contactsList.removeContact(contact3);
        
Now that the contacts have been added to the list and we removed one of them, the assert code is tested. In this assertion, which checks the number of updates and the size of the lists. The assertion returns true. 

        int testAppAUpdateCount = appA.getUpdateCount();
        int testAppBUpdateCount = appB.getUpdateCount();
        int testAppCUpdateCount = appC.getUpdateCount();  
        boolean testConditionI = testAppAUpdateCount > testAppBUpdateCount && testAppBUpdateCount > testAppCUpdateCount;
        boolean testConditionII = mainObservable.size() == contactsList.size();  
        boolean testConditionIII = addObservable.size() == contactsList.size();
        boolean testConditionIV = removeObservable.size() == contactsList.size();
        
        assert(testConditionI && testConditionII && testConditionIII && testConditionIV);

The output of this code is presented looks like this. 

-------------------------------------------------------
	 T E S T S
-------------------------------------------------------
	Running ContactListObservablesTest
	Jon | 111-111-1111 was added to the list in the System App Observable: Main ListObservable
	Jon | 111-111-1111 was added to the list in the Email App Observable: Add ListObservable
	Mike | 222-222-2222 was added to the list in the System App Observable: Main ListObservable
	Mike | 222-222-2222 was added to the list in the Email App Observable: Add ListObservable
	Alex | 333-333-3333 was added to the list in the System App Observable: Main ListObservable
	Alex | 333-333-3333 was added to the list in the Email App Observable: Add ListObservable
	Tom | 444-444-4444 was added to the list in the System App Observable: Main ListObservable
	Tom | 444-444-4444 was added to the list in the Email App Observable: Add ListObservable
	Alex | 333-333-3333 was removed from the list in the System App Observable: Main ListObservable
	Alex | 333-333-3333 was removed from the list in the Garbage Bin App Observable: Remove ListObservable
	Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.17 sec

	Results :

	Tests run: 1, Failures: 0, Errors: 0, Skipped: 0


The information about the changes made to the list are fired to the observing apps and the updates are displayed. 