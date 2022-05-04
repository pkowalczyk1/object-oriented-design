package pl.edu.agh.internetshop;

import java.util.List;

public class CompositeSearchStrategy implements SearchStrategy {

    private List<SearchStrategy> strategies;

    public CompositeSearchStrategy(List<SearchStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean filter(HistoryLog log) {
        for (SearchStrategy strategy : strategies) {
            if (!strategy.filter(log)) {
                return false;
            }
        }

        return true;
    }
}
