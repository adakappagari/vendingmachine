package vendingmachine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple Interface(Facade) to the clients accessing Vending Machine Service.
 */
public class VendingMachineService {

    private BufferedReader reader;

    public VendingMachineService(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void process() throws Exception{
        System.out.println(String.format("Available products and prices"));
        ProductInventory.get().getCatalog().entrySet().forEach(e -> {
            System.out.println(String.format("Code: %s , price: %s", e.getKey(), e.getValue().getPrice()));
        });

        System.out.println("Enter code to purchase: \n");
        String product = reader.readLine();
        System.out.println(String.format("Product code entered:%s",product));

        System.out.println("Insert Dollar Bills: \n");
        String dollars = reader.readLine();
        System.out.println(String.format("Dollars entered:%s",dollars));
        Map<CashDenomination, Integer> cash = new HashMap<>(1);
        cash.put(CashDenomination.DOLLAR, Integer.parseInt(dollars));

        VendingMachine vendingMachine = VendingMachineFactory.getInstance();
        vendingMachine.processRequest(product, cash);
    }
}
