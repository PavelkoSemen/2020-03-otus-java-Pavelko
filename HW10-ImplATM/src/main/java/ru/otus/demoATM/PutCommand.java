package ru.otus.demoATM;

import java.util.Map;

public class PutCommand implements Command {
    private final int moneyToAdd;
    private Map<FaceValue, BankCell> cellLocker;

    public PutCommand(Map<FaceValue, BankCell> cellLocker, int moneyToAdd) {
        this.moneyToAdd = moneyToAdd;
        this.cellLocker = cellLocker;
    }

    @Override
    public Integer execute() {
        return putMoney();
    }

    private int putMoney() {

        FaceValue faceValue = FaceValue.getFaceValue(moneyToAdd);

        if (faceValue == null) {
            return -1;
        }

        if (cellLocker.get(faceValue) == null) {
            cellLocker.put(faceValue, new BankCellImpl(faceValue));
            return 1;
        }

        cellLocker.get(faceValue).addNumberOfBanknote();

        return 1;
    }

}

