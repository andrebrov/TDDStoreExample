package com.mindgames.examples.tdd.store.entities;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 18:40
 */
public class Book extends Item {
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

    @Override
    public String getItemType() {
        return Item.BOOK_TYPE;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return publishYear == book.publishYear && author.equals(book.author) && publisher.equals(book.publisher) && title.equals(book.title);

    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + publishYear;
        return result;
    }
}
