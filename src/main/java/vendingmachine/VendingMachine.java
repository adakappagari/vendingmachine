package vendingmachine;

import java.util.Map;

public interface VendingMachine {
     void processRequest(String product, Map<CashDenomination, Integer> amount) throws Exception;
}
