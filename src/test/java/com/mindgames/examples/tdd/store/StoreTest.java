package com.mindgames.examples.tdd.store;

import org.testng.annotations.Test;

import java.util.List;
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
        assertTrue("Wrong title", titles.contains("Refactoring [][]"));
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
        assertTrue("Wrong title", titles.contains("Refactoring [][]"));
    }

    @Test
    public void shouldFindBookByAuthor() {
        Store store = new Store();
        Book book = new Book();
        book.setAuthor("Kent Beck");
        store.addBook(book);
        List<Book> foundedBooks = store.findByAuthor("Kent Beck");
        assertTrue("Book list is null", foundedBooks != null);
        assertTrue("Book list is empty", !foundedBooks.isEmpty());
        assertEquals("Smth wrong with find by author", book, foundedBooks.get(0));
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

    //Part 2

    @Test
    public void shouldReturnDifferentTitlesForDifferentPublishers() {
        Store store = new Store();
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setPublisher("Piter Press");
        Book book1 = new Book();
        book1.setTitle("Refactoring");
        book1.setPublisher("A-Press");
        store.addBook(book);
        store.addBook(book1);
        Set<String> titles = store.getTitles();
        assertEquals("Wrong amounts of titles", 2, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [Piter Press][]"));
        assertTrue("Wrong title", titles.contains("Refactoring [A-Press][]"));
    }

    @Test
    public void shouldReturnDifferentTitlesForDifferentYears() {
        Store store = new Store();
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        Book book1 = new Book();
        book1.setTitle("Refactoring");
        book1.setPublisher("A-Press");
        book1.setPublishYear(2010);
        store.addBook(book);
        store.addBook(book1);
        Set<String> titles = store.getTitles();
        assertEquals("Wrong amounts of titles", 2, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [A-Press][2012]"));
        assertTrue("Wrong title", titles.contains("Refactoring [A-Press][2010]"));
    }

    @Test
    public void shouldCanAddSameBookToStore() {
        Store store = new Store();
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        store.addBook(book);
        store.addBook(book);
        assertEquals("Two books were not added", 2, store.getTotalBooksAmount());
    }

    @Test
    public void shouldFindAmountOfBooks() {
        Store store = new Store();
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setAuthor("Kent Beck");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        store.addBook(book);
        store.addBook(book);
        List<Book> byAuthor = store.findByAuthor("Kent Beck");
        assertEquals("Two books were not added", 2, byAuthor.size());
    }

}
