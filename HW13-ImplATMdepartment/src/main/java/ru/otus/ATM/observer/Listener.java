package ru.otus.ATM.observer;
import ru.otus.ATM.ATM;

public interface Listener  {

    int onCheckEvent();

    ATM onResetEvent();

}
