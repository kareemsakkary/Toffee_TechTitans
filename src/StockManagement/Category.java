package StockManagement;
import java.util.*;

public class Category {
    private Vector<Item> items = new Vector<Item>();
    private String name;

    public Category(Vector<Item> items, String name) {
        this.items = items;
        this.name = name;
    }

    public void setItems(Vector<Item> items) {
        // get items from json file in an items vec then set it to the attribute
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }
    public void viewItems(){
        /* -- Example of an item --
         1. Name: KitKat chunky
         Description: chocolate filled with.....
         Price: 24.99 EGP
         Category: Chocolates
         Loyalty Points: 20 points
        */
        System.out.println("----- " + this.getName().toUpperCase() + " -----");
        int cnt = 1;
        for(Item itm: items){
            System.out.print(cnt + ". ");
            itm.viewItem();
            System.out.println();
            cnt++;
        }
        System.out.println("----------------------");
    }
    public void addItem(Item i){
        items.add(i);
    }
    public void deleteItem(Item i){
        items.remove(i);
    }
}