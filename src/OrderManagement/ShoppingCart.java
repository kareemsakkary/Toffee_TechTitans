package OrderManagement;

import java.util.HashMap;
import java.util.Map;

import Authentication.User;

public class ShoppingCart {
    private HashMap<String, Integer> cartItems = new HashMap<>();
    private double totalPrice;
    private User customer;

    public void calcTotal() {
        //loop 3la value el items wa update el total price
        totalPrice = 100;
    }

    public void addItem(String item, int amount) {
        // add item to cartItems if it didn't exist
        if (cartItems.containsKey(item)) {
            // add defensive if total amount > 50 ?
            cartItems.put(item, cartItems.get(item) + amount);
        } else {
            cartItems.put(item, amount);
        }
        // lw hktb item added msg
    }

    public void removeItem(String item, int amount) {
        if (cartItems.containsKey(item)) {
            if (cartItems.get(item) - amount > 0) {
                cartItems.put(item, cartItems.get(item) - amount);
            } else {
                cartItems.remove(item);
            }
        }
        // lw hktb item removed msg
    }

    public void viewCart() {
        //etb3 el item be shyaka
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        calcTotal();
        System.out.println("Total price: " + totalPrice);
        System.out.println();
    }
}
