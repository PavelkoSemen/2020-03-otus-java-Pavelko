package ru.otus.demoATM;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class DemoATMTest {
    private final int EXPECTED_CHECK = 11100;

    private DemoATM demoATM = new DemoATM();

    @BeforeEach
    void SetUP() {
        System.out.println("BeforeEach");

        demoATM = new DemoATM();
        demoATM.replenish(100);
        demoATM.replenish(500);
        demoATM.replenish(500);
        demoATM.replenish(1000);
        demoATM.replenish(1000);
        demoATM.replenish(1000);
        demoATM.replenish(2000);
        demoATM.replenish(5000);
    }

    @Test
    void ballanceCheck() {
        assertThat(EXPECTED_CHECK).isEqualTo(demoATM.check());
    }

    @Test
    void balanceCheckAfterWithdrawal() {
        demoATM.giveMyMoney(EXPECTED_CHECK);
        assertThat(demoATM.check()).isZero();

    }

    @ParameterizedTest
    @ValueSource(ints = {5000, 2000, 1000, 500, 100})
    void allValueShouldBeAddedToATM(int value) {

        demoATM.replenish(value);
        assertThat(EXPECTED_CHECK + value).isEqualTo(demoATM.check());
    }

    @Test
    void addBanknoteOfUnspecifiedDenomination() {
        int banknote = 2312;
        assertThrows(RuntimeException.class,
                () -> demoATM.replenish(banknote));
    }

    @Test
    void giveMyMoney() {
        int getMoney = 7500;
        assertThat(getMoney).isEqualTo(demoATM.giveMyMoney(getMoney));
    }



    @Test
    void giveMyMoneyWithError() {
        int getMoneyForError = 500000;
        assertThrows(RuntimeException.class,
                () -> demoATM.giveMyMoney(getMoneyForError));
    }

    @AfterEach
    void TearDown() {
        demoATM = null;
    }


}