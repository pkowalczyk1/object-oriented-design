package pl.edu.agh.internetshop;

import java.util.Objects;

public class NameStrategy implements SearchStrategy {

    private String name;

    public NameStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(HistoryLog log) {
        return Objects.equals(log.getOrder().getShipment().getRecipientAddress().getName(), name);
    }
}
