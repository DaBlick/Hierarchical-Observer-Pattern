/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import com.barfly.hobservable.LoggingHObserver;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jonathanodgis
 */
public class ScoreKeeper extends LoggingHObserver implements Observer 
{
    private PlayerList playerList;
    
    public ScoreKeeper(String observerID)
    {    
        super(observerID);
        playerList = new PlayerList();
    }
    
    public void respondToAdd()
    {
        System.out.println("adding...");
    }

    public void respondToRemove()
    {
        System.out.println("removing...");
    }
    
    public void respondToEdit()
    {
        System.out.println("editing...");
    }    

    public PlayerList getPlayerList()
    {
        return this.playerList;
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
    
}
