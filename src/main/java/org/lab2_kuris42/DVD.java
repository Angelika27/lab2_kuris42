package org.lab2_kuris42;

public class DVD extends Item {
    private int duration;

    public DVD(String Title, String Duration)
    {
        super(Title);
        duration = Integer.parseInt(Duration);
    }

    public int getDuration()
    {
        return duration;
    }

    
}
