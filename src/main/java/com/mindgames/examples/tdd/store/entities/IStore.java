package com.mindgames.examples.tdd.store.entities;

import com.mindgames.examples.tdd.store.paysystem.PaySystem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public interface IStore {
    void addItem(Item item);

    List<Item> getItems();

    void sellItem(Item item);

    void setPaySystem(PaySystem paySystem);

    PaySystem getPaySystem();
}
