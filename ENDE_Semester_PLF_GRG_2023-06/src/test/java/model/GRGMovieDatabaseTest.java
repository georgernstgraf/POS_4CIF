package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GRGMovieDatabaseTest {

    private static final int CAPACITY = 7;
    private static final Long INIT_ID = 21L;
    private Movie[] testmovies;
    private MovieDatabase mdb;

    @BeforeEach
    void setUp() {
        mdb = new MovieDatabase("Quentin Lovers");
        Movie.resetIdCount();
        testmovies = new Movie[8];
        for (int i = 0; i < testmovies.length; i++) {
            testmovies[i] = new Movie("Testmovie " + i, (i+1) * 1000);
        }
    }

    @Test
    private void testFill() {
        for (int i = 0; i < CAPACITY; i++) {
            mdb.add(testmovies[i]);
            assertEquals((i + 1), mdb.countMovies(), "countMovies: Soll=" + (i));
        }
    }

    @Test
    void testCapacity() {
        testFill();
        try {
            mdb.add(testmovies[CAPACITY]);
            fail("Adding one should not be possible");
        } catch (IllegalArgumentException e) {
            // this is OK
        }
    }

    // -- ADD -------------------------------------------------------------------------------------------------

    @Test
    void testAddNull() {
        try {
            mdb.add(null);
            fail("should not add null!");
        } catch (IllegalArgumentException e) {
            // OIK!
        }
    }

    @Test
    void testAddDoppelt() {
        try {
            mdb.add(testmovies[3]);
            mdb.add(testmovies[3]);
            fail("Konnte doppelt aufnehmen!");
        } catch (IllegalArgumentException e) {
            // OK
        }
    }


    // -- SELL -------------------------------------------------------------------------------------------------

    @Test
    void testRemove() {
        testFill();
        Movie result = mdb.removeById(INIT_ID);
        assertEquals(INIT_ID, result.getId(), "removeById arbeitet nicht richtig! Erwartet Movie1 mit ID 21");
    }

    @Test
    void testRemoveNull() {
        testFill();
        Movie result = mdb.removeById(null);
        assertNull(result, "removeById null nicht geprüft");
    }

    @Test
    void testSellNichtVorhandenesMovie() {
        testFill();
        Movie result = mdb.removeById(-1L);
        assertNull(result, "removeById -1: Movie sollte nicht entfernt werden!");
    }


    // -- COUNT ---------------------------------------------------------------------------------------------------


    // -- SORT ----------------------------------------------------------------------------------------------------
    @Test
    void testSort() {
        testFill();
        Movie[] sorted = mdb.sortByDuration(MovieDatabase.DIRECTION_DESC);
        for (int i = 0; i < sorted.length -1; i++) {
            assertTrue(sorted[i].getDuration() >= sorted[i+1].getDuration(), "sortByDuration: Sortierung fehlerhaft");
        }
    }

    // -- TO STRING ----------------------------------------------------------------------------------------------

    @Test
    void testToString() {
        testFill();
        String expected;
        expected = "Movie-DBbezeichnung: Quentin Lovers\n" +
                "7 Vorhandene Filme:\n" +
                "21: Testmovie 0 (1000.0 sec)\n" +
                "22: Testmovie 1 (2000.0 sec)\n" +
                "23: Testmovie 2 (3000.0 sec)\n" +
                "24: Testmovie 3 (4000.0 sec)\n" +
                "25: Testmovie 4 (5000.0 sec)\n" +
                "26: Testmovie 5 (6000.0 sec)\n" +
                "27: Testmovie 6 (7000.0 sec)\n";
        System.out.println(mdb);
        assertEquals(expected, mdb.toString(), "toString: fehlerhaft");
    }

}
