package StockManagement;


public class Item {
    private String id;
    private String name;
    private int loyaltyPoints;
    private String category;
    private int timesOrdered = 0;
    private double price;

    public Item(String id, String name, String category, double price) {
        this.id = id;
        this.name = name;
//        this.loyaltyPoints = loyaltyPoints;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void viewItem(){
        System.out.println("Name: " + this.getName());
        System.out.println("Price: " + this.getPrice() + " EGP");
        System.out.println("Category: " + this.getCategory());
        System.out.println("Loyalty Points: " + this.getLoyaltyPoints() + " points");
    }
}
