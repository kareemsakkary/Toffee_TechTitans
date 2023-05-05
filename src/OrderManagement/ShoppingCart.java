package OrderManagement;

import java.util.HashMap;
import java.util.Map;

import Authentication.Account;
import StockManagement.Item;

public class ShoppingCart {
    private HashMap<Item, Integer> cartItems = new HashMap<>();
    private float totalPrice;
    private Account customer;


    public void addItem(Item item, int amount) {
        // add item to cartItems if it didn't exist
        if (cartItems.containsKey(item)) {
            // add defensive if total amount > 50 ?
            cartItems.put(item, cartItems.get(item) + amount);
        } else {
            cartItems.put(item, amount);
        }
        totalPrice += item.getPrice() * amount;
    }

    public void removeItem(Item item, int amount) {
        if (cartItems.containsKey(item)) {
            if (cartItems.get(item) - amount > 0) {
                totalPrice -= item.getPrice() * amount;
                cartItems.put(item, cartItems.get(item) - amount);
            } else {
                totalPrice -= cartItems.get(item) * item.getPrice();
                cartItems.remove(item);
            }
        }
    }

    public void viewCart() {
        //etb3 el item be shyaka
        int cnt = 1;
        for (Map.Entry<Item, Integer> entry : cartItems.entrySet()) {
            Item item = entry.getKey();
            System.out.println(cnt + ". Name: " + item.getName());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Qnt. : " + entry.getValue());
            cnt++;
            System.out.println();
        }
        System.out.println("Total price: " + totalPrice);
        System.out.println();
    }
}
