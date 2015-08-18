import com.barfly.hobservable.collections.ListObservable;
import com.barfly.hobservable.collectionsexample.Contact;
import com.barfly.hobservable.collectionsexample.ContactsList;
import com.barfly.hobservable.collectionsexample.PhoneApp;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */
public class ContactListObservablesTest 
{
    @Test
    public void phoneAppObservesContactListExample()
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
        
        int testAppAUpdateCount = appA.getUpdateCount();
        int testAppBUpdateCount = appB.getUpdateCount();
        int testAppCUpdateCount = appC.getUpdateCount();  
        boolean testConditionI = testAppAUpdateCount > testAppBUpdateCount && testAppBUpdateCount > testAppCUpdateCount;
        boolean testConditionII = mainObservable.size() == contactsList.size();  
        boolean testConditionIII = addObservable.size() == contactsList.size();
        boolean testConditionIV = removeObservable.size() == contactsList.size();
        
        assert(testConditionI && testConditionII && testConditionIII && testConditionIV);
        
    }
}
