import Authentication.Authentication;
import Authentication.Customer;
import StockManagement.Item;
import Manager.DataManager;
public class Main {
    public static void main(String[] args) {
        Item it = new Item("22" , "candy" , 4 , "aaa",99);
        DataManager Dm = new DataManager();
        Dm.setItem(it);
    }

}