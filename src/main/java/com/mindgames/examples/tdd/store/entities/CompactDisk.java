package com.mindgames.examples.tdd.store.entities;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 20:24
 */
public class CompactDisk extends Item {
    @Override
    public String getItemType() {
        return Item.CD_TYPE;
    }
}
