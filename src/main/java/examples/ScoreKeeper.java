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
    PlayerList playerList = new PlayerList();
    String observerID;
    
    public ScoreKeeper(String observerID)
    {    
        super(observerID);
    }
    
    public void respondToAdd()
    {
        System.out.println("add happened");
    }

    public void respondToRemove()
    {
        System.out.println("remove happened");
    }
    
    public void respondToEdit()
    {
        System.out.println("edit happened");
    }    

    @Override
    public void update(Observable observable, Object eventData) 
    {
        System.out.println("Updating...");
        if (observable.toString().contains("add observable"));          
        {
            respondToAdd();
        }
        if (observable.toString().contains("remove observable"))
        {
            respondToRemove();
        }
        if (observable.toString().contains("edit observable"))
        {
            respondToEdit();           
        }
        super.update(observable, eventData);
    }
    
}
