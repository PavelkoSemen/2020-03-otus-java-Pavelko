package ru.otus.departmentATM;

import ru.otus.ATM.ATM;
import ru.otus.ATM.observer.EventProducer;
import ru.otus.departmentATM.сhain.Event;

public class DepartmentATM {
    private final EventProducer eventProducer = new EventProducer();

    public void addATM(ATM atm){
        Event event = new Event(eventProducer);
        event.process(atm);
    }

    public int ATMbalance(){
        return eventProducer.eventCheck();
    }

    public void ATMtoOriginalCondition(){
         eventProducer.eventReset();
    }

}
