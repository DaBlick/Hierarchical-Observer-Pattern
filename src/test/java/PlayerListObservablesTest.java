/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.barfly.hobservable.HObserver;
import com.barfly.hobservable.LoggingHObserver;
import examples.Player;
import examples.PlayerList;
import org.junit.Test;

/**
 *
 * @author jonathanodgis
 */
public class PlayerListObservablesTest {
    
    @Test
    public void addingToTheList()
    {
        PlayerList playerList = new PlayerList();
        Player player1 = new Player("Jon", 21, "M", "jon@gmail.com");
        HObserver observer = new LoggingHObserver("Observer");
        
        playerList.addObservable.addObserver(observer);
        playerList.mainObservable.addObserver(observer);
        
        playerList.addPlayer(player1);
        assert(playerList.size() > 0 && playerList.addObservable.countObservers() > 0);
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
        
        playerList.removeObservable.addObserver(observer1);
        playerList.addObservable.addObserver(observer2);
        playerList.mainObservable.addObserver(observer3);
        
        playerList.addPlayer(player1);
        playerList.addPlayer(player2);
        playerList.removePlayer(player1);
        assert(playerList.size() > 0 && playerList.removeObservable.countObservers() > 0);
    }    

    @Test
    public void editingInTheList()
    {
        PlayerList playerList = new PlayerList();
        Player player1 = new Player("Jon", 21, "M", "jon@gmail.com");
        Player player2 = new Player("Mike", 21, "M", "mike@gmail.com");
        
        HObserver observer1 = new LoggingHObserver("EditObserver");
        HObserver observer2 = new LoggingHObserver("MainObserver");
        
        playerList.removeObservable.addObserver(observer1);
        playerList.mainObservable.addObserver(observer2);
        
        playerList.addPlayer(player1);
        playerList.addPlayer(player2);
        
        playerList.editPlayer(player1, "Jonathan", player1.getAge(), player1.getGender(), "jonathan@gmail.com");
        playerList.editPlayer(player2, "Michael", player2.getAge(), player2.getGender(), "michael@gmail.com");
        assert("Jonathan".equals(player1.getName()) && "Michael".equals(player2.getName()) && playerList.removeObservable.countAllObservers() == 2);
    }      
    
}
