package ru.otus.ATM.memeto;

import ru.otus.ATM.ATM;
import ru.otus.ATM.DemoATM;

public class Memento {
    private final ATM demoATM;

    public Memento(DemoATM demoATM) {
        this.demoATM = new DemoATM(demoATM);
    }

    public ATM restoreState() {
        return demoATM;
    }
}
