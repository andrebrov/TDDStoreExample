package com.mindgames.examples.tdd.store.entities;

import com.mindgames.examples.tdd.store.paysystem.PaySystem;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 21:27
 */
public class SpyPaySystem extends PaySystem {

    private boolean increaseProfitMethodWasCalled = false;
    private int increaseProfitMethodParam = -1;


    @Override
    public void increaseProfit(int cash) {
        increaseProfitMethodWasCalled = true;
        increaseProfitMethodParam = cash;
    }

    public boolean verifyIncreaseProfit() {
        return increaseProfitMethodWasCalled;
    }

    public int verifyIncreaseProfitParam() {
        return increaseProfitMethodParam;
    }
}
