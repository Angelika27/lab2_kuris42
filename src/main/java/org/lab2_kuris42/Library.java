package org.lab2_kuris42;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class Library implements IManageable {

    static List<Item> items;

    List<Patron> patrons;
    public Library() {
        // Вот здесь нужно инициализировать список
        items = new ArrayList<>();
        patrons = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }
    public List<Patron> getPatrons() {
        return patrons;
    }
    @Override
    public void add(Item item) {
        items.add(item);
    }

    @Override
    public void remove(Item item) {
        items.remove(item);
    }

    @Override
    public boolean listAvailable() {
        var filtered = items.stream().filter(q -> !q.getBorrowed()).toList();
        listItems(filtered);
        return false;
    }

    @Override
    public void listBorrowed() {
        var filtered = items.stream().filter(q -> q.getBorrowed()).toList();
        listItems(filtered);
    }

    void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public Map<String, List<Item>> getAvailableItems() {
        Map<String, List<Item>> itemsByType = new HashMap<>();

        for (Item item : items) {
            if (!item.getBorrowed()) {
                String itemType = item.getClass().getSimpleName();
                itemsByType.computeIfAbsent(itemType, k -> new ArrayList<>()).add(item);
            }
        }

        return itemsByType;
    }
    private void listItems(List<Item> items)
    {
        for (var i: items) {
            if (i instanceof Book) {
                System.out.println(String.format("Type: Book, Title: %s, Author: %s, ISBN: %s, Year: %d",
                        i.getTitle(), ((Book)i).getAuthor(), ((Book)i).getISBN(), ((Book)i).getYear()));
            } else {
                System.out.println(String.format("Type: DVD, Title: %s, Duration: %d minutes",
                        i.getTitle(), ((DVD)i).getDuration()));
            }
        }
    }
}
