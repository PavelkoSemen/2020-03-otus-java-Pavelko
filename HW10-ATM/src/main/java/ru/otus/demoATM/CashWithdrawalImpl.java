package ru.otus.demoATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CashWithdrawalImpl implements CashWithdrawal {
    @Override
    public int giveOutCash(Map<Integer, Integer> balance, Integer countCash) {

        List<Integer> listOfMap = mapToList(balance);
        Integer[] possibleValues = amountToIssue(listOfMap, countCash);

        if (possibleValues.length == 0){
            throw new RuntimeException("This amount cannot be issued");
        }

        int amount = 0;

        for (int i = 0; i < possibleValues.length; i++) {
            balance.put(possibleValues[i], balance.get(possibleValues[i]) - 1);
            amount += possibleValues[i];
        }

        return amount;
    }


    private <T> List<T> mapToList(Map<T, Integer> map) {

        List<T> listOfMap = new ArrayList<>();

        for (Map.Entry<T, Integer> entry : map.entrySet()) {

            for (int i = 0; i < entry.getValue(); i++) {
                listOfMap.add(entry.getKey());
            }

        }

        return listOfMap;
    }


    private Integer[] amountToIssue(List<Integer> listOfMap, Integer amount) {

        Integer[] possibleValues = new Integer[0];

        for (int i = 0; i < listOfMap.size(); i++) {
            boolean isDecision = false;
            ArrayList<Integer> arrayToIterateOver = new ArrayList<>();
            arrayToIterateOver.add(listOfMap.get(i));

            int amountSought = listOfMap.get(i);

            for (int j = i + 1; j < listOfMap.size(); j++) {

                amountSought += listOfMap.get(j);

                if (amountSought < amount) {
                    arrayToIterateOver.add(listOfMap.get(j));
                } else if (amountSought > amount) {
                    amountSought -= listOfMap.get(j);
                } else {
                    arrayToIterateOver.add(listOfMap.get(j));
                    isDecision = true;
                    break;
                }

            }

            if (isDecision & (possibleValues.length == 0 || possibleValues.length > arrayToIterateOver.size())) {
                possibleValues = arrayToIterateOver.toArray(new Integer[0]);
            }

        }

        return possibleValues;

    }

}
