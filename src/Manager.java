import OrderManagement.*;
import StockManagement.*;
import Authentication.*;

import java.util.Scanner;
import java.util.Vector;

import static Authentication.Authentication.LoggedInUser;

public class Manager {
    private OrderManagement ordManager;
    private StockManagement stckManager;
    private Authentication auth;

    public Manager(OrderManagement ordManager, StockManagement stckManager, Authentication auth) {
        this.ordManager = ordManager;
        this.stckManager = stckManager;
        this.auth = auth;
    }

    public void viewItemsScreen() {

    }

    public void viewCartScreen() {
        Scanner choice = new Scanner(System.in);
        Scanner input = new Scanner(System.in); // m3rfsh eh el bug bs da m5sos lel int
//        ha5od cart el logged in
        ShoppingCart cart = new ShoppingCart(); // dummy cart
        System.out.println("----- YOUR CART ------");
        cart.viewCart();
        System.out.println("1- Change quantity");
        System.out.println("2- Remove Item");
        System.out.println("3- Place order");
        System.out.println("4- Back");
        System.out.print(">>");
        String option = choice.nextLine();

        label:
        while (true) {
            switch (option) {
                case "1":
                    if (cart.getCartItems().isEmpty())
                        System.out.println("\nCart is empty, failed to change quantity.");
                    else {
                        Vector<Item> v = cart.getItems();
                        System.out.print("Choose the item (1->" + v.size() + "): ");
                        int index = input.nextInt();
                        System.out.print("Change quantity to: ");
                        int qnt = input.nextInt();

                        Item it = v.get(index - 1);
                        cart.removeItem(it, 50);
                        cart.addItem(it, qnt);
                    }
                    break;
                case "2":
                    if (cart.getCartItems().isEmpty())
                        System.out.println("\nCart is empty, failed to remove item.");
                    else {
                        Vector<Item> v = cart.getItems();
                        System.out.print("Choose the item you wish to remove(1->" + v.size() + "): ");
                        int index = input.nextInt();
                        Item it = v.get(index - 1);
                        cart.removeItem(it,50);
                    }
                    break;
                case "3":
                    if (cart.getCartItems().isEmpty())
                        System.out.println("\nCart is empty, failed to place order.");
                    else
                        ordManager.placeOrder();
                    break label;
                case "4":
                    break label;
                default:
                    System.out.println(option + "\n Invalid input, try again.\n");
                    break;
            }
            cart.viewCart();
            System.out.println("1- Change quantity");
            System.out.println("2- Remove Item");
            System.out.println("3- Place order");
            System.out.println("4- Back");
            System.out.print(">>");
            option = choice.nextLine();
        }
        System.out.println("----------------------");

    }

    public void run() {
        if (LoggedInUser instanceof Customer) {
            Scanner scan = new Scanner(System.in);
            String txtBlock = """ 
                    Please choose one of the following options:
                    1. View Cart
                    2. View Items
                    3. Logout
                    """;
            System.out.print(txtBlock);
            int choice = scan.nextInt();
            if (choice == 1) {
//                viewCartScreen();
            } else if (choice == 2) {
                viewItemsScreen();
            } else if (choice == 3) {
                auth.logout();
            } else {
                System.out.print("Invalid choice!");
                System.exit(0);
            }
        } else if (LoggedInUser instanceof Admin) {
            Scanner scan = new Scanner(System.in);
            String txtBlock = """ 
                    Please choose one of the following options:
                    1. View Users
                    2. View Items
                    3. Logout
                    """;
            System.out.print(txtBlock);
            int choice = scan.nextInt();
            if (choice == 1) {
//                viewUsersScreen();
            } else if (choice == 2) {
                viewItemsScreen();
            } else if (choice == 3) {
                viewItemsScreen();
            } else {
                System.out.print("Invalid choice!");
                System.exit(0);
            }
        } else {
            Scanner scan = new Scanner(System.in);
            String txtBlock = """ 
                    Please choose one of the following options:
                    1. Register
                    2. Login
                    3. View Items
                    """;
            System.out.print(txtBlock);
            int choice = scan.nextInt();
            if (choice == 1) {
                auth.register();
            } else if (choice == 2) {
                auth.login();
            } else if (choice == 3) {
                viewItemsScreen();
            } else {
                System.out.print("Invalid choice!");
                System.exit(0);
            }
        }
    }


}
