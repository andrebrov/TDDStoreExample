package com.mindgames.examples.tdd.store;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
public class Book {
    private String author;
    private String title;
    private String publisher;
    private int publishYear;

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getPublishYear() {
        return publishYear;
    }
}
