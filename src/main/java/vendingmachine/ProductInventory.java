package vendingmachine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductInventory {

    private static ProductInventory productInventory;
    /**
     * @return Singleton ProductInventory instance
     */
    public static ProductInventory get(){
        if(Objects.isNull(productInventory)){
            synchronized (ProductInventory.class){
                if(Objects.isNull(productInventory)){
                    productInventory = new ProductInventory();
                }
                return productInventory;
            }
        }
        return productInventory;
    }


    private static Map<ProductCode, Product> catalog = new HashMap<>();
    private Map<ProductCode, Integer> inventory = new HashMap<>();

    private ProductInventory(){
        catalog.put(ProductCode.CHIPS, new Product(ProductCode.CHIPS, BigDecimal.valueOf(1.0) ));
        catalog.put(ProductCode.CANDY, new Product(ProductCode.CANDY,  BigDecimal.valueOf(1.25) ));
        catalog.put(ProductCode.PEANUTS, new Product(ProductCode.PEANUTS,  BigDecimal.valueOf(.75) ));

        inventory.put(ProductCode.CHIPS,5);
        inventory.put(ProductCode.CANDY,5);
        inventory.put(ProductCode.PEANUTS,0);
    }

    public Map<ProductCode, Product> getCatalog(){
        return catalog;
    }

    public boolean isAvailable(ProductCode productCode){
        return inventory.get(new Product(productCode)) > 0;
    }

    public void processTransaction(ProductCode productCode, int quantity){
        int available = inventory.get(productCode);
        if(available < quantity){
            throw new IllegalArgumentException("requested more than available");
        }
        inventory.put(productCode, available-quantity);
    }
}
