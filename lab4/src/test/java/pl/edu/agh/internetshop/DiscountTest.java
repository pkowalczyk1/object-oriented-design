package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class DiscountTest {

    @Test
    public void testCreateCorrectDiscount() {
        assertDoesNotThrow(() -> new Discount(50));
    }

    @Test
    public void testCreateTooBigDiscount() {
        assertThrows(IllegalArgumentException.class, () -> new Discount(150));
    }

    @Test
    public void testCreateTooSmallDiscount() {
        assertThrows(IllegalArgumentException.class, () -> new Discount(-1));
    }

    @Test
    public void testZeroDiscountApply() {
        // given
        Discount discount = new Discount(0);
        BigDecimal expectedValue = BigDecimal.valueOf(5);

        // when
        BigDecimal actualValue = discount.applyDiscount(BigDecimal.valueOf(5));

        // then
        assertBigDecimalCompareValue(expectedValue, actualValue);
    }

    @Test
    public void testFullDiscountApply() {
        // given
        Discount discount = new Discount(100);
        BigDecimal expectedValue = BigDecimal.valueOf(0);

        // when
        BigDecimal actualValue = discount.applyDiscount(BigDecimal.valueOf(5));

        // then
        assertBigDecimalCompareValue(expectedValue, actualValue);
    }

    @Test
    public void testDiscountApplyWithoutRoundUp() {
        // given
        Discount discount = new Discount(50);
        BigDecimal expectedValue = BigDecimal.valueOf(3);

        // when
        BigDecimal actualValue = discount.applyDiscount(BigDecimal.valueOf(6));

        // then
        assertBigDecimalCompareValue(expectedValue, actualValue);
    }

    @Test
    public void testDiscountApplyWithRoundUp() {
        // given
        Discount discount = new Discount(5);
        BigDecimal expectedValue = BigDecimal.valueOf(6.37);

        // when
        BigDecimal actualValue = discount.applyDiscount(BigDecimal.valueOf(6.7));

        // then
        assertBigDecimalCompareValue(expectedValue, actualValue);
    }
}
