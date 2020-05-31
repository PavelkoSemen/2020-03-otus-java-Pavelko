package ru.otus.ATM.bankCell;

public enum FaceValue {
    HUNDRED(100), FIVE_HUNDRED(500), THOUSAND(1000), TWO_THOUSAND(2000), FIVE_THOUSAND(5000);

    private final int value;

    FaceValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FaceValue getFaceValue(int value) {

        for (FaceValue faceValue : FaceValue.values()) {
            if (faceValue.getValue() == value) {
                return faceValue;
            }
        }

        return null;
    }
}
