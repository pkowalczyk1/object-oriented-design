package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class PriceStrategy implements SearchStrategy {

    private BigDecimal price;

    public PriceStrategy(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean filter(HistoryLog log) {
        return log.getOrder().getPriceWithOrderDiscount().equals(price);
    }
}
