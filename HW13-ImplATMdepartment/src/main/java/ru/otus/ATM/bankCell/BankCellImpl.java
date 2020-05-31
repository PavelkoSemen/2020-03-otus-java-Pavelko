package ru.otus.ATM.bankCell;

public class BankCellImpl implements BankCell {

    private int numberOfBanknote;
    private final FaceValue faceValue;

    public BankCellImpl(FaceValue faceValue) {
        this.faceValue = faceValue;
        numberOfBanknote++;
    }

    @Override
    public int getNumberOfBanknote() {
        return numberOfBanknote;
    }

    @Override
    public void addNumberOfBanknote() {
        numberOfBanknote = numberOfBanknote + 1;
    }

    @Override
    public void reduceNumberOfBanknote() {

        if (numberOfBanknote - 1 < 0) {
            throw new ArithmeticException("Negative number of banknotes");
        }
        numberOfBanknote = numberOfBanknote - 1;
    }

    @Override
    public int amountMoneyInCell() {
        return this.getNumberOfBanknote() * this.getFaceValue().getValue();
    }


    @Override
    public BankCell copy() {
        try {
            return (BankCell) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FaceValue getFaceValue() {
        return faceValue;
    }
}
      