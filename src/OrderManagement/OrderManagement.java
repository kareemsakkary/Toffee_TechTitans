package OrderManagement;

import Authentication.Account;
import Authentication.Customer;

import Authentication.Authentication;
import Manager.DataManager;

import java.time.LocalDateTime;
import java.util.*;

/**
 * This class Manages the ordering processes in the system as well as mapping orders
 * to a particular customer and storing them is the responsibility of this class.
 */
public class OrderManagement {
    private DataManager DM;

    /**
     * Empty OrderManagement constructor.
     */
    public OrderManagement() {
        DM = new DataManager();
    }

    /**
     * Displays each order made and its details
     */
    public void viewAllOrders() {
        System.out.println("----- ALL ORDERS ------");
        int i = 0;
        Vector<Order> orders = DM.getOrders();
        for (Order order : orders) {
            System.out.println("Order #" + i);
            System.out.println("User name: " + order.getCart().getCustomer().getName());
            System.out.println("------- ITEMS --------");
            order.getCart().viewCart();
            System.out.println("Price after vouchers: " + order.getBill().getNetPrice());
            System.out.println("Status: " + order.getStatus());
            System.out.println("----------------------");
            i++;
        }
        String txtBlock = """ 
                Please choose one of the following options:
                1. Check order delivered
                2. Return Back
                >>""";
        Scanner input = new Scanner(System.in);
        loop:
        while (true) {
            System.out.print(txtBlock);
            int option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter order number: ");
                    int num = input.nextInt();
                    if (num < i) {
                        orders.get(num).setStatus("delivered");
                        DM.setOrder(orders.get(num));
                        System.out.println("Order status changed successfully!");
                    } else {
                        System.out.println("No order with this number!");
                    }
                    break;
                case 2:
                    break loop;
                default:
                    System.out.println("Wrong option!");
            }
        }
    }

    /**
     * Takes Voucher code as input and adds it to the Payment
     *
     * @param payment the Payment to add the voucher inside.
     * @return The leftover value from the used voucher as a new voucher
     */
    public Voucher addVoucher(Payment payment) {
        Voucher temp = null;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Code of the voucher:");
        String code = input.nextLine();
        Voucher voucher = DM.getVoucher(code);
        if (voucher != null && !payment.getVouchers().contains(voucher)) {
            if (voucher.value > payment.getNetPrice()) {
                temp = new Voucher(voucher.code, voucher.value - payment.getNetPrice());
                voucher.value = payment.getNetPrice();
                System.out.println("The rest of your voucher is returned back by value of " + temp.value + ".");
                System.out.println("Voucher code: " + voucher.code);
            }
            payment.addVoucher(voucher);
        } else {
            System.out.println("Wrong Voucher!");
        }
        return temp;
    }

    /**
     * Confirms the order with otp then removes the vouchers from the database
     * and adds the leftover voucher if it has any value remaining.
     *
     * @param payment The Payment to be confirmed.
     * @param temp    The leftover Voucher from the addVoucher method.
     * @return boolean to confirm the result of the checkout process.
     */
    public boolean checkout(Payment payment, Voucher temp) {
        if (Authentication.checkOTP((Customer) Authentication.LoggedInUser)) {
            for (Voucher v : payment.getVouchers()) {
                DM.removeVoucher(v);
            }
            payment.setPaymentMethod("cash");
            System.out.println();
            if (temp != null)
                DM.setVoucher(temp);
            return true;
        }
        return false;
    }

    /**
     * Gives the customer the option to add voucher to the Payment
     * or to pay with cash the remaining netPrice or to cancel the order.
     *
     * @param total value to be set as the totalPrice.
     * @return returns the Payment object after adding Vouchers and calculating the new netPrice.
     */
    public Payment choosePayment(float total) {
        Scanner input = new Scanner(System.in);
        Payment payment = new Payment();
        payment.setTotalPrice(total);
        Voucher nwVoucher = null;
        label:
        while (true) {
            String option;
            System.out.println("Total price: " + payment.getTotalPrice() + " -->After using voucher/s: " + payment.getNetPrice());
            if (payment.getNetPrice() != 0) {
                System.out.println("1- Add Voucher");
                System.out.println("2- Checkout and pay with cash");
                System.out.println("3- Cancel order");
                System.out.print(">>");
                option = input.nextLine();
                switch (option) {
                    case "1":
                        nwVoucher = addVoucher(payment);
                        break;
                    case "2":
                        if (checkout(payment, nwVoucher)) {
                            return payment;
                        } else {
                            return null;
                        }
                    case "3":
                        break label;
                    default:
                        System.out.println("\nInvalid input, try again.\n");
                }
            } else {
                System.out.println("1- Checkout");
                System.out.println("2- Cancel order");
                System.out.print(">>");
                option = input.nextLine();
                switch (option) {
                    case "1":
                        checkout(payment, nwVoucher);
                        return payment;
                    case "2":
                        break label;
                    default:
                        System.out.println("\nInvalid input, try again.\n");
                }
            }
        }
        payment = null;
        return payment;
    }

    /**
     * Initiates the process of placing order which contains confirmation, order cancellation,
     * choosing the payment method and shipping address.
     */
    public void placeOrder() {
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
        ord.setOrderID(Integer.toString(DM.orderSize() + 1));

        ShoppingCart cart = ((Customer) Authentication.LoggedInUser).getCart();
        ord.setCart(cart);

        System.out.print("Choose shipping address: ");
        String address = input.nextLine();
        ord.setShippingAddress(address);

        Payment bill = choosePayment(cart.getTotalPrice());
        if (bill != null) {
            ord.setBill(bill);
            ord.setStatus("pending");

            ord.setOrderDate(LocalDateTime.now());
            DM.setOrder(ord);

            cart.clear();
            DM.setCustomer((Customer) Authentication.LoggedInUser);

            System.out.println("Order placed Successfully!");
        } else {
            System.out.println("Order canceled!");
        }
    }

}
