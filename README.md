# Hierarchical Observer

HObserver is an extension of the `java.util.Observable` class and it's supporting framework that allows Observables to 
be part of an hierarchy such that events fired to any observable are also fired to its ancestors.

#Observer Pattern

`Observable` is an implementation of the GOF (Gang of Four) "Observer" pattern. An "Observable" provides an API with which
"Observers" can register themselves for notifications whenever the Observable is changed.   Since Observers register with 
Observable, the Observer is unaware of and thus decouple from its observers.

For example, in a typical AJAX web application changing the name of some domain entity may require re-rendering in another 
part of the DOM where the name is displayed.   Let's say we have a web app that maintains a contact list.   Whenever the 
name of  a contact is changed, we might need to update any place where names are used.   Such places would register
themselves as observers of the the contact list.

#What is an Hierarchical Observer?

Suppose we wanted finer grained control over the update notification.   For example, if we have a component that only displays the name, we don't need (nor "want") the expense of re-rendering if the thing that changed was the contact's 
phone number.

HObserver expands on the design pattern by introducing the notion of Observables that are part of a tree of Observables. The tree reflects the logical structure of the domain objects.   Each Observable in the tree C might have a parent Observable P. Anytime a notification is sent to observers of C, notifications are also sent to observers of all ancestors of C.

It might be best to explain this in terms of the contact list example.  

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

The Logging Observer creates Log File entries in response to changes. This is accomplished via a builtin Observer that writes a Log File entry for each change to the Observable via the SLF4J API.

The LoggingObserver instantiates a Logger using the Logger Factory and creates a log which contains information about the passed events. The `LoggingObserver` can write the data to different log levels such as `DEBUG`, `TRACE`, `WARN`, `ERROR`, and `INFO`. 

Consider a tournament application that contains a list of players and a score keeper that observes the changes in the list. 

The `PlayerList` class contains functions that add , remove, and edit `Player`s. 

For each function, there is a corresponding observable. 

`AddObservable`, `removeObservable`, and `editObservable` are children of  `mainObservable`.  If a player is added, `addObservable` will fire an event to its parent's and its observers. 

- `Player List`
	- `mainObservable`
		- `ScoreKeeper`
	- `AddObservable`
		- `ScoreKeeper`
	- `RemoveObservable`
		- `ScoreKeeper`
	- `EditingObservable`
		- `ScoreKeeper`

First, we create the `ScoreKeeper`which will be an observer. 

        ScoreKeeper scoreKeeper = new ScoreKeeper("Score Keeper");

Here's a block from the `ScoreKeeper` class. 

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

`ScoreKeeper` contains a reference to `PlayerList` and an observerID. `ScoreKeeper` logs events using the overriding Update() method in response to changes in the list. 

Next, we initialize `PlayerList` and populate it with `Player`s

        PlayerList playerList = scoreKeeper.getPlayerList();
        Player player = new Player("Jon", 21, "M", "jonathan@gmail.com");   

`Player`contains the player's name, age, gender, and email.

Next, we create references to the observables.

        BaseHObservable mainObservable = playerList.getMainObservable();
        BaseHObservable addObservable = playerList.getAddObservable();
        BaseHObservable editObservable = playerList.getEditObservable();
        
We add `ScoreKeeper` to the observables.

        System.out.println("Starting...");
        
        mainObservable.addObserver(scoreKeeper);
        addObservable.addObserver(scoreKeeper);
        editObservable.addObserver(scoreKeeper);

`ScoreKeeper` is an observer of `mainObservable`, `addObservable`, and `editObservable`. When a player is added, removed, edited, observables will fire the events.

Next, let's make some changes to `PlayerList`. 

        playerList.addPlayer(player);
        playerList.editPlayer(player, "Jonathan", player.getAge(), player.getGender(), "jonathan@gmail.com");
        
This code will add a  `Player` and edit a `Player`s name and email fields. As these two calls occur, the observers are updated. Let's take a look at the addPlayer() method in PlayerList.

    public void addPlayer(Player player)
    {
        players.add(player);
        addObservable.notifyObservers(player + " was added to the list");
    }
    
The player is added to the list and the addObservable notifies the observers of the event. `ScoreKeeper` is updated via the notifyObservers() method.

Finally, we run an assertion to test the size of the list and the information of the edited player.

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

Using the `LoggingHObserver` allows for the observers to keep track of information of different observables and display information at the console level of the application. 

#What is the Collection Observable?

Collection Observable allows for observers to receive events that provide information about data that was changed in the collection.

The Collection Observable is a sub-class of the existing `BaseHObservable` class. `AbstractCollectionObservable` is an abstract class that implements the `Collection` Interface and has sub classes which use other types of collections.

- `HObservable`
	- `BaseHObservable`
      - `AbstractCollectionObservable`
	      - `ListObservable`

Consider a mobile phone which contains different apps and data such as a list of contacts. The contacts can be used in a phone app to make calls or in other types of apps. 

- `ContactsList`
	- `mainObservable`
		- `AppA`
	- `AddObservable`
		- `AppB`
	- `RemoveObservable`
		- `AppC`

The `ContactList` contains a list of `Contact`s and can have them added and removed. When these operations occur, the`ContactsList`'s `mainObservable`, `addObservable`, and `removeObservable` notify their observers of these changes. 

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

The observers of these ListObservables are `appA`, `appB`, and `appC` which are all of the type `PhoneApp` and implement the `HObserver` interface. 

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

Similar to the observer pattern, when `ListObservable` calls notifyObservers(), the overriding update() method in `PhoneApp` will be called and the information will be displayed. `PhoneApp` allows the user to get the name of the app and number of updates that the app received.

The example program involves 3 apps (observers) being notified of changes in the phone's `ContactList`. 

First, we initialize the observers or in this case, the `PhoneApp`s.

        PhoneApp appA = new PhoneApp("System App");  
        PhoneApp appB = new PhoneApp("Email App");
        PhoneApp appC = new PhoneApp("Garbage Bin App");

`PhoneApp`'s parameter is a `String` that is the app's name. `AppA` is an example of a phone app that you open when calling a contact, `appB` is an email app example that observes the contacts and can be notified of any emails tied to that phone number. `AppC` is a utility app example that is informed of contacts that are removed from the list.  

Next, we initialize `ContactsList` and reference objects to the observables in `ContactsList`.

        ContactsList contactsList = new ContactsList("User");
        ListObservable mainObservable = contactsList.getMainObservable();
        ListObservable addObservable = contactsList.getAddObservable();
        ListObservable removeObservable = contactsList.getRemoveObservable(); 

Next we initiate `Contact`s.  

        Contact contact1 = new Contact("Jon", "111-111-1111");
        Contact contact2 = new Contact("Mike", "222-222-2222");
        Contact contact3 = new Contact("Alex", "333-333-3333");
        Contact contact4 = new Contact("Tom", "444-444-4444");     

The parameters of `Contact` are the name and the phone number. Both parameters are type `String`.         

Next, we add the observers `appA`, `appB`, and `appC` to the different observables.

        mainObservable.addObserver(appA);
        addObservable.addObserver(appB);
        removeObservable.addObserver(appC);

`AppA` is an observer of `mainObservable`, `appB`  is an observer of `addObservable`, and `appC`  is an observer of `removeObservable`.

We add `Contact`s to the `ContactList`.

        contactsList.addContact(contact1);
        contactsList.addContact(contact2);        
        contactsList.addContact(contact3);        
        contactsList.addContact(contact4); 

To test the remove capabilities, let's remove `contact3`.

        contactsList.removeContact(contact3);
        
Now, the assert code is tested. This assertion tests both the number of updates and size of the lists. 

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


The changes are fired to the observing apps and the updates are displayed. 