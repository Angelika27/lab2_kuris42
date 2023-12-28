package org.lab2_kuris42;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
public class Patron {
    private UUID id;
    private String name;
    private List<Item> borrowedItems;

    public Patron(String Name)
    {
        id = UUID.randomUUID();
        name = Name;
        this.borrowedItems = new ArrayList<>();


    }

    public UUID getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public List<Item> getBorrowedItems() {
        return borrowedItems;
    }
    public boolean borrowItem(Item item)
    {
        if (item.borrowItem())
        {
            borrowedItems.add(item);
            System.out.println("Предмет '" + item.getTitle() + "' успішно видано користувачу " + this.name + ".");

            return true;
        }
        else
        {
            System.out.println("Предмет вже виданий");
            return false;
        }
    }

    public void returnItem(Item item)
    {
        item.returnItem();
        borrowedItems.remove(item);
    }

}
