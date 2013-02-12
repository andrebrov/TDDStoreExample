package com.mindgames.examples.tdd.store;

import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 18:35
 * To change this template use File | Settings | File Templates.
 */
public class StoreTest {

    //Part 1

    @Test
    public void testStoreIsEmptyByDefault() {
        Store store = new Store();
        assertEquals("There are books in just created store", 0, store.getTotalBooksAmount());
    }

    @Test
    public void testAddBook() {
        Store store = new Store();
        Book book = new Book();
        store.addBook(book);
        assertEquals("Book was not added", 1, store.getTotalBooksAmount());
    }

    @Test
    public void shouldGetAuthorsList() {
        Store store = new Store();
        Book book = new Book();
        book.setAuthor("Kent Beck");
        store.addBook(book);
        Set<String> authors = store.getAuthors();
        assertEquals("Wrong amounts of authors", 1, authors.size());
        assertTrue("Wrong author", authors.contains("Kent Beck"));
    }

    @Test
    public void shouldReturnUniqueAuthor() {
        Store store = new Store();
        Book book = new Book();
        book.setAuthor("Kent Beck");
        Book book1 = new Book();
        book1.setAuthor("Kent Beck");
        store.addBook(book);
        store.addBook(book1);
        Set<String> authors = store.getAuthors();
        assertEquals("Wrong amounts of authors", 1, authors.size());
        assertTrue("Wrong author", authors.contains("Kent Beck"));
    }

    @Test
    public void shouldGetTitlesList() {
        Store store = new Store();
        Book book = new Book();
        book.setTitle("Refactoring");
        store.addBook(book);
        Set<String> titles = store.getTitles();
        assertEquals("Wrong amounts of titles", 1, titles.size());
        assertTrue("Wrong author", titles.contains("Refactoring"));
    }

    @Test
    public void shouldReturnUniqueTitle() {
        Store store = new Store();
        Book book = new Book();
        book.setTitle("Refactoring");
        Book book1 = new Book();
        book1.setTitle("Refactoring");
        store.addBook(book);
        store.addBook(book1);
        Set<String> titles = store.getTitles();
        assertEquals("Wrong amounts of titles", 1, titles.size());
        assertTrue("Wrong author", titles.contains("Refactoring"));
    }

    @Test
    public void shouldFindBookByAuthor() {
        Store store = new Store();
        Book book = new Book();
        book.setAuthor("Kent Beck");
        store.addBook(book);
        Book foundedBook = store.findByAuthor("Kent Beck");
        assertTrue("Book object is null", foundedBook != null);
        assertEquals("Smth wrong with find by author", book, foundedBook);
    }

    @Test
    public void shouldFindBookByTitle() {
        Store store = new Store();
        Book book = new Book();
        book.setTitle("Refactoring");
        store.addBook(book);
        Book foundedBook = store.findByTitle("Refactoring");
        assertTrue("Book object is null", foundedBook != null);
        assertEquals("Smth wrong with find by author", book, foundedBook);
    }

}
