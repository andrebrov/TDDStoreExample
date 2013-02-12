package com.mindgames.examples.tdd.store.entities;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 20:05
 * To change this template use File | Settings | File Templates.
 */
public class StoreTest {

    @Test
    public void testStoreIsEmptyByDefault() {
        Store store = new Store();
        List<Item> items = store.getItems();
        assertTrue("There are books in just created store", items.isEmpty());
    }
}
