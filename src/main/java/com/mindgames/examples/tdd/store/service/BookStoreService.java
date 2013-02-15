package com.mindgames.examples.tdd.store.service;

import com.mindgames.examples.tdd.store.entities.Book;
import com.mindgames.examples.tdd.store.entities.IStore;
import com.mindgames.examples.tdd.store.entities.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 20:06
 */
public class BookStoreService {

    private IStore IStore;
    private Set<String> authors;
    private Set<String> titles;

    public BookStoreService(IStore IStore) {
        this.IStore = IStore;
        authors = new HashSet<String>();
        titles = new HashSet<String>();
    }

    public void addBook(Book book) {
        IStore.addItem(book);
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

    public List<Book> findByTitle(String s) {
        List<Book> result = new ArrayList<Book>();
        for (Book book : getBooks()) {
            if (book.getTitle().equals(s)) {
                result.add(book);
            }
        }
        return result;
    }


    public List<Book> getBooks() {
        List<Item> items = IStore.getItems();
        List<Book> books = new ArrayList<Book>();
        for (Item item : items) {
            if (item.getItemType().equals(Item.BOOK_TYPE)) {
                books.add((Book) item);
            }
        }
        return books;
    }

    public void sellBook(Book book) {
        String title = book.getTitle();
        if (findByTitle(title).size() == 1) {
            titles.remove(title);
        }
        String author = book.getAuthor();
        if (findByTitle(author).size() == 1) {
            titles.remove(title);
        }
        IStore.sellItem(book);
    }

    public int find(Book book) {
        List<Item> items = IStore.getItems();
        int result = 0;
        for (Item item : items) {
            if (book.equals(item)) {
                result++;
            }
        }
        return result;
    }
}
