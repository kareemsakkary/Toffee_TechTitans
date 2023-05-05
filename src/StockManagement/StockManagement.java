package StockManagement;

import java.util.Vector;

public class StockManagement {
    private Vector<Category> categories = new Vector<Category>();

//    public boolean searchForItem(String iName){
//    search in categories vector, if found; view the item otherwise print not found
//    }
    public void viewCategories(){
        System.out.println("--- ALL CATEGORIES ---");
        int cnt = 1;
        for (int i = 0; i < categories.size(); i++) {
            System.out.print(cnt + ".");
            System.out.println(categories.get(i).getName() + " ");
            cnt++;
        }
        System.out.println("----------------------");
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

    public void viewCatalog(){
        int cnt = 1;
        System.out.println("----- ALL ITEMS ------");
        for (int i = 0; i < categories.size(); i++){
            for (int j = 0; j < categories.get(i).getItems().size(); j++){
                System.out.println(cnt +".Name: " + categories.get(i).getItems().get(j).getName());
                System.out.println("Category: " + categories.get(i).getItems().get(j).getCategory());
                System.out.println("Price: " + categories.get(i).getItems().get(j).getPrice() + " EGP" );
                System.out.println();
                cnt++;
            }
        }
        System.out.println("----------------------");
    }
    public void printStatistics(){

    }
    public void updateCategory(){

    }
}