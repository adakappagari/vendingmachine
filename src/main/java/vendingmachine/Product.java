package vendingmachine;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private ProductCode code;
    private BigDecimal price;

    public Product(ProductCode code) {
        this.code = code;
    }

    public Product(ProductCode code, BigDecimal price) {
        this.code = code;
        this.price = price;
    }

    public ProductCode getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return code == product.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }
}
