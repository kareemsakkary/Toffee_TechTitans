package Authentication;

import OrderManagement.ShoppingCart;

/**
 * A class derived from the account class that
 * contains customer information.
 */
public class Customer extends Account {
    private ShoppingCart cart;
    private int loyaltyPoints;
    private boolean isSuspend;
    private String address;

    /**
     * Parameterized constructor for Customer.
     *
     * @param accountID Customer account ID.
     * @param name      Customer account name.
     * @param password  Customer password.
     * @param phone     Customer phone.
     * @param email     Customer email.
     * @param isAdmin   Customer permissions, usually set to False(customer).
     * @param cart      Customer ShoppingCart.
     * @param address   Customer address.
     */
    public Customer(String accountID, String name, String password, String phone, String email, Boolean isAdmin, ShoppingCart cart, String address) {
        super(accountID, name, password, phone, email, isAdmin);
        this.cart = cart;
        this.cart.setCustomer(this);
        this.address = address;
    }

    /**
     * Customer's ShoppingCart getter.
     *
     * @return The shopping cart.
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * Customer's ShoppingCart setter.
     *
     * @param cart The cart to be assigned to the customer.
     */
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    /**
     * Customer's LoyaltyPoints getter.
     *
     * @return The LoyaltyPoints the customer currently have.
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Customer's loyaltyPoints setter.
     *
     * @param loyaltyPoints The loyaltyPoints to be assigned to the customer.
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * Customer's suspension status getter.
     *
     * @return boolean indicating suspension status.
     */
    public boolean isSuspend() {
        return isSuspend;
    }

    /**
     * Customer's suspension status setter.
     *
     * @param suspend The suspension status to be assigned to the customer.
     */
    public void setSuspend(boolean suspend) {
        isSuspend = suspend;
    }

    /**
     * Customer's address getter.
     *
     * @return The customer address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Customer's address setter.
     *
     * @param address The address to be assigned to the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
