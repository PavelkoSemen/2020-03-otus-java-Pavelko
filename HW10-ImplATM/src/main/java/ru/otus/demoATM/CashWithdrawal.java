package ru.otus.demoATM;

import java.util.Map;

public interface CashWithdrawal {

    int giveOutCash(Map<Integer, Integer> balance, Integer countCash);

}