/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

/**
 *
 * @author jonathanodgis
 */
public class PlayerListExample 
{
    public static void main(String[] args)
    {
        System.out.println("Starting...");
        
        ScoreKeeper scoreKeeper = new ScoreKeeper("Score Keeper");
        ScoreKeeper mainScoreKeeper = new ScoreKeeper("Main Score Keeper");
        Player player  = new Player("Jon", 21, "M", "jonathan@gmail.com");
        
        scoreKeeper.playerList.mainObservable.addObserver(mainScoreKeeper);
        scoreKeeper.playerList.addObservable.addObserver(scoreKeeper);
        scoreKeeper.playerList.removeObservable.addObserver(scoreKeeper);
        scoreKeeper.playerList.editObservable.addObserver(scoreKeeper);
        
        scoreKeeper.playerList.addPlayer(player);
        scoreKeeper.playerList.editPlayer(player, "Jonathan", player.getAge(), player.getGender(), "jonathan@gmail.com");
        scoreKeeper.playerList.removePlayer(player);

    }
}
