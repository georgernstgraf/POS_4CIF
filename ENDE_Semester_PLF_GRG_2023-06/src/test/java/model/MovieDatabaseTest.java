package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieDatabaseTest {

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
            testmovies[i] = new Movie("Testmovie " + i, (i + 1) * 1000);
        }
    }

    @Test
    private void testFill() {
        // TODO
    }

    @Test
    void testCapacity() {
        // TODO
    }

    // -- ADD -------------------------------------------------------------------------------------------------

    @Test
    void testAddNull() {
        // TODO
    }

    @Test
    void testAddDoppelt() {
        // TODO
    }


    // -- SELL -------------------------------------------------------------------------------------------------

    @Test
    void testRemove() {
      // TODO
         }

    @Test
    void testRemoveNull() {
       // TODO
        }

    @Test
    void testRemoveNichtVorhandenesMovie() {
       // TODO
        }


    // -- COUNT ---------------------------------------------------------------------------------------------------
@Test
void testCount() {
        // TODO
}

    // -- SORT ----------------------------------------------------------------------------------------------------
    @Test
    void testSort() {
      // TODO
    }

    // -- TO STRING ----------------------------------------------------------------------------------------------

    @Test
    void testToString() {
     // TODO
    }

}
