package project16.inventory;

public class Product {
    // These are the "fields" or "attributes" of our product
    String name;
    double price;
    int quantity;

    // This is the CONSTRUCTOR.
    // It's like a specialized method used to "build" the object.
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
