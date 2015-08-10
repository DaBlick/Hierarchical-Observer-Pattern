/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import com.barfly.hobservable.BaseHObservable;
import com.barfly.hobservable.HObservable;
import java.util.ArrayList;

/**
 *
 * @author jonathanodgis
 */
public class PlayerList 
{
    ArrayList <Player>players = new ArrayList<>(); 
    
    public HObservable mainObservable = new BaseHObservable("main observable", null);
    public HObservable addObservable = new BaseHObservable("add observable", (BaseHObservable) mainObservable);
    public HObservable removeObservable = new BaseHObservable("remove observable", (BaseHObservable) mainObservable);
    public HObservable editObservable = new BaseHObservable("edit observable", (BaseHObservable) mainObservable);
    
    public PlayerList()
    {
          
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
        players.get(players.indexOf(player)).setName(name);
        players.get(players.indexOf(player)).setAge(age);
        players.get(players.indexOf(player)).setGender(gender);
        players.get(players.indexOf(player)).setEmail(email);
        editObservable.notifyObservers(player + " was edited in the list");
    } 
    
    public int size()
    {
        return players.size();
    }
}
