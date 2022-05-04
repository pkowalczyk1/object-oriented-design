package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PriceStrategyTest {

    private final BigDecimal price = BigDecimal.valueOf(100);

    @Test
    public void testCorrectPrice() {
        // given
        Order order = mock(Order.class);
        HistoryLog log = mock(HistoryLog.class);
        given(log.getOrder()).willReturn(order);
        given(order.getPriceWithOrderDiscount()).willReturn(price);

        // when
        PriceStrategy strategy = new PriceStrategy(price);

        // then
        assertTrue(strategy.filter(log));
    }

    @Test
    public void testIncorrectPrice() {
        // given
        Order order = mock(Order.class);
        HistoryLog log = mock(HistoryLog.class);
        given(log.getOrder()).willReturn(order);
        given(order.getPriceWithOrderDiscount()).willReturn(BigDecimal.valueOf(90));

        // when
        PriceStrategy strategy = new PriceStrategy(price);

        // then
        assertFalse(strategy.filter(log));
    }
}
