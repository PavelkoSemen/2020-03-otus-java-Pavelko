package ru.otus.demoATM;

import java.util.Map;

public interface BalanceCheck {
    int checkBalance(Map<Integer, Integer> balanceMap);
}
