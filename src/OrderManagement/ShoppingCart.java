package OrderManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Authentication.Account;
import StockManagement.Item;

/**
 * In this class, we store all the information we need about a customer's shopping cart,
 * as well as methods related to the actions the user might take and processes we might need to run.
 */
public class ShoppingCart {
    private HashMap<Item, Integer> cartItems = new HashMap<>();
    private float totalPrice;
    private Account customer;

    /**
     * Empty ShoppingCart constructor.
     */
    public ShoppingCart() {
    }

    /**
     * Cart owner getter.
     *
     * @return the owner of this ShoppingCart.
     */
    public Account getCustomer() {
        return customer;
    }

    /**
     * Cart owner setter.
     *
     * @param customer cart owner.
     */
    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    /**
     * Adds the item certain amount of times to the ShoppingCart.
     *
     * @param item   The item to be added to cart.
     * @param amount The amount of the specified item.
     */
    public void addItem(Item item, int amount) {
        // add item to cartItems if it didn't exist
        if (cartItems.containsKey(item) && amount > 0) {
            cartItems.put(item, Math.min(cartItems.get(item) + amount, 50));
        } else {
            cartItems.put(item, amount);
        }

        totalPrice += item.getPrice() * amount;
    }

    /**
     * Removes the item certain amount of times from the ShoppingCart.
     *
     * @param item   The item to be removed from cart.
     * @param amount The amount of the specified item.
     */
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

    /**
     * Prints the Cart content and the total price.
     */
    public void viewCart() {
        if (cartItems.isEmpty()) {
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
    }

    /**
     * Empties the ShoppingCart after an order has been placed.
     */
    public void clear() {
        cartItems.clear();
        totalPrice = 0;
    }

    /**
     * ShoppingCart total price getter.
     *
     * @return The cart content total price.
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * ShoppingCart content getter.
     *
     * @return The cart content as a hashmap of key item and value integer.
     */
    public HashMap<Item, Integer> getCartItems() {
        return cartItems;
    }

    /**
     * ShoppingCart content getter.
     *
     * @return The cart content as a Vector of items.
     */
    public Vector<Item> getItems() {
        Vector<Item> v = new Vector<>();
        for (Map.Entry<Item, Integer> entry : cartItems.entrySet())
            v.add(entry.getKey());
        return v;
    }
}
