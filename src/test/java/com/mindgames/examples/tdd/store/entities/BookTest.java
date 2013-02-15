package com.mindgames.examples.tdd.store.entities;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 19:58
 */
public class BookTest {

    @Test
    public void testBookIsItemWithBookType() {
        Item item = new Book();
        assertEquals("Wrong type of book entity", Item.BOOK_TYPE, item.getItemType());
    }
}
