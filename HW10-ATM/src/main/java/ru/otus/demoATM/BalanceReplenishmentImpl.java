package ru.otus.demoATM;

import java.util.Map;

public class BalanceReplenishmentImpl implements BalanceReplenishment {
    @Override
    public void replenishBalance(Map<Integer, Integer> balance, Integer cash) {

        if (!balance.containsKey(cash)) {
            balance.put(cash, 1);
        } else {
            balance.put(cash, balance.get(cash) + 1);
        }

    }
}
