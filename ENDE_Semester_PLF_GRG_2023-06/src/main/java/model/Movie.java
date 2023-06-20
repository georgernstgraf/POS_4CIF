package model;

public class Movie {

    private static Long idCounter = 21L;

    private Long id;
    private String name;
    private double duration;
        public Movie(String name, double duration) {
    // TODO
    }

    public static void resetIdCount () {
            // bitte so lassen, ist relevant für die Tests
            idCounter = 21L;
    }
    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        // TODO
    }

    public String getName() {
        // TODO
        return null;
    }

    public void setName(String name) {
        // TODO
    }

    public double getDuration() {
        // TODO
            return 0.0;
    }

    public void setDuration(double duration) {
        // TODO
    }

    @Override
    public String toString() {
        return null;
        // TODO
    }
}
