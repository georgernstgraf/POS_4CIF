package model;

public class Movie {

    private static Long idCounter = 34L;

    private Long id;
    private String name;
    private double price;
    private char category;

    public Movie(String name, double price, char category) {
        setId( idCounter++ );
        setName(name);
        setPrice(price);
        setCategory(category);
    }

    public Long getId() {
        return id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getCategory() {
        return category;
    }

    public void setCategory(char category) {

        if(category == 'm' || category == 'f' || category == 'c' || category == 'o') {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Die Kategory darf nur m, f, c oder o sein!");
        }
    }

    @Override
    public String toString() {
        String categoryLong = "";
        switch (category) {
            case 'm': categoryLong = "Media"; break;
            case 'f': categoryLong = "Food"; break;
            case 'c': categoryLong = "Cosmetics"; break;
            case 'o': categoryLong = "Office Equipment"; break;
            default:
                throw new IllegalArgumentException("Wert unbekannt!");
        }
        return id + ", " + name + ", " + price + "€, " + categoryLong;
    }
}
