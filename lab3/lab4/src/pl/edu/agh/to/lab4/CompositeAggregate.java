package pl.edu.agh.to.lab4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeAggregate {

    private final List<SuspectAggregate> aggregates;

    public CompositeAggregate(List<SuspectAggregate> aggregates) {
        this.aggregates = aggregates;
    }

    public Iterator<Suspect> iterator() {
        List<Suspect> allPeople = new ArrayList<>();
        for (SuspectAggregate aggregate : aggregates) {
            Iterator<Suspect> iterator = aggregate.iterator();
            while (iterator.hasNext()) {
                Suspect suspect = iterator.next();
                allPeople.add(suspect);
            }
        }

        return allPeople.iterator();
    }
}
