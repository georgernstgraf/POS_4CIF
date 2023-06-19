package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SLMMovieDatabaseTest {

    private static final int CAPACITY = 5;
    private static final Long INIT_ID = 34L;

    private static final Movie P1 = new Movie("Movie" + 1, 1000, 'm');
    private static final Movie P2 = new Movie("Movie" + 2, 236, 'm');
    private static final Movie P3 = new Movie("Movie" + 3, 982, 'm');
    private static final Movie P4 = new Movie("Movie" + 4, 1, 'm');



    private MovieDatabase generate() {
        MovieDatabase mdb = new MovieDatabase("SPG-Movie-DB");
        mdb.add(P1);
        mdb.add(P2);
        mdb.add(P3);
        mdb.add(P4);
        return mdb;
    }


    @Test
    void testCapacity() {
        MovieDatabase mdb = new MovieDatabase("SPG-Movie-DB");
        for(int i = 1; i <= CAPACITY; i++) {
            boolean result = mdb.add( new Movie("Movie" + i, 1000, 'm') );
            Assertions.assertEquals(true, result, "Capacity sollte 5 sein!!!! Ist=" + i);
        }
    }

    // -- ADD -------------------------------------------------------------------------------------------------

    @Test
    void testAddNull() {
        MovieDatabase mdb = new MovieDatabase("SPG-Movie-DB");
        boolean result = mdb.add( null );
        Assertions.assertFalse(result);
    }

    @Test
    void testAddDoppelt() {
        Movie p = new Movie("Movie1", 1000, 'm');
        MovieDatabase mdb = new MovieDatabase("SPG-Movie-DB");

        // Aufnahme
        boolean result = mdb.add( p );
        Assertions.assertTrue(result, "Movie1 sollte aufgenommen werden!");

        // Doppelt
        result = mdb.add( p );
        Assertions.assertFalse(result, "Movie1 sollte NICHT 2x aufgenommen werden");
    }

    @Test
    void testVoll() {
        MovieDatabase mdb = new MovieDatabase("SPG-Movie-DB");
        for(int i = 1; i <= CAPACITY + 1; i++) {
            boolean result = mdb.add( new Movie("Movie" + i, 1000, 'm') );
            if(i <= CAPACITY) {
                Assertions.assertTrue(result, "Zu früh befüllt! Capacity Soll=5 Ist=" + i);
            } else {
                Assertions.assertFalse(result, "Sollte voll sein! Capacity Soll=5 Ist=" + i);
            }
        }
    }


    // -- SELL -------------------------------------------------------------------------------------------------

    @Test
    void testSell() {
        MovieDatabase mdb = generate();
        Movie result = mdb.sellById( INIT_ID );
        Assertions.assertEquals(INIT_ID, result.getId(), "sellById arbeitet nicht richtig! Erwartet Movie1 mit ID 34");
    }

    @Test
    void testSellNull() {
        MovieDatabase mdb = generate();
        Movie result = mdb.sellById( null );
        Assertions.assertNull(result, "sellById null nicht geprüft");
    }

    @Test
    void testSellNichtVorhandenesMovie() {
        MovieDatabase mdb = generate();
        Movie result = mdb.sellById( -1L );
        Assertions.assertNull(result, "sellById -1: Movie sollte nicht entfernt werden!");
    }


    // -- COUNT ---------------------------------------------------------------------------------------------------


    @Test
    void testCount() {
        MovieDatabase mdb = new MovieDatabase("SPG-Movie-DB");
        for(int i = 1; i <= CAPACITY; i++) {
            mdb.add( new Movie("Movie" + i, 1000, 'm') );
            Assertions.assertEquals((i), mdb.countMovies(), "countMovies: Soll=" + (i));
        }
    }


    // -- SORT ----------------------------------------------------------------------------------------------------
    @Test
    void testSort() {
        int[] expected = new int[]{1, 236, 1000};
        MovieDatabase movieDatabase = generate();
        movieDatabase.sellById(INIT_ID + 2);
        Movie[] sorted = movieDatabase.sortByPrice();
        for(int i = 0; i < sorted.length; i++) {
            System.out.println( sorted[i] );
            if(sorted[i] == null) {
                continue;
            }
            Assertions.assertEquals(expected[i], sorted[i].getPrice(), "sortByPrice: Sortierung fehlerhaft");
        }
    }

    // -- TO STRING ----------------------------------------------------------------------------------------------

    @Test
    void testToString() {
        MovieDatabase movieDatabase = generate();
        String expected = "Movie-DBbezeichnung: SPG-Movie-DB\n" +
                            "Vorhandene ProdukteNL34, Movie1, 1000.0€, Media\n" +
                            "35, Movie2, 236.0€, Media\n" +
                            "36, Movie3, 982.0€, Media\n" +
                            "37, Movie4, 1.0€, Media\n" +
                            "null\n";
        System.out.println(movieDatabase);
        Assertions.assertEquals(expected, movieDatabase.toString(), "toString: fehlerhaft");
    }

}
