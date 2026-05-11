package project16_com.store.inventory;

public class InventoryManager {
    public static void main(String[] args) {
        Product[] warehouse = new Product[3];
        warehouse[0] = new Product("Java Book", 45.00, 10);
        warehouse[1] = new Product("Coding Mouse", 25.50, 5);
        warehouse[2] = new Product("Mechanical Keyboard", 120.00, 2);

        double totalWarehouseValue = 0; // 1. The "holding" variable

        for (int i = 0; i < warehouse.length; i++) {
            // 2. Calculate this item's value and add it to the total
            double itemValue = warehouse[i].price * warehouse[i].quantity;
            totalWarehouseValue += itemValue;

            System.out.println(warehouse[i].name + " value: $" + itemValue);
        }

        // 3. Print the final result after the loop finishes
        System.out.println("Total Inventory Value: $" + totalWarehouseValue);
    }
}
