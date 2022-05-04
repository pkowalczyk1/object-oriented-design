package pl.edu.agh.to.lab4;

public class AgeStrategy implements SearchStrategy {

    private final int age;

    public AgeStrategy(int age) {
        this.age = age;
    }

    public boolean filter(Suspect suspect) {
        return suspect.getAge() == age && suspect.canBeAccused();
    }
}
