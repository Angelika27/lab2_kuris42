package org.lab2_kuris42;

import java.util.List;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testAddItemToLibrary() {
        Library library = new Library();
        Item book = new Book("Test Book", "Test Author", "1234567890", "2023");

        library.add(book);

        List<Item> items = library.getItems();
        assertTrue(items.contains(book));
    }


    @Test
    void testRemoveItemFromLibrary() {
        Library library = new Library();
        Item book = new Book("Test Book", "Test Author", "1234567890", "2023");
        library.add(book);

        library.remove(book);

        List<Item> items = library.getItems();
        assertFalse(items.contains(book));
    }
    @Test
    void testListAvailableItems() {
        Library library = new Library();
        Item book = new Book("Test Book", "Test Author", "1234567890", "2023");
        library.add(book);

        library.listAvailable();
    }

    @Test
    void listBorrowed() {
        Library library = new Library();
        Item borrowedBook = new Book("Borrowed Book", "Borrower", "9876543210", "2020");
        borrowedBook.borrowItem();
        library.add(borrowedBook);

        library.listBorrowed();
    }

    @Test
    void addPatron() {
        Library library = new Library();
        Patron patron = new Patron("John Doe");
        library.addPatron(patron);

        assertTrue(library.getPatrons().contains(patron));
    }

    @Test
    void getAvailableItems() {
        Library library = new Library();
        Item availableBook = new Book("Available Book", "Test Author", "1234567890", "2023");
        library.add(availableBook);

        assertTrue(library.getAvailableItems().containsKey("Book"));
        assertTrue(library.getAvailableItems().get("Book").contains(availableBook));
    }
}