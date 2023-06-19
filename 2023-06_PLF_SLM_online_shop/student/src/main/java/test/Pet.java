package test;

public class Pet {

    private String name;

    public Pet(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        if(name == null)
            throw new IllegalArgumentException("Bitte geben Sie einen Wert für den Namen ein.");

        if(name.length() < 5)
            throw new IllegalArgumentException("Der Name muss mindestens 5 Zeichen lang sein.");

        this.name = name;
    }
}
