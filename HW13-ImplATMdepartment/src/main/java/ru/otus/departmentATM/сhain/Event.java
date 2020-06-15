package ru.otus.departmentATM.сhain;

import ru.otus.ATM.ATM;
import ru.otus.ATM.observer.EventProducer;

public class Event extends EventChain {

    private final EventProducer eventProducer;

    public Event(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @Override
    protected void processInternal(ATM atm) {
        eventProducer.addListener(atm);
    }

}
