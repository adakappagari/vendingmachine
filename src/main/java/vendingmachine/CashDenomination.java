package vendingmachine;

import java.math.BigDecimal;

public enum CashDenomination {

    DOLLAR(BigDecimal.valueOf(1.0)),
    HALF_DOLLAR(BigDecimal.valueOf(.5)),
    QUARTER_DOLLAR(BigDecimal.valueOf(.25)),
    TEN_CENTS(BigDecimal.valueOf(.1)),
    CENT(BigDecimal.valueOf(.01));

    private BigDecimal faceValue;

    CashDenomination(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }
}
