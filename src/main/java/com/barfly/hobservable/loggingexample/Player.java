package com.barfly.hobservable.loggingexample;

/**
 *
 * @author jonathanodgis
 */
public class Player 
{
    private String name;
    private int age;
    private String gender;
    private String email;
    
    public Player(String name, int age, String gender, String email)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getAge()
    {
        return this.age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getGender()
    {
        return this.gender;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
}
