package org.lab2_kuris42;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatronTest {

    @Test
    void testGetId() {
        Patron patron = new Patron("Pasha");
        assertNotNull(patron.getId());
    }


    @Test
    void testGetName() {
        Patron patron = new Patron("Angelika Kuris");
        assertEquals("Angelika Kuris", patron.getName());
    }

    @Test
    void testGetBorrowedItems() {
        Patron patron = new Patron("Angelika Kuris");
        Item borrowedBook = new Book("Borrowed Book", "Test Author", "1234567890", "2023");
        borrowedBook.borrowItem();
        patron.borrowItem(borrowedBook);

        assertFalse(patron.getBorrowedItems().contains(borrowedBook));
    }

    @Test
    void testBorrowItem() {
        Patron patron = new Patron("Angelika");
        Item book = new Book("Test Book", "Test Author", "1234567890", "2023");

        patron.borrowItem(book);

        assertTrue(patron.getBorrowedItems().contains(book));
    }

    @Test
    void testReturnItem() {
        Patron patron = new Patron("Angelika");
        Item book = new Book("Test Book", "Test Author", "1234567890", "2023");
        patron.borrowItem(book);

        patron.returnItem(book);

        assertFalse(patron.getBorrowedItems().contains(book));
    }
}