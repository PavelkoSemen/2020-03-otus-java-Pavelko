package ru.otus.ATM.bankCell;

public interface BankCell extends Cloneable {

    int getNumberOfBanknote();

    void addNumberOfBanknote();

    void reduceNumberOfBanknote();

    FaceValue getFaceValue();

    int amountMoneyInCell();

    BankCell copy();

}
