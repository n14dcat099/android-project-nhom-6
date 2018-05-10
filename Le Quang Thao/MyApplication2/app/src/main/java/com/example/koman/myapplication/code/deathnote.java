package com.example.koman.myapplication.code;
import java.io.Serializable;
/**
 * Created by koman on 4/20/2018.
 */

public class deathnote implements Serializable
{
    private int id;
    private String name;
    private String reason;
    public deathnote(){

    }

    public deathnote(String name,String reason)
    {
        this.name = name;
        this.reason = reason;
    }

    public deathnote( int id , String name , String reason )
    {
        this.id = id;
        this.name = name;
        this.reason = reason;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
