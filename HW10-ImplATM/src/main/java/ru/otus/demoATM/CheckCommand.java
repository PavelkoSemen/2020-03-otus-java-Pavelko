package ru.otus.demoATM;

import java.util.Map;

public class CheckCommand implements Command {
    private Map<FaceValue, BankCell> cellLocker;

    public CheckCommand(Map<FaceValue, BankCell> cellLocker) {
        this.cellLocker = cellLocker;
    }

    @Override
    public Integer execute() {
        return balanceCheck();
    }

    private int balanceCheck() {
        int balanceCheck = 0;

        for (Map.Entry<FaceValue, BankCell> entry : cellLocker.entrySet()) {
            balanceCheck += entry.getValue().amountMoneyInCell();
        }

        return balanceCheck;

    }

}
