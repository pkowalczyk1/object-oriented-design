package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompositeStrategyTest {

    @Test
    public void testAcceptLog() {
        // given
        HistoryLog log = mock(HistoryLog.class);
        SearchStrategy strategy1 = mock(SearchStrategy.class);
        SearchStrategy strategy2 = mock(SearchStrategy.class);
        SearchStrategy strategy3 = mock(SearchStrategy.class);
        when(strategy1.filter(anyObject())).thenReturn(true);
        when(strategy2.filter(anyObject())).thenReturn(true);
        when(strategy3.filter(anyObject())).thenReturn(true);

        // when
        CompositeSearchStrategy compositeStrategy = new CompositeSearchStrategy(List.of(strategy1, strategy2, strategy3));

        // then
        assertTrue(compositeStrategy.filter(log));
    }

    @Test
    public void testIgnoreLog() {
        // given
        HistoryLog log = mock(HistoryLog.class);
        SearchStrategy strategy1 = mock(SearchStrategy.class);
        SearchStrategy strategy2 = mock(SearchStrategy.class);
        SearchStrategy strategy3 = mock(SearchStrategy.class);
        when(strategy1.filter(anyObject())).thenReturn(true);
        when(strategy2.filter(anyObject())).thenReturn(false);
        when(strategy3.filter(anyObject())).thenReturn(true);

        // when
        CompositeSearchStrategy compositeStrategy = new CompositeSearchStrategy(List.of(strategy1, strategy2, strategy3));

        // then
        assertFalse(compositeStrategy.filter(log));
    }
}
