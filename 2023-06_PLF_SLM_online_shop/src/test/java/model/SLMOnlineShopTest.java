package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SLMOnlineShopTest {

    private static final int CAPACITY = 5;
    private static final Long INIT_ID = 34L;

    private static final Product P1 = new Product("Product" + 1, 1000, 'm');
    private static final Product P2 = new Product("Product" + 2, 236, 'm');
    private static final Product P3 = new Product("Product" + 3, 982, 'm');
    private static final Product P4 = new Product("Product" + 4, 1, 'm');



    private OnlineShop generate() {
        OnlineShop shop = new OnlineShop("SPG-Shop");
        shop.add(P1);
        shop.add(P2);
        shop.add(P3);
        shop.add(P4);
        return shop;
    }


    @Test
    void testCapacity() {
        OnlineShop shop = new OnlineShop("SPG-Shop");
        for(int i = 1; i <= CAPACITY; i++) {
            boolean result = shop.add( new Product("Product" + i, 1000, 'm') );
            Assertions.assertEquals(true, result, "Capacity sollte 5 sein!!!! Ist=" + i);
        }
    }

    // -- ADD -------------------------------------------------------------------------------------------------

    @Test
    void testAddNull() {
        OnlineShop shop = new OnlineShop("SPG-Shop");
        boolean result = shop.add( null );
        Assertions.assertFalse(result);
    }

    @Test
    void testAddDoppelt() {
        Product p = new Product("Product1", 1000, 'm');
        OnlineShop shop = new OnlineShop("SPG-Shop");

        // Aufnahme
        boolean result = shop.add( p );
        Assertions.assertTrue(result, "Product1 sollte aufgenommen werden!");

        // Doppelt
        result = shop.add( p );
        Assertions.assertFalse(result, "Product1 sollte NICHT 2x aufgenommen werden");
    }

    @Test
    void testVoll() {
        OnlineShop shop = new OnlineShop("SPG-Shop");
        for(int i = 1; i <= CAPACITY + 1; i++) {
            boolean result = shop.add( new Product("Product" + i, 1000, 'm') );
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
        OnlineShop shop = generate();
        Product result = shop.sellById( INIT_ID );
        Assertions.assertEquals(INIT_ID, result.getId(), "sellById arbeitet nicht richtig! Erwartet Product1 mit ID 34");
    }

    @Test
    void testSellNull() {
        OnlineShop shop = generate();
        Product result = shop.sellById( null );
        Assertions.assertNull(result, "sellById null nicht geprüft");
    }

    @Test
    void testSellNichtVorhandenesProduct() {
        OnlineShop shop = generate();
        Product result = shop.sellById( -1L );
        Assertions.assertNull(result, "sellById -1: Product sollte nicht entfernt werden!");
    }


    // -- COUNT ---------------------------------------------------------------------------------------------------


    @Test
    void testCount() {
        OnlineShop shop = new OnlineShop("SPG-Shop");
        for(int i = 1; i <= CAPACITY; i++) {
            shop.add( new Product("Product" + i, 1000, 'm') );
            Assertions.assertEquals((i), shop.countProducts(), "countProducts: Soll=" + (i));
        }
    }


    // -- SORT ----------------------------------------------------------------------------------------------------
    @Test
    void testSort() {
        int[] expected = new int[]{1, 236, 1000};
        OnlineShop onlineShop = generate();
        onlineShop.sellById(INIT_ID + 2);
        Product[] sorted = onlineShop.sortByPrice();
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
        OnlineShop onlineShop = generate();
        String expected = "Shopbezeichnung: SPG-Shop\n" +
                            "Vorhandene ProdukteNL34, Product1, 1000.0€, Media\n" +
                            "35, Product2, 236.0€, Media\n" +
                            "36, Product3, 982.0€, Media\n" +
                            "37, Product4, 1.0€, Media\n" +
                            "null\n";
        System.out.println(onlineShop);
        Assertions.assertEquals(expected, onlineShop.toString(), "toString: fehlerhaft");
    }

}
