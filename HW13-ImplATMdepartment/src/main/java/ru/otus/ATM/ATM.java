package ru.otus.ATM;

import ru.otus.ATM.observer.Listener;

public interface ATM  extends Listener {

    int check();

    void replenish(int[] banknotes);

    int giveMyMoney(int countCash);

}
