package Manager;

import Authentication.Account;
import Authentication.Customer;
import OrderManagement.ShoppingCart;
import StockManagement.Item;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class DataManager {
    private JSONObject data;
    public DataManager(){
        loadData();
    }
    private JSONObject toJsonObj(Customer acc){
        JSONObject user = new JSONObject();
        user.put("id",acc.getAccountID());
        user.put("name",acc.getName());
        user.put("password",acc.getPassword());
        user.put("phone",acc.getPhone());
        user.put("email",acc.getEmail());
        user.put("isAdmin",acc.isAdmin());
        user.put("cart",toJsonObj(acc.getCart()));
        user.put("address",acc.getAddress());
        return user;
    }
    private JSONObject toJsonObj(ShoppingCart Cart){
        JSONObject cart = new JSONObject();
        //map of item not string Waleed:>)
        for( Map.Entry<Item, Integer> entry: Cart.getCartItems().entrySet()) {
            cart.put(entry.getKey().getId(),entry.getValue());
        }
        return cart;
    }
    private JSONObject toJsonObj(Item it){
        JSONObject item = new JSONObject();
        item.put("id",it.getId());
        item.put("name",it.getName());
        item.put("loyaltyPoints",it.getLoyaltyPoints());
        item.put("category",it.getCategory());
        item.put("price",it.getPrice());
        item.put("timesOrdered",it.getTimesOrdered());
        return item;
    }
    private void loadData(){
        JSONParser parser = new JSONParser();
        try {
            data = (JSONObject) parser.parse(new FileReader("Data.json"));
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private void saveData(){
        try {
            FileWriter file = new FileWriter("Data.json");
            file.write(data.toJSONString());
            file.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ShoppingCart getCart( JSONObject CartJson){
        ShoppingCart cart = new ShoppingCart();

        return cart;
    }
    public Customer getCustomer(String id){
        JSONObject customerJson =(JSONObject)((JSONObject)(data.get("users"))).get(id);
        Customer customer = new Customer(
                (String) customerJson.get("id"),
                (String) customerJson.get("name"),
                (String) customerJson.get("password"),
                (String) customerJson.get("phone"),
                (String) customerJson.get("email"),
                (boolean)  customerJson.get("isAdmin"),
                getCart((JSONObject)customerJson.get("cart")),
                (String) customerJson.get("address"));
        return customer;
    }
    public Item getItem(String id){
        if(data.get("items") == null){
            System.out.print("No items found");
//            return new Item();
        }
        JSONObject customerJson =(JSONObject)((JSONObject)(data.get("items"))).get(id);
        Item item = new Item(
                (String) customerJson.get("id"),
                (String) customerJson.get("name"),
                (int) customerJson.get("loyaltyPoints"),
                (String) customerJson.get("category"),
                (double) customerJson.get("price"));
        return item;
    }
    public void setCustomer(Customer customer){
        if(data.get("users") == null){ data.put("users",new JSONObject());}
        ((JSONObject)(data.get("users"))).put(customer.getAccountID(),toJsonObj(customer));
        saveData();
    }
    public void setItem(Item item){
        if(data.get("items") == null){ data.put("items",new JSONObject());}
        ((JSONObject)(data.get("items"))).put(item.getId(),toJsonObj(item));
        saveData();
    }
//    boolean checkAuth(String email , String pass){
//        boolean found = false;
//        for(E)
//
//    }
    public void print(){
        System.out.print(data.toString());
    }
}
