package examples;

import com.barfly.hobservable.HObserver;
import com.barfly.hobservable.LoggingHObserver;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jonathanodgis
 */
public class PlayerListTester 
{
    @Test
    public void addingToTheList()
    {
        PlayerList playerList = new PlayerList();
        Player player1 = new Player("Jonathan", 21, "M", "jonathanodgis@gmail.com");
        HObserver observer = new LoggingHObserver("Observer");
        
        playerList.addObservable.addObserver(observer);
        playerList.addPlayer(player1);
        assert(observer.toString() != null);
    }
    
}
