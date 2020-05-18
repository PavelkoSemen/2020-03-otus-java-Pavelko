package ru.otus.demoATM;

import java.util.TreeMap;

public class DemoATM {
    private BalanceCheck balanceCheck;
    private BalanceReplenishment balanceReplenishment;
    private CashWithdrawal cashWithdrawal;
    private TreeMap<Integer, Integer> balanceATM = new TreeMap<>((t1, t2) -> t2 - t1);

    public DemoATM() {
        balanceCheck = new BalanceCheckImpl();
        balanceReplenishment = new BalanceReplenishmentImpl();
        cashWithdrawal = new CashWithdrawalImpl();
    }

    public int check() {
       return balanceCheck.checkBalance(balanceATM);
    }


    public void replenish(Integer banknotes) {
        balanceReplenishment.replenishBalance(balanceATM, banknotes);
    }

    public int giveMyMoney(Integer countCash) {
       return cashWithdrawal.giveOutCash(balanceATM, countCash);
    }

}
