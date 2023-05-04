package StockManagement;

import java.util.Vector;

public class StockManagement {
    private Vector<Category> categories = new Vector<Category>();

//    public boolean searchForItem(String iName){
//    search in categories vector, if found; view the item otherwise print not found
//    }
    public void viewCategories(){
        int cnt = 1;
        for (int i = 0; i < categories.size(); i++) {
            System.out.print(cnt + ".");
            System.out.println(categories.get(i) + " ");
            cnt++;
        }
    }
    public void addCategory(Category c){
        categories.add(c);
    }

    public void setCategories(Vector<Category> categories) {
        this.categories = categories;
    }

    public Vector<Category> getCategories() {
        return categories;
    }

    public void viewAllItems(){
        for(Category c: categories){
            c.viewItems();
        }
    }
    public void printStatistics(){

    }
    public void updateCategory(){
        // ask which attr the admin wants to update
    }
}
