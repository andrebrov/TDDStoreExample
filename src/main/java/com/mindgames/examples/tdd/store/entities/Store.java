package com.mindgames.examples.tdd.store.entities;

import com.mindgames.examples.tdd.store.paysystem.PaySystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 18:40
 */
public class Store implements IStore {

    private List<Item> items;
    private PaySystem paySystem;


    public Store() {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void sellItem(Item item) {
        paySystem.increaseProfit(item.getPrice());
        items.remove(item);
    }

    public void setPaySystem(PaySystem paySystem) {
        this.paySystem = paySystem;
    }

}
