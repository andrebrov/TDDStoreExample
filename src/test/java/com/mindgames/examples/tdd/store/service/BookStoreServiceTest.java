package com.mindgames.examples.tdd.store.service;

import com.mindgames.examples.tdd.store.entities.Book;
import com.mindgames.examples.tdd.store.entities.IStore;
import org.testng.annotations.BeforeMethod;
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
 */
public class BookStoreServiceTest {

    private IStore store;

    @BeforeMethod
    public void setUp() {
        store = new MockStore();
    }

    @Test
    public void testCanAddBook() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        bookStoreService.addBook(book);
        List<Book> books = bookStoreService.getBooks();
        assertEquals("Book was not added", 1, books.size());
        assertEquals("Wrong book was added", book, books.get(0));
    }

    @Test
    public void shouldGetAuthorsList() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setAuthor("Kent Beck");
        bookStoreService.addBook(book);
        Set<String> authors = bookStoreService.getAuthors();
        assertEquals("Wrong amounts of authors", 1, authors.size());
        assertTrue("Wrong author", authors.contains("Kent Beck"));
    }

    @Test
    public void shouldReturnUniqueAuthor() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setAuthor("Kent Beck");
        Book book1 = new Book();
        book1.setAuthor("Kent Beck");
        bookStoreService.addBook(book);
        bookStoreService.addBook(book1);
        Set<String> authors = bookStoreService.getAuthors();
        assertEquals("Wrong amounts of authors", 1, authors.size());
        assertTrue("Wrong author", authors.contains("Kent Beck"));
    }

    @Test
    public void shouldGetTitlesList() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        bookStoreService.addBook(book);
        Set<String> titles = bookStoreService.getTitles();
        assertEquals("Wrong amounts of titles", 1, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [][]"));
    }

    @Test
    public void shouldReturnUniqueTitle() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        Book book1 = new Book();
        book1.setTitle("Refactoring");
        bookStoreService.addBook(book);
        bookStoreService.addBook(book1);
        Set<String> titles = bookStoreService.getTitles();
        assertEquals("Wrong amounts of titles", 1, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [][]"));
    }

    @Test
    public void shouldFindBookByAuthor() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setAuthor("Kent Beck");
        bookStoreService.addBook(book);
        List<Book> foundedBooks = bookStoreService.findByAuthor("Kent Beck");
        assertTrue("Book list is null", foundedBooks != null);
        assertTrue("Book list is empty", !foundedBooks.isEmpty());
        assertEquals("Smth wrong with find by author", book, foundedBooks.get(0));
    }

    @Test
    public void shouldFindBookByTitle() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        bookStoreService.addBook(book);
        List<Book> foundedBooks = bookStoreService.findByTitle("Refactoring");
        assertTrue("Book list is null", foundedBooks != null);
        assertTrue("Book list is empty", !foundedBooks.isEmpty());
        assertEquals("Smth wrong with find by author", book, foundedBooks.get(0));
    }

    @Test
    public void shouldReturnDifferentTitlesForDifferentPublishers() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setPublisher("Piter Press");
        Book book1 = new Book();
        book1.setTitle("Refactoring");
        book1.setPublisher("A-Press");
        bookStoreService.addBook(book);
        bookStoreService.addBook(book1);
        Set<String> titles = bookStoreService.getTitles();
        assertEquals("Wrong amounts of titles", 2, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [Piter Press][]"));
        assertTrue("Wrong title", titles.contains("Refactoring [A-Press][]"));
    }

    @Test
    public void shouldReturnDifferentTitlesForDifferentYears() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        Book book1 = new Book();
        book1.setTitle("Refactoring");
        book1.setPublisher("A-Press");
        book1.setPublishYear(2010);
        bookStoreService.addBook(book);
        bookStoreService.addBook(book1);
        Set<String> titles = bookStoreService.getTitles();
        assertEquals("Wrong amounts of titles", 2, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [A-Press][2012]"));
        assertTrue("Wrong title", titles.contains("Refactoring [A-Press][2010]"));
    }

    @Test
    public void shouldCanAddSameBookToStore() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        bookStoreService.addBook(book);
        bookStoreService.addBook(book);
        assertEquals("Two books were not added", 2, bookStoreService.getBooks().size());
    }

    @Test
    public void shouldFindAmountOfBooksByAuthor() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setAuthor("Kent Beck");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        bookStoreService.addBook(book);
        bookStoreService.addBook(book);
        List<Book> byAuthor = bookStoreService.findByAuthor("Kent Beck");
        assertEquals("Two books were not added", 2, byAuthor.size());
    }

    @Test
    public void shouldFindAmountOfBooksByTitle() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setAuthor("Kent Beck");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        bookStoreService.addBook(book);
        bookStoreService.addBook(book);
        List<Book> byTitle = bookStoreService.findByTitle("Refactoring");
        assertEquals("Two books were not added", 2, byTitle.size());
    }

    @Test
    public void shouldFindSameBooksCount() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setAuthor("Kent Beck");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        bookStoreService.addBook(book);
        bookStoreService.addBook(book);
        int sameBooksCount = bookStoreService.find(book);
        assertEquals("Two books were not added", 2, sameBooksCount);
    }

    @Test
    public void testCanAfterBuyBookItWillDisappear() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setAuthor("Kent Beck");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        book.setPrice(1000);
        bookStoreService.addBook(book);
        bookStoreService.sellBook(book);
        assertTrue("Book didn't disappear", bookStoreService.findByTitle("Refactoring").isEmpty());
        assertTrue("Book didn't disappear", bookStoreService.findByAuthor("Kent Beck").isEmpty());
    }

}
