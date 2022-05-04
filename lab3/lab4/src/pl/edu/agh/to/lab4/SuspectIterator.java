package pl.edu.agh.to.lab4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SuspectIterator implements Iterator<Suspect> {

    private Suspect suspect;
    private final Iterator<? extends Suspect> iterator;

    public SuspectIterator(Iterator<? extends Suspect> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if (iterator.hasNext()) {
            suspect = iterator.next();
            return true;
        }

        suspect = null;
        return false;
    }

    @Override
    public Suspect next() {
        if (suspect != null) {
            return suspect;
        }

        throw new NoSuchElementException("Iterator is empty");
    }
}
