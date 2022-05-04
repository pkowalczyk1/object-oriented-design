package pl.edu.agh.to.lab4;

import java.util.*;

public class Finder {

    private final CompositeAggregate data;
    private final CompositeSearchStrategy strategies;

    public Finder(CompositeAggregate data, CompositeSearchStrategy strategies) {
        this.data = data;
        this.strategies = strategies;
    }

    public void displayAllSuspects() {
        List<Suspect> suspects = new ArrayList<>();

        Iterator<Suspect> iterator = data.iterator();
        while (iterator.hasNext()) {
            Suspect suspect = iterator.next();
            if (strategies.applyFilters(suspect)) {
                suspects.add(suspect);
            }
            if (suspects.size() >= 10) {
                break;
            }
        }

        System.out.println("Znalazlem " + suspects.size() + " pasujacych podejrzanych!");

        for (Suspect n : suspects) {
            System.out.println(n.display());
        }
    }
}
