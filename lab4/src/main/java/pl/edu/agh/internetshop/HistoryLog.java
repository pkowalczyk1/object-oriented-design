package pl.edu.agh.internetshop;

public class HistoryLog {

    private Order order;

    public HistoryLog(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
