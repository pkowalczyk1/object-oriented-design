package pl.edu.agh.to.lab4;

public class CracovCitizen extends Suspect {

    public CracovCitizen(String name, String surname, int age) {
        this.age = age;
        setName(name);
        setSurname(surname);
    }

    @Override
    public boolean canBeAccused() {
        return age > 18;
    }
}
