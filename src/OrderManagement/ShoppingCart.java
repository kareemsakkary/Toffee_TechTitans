package OrderManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Authentication.Account;
import StockManagement.Item;

public class ShoppingCart {
    private HashMap<Item, Integer> cartItems = new HashMap<>();
    private float totalPrice;

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    private Account customer;


    public void addItem(Item item, int amount) {
        // add item to cartItems if it didn't exist
        if (cartItems.containsKey(item) && amount > 0) {
            cartItems.put(item, Math.min(cartItems.get(item) + amount, 50));
        }
        else {
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
        if (cartItems.isEmpty()){
            System.out.println("\n\t\t\t\tCart is Empty\n");
            return;
        }
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public HashMap<Item, Integer> getCartItems() {
        return cartItems;
    }
    public Vector<Item> getItems(){
        Vector<Item> v = new Vector<>();
        for (Map.Entry<Item, Integer> entry : cartItems.entrySet())
            v.add(entry.getKey());
        return v;
    }
}
