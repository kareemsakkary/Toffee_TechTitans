package Manager;

import Authentication.Account;
import Authentication.Admin;
import Authentication.Customer;
import OrderManagement.Order;
import OrderManagement.ShoppingCart;
import StockManagement.Item;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

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
        for( Map.Entry<Item, Integer> entry: Cart.getCartItems().entrySet()) {
            cart.put(entry.getKey().getId(),entry.getValue().toString());
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
    private JSONObject toJsonObj(Order order){
        JSONObject orderJson = new JSONObject();
        orderJson.put("id",order.getOrderID());
        orderJson.put("userId",order.getCart().getCustomer().getAccountID());
        orderJson.put("cart",toJsonObj(order.getCart()));
        orderJson.put("statues",order.isStatus());
        orderJson.put("bill",order.getBill());
        orderJson.put("date",order.getOrderDate());
        orderJson.put("address",order.getShippingAddress());
        return orderJson;
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
    private ShoppingCart getCart(JSONObject CartJson){
        ShoppingCart cart = new ShoppingCart();
        for(Object entry: CartJson.keySet()){
//            System.out.print(CartJson.get(entry.toString()));
            cart.addItem(getItem(entry.toString()) , Integer.parseInt((String)CartJson.get(entry.toString())));
        }
        return cart;
    }
    public Customer getCustomer(String id){
        JSONObject customerJson =(JSONObject)((JSONObject)(data.get("users"))).get(id);
        return new Customer(
                (String) customerJson.get("id"),
                (String) customerJson.get("name"),
                (String) customerJson.get("password"),
                (String) customerJson.get("phone"),
                (String) customerJson.get("email"),
                (boolean)  customerJson.get("isAdmin"),
                getCart((JSONObject)customerJson.get("cart")),
                (String) customerJson.get("address"));
    }
    public Customer getCustomer(JSONObject customerJson){
        return new Customer(
                (String) customerJson.get("id"),
                (String) customerJson.get("name"),
                (String) customerJson.get("password"),
                (String) customerJson.get("phone"),
                (String) customerJson.get("email"),
                (boolean)  customerJson.get("isAdmin"),
                getCart((JSONObject)customerJson.get("cart")),
                (String) customerJson.get("address"));
    }
    public Admin getAdmin(JSONObject customerJson){
        return new Admin(
                (String) customerJson.get("id"),
                (String) customerJson.get("name"),
                (String) customerJson.get("password"),
                (String) customerJson.get("phone"),
                (String) customerJson.get("email")
        );
    }
    public Item getItem(String id){
        if(data.get("items") == null){
            System.out.print("No items found");
//            return new Item();
        }
        JSONObject item =(JSONObject)((JSONObject)(data.get("items"))).get(id);
        return new Item(
                (String) item.get("id"),
                (String) item.get("name"),
                (String) item.get("category"),
                (double) item.get("price"));

    }
    public Item getItem(JSONObject item){
        if(data.get("items") == null){
            System.out.print("No items found");
//            return new Item();
        }
        return new Item(
                (String) item.get("id"),
                (String) item.get("name"),
                (String) item.get("category"),
                (double) item.get("price"));

    }

    public Order getOrder(String id){
        if(data.get("Order") == null){
            System.out.print("No Orders found");
//            return new Item();
        }
        JSONObject order =(JSONObject)((JSONObject)(data.get("items"))).get(id);
        Order nwOrder = new Order();

        return new Order();

    }
//    public Item getItem(JSONObject item){
//        if(data.get("items") == null){
//            System.out.print("No items found");
////            return new Item();
//        }
//        return new Item(
//                (String) item.get("id"),
//                (String) item.get("name"),
//                (String) item.get("category"),
//                (double) item.get("price"));
//
//    }
    public void setCustomer(Customer customer){
        if(data.get("users") == null){ data.put("users",new JSONObject());}
        if(((JSONObject)(data.get("users"))).get(customer.getAccountID()) == null){
            int sz = accountSize();
            sz++;
            data.put("accSize",Integer.toString(sz));
        }
        ((JSONObject)(data.get("users"))).put(customer.getAccountID(),toJsonObj(customer));
        saveData();
    }
    public void setItem(Item item){
        if(data.get("items") == null){ data.put("items",new JSONObject());}
        if(((JSONObject)(data.get("items"))).get(item.getId()) == null){
            int sz = itemSize();
            sz++;
            data.put("itemSize",Integer.toString(sz));
        }
        ((JSONObject)(data.get("items"))).put(item.getId(),toJsonObj(item));
        saveData();
    }
    public void setOrder(Order order){
        if(data.get("orders") == null){ data.put("users",new JSONObject());}
        if(((JSONObject)(data.get("orders"))).get(order.getOrderID()) == null){
            int sz = orderSize();
            sz++;
            data.put("orderSize",Integer.toString(sz));
        }
        ((JSONObject)(data.get("orders"))).put(order.getOrderID(),toJsonObj(order));
        saveData();
    }
    public Account checkAuth(String email , String pass){
        JSONObject users = (JSONObject) data.get("users");
        Account acc = null;
        if(users!=null){
            for(Object user : users.values()){

                if(((JSONObject)user).get("email").toString().equals(email)
                && ((JSONObject)user).get("password").toString().equals(pass)){
                    if((boolean)((JSONObject)user).get("isAdmin"))acc = getAdmin((JSONObject)user);
                    else acc = getCustomer((JSONObject)user);
                }
            }
        }
        return acc;
    }
    public int accountSize(){
        if(data.get("accSize") == null){ return 0;}
        return Integer.parseInt(data.get("accSize").toString());
    }
    public int itemSize(){
        if(data.get("itemSize") == null){ return 0;}
        return Integer.parseInt((data.get("itemSize")).toString());
    }
    public int orderSize(){
        if(data.get("orderSize") == null){ return 0;}
        return Integer.parseInt((data.get("orderSize")).toString());
    }
    public boolean emailExist(String email){
        JSONObject users = (JSONObject) data.get("users");
        if(users!=null){
            for(Object user : users.values()){
                if(((JSONObject)user).get("email").toString().equals(email)){
                    return true;
                }
            }
        }
        return false;
    }
    public Vector<Item> getCatalog(){
        Vector<Item> getCatalog = new Vector<Item>();
        JSONObject items = (JSONObject) data.get("items");
        if(items!=null){
            for(Object item : items.values()){
                getCatalog.add(getItem((JSONObject) item));
            }
        }
        return getCatalog;
    }
    public void print(){
        System.out.print(data.toString());
    }
}
