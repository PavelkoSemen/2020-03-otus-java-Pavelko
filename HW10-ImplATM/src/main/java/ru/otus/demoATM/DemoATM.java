package ru.otus.demoATM;

import java.util.Map;
import java.util.TreeMap;

public class DemoATM {
    Map<FaceValue, BankCell> cellLocker = new TreeMap<>((o1, o2) -> o2.getValue() - o1.getValue());

    public int check() {
        return executeCommand(new CheckCommand(cellLocker));
    }

    public void replenish(Integer banknotes) {
        int isReplenish = executeCommand(new PutCommand(cellLocker, banknotes));
        if (isReplenish == -1) {
            throw new RuntimeException("Banknote of an unknown denomination");
        }
    }

    public int giveMyMoney(Integer countCash) {
        int money = executeCommand(new GetCommand(cellLocker, countCash));
        if (money == -1) {
            throw new RuntimeException("Unable to issue requested amount");
        }
        return money;
    }

    private int executeCommand(Command command) {

        return command.execute();

    }
}