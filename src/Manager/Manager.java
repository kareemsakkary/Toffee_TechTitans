package Manager;

import OrderManagement.*;
import StockManagement.*;
import Authentication.*;

import java.util.Scanner;
import java.util.Vector;

import static Authentication.Authentication.LoggedInUser;

/**
 * Using this class, you can control how the system
 * functions and get the options that the user wants.
 */
public class Manager {
    private OrderManagement ordManager;
    private StockManagement stckManager;
    private Authentication auth;
    private DataManager DM;

    /**
     * Empty Manager constructor.
     * Creates new objects of the other managers inside itself
     */
    public Manager() {
        this.ordManager = new OrderManagement();
        this.stckManager = new StockManagement();
        this.auth = new Authentication();
        this.DM = new DataManager();
    }

    /**
     * Shows the catalog and the option to add item to cart
     * or logout or return to the previous menu
     */
    public void viewItemsScreen() {
        while (true) {
            stckManager.viewCatalog();
            if (LoggedInUser instanceof Customer) {
                Scanner scan = new Scanner(System.in);
                String txtBlock = """ 
                        Please choose one of the following options:
                        1. Add Item to Cart
                        2. Logout
                        3. Return back
                        """;
                System.out.print(txtBlock);
                System.out.print(">>");
                String choice = scan.nextLine();
                switch (choice) {
                    case "1":
                        ShoppingCart cart = ((Customer) LoggedInUser).getCart();
                        System.out.print("Please enter the item number: ");
                        int itemNum = scan.nextInt();
                        boolean found = false;
                        for (int i = 0; i < stckManager.getCategories().size(); i++) {
                            if (itemNum - 1 == i) {
                                Item target = stckManager.getCategories().get(i);
                                cart.addItem(target, 1);
                                DM.setCustomer(((Customer) LoggedInUser));
                                found = true;
                                System.out.println("Item added successfully!\n");
                            }
                        }
                        if (!found)
                            System.out.println("Invalid item number, item doesn't exist!");
                        break;
                    case "2":
                        auth.logout();
                        return;
                    case "3":
                        return;
                    default:
                        System.out.println("Invalid input, try again.\n");
                        break;
                }
            } else if (LoggedInUser instanceof Admin) {
                Scanner scan = new Scanner(System.in);
                String txtBlock = """ 
                        Please choose one of the following options:
                        1. Add Item
                        2. Remove Item
                        3. Logout
                        4. Return back
                        """;
                System.out.print(txtBlock);
                System.out.print(">>");
                String choice = scan.nextLine();
                switch (choice) {
                    case "1", "2":
                        break;
                    case "3":
                        auth.logout();
                        return;
                    case "4":
                        return;
                    default:
                        System.out.println("Invalid input, try again.\n");
                        break;
                }
            } else {
                Scanner scan = new Scanner(System.in);
                String txtBlock = """ 
                        Please choose one of the following options:
                        1. Register
                        2. Login
                        3. Return back
                        4. Exit
                        """;
                System.out.print(txtBlock);
                System.out.print(">>");
                String choice = scan.nextLine();
                switch (choice) {
                    case "1":
                        auth.register();
                        break;
                    case "2":
                        auth.login();
                        break;
                    case "3":
                        return;
                    case "4":
                        System.out.print("Thank you for using our application!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid input, try again.\n");
                        break;
                }

            }
        }

    }

    /**
     * Display the content of the cart and the total price
     * and options to change quantity, remove item, placing order
     * or to go back to the previous menu
     */
    public void viewCartScreen() {
        Scanner choice = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        ShoppingCart cart = ((Customer) Authentication.LoggedInUser).getCart();
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
                        if(index > v.size() || index <= 0){
                            System.out.println("Invalid item number!");
                            break;
                        }
                        System.out.print("Change quantity to: ");
                        int qnt = input.nextInt();
                        Item it = v.get(index - 1);
                        cart.removeItem(it, 50);
                        if(qnt > 0)
                            cart.addItem(it, qnt);
                        else
                            System.out.println("Item removed successfully!\n");
                        DM.setCustomer((Customer) Authentication.LoggedInUser);
                    }
                    break;
                case "2":
                    if (cart.getCartItems().isEmpty())
                        System.out.println("\nCart is empty, failed to remove item.");
                    else {
                        Vector<Item> v = cart.getItems();
                        System.out.print("Choose the item you wish to remove(1->" + v.size() + "): ");
                        int index = input.nextInt();
                        if(index > v.size() || index <= 0){
                            System.out.println("Invalid item number!");
                            break;
                        }
                        Item it = v.get(index - 1);
                        cart.removeItem(it, 50);
                        System.out.println("Item removed successfully!\n");
                        DM.setCustomer((Customer) Authentication.LoggedInUser);
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
            System.out.println("----- YOUR CART ------");
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

    /**
     * displays all the orders made
     */
    public void viewOrdersScreen() {
        ordManager.viewAllOrders();
    }

    /**
     * Responsible for organizing all the menus for the logged-in users,
     * general users and admins
     */
    public void run() {
        System.out.println("Welcome to Toffee store :)");
        System.out.println("-------------------------------------------");
        while (true) {
            if (LoggedInUser instanceof Customer) {
                Scanner scan = new Scanner(System.in);
                String txtBlock = """ 
                        Please choose one of the following options:
                        1. View Cart
                        2. View Items
                        3. Logout
                        """;
                System.out.print(txtBlock);
                System.out.print(">>");
                int choice = scan.nextInt();
                if (choice == 1) {
                    viewCartScreen();
                } else if (choice == 2) {
                    viewItemsScreen();
                } else if (choice == 3) {
                    auth.logout();
                } else {
                    System.out.println("Invalid input, try again.\n");
                }
            } else if (LoggedInUser instanceof Admin) {
                Scanner scan = new Scanner(System.in);
                String txtBlock = """ 
                        Please choose one of the following options:
                        1. View Users
                        2. View Items
                        3. View Orders
                        4. Logout
                        """;
                System.out.print(txtBlock);
                System.out.print(">>");
                int choice = scan.nextInt();
                if (choice == 1) {
                    viewUsersScreen();
                } else if (choice == 2) {
                    viewItemsScreen();
                } else if (choice == 3) {
                    viewOrdersScreen();
                } else if (choice == 4) {
                    auth.logout();
                } else {
                    System.out.println("Invalid input, try again.\n");
                }
            } else {
                Scanner scan = new Scanner(System.in);
                String txtBlock = """ 
                        Please choose one of the following options:
                        1. Register
                        2. Login
                        3. View Items
                        4. Exit
                        """;
                System.out.print(txtBlock);
                System.out.print(">>");
                int choice = scan.nextInt();
                if (choice == 1) {
                    auth.register();
                } else if (choice == 2) {
                    auth.login();
                } else if (choice == 3) {
                    viewItemsScreen();
                } else if (choice == 4) {
                    System.out.print("Thank you for using our application!");
                    System.exit(0);
                } else {
                    System.out.println("Invalid input, try again.\n");
                }
            }
        }
    }

    /**
     * Displays all the Accounts created
     */
    private void viewUsersScreen() {
        auth.showAllUsers();
    }
}
