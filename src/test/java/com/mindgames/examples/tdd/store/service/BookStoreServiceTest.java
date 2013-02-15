package com.mindgames.examples.tdd.store.service;

import com.mindgames.examples.tdd.store.entities.Book;
import com.mindgames.examples.tdd.store.entities.IStore;
import com.mindgames.examples.tdd.store.entities.Item;
import com.mindgames.examples.tdd.store.util.BookBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    private List<Item> listWithTwoItems;

    @BeforeMethod
    public void setUp() {
        store = mock(IStore.class);
        listWithTwoItems = new ArrayList<Item>();
        listWithTwoItems.add(BookBuilder.fullfilledBook());
        listWithTwoItems.add(BookBuilder.fullfilledBook());
    }

    @Test
    public void testCanAddBook() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Item book = new Book();
        bookStoreService.addBook(new Book());
        List<Item> items = Collections.singletonList(book);
        when(store.getItems()).thenReturn(items);
        List<Book> books = bookStoreService.getBooks();
        assertEquals("Book was not added", 1, books.size());
        assertEquals("Wrong book was added", book, books.get(0));
    }

    @Test
    public void shouldGetAuthorsList() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new BookBuilder().author("Kent Beck").build();
        bookStoreService.addBook(book);
        Set<String> authors = bookStoreService.getAuthors();
        assertEquals("Wrong amounts of authors", 1, authors.size());
        assertTrue("Wrong author", authors.contains("Kent Beck"));
    }

    @Test
    public void shouldReturnUniqueAuthor() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new BookBuilder().author("Kent Beck").build();
        Book book1 = new BookBuilder().author("Kent Beck").build();
        bookStoreService.addBook(book);
        bookStoreService.addBook(book1);
        Set<String> authors = bookStoreService.getAuthors();
        assertEquals("Wrong amounts of authors", 1, authors.size());
        assertTrue("Wrong author", authors.contains("Kent Beck"));
    }

    @Test
    public void shouldGetTitlesList() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new BookBuilder().title("Refactoring").build();
        bookStoreService.addBook(book);
        Set<String> titles = bookStoreService.getTitles();
        assertEquals("Wrong amounts of titles", 1, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [][]"));
    }

    @Test
    public void shouldReturnUniqueTitle() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new BookBuilder().title("Refactoring").build();
        Book book1 = new BookBuilder().title("Refactoring").build();
        bookStoreService.addBook(book);
        bookStoreService.addBook(book1);
        Set<String> titles = bookStoreService.getTitles();
        assertEquals("Wrong amounts of titles", 1, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [][]"));
    }

    @Test
    public void shouldFindBookByAuthor() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Item book = new BookBuilder().author("Kent Beck").build();
        bookStoreService.addBook((Book) book);
        List<Item> items = Collections.singletonList(book);
        when(store.getItems()).thenReturn(items);
        List<Book> foundedBooks = bookStoreService.findByAuthor("Kent Beck");
        assertTrue("Book list is null", foundedBooks != null);
        assertTrue("Book list is empty", !foundedBooks.isEmpty());
        assertEquals("Smth wrong with find by author", book, foundedBooks.get(0));
    }

    @Test
    public void shouldFindBookByTitle() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Item book = new BookBuilder().title("Refactoring").build();
        bookStoreService.addBook((Book) book);
        List<Item> items = Collections.singletonList(book);
        when(store.getItems()).thenReturn(items);
        List<Book> foundedBooks = bookStoreService.findByTitle("Refactoring");
        assertTrue("Book list is null", foundedBooks != null);
        assertTrue("Book list is empty", !foundedBooks.isEmpty());
        assertEquals("Smth wrong with find by author", book, foundedBooks.get(0));
    }

    @Test
    public void shouldReturnDifferentTitlesForDifferentPublishers() {
        BookStoreService bookStoreService = new BookStoreService(store);
        Book book = new BookBuilder().title("Refactoring").publisher("Piter Press").build();
        Book book1 = new BookBuilder().title("Refactoring").publisher("A-Press").build();
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
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        Book book = (Book) BookBuilder.fullfilledBook();
        book.setPublishYear(2010);
        bookStoreService.addBook(book);
        Set<String> titles = bookStoreService.getTitles();
        assertEquals("Wrong amounts of titles", 2, titles.size());
        assertTrue("Wrong title", titles.contains("Refactoring [A-Press][2012]"));
        assertTrue("Wrong title", titles.contains("Refactoring [A-Press][2010]"));
    }

    @Test
    public void shouldCanAddSameBookToStore() {
        BookStoreService bookStoreService = new BookStoreService(store);
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        when(store.getItems()).thenReturn(listWithTwoItems);
        assertEquals("Two books were not added", 2, bookStoreService.getBooks().size());
    }

    @Test
    public void shouldFindAmountOfBooksByAuthor() {
        BookStoreService bookStoreService = new BookStoreService(store);
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        when(store.getItems()).thenReturn(listWithTwoItems);
        List<Book> byAuthor = bookStoreService.findByAuthor("Kent Beck");
        assertEquals("Two books were not added", 2, byAuthor.size());
    }

    @Test
    public void shouldFindAmountOfBooksByTitle() {
        BookStoreService bookStoreService = new BookStoreService(store);
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        when(store.getItems()).thenReturn(listWithTwoItems);
        List<Book> byTitle = bookStoreService.findByTitle("Refactoring");
        assertEquals("Two books were not added", 2, byTitle.size());
    }

    @Test
    public void shouldFindSameBooksCount() {
        BookStoreService bookStoreService = new BookStoreService(store);
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        when(store.getItems()).thenReturn(listWithTwoItems);
        int sameBooksCount = bookStoreService.find((Book) BookBuilder.fullfilledBook());
        assertEquals("Two books were not added", 2, sameBooksCount);
    }

    @Test
    public void testCanAfterBuyBookItWillDisappear() {
        BookStoreService bookStoreService = new BookStoreService(store);
        bookStoreService.addBook((Book) BookBuilder.fullfilledBook());
        bookStoreService.sellBook((Book) BookBuilder.fullfilledBook());
        assertTrue("Book didn't disappear", bookStoreService.findByTitle("Refactoring").isEmpty());
        assertTrue("Book didn't disappear", bookStoreService.findByAuthor("Kent Beck").isEmpty());
    }

}
