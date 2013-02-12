package com.mindgames.examples.tdd.store.entities;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
public class CompactDisk extends Item {
    @Override
    public String getItemType() {
        return Item.CD_TYPE;
    }
}
