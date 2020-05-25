package ru.otus.demoATM;

public interface ATM {

    int check();

    void replenish(Integer banknotes);

    int giveMyMoney(Integer countCash);

}
