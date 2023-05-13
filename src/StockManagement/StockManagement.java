package StockManagement;

import Manager.DataManager;

import java.util.Vector;

/**
 * It is responsible for keeping track of all items and categories
 * within the system as well as managing the stock itself.
 */
public class StockManagement {

    private DataManager dataManager;
    private Vector<Item> categories = new Vector<>();

    /**
     * Empty StockManagement constructor.
     */
    public StockManagement() {
        dataManager = new DataManager();
        categories = dataManager.getCatalog();
    }

    /**
     * Prints all the categories.
     */
    public void viewCategories() {
        System.out.println("--- ALL CATEGORIES ---");
        int cnt = 1;
        for (int i = 0; i < categories.size(); i++) {
            System.out.print(cnt + ".");
            System.out.println(categories.get(i).getCategory() + " ");
            cnt++;
        }
        System.out.println("----------------------");
    }

    /**
     * Returns vector of Item.
     *
     * @return Vector of Item.
     */
    public Vector<Item> getCategories() {
        return categories;
    }

    /**
     * Prints all the items.
     */
    public void viewCatalog() {
        int cnt = 1;
        System.out.println("----- ALL ITEMS ------");
        for (int i = 0; i < categories.size(); i++) {
            System.out.print(cnt + ".");
            categories.get(i).viewItem();
            System.out.println();
            cnt++;
        }
        System.out.println("----------------------");
    }

}