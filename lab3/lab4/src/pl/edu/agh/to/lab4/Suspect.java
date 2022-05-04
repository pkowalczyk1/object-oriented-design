package pl.edu.agh.to.lab4;

public abstract class Suspect {
    private String name;
    private String surname;
    int age;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String display() {
        return name + " " + surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public abstract boolean canBeAccused();
}
