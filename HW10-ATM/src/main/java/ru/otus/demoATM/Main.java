package ru.otus.demoATM;

public class Main {
    public static void main(String[] args) {
        DemoATM demoATM = new DemoATM();
        demoATM.replenish(500);
        demoATM.replenish(500);
        demoATM.replenish(500);
        demoATM.replenish(200);
        demoATM.replenish(100);
        System.out.println(demoATM.check());
        System.out.println(demoATM.giveMyMoney(1600));
        System.out.println(demoATM.check());
    }
}
