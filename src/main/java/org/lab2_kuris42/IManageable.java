package org.lab2_kuris42;

public interface IManageable {
    void add(Item item);
    void remove(Item item);
    boolean listAvailable();
    void listBorrowed();
}
