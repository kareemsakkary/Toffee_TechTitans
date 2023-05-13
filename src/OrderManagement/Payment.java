package OrderManagement;

import Authentication.Account;

import java.util.Scanner;
import java.util.Vector;

/**
 * The purpose of this class is to perform the payment process
 * and to provide information, methods for performing this process.
 */
public class Payment {

    private String PaymentMethod;
    private float netPrice;
    private float totalPrice;
    private Vector<Voucher> vouchers;
    private float loyaltyPoints;

    /**
     * Empty Payment constructor.
     */
    public Payment() {
        vouchers = new Vector<Voucher>();
    }

    /**
     * Displays two options to the user
     * either use loyalty points to reduce the total price
     * or proceed with the order.
     */
    public void useLoyalty() {
        Scanner input = new Scanner(System.in);
        System.out.println("1- Use loyalty points");
        System.out.println("2- Proceed with order");
        System.out.print(">>");
        String option = input.nextLine();
        while (!option.equals("2")) {
            if (option.equals("1")) {
                /*LoyaltyPoints function here*/
            }
            if (!option.equals("1"))
                System.out.println("\nInvalid input, try again.\n");
            System.out.println("1- Use loyalty points");
            System.out.println("2- Proceed with order");
            System.out.print(">>");
            option = input.nextLine();
        }
        System.out.println();

    }


    /**
     * Calculates the new price after adding a voucher.
     */
    public void setNetPrice() {
        float voucher = 0;
        //calculate sum of vouchers
        for (Voucher v : vouchers) {
            voucher += v.value;
        }
        this.netPrice = totalPrice - voucher;
    }

    /**
     * Returns the paymentMethod in a String.
     *
     * @return The used method to pay for the order.
     */
    public String getPaymentMethod() {
        return PaymentMethod;
    }

    /**
     * paymentMethod setter.
     *
     * @param paymentMethod The used method to pay for the order.
     */
    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    /**
     * totalPrice getter.
     *
     * @return The totalPrice of the order.
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * totalPrice setter and calls the function responsible
     * for calculating the netPrice.
     *
     * @param totalPrice The calculated total price.
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
        setNetPrice();
    }

    /**
     * netPrice getter
     *
     * @return The netPrice of the order
     */
    public float getNetPrice() {
        return netPrice;
    }

    /**
     * Returns a vector of Vouchers containing the
     * added vouchers in the Payment.
     *
     * @return Vector of vouchers
     */
    public Vector<Voucher> getVouchers() {
        return vouchers;
    }

    /**
     * Adds the Voucher in the vouchers vector and calls the function responsible
     * for calculating the netPrice.
     *
     * @param voucher The voucher to be added in the vouchers vector
     */
    public void addVoucher(Voucher voucher) {
        vouchers.add(voucher);
        setNetPrice();
    }
}
