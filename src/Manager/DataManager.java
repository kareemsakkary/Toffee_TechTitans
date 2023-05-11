package Manager;

import Authentication.Account;
import Authentication.Admin;
import Authentication.Customer;
import OrderManagement.Order;
import OrderManagement.Payment;
import OrderManagement.ShoppingCart;
import OrderManagement.Voucher;
import StockManagement.Item;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

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
    private JSONObject toJsonObj(Payment payment){
        JSONObject pay = new JSONObject();
        pay.put("paymentMethod",payment.getPaymentMethod());
        JSONObject vouchers = new JSONObject();
        for(Voucher v  : payment.getVouchers()){
            vouchers.put(v.getCode(),v.getValue());
        }
        pay.put("vouchers",vouchers);
        return pay;
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
        orderJson.put("status",order.getStatus());
        orderJson.put("bill",toJsonObj(order.getBill()));
        orderJson.put("date",order.getOrderDate().toString());
        orderJson.put("address",order.getShippingAddress());
        return orderJson;
    }
    private JSONObject toJsonObj(Voucher voucher){
        JSONObject jVoucher = new JSONObject();
        jVoucher.put("code",voucher.getCode());
        jVoucher.put("value",voucher.getValue());
        return jVoucher;
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
    private ArrayList<Voucher> getVouchers(JSONObject vouchers){
        ArrayList<Voucher> vouchs = new ArrayList<>();
        for(Object entry: vouchers.keySet()){
            vouchs.add(new Voucher(entry.toString(), (Double)vouchers.get(entry.toString())));
        }
        return vouchs;
    }
    public Customer getCustomer(String id){
        if(data.get("users") == null){
            return null;
        }
        JSONObject customerJson =(JSONObject)((JSONObject)(data.get("users"))).get(id);
        if(customerJson == null){
            return null;
        }
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
            return null;
        }
        JSONObject item =(JSONObject)((JSONObject)(data.get("items"))).get(id);
        if(item == null){
            return null;
        }
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
    public Voucher getVoucher(String code){
        if(data.get("vouchers") == null){
            return null;
        }
        JSONObject voucher =(JSONObject)((JSONObject)(data.get("vouchers"))).get(code);
        if(voucher == null){
            return null;
        }
        return new Voucher(
                (String) voucher.get("code"),
                Double.parseDouble(voucher.get("value").toString())
                );
    }
    public Order getOrder(JSONObject order){
        if(order == null){
            return null;
        }
        Order nwOrder = new Order();
        nwOrder.setOrderID(order.get("id").toString());
        nwOrder.setOrderDate(LocalDateTime.parse(order.get("date").toString()));
        ShoppingCart shoppingCart = getCart((JSONObject)order.get("cart"));
        shoppingCart.setCustomer(getCustomer(order.get("userId").toString()));
        nwOrder.setStatus(order.get("status").toString());
        nwOrder.setCart(shoppingCart);
        Payment payment = new Payment();
        payment.setTotalPrice(shoppingCart.getTotalPrice());

        for(Voucher voucher : getVouchers((JSONObject)((JSONObject) order.get("bill")).get("vouchers")))
            payment.addVoucher(voucher);
        nwOrder.setBill(payment);
        return nwOrder;
    }
    // Set Data
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
        if(data.get("orders") == null){ data.put("orders",new JSONObject());}
        if(((JSONObject)(data.get("orders"))).get(order.getOrderID()) == null){
            int sz = orderSize();
            sz++;
            data.put("orderSize",Integer.toString(sz));
        }
        ((JSONObject)(data.get("orders"))).put(order.getOrderID(),toJsonObj(order));
        saveData();
    }
    public void setVoucher(Voucher voucher){
        if(data.get("vouchers") == null){ data.put("vouchers",new JSONObject());}
        if(((JSONObject)(data.get("vouchers"))).get(voucher.getCode()) == null){
            int sz = voucherSize();
            sz++;
            data.put("voucherSize",Integer.toString(sz));
        }
        ((JSONObject)(data.get("vouchers"))).put(voucher.getCode(),toJsonObj(voucher));
        saveData();
    }
    // delete Data
    public boolean removeVoucher(Voucher voucher){
        if(data.get("vouchers") == null){
            return false;
        }
        if(((JSONObject)(data.get("vouchers"))).remove(voucher.getCode()) != null){
            int sz = voucherSize();
            sz--;
            data.put("voucherSize",Integer.toString(sz));
            saveData();
            return true;
        }

        return false;
    }
    // Get Data sizes
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
    public int voucherSize(){
        if(data.get("voucherSize") == null){ return 0;}
        return Integer.parseInt((data.get("voucherSize")).toString());
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
    public Vector<Order> getOrders(){
        Vector<Order> orders = new Vector<>();
        JSONObject ordersJson = (JSONObject) data.get("orders");
        if(ordersJson!=null){
            for(Object order : ordersJson.values()){
                orders.add(getOrder((JSONObject) order));
            }
        }
        return orders;
    }
    public Vector<Account> getAccounts(){
        JSONObject users = (JSONObject) data.get("users");
        Vector<Account> accounts = new Vector<>();
        if(users!=null){
            Account acc;
            for(Object user : users.values()){
                if((boolean)((JSONObject)user).get("isAdmin"))acc = getAdmin((JSONObject)user);
                else acc = getCustomer((JSONObject)user);
                accounts.add(acc);
            }
        }
        return accounts;
    }
    public void print(){
        System.out.print(data.toString());
    }
}