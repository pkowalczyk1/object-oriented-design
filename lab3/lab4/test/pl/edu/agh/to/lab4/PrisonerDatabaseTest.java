package pl.edu.agh.to.lab4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrisonerDatabaseTest {

    private PrisonersDataProvider prisonersDataProvider = new PrisonersDataProvider();

    @Test
    public void testThereAreThreeJails() {
        prisonersDataProvider.addPrisoner("Wiezienie krakowskie", new Prisoner("Jan", "Kowalski", "87080452357", 2005, 7, 30));
        prisonersDataProvider.addPrisoner("Wiezienie krakowskie", new Prisoner("Anita", "Wiercipieta", "84080452357", 2009, 3, 52));
        prisonersDataProvider.addPrisoner("Wiezienie krakowskie", new Prisoner("Janusz", "Zlowieszczy", "92080445657", 2001, 10, 23));
        prisonersDataProvider.addPrisoner("Wiezienie przedmiejskie", new Prisoner("Janusz", "Zamkniety", "802104543357", 2010, 5, 54));
        prisonersDataProvider.addPrisoner("Wiezienie przedmiejskie", new Prisoner("Adam", "Future", "880216043357", 2020, 5, 20));
        prisonersDataProvider.addPrisoner("Wiezienie przedmiejskie", new Prisoner("Zbigniew", "Nienajedzony", "90051452335", 2011, 1, 33));
        prisonersDataProvider.addPrisoner("Wiezienie centralne", new Prisoner("Jan", "Przedziwny", "91103145223", 2009, 4, 60));
        prisonersDataProvider.addPrisoner("Wiezienie centralne", new Prisoner("Janusz", "Podejrzany", "85121212456", 2012, 1, 25));
        assertEquals(3, prisonersDataProvider.getAllPrisons().size());
    }
}
