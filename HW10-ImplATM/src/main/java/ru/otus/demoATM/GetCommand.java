package ru.otus.demoATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetCommand implements Command {
    private final int amountToReceive;
    private int amountToCheck;
    private Map<FaceValue, BankCell> cellLocker;
    private List<BankCell> cellLockerList;

    public GetCommand(Map<FaceValue, BankCell> cellLocker, int amountToReceive) {
        this.amountToReceive = amountToReceive;
        this.cellLocker = cellLocker;
        this.cellLockerList = new ArrayList<>(cellLocker.values());
    }

    @Override
    public Integer execute() {
        return giveOutCash(amountToReceive);
    }

    private int giveOutCash(int amountToReceive) {

        FaceValue[] possibleValues = new FaceValue[0];

        for (int i = 0; i < cellLockerList.size(); i++) {
            boolean isDecision = false;
            ArrayList<FaceValue> arrayToIterateOver = new ArrayList<>();

            isDecision = iterativeCheck(arrayToIterateOver, i);

            for (int j = i + 1; j < cellLockerList.size(); j++) {

                if (isDecision = iterativeCheck(arrayToIterateOver, j)) {
                    break;
                }

            }

            if (isDecision & (possibleValues.length == 0 || possibleValues.length > arrayToIterateOver.size())) {
                possibleValues = arrayToIterateOver.toArray(new FaceValue[0]);
            }

        }

        if (possibleValues.length == 0) {
            return -1;
        }

        for (FaceValue possibleValue : possibleValues) {
            cellLocker.get(possibleValue).reduceNumberOfBanknote();
        }

        return amountToCheck;

    }

    private boolean iterativeCheck(List<FaceValue> listForExclusion, int step) {

        for (int numberOfBanknote = 0; numberOfBanknote < cellLockerList.get(step).getNumberOfBanknote(); numberOfBanknote++) {

            amountToCheck += cellLockerList.get(step).getFaceValue().getValue();

            if (amountToCheck < amountToReceive) {
                listForExclusion.add(cellLockerList.get(step).getFaceValue());
            } else if (amountToCheck > amountToReceive) {
                amountToCheck -= cellLockerList.get(step).getFaceValue().getValue();
            } else {
                listForExclusion.add(cellLockerList.get(step).getFaceValue());
                return true;
            }

        }

        return false;
    }

}
