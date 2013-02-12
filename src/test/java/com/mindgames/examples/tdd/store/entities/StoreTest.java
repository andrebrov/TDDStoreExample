package com.mindgames.examples.tdd.store.entities;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
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
        IStore store = new Store();
        List<Item> items = store.getItems();
        assertTrue("There are items in just created store", items.isEmpty());
    }

    @Test
    public void testCanAddItem() {
        IStore store = new Store();
        DummyItem item = new DummyItem();
        store.addItem(item);
        assertEquals("Item was not added", 1, store.getItems().size());
    }

    @Test
    public void testItemWillDisappearAfterSelling() {
        IStore store = new Store();
        store.setPaySystem(new StubPaySystem());
        DummyItem item = new DummyItem();
        store.addItem(item);
        store.sellItem(item);
        List<Item> items = store.getItems();
        assertTrue("Item was not sold", items.isEmpty());
    }

    @Test
    public void testProfitIncreasedAfterSell() {
        IStore store = new Store();
        SpyPaySystem paySystem = new SpyPaySystem();
        store.setPaySystem(paySystem);
        DummyItem item = new DummyItem();
        item.setPrice(100);
        store.addItem(item);
        store.sellItem(item);
        assertTrue("PaySystem.increaseProfit() was not called", paySystem.verifyIncreaseProfit());
        assertEquals("PaySystem.increaseProfit() not called with wrong param", 100, paySystem.verifyIncreaseProfitParam());
    }
}
