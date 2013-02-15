package com.mindgames.examples.tdd.store.entities;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 20:23
 */
public class CompactDiskTest {

    @Test
    public void testBookIsItemWithBookType() {
        Item item = new CompactDisk();
        assertEquals("Wrong type of book entity", Item.CD_TYPE, item.getItemType());
    }
}
