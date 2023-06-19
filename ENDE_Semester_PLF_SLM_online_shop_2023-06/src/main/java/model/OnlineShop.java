package model;

public class OnlineShop {

    private static final String NL = "\n";
    private static final int DIRECTION_ASC = 5;
    private static final int DIRECTION_DESC = 8;
    public static final int CAPACITY = 5;

    private String name;
    private Product[] products;

    public OnlineShop(String name) {
        setName(name);
        this.products = new Product[CAPACITY];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // -- METHODS ----

    public boolean add(Product product) {
        // Check null
        if(product == null)
            return false;

        // Check doppelt
        for(Product p : products) {
            if(p != null && p.getId() != null && p.getId().equals( product.getId() )) {
                return false;
            }
        }

        for(int i = 0; i < products.length; i++) {
            Product temp = products[i];

            if(temp == null) {
                products[i] = product;
                return true;
            }
        }

        return false;
    }


    public Product sellById(Long id) {
        if(id == null)
            return null;


        for(int i = 0; i < products.length; i++) {
            Product temp = products[i];
            if( temp != null && id.equals( temp.getId() ) ) {
                products[i] = null;
                return temp;
            }
        }

        return null;
    }



    public Product[] sortByPrice() {
        Product[] sorted = OnlineShop.copy( products );
        int length = sorted.length;

        boolean isSorted = true;

//        for(int j = 0; j < length - 1; j++) {
        while (isSorted) {  // bonus
            isSorted = false;
            for(int i = 0; i < length - 1; i++) {
                Product p1 = sorted[i];
                Product p2 = sorted[i + 1];
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

    private void swap(Product[] products, int i1, int i2) {
        if(products == null)
            throw new IllegalArgumentException("Products darf nicht null sein!");

        Product temp = products[i1];
        products[i1] = products[i2];
        products[i2] = temp;
    }


    // Kann statisch und public implementiert werden, da Helper-Method
    public static Product[] copy(Product[] products) {
        if(products == null)
            throw new IllegalArgumentException("Products darf nicht null sein!");
        Product[] copy = new Product[products.length];
        for(int i = 0; i < products.length; i++) {
            copy[i] = products[i];
        }

        return copy;
    }


    public int countProducts() {
        int count = 0;
        for(Product p : products) {
            count += (p == null ? 0 : 1);
        }
        return count;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Shopbezeichnung: ").append(name).append(NL);
        sb.append("Vorhandene Produkte").append("NL");
        for(Product p : products) {
            sb.append(p).append(NL);
        }

        return sb.toString();
    }
}
