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
        this.movies = new Movie[CAPACITY];
    }

    // Kann statisch und public implementiert werden, da Helper-Method
    public static Movie[] copy(Movie[] movies) {
        if (movies == null) throw new IllegalArgumentException("Movies darf nicht null sein!");
        Movie[] copy = new Movie[movies.length];
        System.arraycopy(movies, 0, copy, 0, movies.length);
        return copy;
    }

    public String getName() {
        return name;
    }

    // -- METHODS ----

    public void setName(String name) {
        this.name = name;
    }

    public void add(Movie movie) {
        // Check null
        if (movie == null) throw new IllegalArgumentException("Add: Movie darf nicht null sein!");

        // Check doppelt
        for (Movie p : movies) {
            if (p != null && p.getId().equals(movie.getId())) {
                throw new IllegalArgumentException("Add: Movie ist schon vorhanden!");
            }
        }

        for (int i = 0; i < movies.length; i++) {
            if (movies[i] == null) {
                movies[i] = movie;
                return;
            }
        }
        throw new IllegalArgumentException("Kein Platz mehr!");
    }

    public Movie removeById(Long id) {
        if (id == null) return null;
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] == null) {
                continue;
            }
            Movie temp = movies[i];
            if (movies[i].getId() == id) {
                movies[i] = null;
                return temp;
            }
        }
        return null;
    }

    public Movie[] sortByDuration(boolean direction) {
        Movie[] sorted = MovieDatabase.copy(movies);
        int length = sorted.length;
        boolean needswap;
        boolean didChange = true;

//        for(int j = 0; j < length - 1; j++) {
        while (didChange) {  // bonus
            didChange = false;
            for (int i = 0; i < length - 1; i++) {
                Movie p1 = sorted[i];
                Movie p2 = sorted[i + 1];
                if (p2 == null) continue;
                if (direction == DIRECTION_ASC)
                needswap = p1 == null || p1.getDuration() >= p2.getDuration();
                else needswap = p1 == null || p1.getDuration() <= p2.getDuration();
                if (needswap)
                {
                    swap(sorted, i, i + 1);
                    didChange = true;
                }
            }
        }
        return sorted;
    }

    private void swap(Movie[] movies, int i1, int i2) {
        if (movies == null) throw new IllegalArgumentException("Movies darf nicht null sein!");

        Movie temp = movies[i1];
        movies[i1] = movies[i2];
        movies[i2] = temp;
    }

    public int countMovies() {
        int count = 0;
        for (Movie p : movies) {
            count += (p == null ? 0 : 1);
        }
        return count;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Movie-DBbezeichnung: ").append(name).append(NL);
        sb.append(this.countMovies() + " Vorhandene Filme:").append(NL);
        for (Movie p : movies) {
            if (p == null) continue;
            sb.append(p).append(NL);
        }

        return sb.toString();
    }
}
