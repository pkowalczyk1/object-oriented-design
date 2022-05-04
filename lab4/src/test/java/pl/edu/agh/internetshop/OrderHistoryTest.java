package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OrderHistoryTest {

    @Test
    public void testAcceptAllLogs() {
        // given
        HistoryLog log1 = mock(HistoryLog.class);
        HistoryLog log2 = mock(HistoryLog.class);
        HistoryLog log3 = mock(HistoryLog.class);
        SearchStrategy strategy = mock(SearchStrategy.class);
        given(strategy.filter(log1)).willReturn(true);
        given(strategy.filter(log2)).willReturn(true);
        given(strategy.filter(log3)).willReturn(true);
        List<HistoryLog> expectedResult = List.of(log1, log2, log3);

        // when
        OrderHistory orderHistory = new OrderHistory(List.of(log1, log2, log3));

        // then
        assertEquals(orderHistory.search(strategy), expectedResult);
    }

    @Test
    public void testAcceptPartOfLogs() {
        // given
        HistoryLog log1 = mock(HistoryLog.class);
        HistoryLog log2 = mock(HistoryLog.class);
        HistoryLog log3 = mock(HistoryLog.class);
        SearchStrategy strategy = mock(SearchStrategy.class);
        given(strategy.filter(log1)).willReturn(true);
        given(strategy.filter(log2)).willReturn(false);
        given(strategy.filter(log3)).willReturn(true);
        List<HistoryLog> expectedResult = List.of(log1, log3);

        // when
        OrderHistory orderHistory = new OrderHistory(List.of(log1, log2, log3));

        // then
        assertEquals(orderHistory.search(strategy), expectedResult);
    }

    @Test
    public void testAcceptNoLogs() {
        // given
        HistoryLog log1 = mock(HistoryLog.class);
        HistoryLog log2 = mock(HistoryLog.class);
        HistoryLog log3 = mock(HistoryLog.class);
        SearchStrategy strategy = mock(SearchStrategy.class);
        given(strategy.filter(log1)).willReturn(false);
        given(strategy.filter(log2)).willReturn(false);
        given(strategy.filter(log3)).willReturn(false);
        List<HistoryLog> expectedResult = Collections.emptyList();

        // when
        OrderHistory orderHistory = new OrderHistory(List.of(log1, log2, log3));

        // then
        assertEquals(orderHistory.search(strategy), expectedResult);
    }

    @Test
    public void testGetLogs() {
        // given
        HistoryLog log1 = mock(HistoryLog.class);
        HistoryLog log2 = mock(HistoryLog.class);
        HistoryLog log3 = mock(HistoryLog.class);
        List<HistoryLog> expectedResult = List.of(log1, log2, log3);

        // when
        OrderHistory orderHistory = new OrderHistory(List.of(log1, log2, log3));

        // then
        assertEquals(orderHistory.getLogs(), expectedResult);
    }
}
