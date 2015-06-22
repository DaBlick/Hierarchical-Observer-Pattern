/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable; 

import java.util.Observable;

/**
 *
 * @author dblickstein
 */
public class ObserverOfTournament implements HObserver 
{
    private String observerID;
    
    public ObserverOfTournament(String observerID) 
    {
        BarflyObservableEnum.TOURNAMENT.registerObserver(this);
        this.observerID = observerID;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Hey, ObserverOfTournament got an event: " + arg);
    }                                                                                    //If this is the proxy, what is this call doing that the main method isnt doing?
    
    public BarflyObservableEnum getBarflyEnum() 
    {
        return BarflyObservableEnum.TOURNAMENT.getBarflyEnum();
    }
    
    public HObservable getObservable()
    {
        return BarflyObservableEnum.TOURNAMENT.getObservableObject(); 
    }
    
    @Override
    public String getObserverID()
    {
        return observerID;
    }
}
