package OrderManagement;

import Authentication.Account;
import Authentication.Authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OrderManagement { // m7tag el user ytzbt el awl
    private HashMap<Account, ArrayList<Order>> orders = new HashMap<>();

    public void viewAllOrders() {
    }


    public void setStatus() {
    }


    public void viewOrder() {
    }


    public void cancelOrder() {
    }

    public void reorder() {
    }


    public void changeStatus() {
    }


    public void printStatistics() {
    }


    public void checkOut(Order order) {
        ArrayList<Order> temp = new ArrayList<>();
        //temp = orders.get(loggedIn);
        //temp.add(order);
        //orders.put(loggedIn, order);
        System.out.println();
    } // logged in is global

    public boolean placeOrder() {
//        Account loggedInUser = Authentication.LoggedInUser;
        Scanner input = new Scanner(System.in);
        String option = "3";
        while (!option.equals("1") && !option.equals("2")) {
            System.out.println("\nAre you sure you want to place order?");
            System.out.println("1- Proceed");
            System.out.println("2- Cancel order");
            System.out.print(">>");
            option = input.nextLine();

        }
        if (option.equals("2")) {
            System.out.println("\nOrder canceled!\n");
            return false;
        }
        System.out.println();

        Payment bill = new Payment();
        if (!bill.choosePayment()) // by7sl feha set lel payment method
            return false;
        bill.useVoucher();
        bill.useLoyalty();
        //bill.setCustomer(loggedIn);

        Order ord = new Order();
        ord.setOrderID("1");
        ord.setBill(bill);
        //ord.setCart(loggedIn.getCart());
        ord.setStatus(false); // pending
        System.out.print("Choose shipping address: ");
        String address = input.nextLine();
        ord.setShippingAddress(address);
        //ord.setOrderDate(); idk

        checkOut(ord); // updates orders map
        return true;
    } // logged in is global

}
