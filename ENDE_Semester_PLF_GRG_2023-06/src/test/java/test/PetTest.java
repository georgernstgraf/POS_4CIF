package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PetTest {

    @Test
    void testNull() {

        try {
            Pet p = new Pet(null);
            Assertions.fail("Nullprüfung nicht vorhanden!");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Bitte geben Sie einen Wert für den Namen ein.", e.getMessage(), "Falsche Fehlermeldung!");
        }
    }

}
