package vendingmachine;

import java.util.Objects;
import java.util.Optional;

public enum ProductCode {

    CHIPS,
    CANDY,
    PEANUTS;

    public static Optional<ProductCode> from(String productCode){
        if(Objects.isNull(productCode)){ return Optional.empty();}
        try{
            ProductCode resolvedProductCode = ProductCode.valueOf(productCode.toUpperCase());
            return Optional.of(resolvedProductCode);
        }catch (IllegalArgumentException e){
            return Optional.empty();
        }
    }

}
