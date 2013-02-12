package com.mindgames.examples.tdd.store;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
public class Store {

    private List<Book> books;
    private Set<String> authors;
    private Set<String> titles;

    public Store() {
        books = new ArrayList<Book>();
        authors = new HashSet<String>();
        titles = new HashSet<String>();
    }

    public int getTotalBooksAmount() {
        return books.size();
    }

    public void addBook(Book book) {
        books.add(book);
        authors.add(book.getAuthor());
        titles.add(book.getTitle());
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public Set<String> getTitles() {
        return titles;
    }

    public Book findByAuthor(String s) {
        for (Book book : books) {
            if (book.getAuthor().equals(s)) {
                return book;
            }
        }
        return null;
    }

    public Book findByTitle(String s) {
        for (Book book : books) {
            if (book.getTitle().equals(s)) {
                return book;
            }
        }
        return null;
    }
}
