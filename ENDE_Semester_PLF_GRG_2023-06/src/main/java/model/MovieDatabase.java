package model;

public class MovieDatabase {

    private static final String NL = "\n";
    private static final int DIRECTION_ASC = 5;
    private static final int DIRECTION_DESC = 8;
    public static final int CAPACITY = 5;

    private String name;
    private Movie[] movies;

    public MovieDatabase(String name) {
        setName(name);
        this.movies = new Movie[CAPACITY];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // -- METHODS ----

    public boolean add(Movie movie) {
        // Check null
        if(movie == null)
            return false;

        // Check doppelt
        for(Movie p : movies) {
            if(p != null && p.getId() != null && p.getId().equals( movie.getId() )) {
                return false;
            }
        }

        for(int i = 0; i < movies.length; i++) {
            Movie temp = movies[i];

            if(temp == null) {
                movies[i] = movie;
                return true;
            }
        }

        return false;
    }


    public Movie sellById(Long id) {
        if(id == null)
            return null;


        for(int i = 0; i < movies.length; i++) {
            Movie temp = movies[i];
            if( temp != null && id.equals( temp.getId() ) ) {
                movies[i] = null;
                return temp;
            }
        }

        return null;
    }



    public Movie[] sortByPrice() {
        Movie[] sorted = MovieDatabase.copy( movies );
        int length = sorted.length;

        boolean isSorted = true;

//        for(int j = 0; j < length - 1; j++) {
        while (isSorted) {  // bonus
            isSorted = false;
            for(int i = 0; i < length - 1; i++) {
                Movie p1 = sorted[i];
                Movie p2 = sorted[i + 1];
                if(p2 == null)
                    continue;
                if(p1 == null || p1.getPrice() > p2.getPrice()) {
                    swap(sorted, i, i+1);
                    isSorted = true;
                }
            }
        }
        return sorted;
    }

    private void swap(Movie[] movies, int i1, int i2) {
        if(movies == null)
            throw new IllegalArgumentException("Movies darf nicht null sein!");

        Movie temp = movies[i1];
        movies[i1] = movies[i2];
        movies[i2] = temp;
    }


    // Kann statisch und public implementiert werden, da Helper-Method
    public static Movie[] copy(Movie[] movies) {
        if(movies == null)
            throw new IllegalArgumentException("Movies darf nicht null sein!");
        Movie[] copy = new Movie[movies.length];
        for(int i = 0; i < movies.length; i++) {
            copy[i] = movies[i];
        }

        return copy;
    }


    public int countMovies() {
        int count = 0;
        for(Movie p : movies) {
            count += (p == null ? 0 : 1);
        }
        return count;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Movie-DBbezeichnung: ").append(name).append(NL);
        sb.append("Vorhandene Produkte").append("NL");
        for(Movie p : movies) {
            sb.append(p).append(NL);
        }

        return sb.toString();
    }
}
