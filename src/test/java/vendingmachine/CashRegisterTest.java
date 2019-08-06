package vendingmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CashRegisterTest {


    @Test
    public void sufficientFundTest(){
        CashRegister cashRegister = CashRegister.get();
        Assertions.assertTrue(cashRegister.hasSufficientChange(BigDecimal.valueOf(1)));

        Assertions.assertTrue(cashRegister.hasSufficientChange(BigDecimal.valueOf(1.5)));
        Assertions.assertTrue(cashRegister.hasSufficientChange(BigDecimal.valueOf(1.6)));
        Assertions.assertFalse(cashRegister.hasSufficientChange(BigDecimal.valueOf(1.06)));
        Assertions.assertTrue(cashRegister.hasSufficientChange(BigDecimal.valueOf(17.55)));
        Assertions.assertFalse(cashRegister.hasSufficientChange(BigDecimal.valueOf(17.56)));

    }
}
