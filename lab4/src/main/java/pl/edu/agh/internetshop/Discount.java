package pl.edu.agh.internetshop;

import java.math.BigDecimal;

import static pl.edu.agh.internetshop.Product.PRICE_PRECISION;
import static pl.edu.agh.internetshop.Product.ROUND_STRATEGY;

public class Discount {
    public static final BigDecimal hundred = BigDecimal.valueOf(100);
    private BigDecimal percentage;

    public Discount(int percentage) {
        if (percentage > 100 || percentage < 0) {
            throw new IllegalArgumentException("Discount percentage should be between 0 and 100");
        }
        this.percentage = BigDecimal.valueOf(percentage);
    }

    public BigDecimal applyDiscount(BigDecimal price) {
        return price.subtract(price.multiply(percentage.divide(hundred, PRICE_PRECISION, ROUND_STRATEGY)))
                .setScale(PRICE_PRECISION, ROUND_STRATEGY);
    }
}
