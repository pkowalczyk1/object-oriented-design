package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class NameStrategyTest {

    private final String name = "Kowalski";

    @Test
    public void testCorrectName() {
        // given
        Order order = mock(Order.class);
        given(order.getShipment()).willReturn(mock(Shipment.class));
        given(order.getShipment().getRecipientAddress()).willReturn(mock(Address.class));
        given(order.getShipment().getRecipientAddress().getName()).willReturn(name);
        HistoryLog log = mock(HistoryLog.class);
        given(log.getOrder()).willReturn(order);

        // when
        NameStrategy strategy = new NameStrategy(name);

        // then
        assertTrue(strategy.filter(log));
    }

    @Test
    public void testIncorrectName() {
        // given
        Order order = mock(Order.class);
        given(order.getShipment()).willReturn(mock(Shipment.class));
        given(order.getShipment().getRecipientAddress()).willReturn(mock(Address.class));
        given(order.getShipment().getRecipientAddress().getName()).willReturn("Wrong");
        HistoryLog log = mock(HistoryLog.class);
        given(log.getOrder()).willReturn(order);

        // when
        NameStrategy strategy = new NameStrategy(name);

        // then
        assertFalse(strategy.filter(log));
    }
}
