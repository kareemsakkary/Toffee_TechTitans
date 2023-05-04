package OrderManagement;

import Authentication.Account;

import java.util.Scanner;
import java.util.ArrayList;

public class Payment {
    private Account customer;
    private Order order;
    private String PaymentMethod;//m3rfsh 7war el enumerations lsa
    private double netPrice;
    private ArrayList<Voucher> vouchers;// cart item wla asebha hena
    private double loyaltyPoints;

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

    public void useLoyalty() {
        Scanner input = new Scanner(System.in);
        System.out.println("1- Use loyalty points");
        System.out.println("2- Proceed with order");
        System.out.print(">>");
        String option = input.nextLine();
        while (!option.equals("2")) {
            if (option.equals("1")) {
                System.out.println("Added Loyalty points!\n");
                /* 7bshtkanat el loyalty*/
                // recalculate el net price
                // loyalty points leha limit? law ah return hena
            }
            if (!option.equals("1"))
                System.out.println("\nInvalid input, try again.\n");
            System.out.println("1- Use loyalty points");
            System.out.println("2- Proceed with order");
            System.out.print(">>");
            option = input.nextLine();
        }
        //System.out.println("Using loyalty done!");
        System.out.println();

    }

    public boolean choosePayment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose payment method!");
        System.out.println("1- Cash");
        System.out.println("2- Cancel order");
        System.out.print(">>");
        String option = input.nextLine();
        while (!option.equals("2")) {
            if (option.equals("1")) {
                PaymentMethod = "cash";
                System.out.println();
                return true;
            }
            System.out.println("\nInvalid input, try again.\n");
            System.out.println("Choose payment method!");
            System.out.println("1- Cash");
            System.out.println("2- Cancel order");
            System.out.print(">>");
            option = input.nextLine();
        }
        System.out.println("Order Canceled.\n");
        return false;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }
}
