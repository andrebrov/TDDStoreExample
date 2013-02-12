package com.mindgames.examples.tdd.store.service;

import com.mindgames.examples.tdd.store.entities.IStore;
import com.mindgames.examples.tdd.store.entities.Item;
import com.mindgames.examples.tdd.store.paysystem.PaySystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class MockStore implements IStore {

    private List<Item> items;
    private PaySystem paySystem;


    public MockStore() {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void sellItem(Item item) {
        items.remove(item);
    }

    public void setPaySystem(PaySystem paySystem) {
    }

    public PaySystem getPaySystem() {
        return null;
    }

}
