package org.lab2_kuris42;
import java.util.UUID;

public abstract class Item {
    private UUID id;
    private String title;
    private boolean isBorrowed = false;

    public Item(String Title)
    {
        id = UUID.randomUUID();
        title = Title;
    }

    public UUID getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public boolean getBorrowed()
    {
        return isBorrowed;
    }

    public void setBorrowed(boolean value)
    {
        isBorrowed = value;
    }

    protected boolean borrowItem()
    {
        if (getBorrowed()) return false;
        setBorrowed(true);
        return true;
    }

    protected void returnItem()
    {
        setBorrowed(false);
    }

}
