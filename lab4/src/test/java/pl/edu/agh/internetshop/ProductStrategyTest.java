package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ProductStrategyTest {

    private final String productName = "Book";

    @Test
    public void testCorrectProduct() {
        // given
        Order order = mock(Order.class);
        HistoryLog log = mock(HistoryLog.class);
        given(log.getOrder()).willReturn(order);
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        given(product1.getName()).willReturn(productName);
        given(product2.getName()).willReturn("Wrong name");
        given(order.getProducts()).willReturn(List.of(product1, product2));

        // when
        ProductStrategy strategy = new ProductStrategy(productName);

        // then
        assertTrue(strategy.filter(log));
    }

    @Test
    public void testIncorrectProduct() {
        // given
        Order order = mock(Order.class);
        HistoryLog log = mock(HistoryLog.class);
        given(log.getOrder()).willReturn(order);
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        given(product1.getName()).willReturn("Wrond name 1");
        given(product2.getName()).willReturn("Wrong name 2");
        given(order.getProducts()).willReturn(List.of(product1, product2));

        // when
        ProductStrategy strategy = new ProductStrategy(productName);

        // then
        assertFalse(strategy.filter(log));
    }
}
