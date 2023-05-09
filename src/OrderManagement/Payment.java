package OrderManagement;

import Authentication.Account;

import java.util.Scanner;
import java.util.ArrayList;

public class Payment {

    private String PaymentMethod;//m3rfsh 7war el enumerations lsa
    private double netPrice;
    private double totalPrice;
    private ArrayList<Voucher> vouchers;// cart item wla asebha hena
    private double loyaltyPoints;
    public Payment(){
        vouchers = new ArrayList<Voucher>();
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



    public void setNetPrice() {
        double voucher = 0;
        //calculate sum of vouchers
        for(Voucher v : vouchers){
            voucher += v.value;
        }
        this.netPrice = totalPrice-voucher;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        setNetPrice();
    }

    public double getNetPrice() {
        return netPrice;
    }

    public ArrayList<Voucher> getVouchers() {
        return vouchers;
    }

    public void addVoucher(Voucher voucher) {
        vouchers.add(voucher);
        setNetPrice();
    }
}
