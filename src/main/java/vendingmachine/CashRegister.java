package vendingmachine;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static vendingmachine.CashDenomination.*;

public class CashRegister {

    private static CashRegister cashRegister;

    /**
     * @return Singleton CashRegister instance
     */
    public static CashRegister get(){
        if(Objects.isNull(cashRegister)){
            synchronized (CashRegister.class){
                if(Objects.isNull(cashRegister)){
                    cashRegister = new CashRegister();
                }
                return cashRegister;
            }
        }
        return cashRegister;
    }

    private BigDecimal total;
    private Map<CashDenomination, Integer> register = new LinkedHashMap<>();

    private CashRegister() {
        register.put(DOLLAR, 10);
        register.put(HALF_DOLLAR, 7);
        register.put(QUARTER_DOLLAR, 10);
        register.put(TEN_CENTS, 15);
        register.put(CENT, 5);
        total = register.entrySet().stream()
                .map(e -> e.getKey().getFaceValue().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public boolean hasSufficientChange(BigDecimal amount){
        if(total.compareTo(amount) < 0){ return false; }

        for(CashDenomination denomination: register.keySet()){
            int available = findAvailable(amount, denomination);
            amount = amount.subtract(denomination.getFaceValue().multiply(BigDecimal.valueOf(available)));
            if(amount.compareTo(BigDecimal.ZERO) == 0){ return true;}
        }
        return false;
    }

    public Map<CashDenomination, Integer> processTransaction(Map<CashDenomination, Integer> totAmount, BigDecimal deductAmount) throws Exception{
        BigDecimal totAmtSum = totAmount.entrySet().stream().map(e -> e.getKey().getFaceValue().multiply(BigDecimal.valueOf(e.getValue()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal returnAmt = totAmtSum.subtract(deductAmount);
        if(returnAmt.compareTo(BigDecimal.ZERO) == 0){
            return Collections.EMPTY_MAP;
        }
        //compute change
        Map<CashDenomination, Integer> change = computeChange(returnAmt);
        //process addition
        for(Map.Entry<CashDenomination, Integer> each: totAmount.entrySet()){
            register.put(each.getKey(), each.getValue() + register.get(each.getKey()));
        }
        //process subtraction
        for(Map.Entry<CashDenomination, Integer> each: change.entrySet()){
            register.put(each.getKey(), each.getValue() - register.get(each.getKey()));
        }
        return change;
    }

    private Map<CashDenomination, Integer> computeChange(BigDecimal amount) throws ChangeNotAvailableException {
        Map<CashDenomination, Integer> change = new LinkedHashMap<>();
        for(CashDenomination denomination: register.keySet()){
            int available = findAvailable(amount, denomination);
            amount = amount.subtract(denomination.getFaceValue().multiply(BigDecimal.valueOf(available)));
            change.put(denomination, available);
            if(amount.compareTo(BigDecimal.ZERO) == 0){ break;}
        }
        if(amount.compareTo(BigDecimal.ZERO) == 0){ return change;}
        else{ throw new ChangeNotAvailableException("Could not find sufficient change to process request");}
    }

    private int findAvailable(BigDecimal amount, CashDenomination denomination) {
//        BigDecimal change = new BigDecimal(0);
        int dollarsAvail = register.get(denomination);
        int toReturn = 0;
        while(amount.compareTo(denomination.getFaceValue()) >= 0
                && dollarsAvail > 0){
            amount = amount.subtract(denomination.getFaceValue());
//            change = change.add(denomination.getFaceValue());
            dollarsAvail--;
            toReturn++;
        }
        return toReturn;
    }
}
