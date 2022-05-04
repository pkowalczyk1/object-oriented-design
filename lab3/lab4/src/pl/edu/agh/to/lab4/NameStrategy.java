package pl.edu.agh.to.lab4;

public class NameStrategy implements SearchStrategy {

    private final String name;

    public NameStrategy(String name) {
        this.name = name;
    }

    public boolean filter(Suspect suspect) {
        return suspect.getName().equals(name) && suspect.canBeAccused();
    }
}
