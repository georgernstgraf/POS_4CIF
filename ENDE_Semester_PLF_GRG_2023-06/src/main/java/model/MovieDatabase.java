package model;

public class MovieDatabase {

    public static final boolean DIRECTION_ASC = false;
    public static final boolean DIRECTION_DESC = true;
    public static final int CAPACITY = 7;
    private static final String NL = "\n";
    private final Movie[] movies;
    private String name;

    public MovieDatabase(String name) {
        setName(name);
        this.movies = null; // TODO
    }

    // Kann statisch und public implementiert werden, da Helper-Method
    public static Movie[] copy(Movie[] movies) {
        if (movies == null) throw new IllegalArgumentException("Movies darf nicht null sein!");
        Movie[] copy = new Movie[movies.length];
        // TODO
        return copy;
    }

    public String getName() {
        return null; // TODO
    }

    // -- METHODS ----

    public void setName(String name) {
        // TODO
    }

    public void add(Movie movie) {
        // Check null
        // Check doppelt
        // TODO einfügen wenn wo null ist, sonst Exception werfen! }
    }

    public Movie removeById(Long id) {
        // TODO
        return null;
    }

    public Movie[] sortByDuration(boolean direction) {
        Movie[] sorted = MovieDatabase.copy(movies);
        // TODO sortieren
        return sorted;
    }

    private void swap(Movie[] movies, int i1, int i2) {
        // TODO
    }

    public int countMovies() {
        // TODO
        return 0;
    }


    @Override
    public String toString() {

        // TODO String befüllen lt. Angabe
        return "";
    }
}
