package model;

public class Movie {

    private static Long idCounter = 21L;

    private Long id;
    private String name;
    private double duration;
        public Movie(String name, double duration) {
        setId( idCounter++ );
        setName(name);
        setDuration(duration);
    }

    public static void resetIdCount () {
            idCounter = 21L;
    }
    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%d: %s (%s sec)", this.id, name, duration);
    }
}
