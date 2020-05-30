package ru.otus.demoATM;

public interface BankCell {

    int getNumberOfBanknote();

    void addNumberOfBanknote();

    void reduceNumberOfBanknote();

    FaceValue getFaceValue();

    int amountMoneyInCell();

}
