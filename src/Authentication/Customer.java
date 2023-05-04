package Authentication;
import OrderManagement.ShoppingCart;

public class Customer extends Account {
    private ShoppingCart cart;
    private int loyaltyPoints;
    private boolean isSuspend;
    private String address;
    public Customer(int accountID, String password, String phone, String email, Boolean isAdmin, ShoppingCart cart, int loyaltyPoints, boolean isSuspend, String address) {
        super(accountID, password, phone, email, isAdmin);
        this.cart = cart;
        this.loyaltyPoints = loyaltyPoints;
        this.isSuspend = isSuspend;
        this.address = address;
    }
    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isSuspend() {
        return isSuspend;
    }

    public void setSuspend(boolean suspend) {
        isSuspend = suspend;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
