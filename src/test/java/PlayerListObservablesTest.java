import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.HObserver;
import com.barfly.hobservable.LoggingHObserver;
import com.barfly.hobservable.loggingexample.Player;
import com.barfly.hobservable.loggingexample.PlayerList;
import com.barfly.hobservable.loggingexample.ScoreKeeper;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */
public class PlayerListObservablesTest 
{
    
    @Test
    public void addingToTheList()
    {
        PlayerList playerList = new PlayerList();
        Player player1 = new Player("Jon", 21, "M", "jon@gmail.com");
        HObserver observer = new LoggingHObserver("Observer");
        BaseHObservable addObservable = playerList.getAddObservable();
        BaseHObservable mainObservable = playerList.getMainObservable();
        
        addObservable.addObserver(observer);
        mainObservable.addObserver(observer);
        playerList.addPlayer(player1);
        
        int testNumberOfObservers = addObservable.countObservers();
        int testSize = playerList.size();
        
        assert(testSize > 0 && testNumberOfObservers > 0);
    }

    @Test
    public void removingFromTheList()
    {
        PlayerList playerList = new PlayerList();
        Player player1 = new Player("Jon", 21, "M", "jon@gmail.com");
        Player player2 = new Player("Mike", 21, "M", "mike@gmail.com");
        HObserver observer1 = new LoggingHObserver("RemoveObserver");
        HObserver observer2 = new LoggingHObserver("AddingObserver");
        HObserver observer3 = new LoggingHObserver("MainObserver");
        BaseHObservable addObservable = playerList.getAddObservable();
        BaseHObservable mainObservable = playerList.getMainObservable();
        BaseHObservable removeObservable = playerList.getRemoveObservable();
        
        removeObservable.addObserver(observer1);
        addObservable.addObserver(observer2);
        mainObservable.addObserver(observer3);
        
        playerList.addPlayer(player1);
        playerList.addPlayer(player2);
        playerList.removePlayer(player1);
        
        int testNumberOfObservers = removeObservable.countAllObservers();
               
        assert(playerList.size() > 0 && testNumberOfObservers > 0);
    }    

    @Test
    public void editingInTheList()
    {
        PlayerList playerList = new PlayerList();
        Player player1 = new Player("Jon", 21, "M", "jon@gmail.com");
        Player player2 = new Player("Mike", 21, "M", "mike@gmail.com");
        HObserver observer1 = new LoggingHObserver("EditObserver");
        HObserver observer2 = new LoggingHObserver("MainObserver");
        BaseHObservable mainObservable = playerList.getMainObservable();
        BaseHObservable removeObservable = playerList.getRemoveObservable();
        
        removeObservable.addObserver(observer1);
        mainObservable.addObserver(observer2);
        playerList.addPlayer(player1);
        playerList.addPlayer(player2);
        playerList.editPlayer(player1, "Jonathan", player1.getAge(), player1.getGender(), "jonathan@gmail.com");
        playerList.editPlayer(player2, "Michael", player2.getAge(), player2.getGender(), "michael@gmail.com");

        int testNumberOfObservers = removeObservable.countAllObservers();
        
        assert("Jonathan".equals(player1.getName()) && "Michael".equals(player2.getName()) && testNumberOfObservers == 2);
    }    
    
    /**
     * This is the code from the README.MD 
     * TODO Put this into the README.MD
     */
    @Test
    public void scoreKeeperObserverOfPlayerListExample()
    {   
        ScoreKeeper scoreKeeper = new ScoreKeeper("Score Keeper");
        PlayerList playerList = scoreKeeper.getPlayerList();
        Player player = new Player("Jon", 21, "M", "jonathan@gmail.com");   
        BaseHObservable mainObservable = playerList.getMainObservable();
        BaseHObservable addObservable = playerList.getAddObservable();
        BaseHObservable editObservable = playerList.getEditObservable();
        
        System.out.println("Starting...");
        
        mainObservable.addObserver(scoreKeeper);
        addObservable.addObserver(scoreKeeper);
        editObservable.addObserver(scoreKeeper);
        playerList.addPlayer(player);
        playerList.editPlayer(player, "Jonathan", player.getAge(), player.getGender(), "jonathan@gmail.com");
        
        String testName = "Jonathan";
        String testEmail = "jonathan@gmail.com";
        int testIndex = playerList.getPlayers().indexOf(player);
        Player testPlayer = playerList.getPlayers().get(testIndex);
        
        assert(playerList.size() == 1 && testPlayer.getName().equals(testName) && testPlayer.getEmail().equals(testEmail));   
    }
    
}
