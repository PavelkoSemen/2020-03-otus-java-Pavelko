package ru.otus.ATM.command;

import ru.otus.ATM.bankCell.BankCell;
import ru.otus.ATM.bankCell.BankCellImpl;
import ru.otus.ATM.bankCell.FaceValue;

import java.util.Map;

public class PutMultipleCommand implements Command {

    private final int[] moneyToAdd;
    private Map<FaceValue, BankCell> cellLocker;

    public PutMultipleCommand(Map<FaceValue, BankCell> cellLocker, int[] moneyToAdd) {
        this.moneyToAdd = moneyToAdd;
        this.cellLocker = cellLocker;
    }

    @Override
    public int execute() {
        return putMoney();
    }

    private int putMoney() {

        for (int money : moneyToAdd) {
            FaceValue faceValue = FaceValue.getFaceValue(money);

            if (faceValue == null) {
                return -1;
            }

            if (cellLocker.get(faceValue) == null) {
                cellLocker.put(faceValue, new BankCellImpl(faceValue));
                continue;
            }

            cellLocker.get(faceValue).addNumberOfBanknote();

        }
        return 1;

    }

}
