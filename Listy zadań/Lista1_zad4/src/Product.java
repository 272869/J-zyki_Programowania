import java.util.HashSet;
public class Product {
    int number;
    double price;

    public Product(int number, double price){
        this.number=number;
        this.price=price;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return number == product.number;
    }

    public int hashCode(){
        return number;
    }
}
