package com.mindgames.examples.tdd.store.util;

import com.mindgames.examples.tdd.store.entities.Book;
import com.mindgames.examples.tdd.store.entities.Item;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 15.02.13
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class BookBuilder {

    private Book book;

    public static Item fullfilledBook() {
        Book book = new Book();
        book.setAuthor("Kent Beck");
        book.setTitle("Refactoring");
        book.setPublisher("A-Press");
        book.setPublishYear(2012);
        return book;
    }

    public BookBuilder() {
        book = new Book();
    }

    public BookBuilder author(String author) {
        book.setAuthor(author);
        return this;
    }

    public BookBuilder title(String title) {
        book.setTitle(title);
        return this;
    }

    public BookBuilder publisher(String publisher) {
        book.setPublisher(publisher);
        return this;
    }

    public BookBuilder publishYear(int publishYear) {
        book.setPublishYear(publishYear);
        return this;
    }

    public Book build() {
        return book;
    }

}
