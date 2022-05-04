package pl.edu.agh.internetshop;

import java.util.stream.Collectors;

public class ProductStrategy implements SearchStrategy {

    private String productName;

    public ProductStrategy(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean filter(HistoryLog log) {
        return log.getOrder().getProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toList())
                .contains(productName);
    }
}
