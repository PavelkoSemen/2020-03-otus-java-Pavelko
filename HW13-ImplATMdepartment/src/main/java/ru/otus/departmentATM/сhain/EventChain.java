package ru.otus.departmentATM.сhain;

import ru.otus.ATM.ATM;

abstract class EventChain {
    private EventChain next;

    private EventChain getNext() {
        return next;
    }

    public void setNext(EventChain next) {
        this.next = next;
    }

    public void process(ATM atm) {
        if (atm != null){
            processInternal(atm);
        }
        if (getNext() != null) {
            getNext().process(atm);
        }
    }

    protected abstract void processInternal(ATM atm);

}
