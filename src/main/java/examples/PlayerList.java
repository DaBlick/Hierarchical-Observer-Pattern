/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import com.barfly.hobservable.BaseHObservable;
import java.util.ArrayList;

/**
 *
 * @author jonathanodgis
 */
public class PlayerList 
{
    private final ArrayList <Player>players;
    
    private final BaseHObservable mainObservable;
    private final BaseHObservable addObservable;
    private final BaseHObservable removeObservable;
    private final BaseHObservable editObservable;
    
    public PlayerList()
    {
        players = new ArrayList<>();
        mainObservable = new BaseHObservable("main observable", null);      
        addObservable = new BaseHObservable("add observable", mainObservable);
        removeObservable = new BaseHObservable("remove observable", mainObservable);
        editObservable = new BaseHObservable("edit observable", mainObservable);
    }
    
    public void addPlayer(Player player)
    {
        players.add(player);
        addObservable.notifyObservers(player + " was added to the list");
    }
    
    public void removePlayer(Player player)
    {
        players.remove(player);
        removeObservable.notifyObservers(player + " was removed from the list");
    }
        
    public void editPlayer(Player player, String name, int age, String gender, String email)
    {
        int index = players.indexOf(player);
        players.get(index).setName(name);
        players.get(index).setAge(age);
        players.get(index).setGender(gender);
        players.get(index).setEmail(email);
        editObservable.notifyObservers(player + " was edited in the list");
    } 
    
    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }
    
    public int size()
    {
        return players.size();
    }
    
    public BaseHObservable getMainObservable()
    {
        return this.mainObservable;
    }
    
    public void setMainObservable(String observerID, BaseHObservable parentObservable)
    {
        this.mainObservable.setObservableID(observerID);
        this.mainObservable.setParentObservable(parentObservable); 
    }
    
    public BaseHObservable getAddObservable()
    {
        return this.addObservable;
    }

    public void setAddObservable(String observerID, BaseHObservable parentObservable)
    {
        this.addObservable.setObservableID(observerID);
        this.addObservable.setParentObservable(parentObservable); 
    }    
    
    public BaseHObservable getRemoveObservable()
    {
        return this.removeObservable;
    }    

    public void setRemoveObservable(String observerID, BaseHObservable parentObservable)
    {
        this.removeObservable.setObservableID(observerID);
        this.removeObservable.setParentObservable(parentObservable); 
    }    
    
    public BaseHObservable getEditObservable()
    {
        return this.editObservable;
    }        
    
    public void setEditObservable(String observerID, BaseHObservable parentObservable)
    {
        this.mainObservable.setObservableID(observerID);
        this.mainObservable.setParentObservable(parentObservable); 
    }    

}
