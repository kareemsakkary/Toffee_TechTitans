package OrderManagement;

import Authentication.Account;
import Authentication.Customer;

import Authentication.Authentication;
import Manager.DataManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class OrderManagement { // m7tag el user ytzbt el awl
    private DataManager DM;
    public OrderManagement(){
        DM = new DataManager();
    };
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


    public void useVoucher() {
        Scanner input = new Scanner(System.in);
        System.out.println("1- Add voucher");
        System.out.println("2- Proceed with order");
        System.out.print(">>");
        String option = input.nextLine();
        while (!option.equals("2")) {
            if (option.equals("1")) {
                System.out.println("Added Voucher!\n");
                /* Check law el voucher mwgoda fel database wa el 7bshtkanat*/
                // recalculate el net price
            }
            if (!option.equals("1"))
                System.out.println("\nInvalid input, try again.\n");
            System.out.println("1- Add voucher");
            System.out.println("2- Proceed with order");
            System.out.print(">>");
            option = input.nextLine();
        }
        //System.out.println("Using vouchers done!");
        System.out.println();
    }

    public Payment choosePayment(double total) {
        Scanner input = new Scanner(System.in);
        Payment payment = new Payment();
        payment.setTotalPrice(total);
        System.out.println("Total price : " + payment.getTotalPrice() +" after voucher : " + payment.getNetPrice());
        System.out.println("Choose payment method!");
        System.out.println("1- Cash");
        System.out.println("2- Add Voucher");
        System.out.println("3- Cancel order");
        System.out.print(">>");
        String option = input.nextLine();
        while (!option.equals("3")) {
            if (option.equals("1")) {
                for(Voucher v : payment.getVouchers()){
                    DM.removeVoucher(v);
                }
                payment.setPaymentMethod("cash");
                System.out.println();
                return payment;
            }
            Voucher temp = null;
            if(option.equals("2")){
                System.out.println("Enter Code of the voucher :");
                String code = input.nextLine();
                Voucher voucher = DM.getVoucher(code);
                if(voucher != null && !payment.getVouchers().contains(voucher)){
                    if(voucher.value > payment.getNetPrice()) {
                        temp = new Voucher(voucher.code, voucher.value - payment.getNetPrice());
                        voucher.value=payment.getNetPrice();
                    }
                    payment.addVoucher(voucher);

                }else{
                    System.out.println("Wrong Voucher");
                }
            }
            if(!option.equals("2"))
                System.out.println("\nInvalid input, try again.\n");
            System.out.println("Total price : " + payment.getTotalPrice() +" after voucher : " + payment.getNetPrice());
            if(payment.getNetPrice() != 0) {
                System.out.println("Choose payment method!");
                System.out.println("1- Cash");
                System.out.println("2- Add Voucher");
                System.out.println("3- Cancel order");
                System.out.print(">>");
                option = input.nextLine();
            }else{
                System.out.println("1- checkout");
                System.out.println("2- Cancel order");
                System.out.print(">>");
                option = input.nextLine();
                if(option.equals("1")){
                    for(Voucher v : payment.getVouchers()){
                        DM.removeVoucher(v);
                    }
                    if(temp != null)
                        DM.setVoucher(temp);
                    return payment;
                }
            }
        }
        System.out.println("Order Canceled.\n");
        payment = null;
        return payment;
    }

    public void placeOrder() {
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
            return;
        }
        System.out.println();


        Order ord = new Order();
        ord.setOrderID(Integer.toString(DM.orderSize()+1));

        ShoppingCart cart = ((Customer) Authentication.LoggedInUser).getCart();
        ord.setCart(cart);

        Payment bill = choosePayment(cart.getTotalPrice());
        ord.setBill(bill);

        ord.setStatus(false); // pending

        System.out.print("Choose shipping address: ");
        String address = input.nextLine();
        ord.setShippingAddress(address);

        ord.setOrderDate(LocalDateTime.now());
        DM.setOrder(ord);

        cart.clear();
        DM.setCustomer((Customer)Authentication.LoggedInUser);

        System.out.println("Order placed Successfully!");
    }

}
