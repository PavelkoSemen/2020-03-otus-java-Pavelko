package ru.otus.demoATM;

import java.util.Map;

public class BalanceCheckImpl implements BalanceCheck {


    @Override
    public int checkBalance(Map<Integer, Integer> balanceMap) {
        int balance = 0;

        for (Map.Entry<Integer, Integer> entry : balanceMap.entrySet()){
            balance += entry.getKey() * entry.getValue();
        }

        return balance;

    }

}