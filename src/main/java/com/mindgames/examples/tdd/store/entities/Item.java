package com.mindgames.examples.tdd.store.entities;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class Item {

    public static final String BOOK_TYPE = "BOOK";
    public static final String CD_TYPE = "CD";

    private int price;

    public abstract String getItemType();

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
