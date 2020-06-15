package ru.otus.ATM.observer;

import java.util.ArrayList;
import java.util.List;

public class EventProducer {
    private List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public int eventCheck() {
        return listeners.stream().mapToInt(Listener::onCheckEvent).sum();
    }

    public void eventReset() {
        List<Listener> soursListener = new ArrayList<>();

        for (Listener listener : listeners) {
            soursListener.add(listener.onResetEvent());
        }

        listeners = soursListener;
    }


}
