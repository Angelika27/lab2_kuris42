package org.lab2_kuris42;

public class Book extends Item {

    private String author;
    private String isbn;
    private int year;

    public String getAuthor()
    {
        return author;
    }

    public String getISBN()
    {
        return isbn;
    }

    public int getYear()
    {
        return year;
    }

    public Book(String Title, String Author, String ISBN, String Year)
    {
        super(Title);
        author = Author;
        isbn = ISBN;
        year = Integer.parseInt(Year);
    }

}
