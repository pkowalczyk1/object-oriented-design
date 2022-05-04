package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class Product {
	
	public static final int PRICE_PRECISION = 2;
	public static final int ROUND_STRATEGY = BigDecimal.ROUND_HALF_UP;
	
    private final String name;
    private final BigDecimal price;
    private Discount discount;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.price.setScale(PRICE_PRECISION, ROUND_STRATEGY);
        discount = new Discount(0);
    }

    public Product(String name, BigDecimal price, Discount discount) {
        this.name = name;
        this.price = price;
        this.price.setScale(PRICE_PRECISION, ROUND_STRATEGY);
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceWithoutDiscount() {
        return price;
    }

    public BigDecimal getPriceWithDiscount() {
        return discount.applyDiscount(price);
    }
}
