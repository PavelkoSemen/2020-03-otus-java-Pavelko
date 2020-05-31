package ru.otus.ATM.command;

import ru.otus.ATM.bankCell.BankCell;
import ru.otus.ATM.bankCell.FaceValue;

import java.util.Map;

public class CheckCommand implements Command {
    private Map<FaceValue, BankCell> cellLocker;

    public CheckCommand(Map<FaceValue, BankCell> cellLocker) {
        this.cellLocker = cellLocker;
    }

    @Override
    public int execute() {
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
