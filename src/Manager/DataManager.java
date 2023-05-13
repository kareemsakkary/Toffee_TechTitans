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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * This class manages all the database-related aspects of the system,
 * along with loading the data from its storage location.
 */
public class DataManager {
    private JSONObject data;

    /**
     * Empty Constructor that loads the data.
     */
    public DataManager() {
        loadData();
    }

    /**
     * Transforms the customer account into a JSONObject.
     *
     * @param acc customer account.
     * @return JSONObject.
     */
    private JSONObject toJsonObj(Customer acc) {
        JSONObject user = new JSONObject();
        user.put("id", acc.getAccountID());
        user.put("name", acc.getName());
        user.put("password", acc.getPassword());
        user.put("phone", acc.getPhone());
        user.put("email", acc.getEmail());
        user.put("isAdmin", acc.isAdmin());
        user.put("cart", toJsonObj(acc.getCart()));
        user.put("address", acc.getAddress());
        return user;
    }

    /**
     * Transforms the ShoppingCart into a JSONObject.
     *
     * @param Cart ShoppingCart.
     * @return JSONObject.
     */
    private JSONObject toJsonObj(ShoppingCart Cart) {
        JSONObject cart = new JSONObject();
        for (Map.Entry<Item, Integer> entry : Cart.getCartItems().entrySet()) {
            cart.put(entry.getKey().getId(), entry.getValue().toString());
        }
        return cart;
    }

    /**
     * Transforms the Payment into a JSONObject.
     *
     * @param payment Payment.
     * @return JSONObject.
     */
    private JSONObject toJsonObj(Payment payment) {
        JSONObject pay = new JSONObject();
        pay.put("paymentMethod", payment.getPaymentMethod());
        JSONObject vouchers = new JSONObject();
        for (Voucher v : payment.getVouchers()) {
            vouchers.put(v.getCode(), v.getValue());
        }
        pay.put("vouchers", vouchers);
        return pay;
    }

    /**
     * Transforms the Item into a JSONObject.
     *
     * @param it Item.
     * @return JSONObject.
     */
    private JSONObject toJsonObj(Item it) {
        JSONObject item = new JSONObject();
        item.put("id", it.getId());
        item.put("name", it.getName());
        item.put("loyaltyPoints", it.getLoyaltyPoints());
        item.put("category", it.getCategory());
        item.put("price", it.getPrice());
        item.put("timesOrdered", it.getTimesOrdered());
        return item;
    }

    /**
     * Transforms the Order into a JSONObject.
     *
     * @param order Order.
     * @return JSONObject.
     */
    private JSONObject toJsonObj(Order order) {
        JSONObject orderJson = new JSONObject();
        orderJson.put("id", order.getOrderID());
        orderJson.put("userId", order.getCart().getCustomer().getAccountID());
        orderJson.put("cart", toJsonObj(order.getCart()));
        orderJson.put("status", order.getStatus());
        orderJson.put("bill", toJsonObj(order.getBill()));
        orderJson.put("date", order.getOrderDate().toString());
        orderJson.put("address", order.getShippingAddress());
        return orderJson;
    }

    /**
     * Transforms the Voucher into a JSONObject.
     *
     * @param voucher Voucher.
     * @return JSONObject.
     */
    private JSONObject toJsonObj(Voucher voucher) {
        JSONObject jVoucher = new JSONObject();
        jVoucher.put("code", voucher.getCode());
        jVoucher.put("value", voucher.getValue());
        return jVoucher;
    }

    /**
     * Loads the data from the json file.
     */
    private void loadData() {
        JSONParser parser = new JSONParser();
        try {
            data = (JSONObject) parser.parse(new FileReader("Data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the data into the json file.
     */
    private void saveData() {
        try {
            FileWriter file = new FileWriter("Data.json");
            file.write(data.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transforms the JSONObject into a ShoppingCart.
     *
     * @param CartJson cart as a JSONObject.
     * @return ShoppingCart.
     */
    private ShoppingCart getCart(JSONObject CartJson) {
        ShoppingCart cart = new ShoppingCart();
        for (Object entry : CartJson.keySet()) {
//            System.out.print(CartJson.get(entry.toString()));
            cart.addItem(getItem(entry.toString()), Integer.parseInt((String) CartJson.get(entry.toString())));
        }
        return cart;
    }

    /**
     * Transforms the JSONObject into a vector of Vouchers.
     *
     * @param vouchers Vouchers as a JSONObject.
     * @return vector of Vouchers.
     */
    private Vector<Voucher> getVouchers(JSONObject vouchers) {
        Vector<Voucher> vouchs = new Vector<>();
        for (Object entry : vouchers.keySet()) {
            vouchs.add(new Voucher(entry.toString(), (Float) vouchers.get(entry.toString())));
        }
        return vouchs;
    }

    /**
     * Gets the JSONObject using the Customer's id
     * then transforms the JSONObject into Customer.
     *
     * @param id Customer's id.
     * @return Customer.
     */
    public Customer getCustomer(String id) {
        if (data.get("users") == null) {
            return null;
        }
        JSONObject customerJson = (JSONObject) ((JSONObject) (data.get("users"))).get(id);
        if (customerJson == null) {
            return null;
        }
        return new Customer(
                (String) customerJson.get("id"),
                (String) customerJson.get("name"),
                (String) customerJson.get("password"),
                (String) customerJson.get("phone"),
                (String) customerJson.get("email"),
                (boolean) customerJson.get("isAdmin"),
                getCart((JSONObject) customerJson.get("cart")),
                (String) customerJson.get("address"));
    }

    /**
     * Transforms the JSONObject into Customer.
     *
     * @param customerJson Customer as a JSONObject.
     * @return Customer.
     */
    public Customer getCustomer(JSONObject customerJson) {
        return new Customer(
                (String) customerJson.get("id"),
                (String) customerJson.get("name"),
                (String) customerJson.get("password"),
                (String) customerJson.get("phone"),
                (String) customerJson.get("email"),
                (boolean) customerJson.get("isAdmin"),
                getCart((JSONObject) customerJson.get("cart")),
                (String) customerJson.get("address"));
    }

    /**
     * Transforms the JSONObject into Admin.
     *
     * @param customerJson Admin as a JSONObject.
     * @return Customer.
     */
    public Admin getAdmin(JSONObject customerJson) {
        return new Admin(
                (String) customerJson.get("id"),
                (String) customerJson.get("name"),
                (String) customerJson.get("password"),
                (String) customerJson.get("phone"),
                (String) customerJson.get("email")
        );
    }

    /**
     * Gets the JSONObject using the Item's id
     * then transforms the JSONObject into Item.
     *
     * @param id Item's id.
     * @return Item.
     */
    public Item getItem(String id) {
        if (data.get("items") == null) {
            return null;
        }
        JSONObject item = (JSONObject) ((JSONObject) (data.get("items"))).get(id);
        if (item == null) {
            return null;
        }
        return new Item(
                (String) item.get("id"),
                (String) item.get("name"),
                (String) item.get("category"),
                (double) item.get("price"));

    }

    /**
     * Transforms the JSONObject into Item.
     *
     * @param item Item as a JSONObject.
     * @return Item.
     */
    public Item getItem(JSONObject item) {
        if (data.get("items") == null) {
            System.out.print("No items found");
//            return new Item();
        }
        return new Item(
                (String) item.get("id"),
                (String) item.get("name"),
                (String) item.get("category"),
                (double) item.get("price"));

    }

    /**
     * Gets the JSONObject using the Voucher's code
     * then transforms the JSONObject into Voucher.
     *
     * @param code Voucher's code.
     * @return Voucher.
     */
    public Voucher getVoucher(String code) {
        if (data.get("vouchers") == null) {
            return null;
        }
        JSONObject voucher = (JSONObject) ((JSONObject) (data.get("vouchers"))).get(code);
        if (voucher == null) {
            return null;
        }
        return new Voucher(
                (String) voucher.get("code"),
                Float.parseFloat(voucher.get("value").toString())
        );
    }

    /**
     * Transforms the JSONObject into Order.
     *
     * @param order Order as a JSONObject.
     * @return Order.
     */
    public Order getOrder(JSONObject order) {
        if (order == null) {
            return null;
        }
        Order nwOrder = new Order();
        nwOrder.setOrderID(order.get("id").toString());
        nwOrder.setOrderDate(LocalDateTime.parse(order.get("date").toString()));
        ShoppingCart shoppingCart = getCart((JSONObject) order.get("cart"));
        shoppingCart.setCustomer(getCustomer(order.get("userId").toString()));
        nwOrder.setStatus(order.get("status").toString());
        nwOrder.setCart(shoppingCart);
        Payment payment = new Payment();
        payment.setTotalPrice(shoppingCart.getTotalPrice());

        for (Voucher voucher : getVouchers((JSONObject) ((JSONObject) order.get("bill")).get("vouchers")))
            payment.addVoucher(voucher);
        nwOrder.setBill(payment);
        return nwOrder;
    }

    /**
     * Transforms the Customer into JSONObject
     * then saves it in the database.
     *
     * @param customer customer.
     */
    public void setCustomer(Customer customer) {
        if (data.get("users") == null) {
            data.put("users", new JSONObject());
        }
        if (((JSONObject) (data.get("users"))).get(customer.getAccountID()) == null) {
            int sz = accountSize();
            sz++;
            data.put("accSize", Integer.toString(sz));
        }
        ((JSONObject) (data.get("users"))).put(customer.getAccountID(), toJsonObj(customer));
        saveData();
    }

    /**
     * Transforms the Item into JSONObject
     * then saves it in the database.
     *
     * @param item Item.
     */
    public void setItem(Item item) {
        if (data.get("items") == null) {
            data.put("items", new JSONObject());
        }
        if (((JSONObject) (data.get("items"))).get(item.getId()) == null) {
            int sz = itemSize();
            sz++;
            data.put("itemSize", Integer.toString(sz));
        }
        ((JSONObject) (data.get("items"))).put(item.getId(), toJsonObj(item));
        saveData();
    }

    /**
     * Transforms the Order into JSONObject
     * then saves it in the database.
     *
     * @param order Order.
     */
    public void setOrder(Order order) {
        if (data.get("orders") == null) {
            data.put("orders", new JSONObject());
        }
        if (((JSONObject) (data.get("orders"))).get(order.getOrderID()) == null) {
            int sz = orderSize();
            sz++;
            data.put("orderSize", Integer.toString(sz));
        }
        ((JSONObject) (data.get("orders"))).put(order.getOrderID(), toJsonObj(order));
        saveData();
    }

    /**
     * Transforms the Voucher into JSONObject
     * then saves it in the database.
     *
     * @param voucher Voucher.
     */
    public void setVoucher(Voucher voucher) {
        if (data.get("vouchers") == null) {
            data.put("vouchers", new JSONObject());
        }
        if (((JSONObject) (data.get("vouchers"))).get(voucher.getCode()) == null) {
            int sz = voucherSize();
            sz++;
            data.put("voucherSize", Integer.toString(sz));
        }
        ((JSONObject) (data.get("vouchers"))).put(voucher.getCode(), toJsonObj(voucher));
        saveData();
    }

    /**
     * Removes the used Voucher and updates the database.
     *
     * @param voucher Voucher.
     * @return boolean to indicate if the voucher was removed or not.
     */
    public boolean removeVoucher(Voucher voucher) {
        if (data.get("vouchers") == null) {
            return false;
        }
        if (((JSONObject) (data.get("vouchers"))).remove(voucher.getCode()) != null) {
            int sz = voucherSize();
            sz--;
            data.put("voucherSize", Integer.toString(sz));
            saveData();
            return true;
        }

        return false;
    }
    // Get Data sizes

    /**
     * Returns how many accounts are stored in the database.
     *
     * @return number of accounts stored in database.
     */
    public int accountSize() {
        if (data.get("accSize") == null) {
            return 0;
        }
        return Integer.parseInt(data.get("accSize").toString());
    }

    /**
     * Returns how many items are stored in the database.
     *
     * @return number of items stored in database.
     */
    public int itemSize() {
        if (data.get("itemSize") == null) {
            return 0;
        }
        return Integer.parseInt((data.get("itemSize")).toString());
    }

    /**
     * Returns how many orders are stored in the database.
     *
     * @return number of orders stored in database.
     */
    public int orderSize() {
        if (data.get("orderSize") == null) {
            return 0;
        }
        return Integer.parseInt((data.get("orderSize")).toString());
    }

    /**
     * Returns how many vouchers are stored in the database.
     *
     * @return number of vouchers stored in database.
     */
    public int voucherSize() {
        if (data.get("voucherSize") == null) {
            return 0;
        }
        return Integer.parseInt((data.get("voucherSize")).toString());
    }

    /**
     * Checks if the password and email entered belong
     * to the same account.
     *
     * @param email email.
     * @param pass  password.
     * @return The account if exists.
     */
    public Account checkAuth(String email, String pass) {
        JSONObject users = (JSONObject) data.get("users");
        Account acc = null;
        if (users != null) {
            for (Object user : users.values()) {

                if (((JSONObject) user).get("email").toString().equals(email)
                        && ((JSONObject) user).get("password").toString().equals(pass)) {
                    if ((boolean) ((JSONObject) user).get("isAdmin")) acc = getAdmin((JSONObject) user);
                    else acc = getCustomer((JSONObject) user);
                }
            }
        }
        return acc;
    }

    /**
     * Checks if the email exists in the database.
     *
     * @param email email.
     * @return boolean indicating if the email has been found.
     */
    public boolean emailExist(String email) {
        JSONObject users = (JSONObject) data.get("users");
        if (users != null) {
            for (Object user : users.values()) {
                if (((JSONObject) user).get("email").toString().equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Transforms JSONObject of class items to
     * Item object and loads them in a vector.
     *
     * @return vector of Items.
     */
    public Vector<Item> getCatalog() {
        Vector<Item> getCatalog = new Vector<Item>();
        JSONObject items = (JSONObject) data.get("items");
        if (items != null) {
            for (Object item : items.values()) {
                getCatalog.add(getItem((JSONObject) item));
            }
        }
        return getCatalog;
    }

    /**
     * Transforms JSONObject of class orders to
     * Order object and loads them in a vector.
     *
     * @return vector of Order.
     */
    public Vector<Order> getOrders() {
        Vector<Order> orders = new Vector<>();
        JSONObject ordersJson = (JSONObject) data.get("orders");
        if (ordersJson != null) {
            for (Object order : ordersJson.values()) {
                orders.add(getOrder((JSONObject) order));
            }
        }
        return orders;
    }

    /**
     * Transforms JSONObject of class users to
     * Account object and loads them in a vector.
     *
     * @return vector of Account.
     */
    public Vector<Account> getAccounts() {
        JSONObject users = (JSONObject) data.get("users");
        Vector<Account> accounts = new Vector<>();
        if (users != null) {
            Account acc;
            for (Object user : users.values()) {
                if ((boolean) ((JSONObject) user).get("isAdmin")) acc = getAdmin((JSONObject) user);
                else acc = getCustomer((JSONObject) user);
                accounts.add(acc);
            }
        }
        return accounts;
    }

    /**
     * prints the Data.
     */
    public void print() {
        System.out.print(data.toString());
    }
}
