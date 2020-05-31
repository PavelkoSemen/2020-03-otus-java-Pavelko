package ru.otus.ATM;

import ru.otus.ATM.bankCell.BankCell;
import ru.otus.ATM.bankCell.FaceValue;
import ru.otus.ATM.command.CheckCommand;
import ru.otus.ATM.command.Command;
import ru.otus.ATM.command.GetCommand;
import ru.otus.ATM.command.PutMultipleCommand;
import ru.otus.ATM.memeto.Memento;

import java.util.Map;
import java.util.TreeMap;

public class DemoATM implements ATM {
    private Map<FaceValue, BankCell> cellLocker = new TreeMap<>((o1, o2) -> o2.getValue() - o1.getValue());
    private Memento sourceState;

    public DemoATM() {
        sourceState = new Memento(this);
    }

    public DemoATM(DemoATM demoATM) {
        this.cellLocker = cloneMap(demoATM.cellLocker);
        sourceState = demoATM.sourceState;
    }

    public DemoATM(int... args) {
        this.replenish(args);
        sourceState = new Memento(this);
    }

    @Override
    public int check() {
        return executeCommand(new CheckCommand(cellLocker));
    }


    @Override
    public void replenish(int... banknotes) {
        int isReplenish = executeCommand(new PutMultipleCommand(cellLocker, banknotes));
        if (isReplenish == -1) {
            throw new RuntimeException("Banknote of an unknown denomination");
        }
    }

    @Override
    public int giveMyMoney(int countCash) {
        int money = executeCommand(new GetCommand(cellLocker, countCash));
        if (money == -1) {
            throw new RuntimeException("Unable to issue requested amount");
        }
        return money;
    }

    @Override
    public int onCheckEvent() {
        return check();
    }

    @Override
    public ATM onResetEvent() {
        return sourceState.restoreState();
    }

    private Map<FaceValue, BankCell> cloneMap(Map<FaceValue, BankCell> source) {
        TreeMap<FaceValue, BankCell> cloneMap = new TreeMap<>((o1, o2) -> o2.getValue() - o1.getValue());

        for (var entry : source.entrySet()) {
            cloneMap.put(entry.getKey(), entry.getValue().copy());
        }

        return cloneMap;
    }

    private int executeCommand(Command command) {

        return command.execute();

    }

}