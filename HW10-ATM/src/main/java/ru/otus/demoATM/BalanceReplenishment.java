package ru.otus.demoATM;

import java.util.Map;

public interface BalanceReplenishment {
    void replenishBalance(Map<Integer, Integer> balance, Integer cash);
}
