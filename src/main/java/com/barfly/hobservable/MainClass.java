/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barfly.hobservable;

/**
 *
 * @author jonathanodgis
 */
public class MainClass 
{
    public static void main(String[] args)
    {
        TestHObservable testObservable = new TestHObservable("Root", TestObservableEnum.OA);
        TestHObserver observer = new TestHObserver("Observer 1");
        System.out.println("this is the " + testObservable);
        System.out.println("this is the " + observer);
        
        testObservable.registerObserver(observer);                   //Works
        //testObservable.notifyObservers("The test event");         //Doesn't Work (Related to notifyObservers or the overriden update() method in HObserver
    }
}
