package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

import java.math.BigDecimal;


public class ProductTest {

	
    private static final String NAME = "Mr. Sparkle";
    private static final BigDecimal PRICE = BigDecimal.valueOf(1);

	@Test
    public void testProductName() throws Exception{
        //given
    	
        // when
        Product product = new Product(NAME, PRICE);
        
        // then
        assertEquals(NAME, product.getName());
    }
    
    @Test
    public void testProductPrice() throws Exception{
        //given
    	
        // when
        Product product = new Product(NAME, PRICE);
        
        // then
        assertBigDecimalCompareValue(product.getPriceWithoutDiscount(), PRICE);
    }

    @Test
    public void testGetPriceWithZeroDiscount() {
        // given

        // when
        Product product = new Product(NAME, PRICE, new Discount(0));

        // then
        assertBigDecimalCompareValue(product.getPriceWithDiscount(), PRICE);
    }

    @Test
    public void testGetPriceWithFullDiscount() {
        // given

        // when
        Product product = new Product(NAME, PRICE, new Discount(100));

        // then
        assertBigDecimalCompareValue(product.getPriceWithDiscount(), BigDecimal.valueOf(0));
    }

    @Test
    public void testGetPriceWithNormalDiscount() {
        // given

        // when
        Product product = new Product(NAME, PRICE, new Discount(50));

        // then
        assertBigDecimalCompareValue(product.getPriceWithDiscount(), BigDecimal.valueOf(0.5));
    }
}