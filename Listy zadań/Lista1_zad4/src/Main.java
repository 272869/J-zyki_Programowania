import java.util.HashSet;
public class Main {
    public static void main(String[] args) {

        HashSet<Product> hashSet = new HashSet<>();

        Product product1 = new Product(1, 32.42);
        Product product2 = new Product(2, 87.55);
        Product product3 = new Product(3, 13.08);
        Product product4 = new Product(4, 26.67);
        Product product5 = new Product(5, 91.12);

        hashSet.add(product1);
        hashSet.add(product3);
        hashSet.add(product5);
        hashSet.add(product1);

        System.out.println("Rozmiar zbioru: " + hashSet.size());
        System.out.println("Produkty: ");
        for (Product product : hashSet) {
            System.out.println("Number: " + product.number + ", Price: " + product.price);
        }
    }
}