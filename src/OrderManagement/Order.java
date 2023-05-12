package OrderManagement;

import java.time.LocalDateTime;
public class Order {
    private String orderID;
    private ShoppingCart cart;
    private String status;
    private Payment bill;
    private String shippingAddress;
    private LocalDateTime orderDate;

    /**
     * OrderID setter
     * @param orderID The ID to be assigned
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * ShoppingCart setter
     * @param cart The ShoppingCart to be assigned
     */
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    /**
     * Status setter
     * @param status The Status to be assigned
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Payment setter
     * @param bill The Payment to be assigned
     */
    public void setBill(Payment bill) {
        this.bill = bill;
    }

    /**
     * Address setter
     * @param shippingAddress The Address to be assigned
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }


    /**
     * OrderDate setter
     * @param orderDate The Date to be assigned
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * OrderID getter
     * @return orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * ShoppingCart getter
     * @return ShoppingCart
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * status getter
     * @return String the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Payment getter
     * @return Payment
     */
    public Payment getBill() {
        return bill;
    }

    /**
     * address getter
     * @return String the shipping address
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * orderDate getter
     * @return LocalDateTime the date
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
