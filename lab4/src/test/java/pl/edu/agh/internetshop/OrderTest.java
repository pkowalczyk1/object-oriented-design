package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class OrderTest {

	private Order getOrderWithMockedProductsOfGivenPrice(int numberOfProducts, double price) {
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < numberOfProducts; i++) {
			Product newProduct = mock(Product.class);
			given(newProduct.getPriceWithoutDiscount()).willReturn(BigDecimal.valueOf(price));
			given(newProduct.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(price));
			products.add(newProduct);
		}

		return new Order(products);
	}

	@Test
	public void testGetProductsThroughOrder() {
		// given
		Product expectedProduct1 = mock(Product.class);
		Product expectedProduct2 = mock(Product.class);
		Order order = new Order(List.of(expectedProduct1, expectedProduct2));

		// when
		List<Product> actualProducts = order.getProducts();

		// then
		assertEquals(List.of(expectedProduct1, expectedProduct2), actualProducts);
	}

	@Test
	public void testGetProductsThroughOrderWithoutProducts() {
		// given
		Order order = new Order(Collections.emptyList());

		// when
		List<Product> actualProducts = order.getProducts();

		// then
		assertEquals(actualProducts.size(), 0);
	}

	@Test
	public void testSetOrderProductsAsNull() {
		// given

		// when

		//then
		assertThrows(NullPointerException.class, () -> new Order(null));
	}

	@Test
	public void testSetShipment() throws Exception {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);
		Shipment expectedShipment = mock(Shipment.class);

		// when
		order.setShipment(expectedShipment);

		// then
		assertSame(expectedShipment, order.getShipment());
	}

	@Test
	public void testShipmentWithoutSetting() throws Exception {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);

		// when

		// then
		assertNull(order.getShipment());
	}

	@Test
	public void testGetPriceWithMultipleProductsWithoutDiscounts() throws Exception {
		// given
		BigDecimal expectedProductPrice = BigDecimal.valueOf(1000);
		Order order = getOrderWithMockedProductsOfGivenPrice(4, 250);

		// when
		BigDecimal actualProductPrice = order.getPriceWithoutDiscounts();

		// then
		assertBigDecimalCompareValue(expectedProductPrice, actualProductPrice);
	}

	@Test
	public void testGetPriceWithMultipleProductsWithDiscounts() throws Exception {
		// given
		BigDecimal expectedProductPrice = BigDecimal.valueOf(1000);
		Order order = getOrderWithMockedProductsOfGivenPrice(4, 250);

		// when
		BigDecimal actualProductPrice = order.getPriceWithProductDiscounts();

		// then
		assertBigDecimalCompareValue(expectedProductPrice, actualProductPrice);
	}

	@Test
	public void testGetPriceWithoutProductsWithoutDiscounts() {
		// given
		Order order = new Order(Collections.emptyList());

		// when
		BigDecimal actualPrice = order.getPriceWithoutDiscounts();

		// then
		assertBigDecimalCompareValue(BigDecimal.valueOf(0), actualPrice);
	}

	@Test
	public void testGetPriceWithoutProductsWithDiscounts() {
		// given
		Order order = new Order(Collections.emptyList());

		// when
		BigDecimal actualPrice = order.getPriceWithProductDiscounts();

		// then
		assertBigDecimalCompareValue(BigDecimal.valueOf(0), actualPrice);
	}

	@Test
	public void testGetPriceWithZeroDiscount() {
		// given
		Product product1 = mock(Product.class);
		given(product1.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(4));
		Product product2 = mock(Product.class);
		given(product2.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(5));
		Order order = new Order(List.of(product1, product2), new Discount(0));
		BigDecimal expectedValue = BigDecimal.valueOf(9);

		// when
		BigDecimal actualValue = order.getPriceWithOrderDiscount();

		// then
		assertBigDecimalCompareValue(expectedValue, actualValue);
	}

	@Test
	public void testGetPriceWithFullDiscount() {
		// given
		Product product1 = mock(Product.class);
		given(product1.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(4));
		Product product2 = mock(Product.class);
		given(product2.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(5));
		Order order = new Order(List.of(product1, product2), new Discount(100));
		BigDecimal expectedValue = BigDecimal.valueOf(0);

		// when
		BigDecimal actualValue = order.getPriceWithOrderDiscount();

		// then
		assertBigDecimalCompareValue(expectedValue, actualValue);
	}

	@Test
	public void testGetPriceWithNormalDiscount() {
		// given
		Product product1 = mock(Product.class);
		given(product1.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(3));
		Product product2 = mock(Product.class);
		given(product2.getPriceWithDiscount()).willReturn(BigDecimal.valueOf(3));
		Order order = new Order(List.of(product1, product2), new Discount(5)); // without discounts: 6
		BigDecimal expectedValue = BigDecimal.valueOf(5.7); // 6 * 0.95 == 5.7

		// when
		BigDecimal actualValue = order.getPriceWithOrderDiscount();

		// then
		assertBigDecimalCompareValue(actualValue, expectedValue);
	}

	@Test
	public void testPriceWithTaxesWithoutRoundUp() {
		// given

		// when
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 2); // 2 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(2.46)); // 2.46 PLN
	}

	@Test
	public void testPriceWithTaxesWithRoundDown() {
		// given

		// when
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 0.01); // 0.01 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.01)); // 0.01 PLN
																							
	}

	@Test
	public void testPriceWithTaxesWithRoundUp() {
		// given

		// when
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 0.03); // 0.03 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.04)); // 0.04 PLN
																							
	}

	@Test
	public void testSetShipmentMethod() {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);
		ShipmentMethod surface = mock(SurfaceMailBus.class);

		// when
		order.setShipmentMethod(surface);

		// then
		assertSame(surface, order.getShipmentMethod());
	}

	@Test
	public void testSending() {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);
		SurfaceMailBus surface = mock(SurfaceMailBus.class);
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when
		order.setShipmentMethod(surface);
		order.setShipment(shipment);
		order.send();

		// then
		assertTrue(order.isSent());
	}

	@Test
	public void testIsSentWithoutSending() {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when

		// then
		assertFalse(order.isSent());
	}

	@Test
	public void testWhetherIdExists() throws Exception {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);

		// when

		// then
		assertNotNull(order.getId());
	}

	@Test
	public void testSetPaymentMethod() throws Exception {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);

		// when
		order.setPaymentMethod(paymentMethod);

		// then
		assertSame(paymentMethod, order.getPaymentMethod());
	}

	@Test
	public void testPaying() throws Exception {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);
		given(paymentMethod.commit(any(MoneyTransfer.class))).willReturn(true);
		MoneyTransfer moneyTransfer = mock(MoneyTransfer.class);
		given(moneyTransfer.isCommitted()).willReturn(true);

		// when
		order.setPaymentMethod(paymentMethod);
		order.pay(moneyTransfer);

		// then
		assertTrue(order.isPaid());
	}

	@Test
	public void testIsPaidWithoutPaying() throws Exception {
		// given
		Order order = getOrderWithMockedProductsOfGivenPrice(1, 1);

		// when

		// then
		assertFalse(order.isPaid());
	}
}
