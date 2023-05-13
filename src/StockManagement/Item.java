package StockManagement;

/**
 * This class contains all the information regarding
 * the item itself and any methods we might need to access it.
 */
public class Item {
    private String id;
    private String name;
    private int loyaltyPoints;
    private String category;
    private int timesOrdered = 0;
    private double price;

    /**
     * Item constructor that assigns id, name, category and price.
     *
     * @param id       Item id.
     * @param name     Item name.
     * @param category Item category.
     * @param price    Item price.
     */
    public Item(String id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /**
     * Item's id getter.
     *
     * @return Item's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Item's id setter.
     *
     * @param id The id to be assigned.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Item's name getter.
     *
     * @return Item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Item's name setter.
     *
     * @param name The name to be assigned.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Item's LoyaltyPoints getter.
     *
     * @return Item's LoyaltyPoints.
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Item's loyaltyPoints setter.
     *
     * @param loyaltyPoints The loyaltyPoints to be assigned.
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * Item's category getter.
     *
     * @return Item's category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Item's category setter.
     *
     * @param category The category to be assigned.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns how many times this item was included in an order.
     *
     * @return the times this item was ordered.
     */
    public int getTimesOrdered() {
        return timesOrdered;
    }

    /**
     * Item's timesOrdered setter.
     *
     * @param timesOrdered The times the order was ordered.
     */
    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    /**
     * Item's price getter.
     *
     * @return Item's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Item's price setter.
     *
     * @param price The price to be assigned.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Prints the item's info.
     */
    public void viewItem() {
        System.out.println("Name: " + this.getName());
        System.out.println("Price: " + this.getPrice() + " EGP");
        System.out.println("Category: " + this.getCategory());
        System.out.println("Loyalty Points: " + this.getLoyaltyPoints() + " points");
    }
}
