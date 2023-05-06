package StockManagement;

import Manager.DataManager;
import java.util.Vector;

public class StockManagement {

    DataManager dataManager;
    private Vector<Item> categories = new Vector<>();

    public StockManagement(){
        dataManager = new DataManager();
        categories = dataManager.getCatalog();
    }

    public void viewCategories(){
        System.out.println("--- ALL CATEGORIES ---");
        int cnt = 1;
        for (int i = 0; i < categories.size(); i++) {
            System.out.print(cnt + ".");
            System.out.println(categories.get(i).getCategory() + " ");
            cnt++;
        }
        System.out.println("----------------------");
    }

    public Vector<Item> getCategories() {
        return categories;
    }

    public void viewCatalog(){
        int cnt = 1;
        System.out.println("----- ALL ITEMS ------");
        for (int i = 0; i < categories.size(); i++){
            System.out.print(cnt +".");
            categories.get(i).viewItem();
            System.out.println();
            cnt++;
        }
        System.out.println("----------------------");
    }

}