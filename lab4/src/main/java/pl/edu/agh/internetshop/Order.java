package pl.edu.agh.internetshop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;


public class Order {
    private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
	private final UUID id;
    private final List<Product> products;
    private boolean paid;
    private Shipment shipment;
    private ShipmentMethod shipmentMethod;
    private PaymentMethod paymentMethod;
    private Discount discount;

    public Order(List<Product> products) {
        this.products = Objects.requireNonNull(products);
        id = UUID.randomUUID();
        paid = false;
        discount = new Discount(0);
    }

    public Order(List<Product> products, Discount discount) {
        this.products = Objects.requireNonNull(products);
        id = UUID.randomUUID();
        paid = false;
        this.discount = discount;
    }

    public UUID getId() {
        return id;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isSent() {
        return shipment != null && shipment.isShipped();
    }

    public boolean isPaid() { return paid; }

    public Shipment getShipment() {
        return shipment;
    }

    public BigDecimal getPriceWithoutDiscounts() {
        return getPriceWithFunction(Product::getPriceWithoutDiscount);
    }

    public BigDecimal getPriceWithProductDiscounts() {
        return getPriceWithFunction(Product::getPriceWithDiscount);
    }

    private BigDecimal getPriceWithFunction(Function<Product, BigDecimal> function) {
        return products.stream()
                .map(function)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public BigDecimal getPriceWithOrderDiscount() {
        return discount.applyDiscount(getPriceWithProductDiscounts());
    }

    public BigDecimal getPriceWithTaxes() {
        return getPriceWithOrderDiscount().multiply(TAX_VALUE).setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY);
    }

    public List<Product> getProducts() {
        return products;
    }

    public ShipmentMethod getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public void send() {
        boolean sentSuccessful = getShipmentMethod().send(shipment, shipment.getSenderAddress(), shipment.getRecipientAddress());
        shipment.setShipped(sentSuccessful);
    }

    public void pay(MoneyTransfer moneyTransfer) {
        moneyTransfer.setCommitted(getPaymentMethod().commit(moneyTransfer));
        paid = moneyTransfer.isCommitted();
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
