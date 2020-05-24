package ru.otus.demoATM;

public class BankCell {

    private int numberOfBanknote;
    private FaceValue faceValue;


    public BankCell(FaceValue faceValue) {
        this.faceValue = faceValue;
        numberOfBanknote++;
    }

    public int getNumberOfBanknote() {
        return numberOfBanknote;
    }

    public void addNumberOfBanknote() {
        numberOfBanknote = numberOfBanknote + 1;
    }

    public void reduceNumberOfBanknote() {
        numberOfBanknote = numberOfBanknote - 1;
    }


    public FaceValue getFaceValue(){
        return faceValue;
    }
}
