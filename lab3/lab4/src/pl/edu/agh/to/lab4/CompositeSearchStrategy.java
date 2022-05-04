package pl.edu.agh.to.lab4;

import java.util.ArrayList;
import java.util.List;

public class CompositeSearchStrategy {

    private List<SearchStrategy> strategies = new ArrayList<>();

    public CompositeSearchStrategy(List<SearchStrategy> strategies) {
        this.strategies = strategies;
    }

    public boolean applyFilters(Suspect suspect) {
        for (SearchStrategy strategy : strategies) {
            if (!strategy.filter(suspect)) {
                return false;
            }
        }

        return true;
    }
}
