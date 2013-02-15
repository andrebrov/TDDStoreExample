package com.mindgames.examples.tdd.store.entities;

import com.mindgames.examples.tdd.store.paysystem.PaySystem;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 20:05
 */
public class StoreTest {

    public static final Item DUMMY_ITEM = mock(Item.class);
    private PaySystem paySystem = mock(PaySystem.class);

    @Test
    public void testStoreIsEmptyByDefault() {
        IStore store = new Store();
        List<Item> items = store.getItems();
        assertTrue("There are items in just created store", items.isEmpty());
    }

    @Test
    public void testCanAddItem() {
        IStore store = new Store();
        store.addItem(DUMMY_ITEM);
        assertEquals("Item was not added", 1, store.getItems().size());
    }

    @Test
    public void testItemWillDisappearAfterSelling() {
        IStore store = new Store();
        store.setPaySystem(paySystem);
        store.addItem(DUMMY_ITEM);
        store.sellItem(DUMMY_ITEM);
        List<Item> items = store.getItems();
        assertTrue("Item was not sold", items.isEmpty());
    }

    @Test
    public void testProfitIncreasedAfterSell() {
        IStore store = new Store();
        store.setPaySystem(paySystem);
        Item item = mock(Item.class);
        when(item.getPrice()).thenReturn(100);
        item.setPrice(100);
        store.addItem(item);
        store.sellItem(item);
        verify(paySystem).increaseProfit(100);
    }
}
