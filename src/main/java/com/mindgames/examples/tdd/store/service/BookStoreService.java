package com.mindgames.examples.tdd.store.service;

import com.mindgames.examples.tdd.store.entities.Book;
import com.mindgames.examples.tdd.store.entities.Item;
import com.mindgames.examples.tdd.store.entities.Store;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */
public class BookStoreService {

    private Store store;
    private Set<String> authors;
    private Set<String> titles;

    public BookStoreService(Store store) {
        this.store = store;
        authors = new HashSet<String>();
        titles = new HashSet<String>();
    }

    public void addBook(Book book) {
        store.addItem(book);
        authors.add(book.getAuthor());
        String publisher = book.getPublisher() != null ? book.getPublisher() : "";
        String publishYear = book.getPublishYear() > 0 ? "" + book.getPublishYear() : "";
        titles.add(String.format("%s [%s][%s]", book.getTitle(), publisher, publishYear));
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public Set<String> getTitles() {
        return titles;
    }

    public List<Book> findByAuthor(String s) {
        List<Book> result = new ArrayList<Book>();
        for (Book book : getBooks()) {
            if (book.getAuthor().equals(s)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book findByTitle(String s) {
        for (Book book : getBooks()) {
            if (book.getTitle().equals(s)) {
                return book;
            }
        }
        return null;
    }


    public List<Book> getBooks() {
        List<Item> items = store.getItems();
        List<Book> books = new ArrayList<Book>();
        for (Item item : items) {
            if (item.getItemType().equals(Item.BOOK_TYPE)) {
                books.add((Book) item);
            }
        }
        return books;
    }
}
