package vendingmachine;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class VendingMachineImpl implements VendingMachine {

    @Override
    public void processRequest(String productCode, Map<CashDenomination, Integer> cash) throws Exception {
         Optional<ProductCode> parsedProductCode = ProductCode.from(productCode);
        if(!parsedProductCode.isPresent()){
            throw new IllegalArgumentException("Invalid product code");
        }
        BigDecimal amount = cash.entrySet().stream().map(e -> e.getKey().getFaceValue().multiply(BigDecimal.valueOf(e.getValue()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        Product product = ProductInventory.get().getCatalog().get(parsedProductCode.get());
        if(amount.compareTo(product.getPrice()) < 0){
            throw new IllegalArgumentException("Insufficient cash");
        }
        Map<CashDenomination, Integer> change = CashRegister.get().processTransaction(cash, product.getPrice());
        ProductInventory.get().processTransaction(parsedProductCode.get(), 1);

        //dispense product
        System.out.println("Dispensing product:"+productCode);
        //dispense cash
        for(Map.Entry<CashDenomination, Integer> each: change.entrySet()){
            System.out.println(String.format("Dispensing %s %s", each.getValue(), each.getKey()));
        }
    }

    public Double parseCash(String cash){
        try{
            Double amount = Double.valueOf(cash);
            return amount;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Enter valid cash");
        }
    }

    public void tenderChange() {

    }

    public void tenderProduct() {

    }

}
