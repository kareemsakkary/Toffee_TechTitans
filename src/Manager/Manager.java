package Manager;

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
    private DataManager DM;
    public Manager() {
        this.ordManager = new OrderManagement();
        this.stckManager = new StockManagement();
        this.auth = new Authentication();
        this.DM = new DataManager();
    }

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
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        ShoppingCart cart = ((Customer) LoggedInUser).getCart();
                        System.out.print("Please enter the item number: ");
                        int itemNum = scan.nextInt();
                        boolean found = false;
                        for (int i = 0; i < stckManager.getCategories().size(); i++) {
                            if(itemNum - 1 == i){
                                Item target = stckManager.getCategories().get(i);
                                cart.addItem(target ,1);
                                DM.setCustomer(((Customer) LoggedInUser));
                                found = true;
                                System.out.println("Item added successfully!\n");
                            }
                        }
                        if(!found)
                            System.out.println("Invalid item number, doesn't exist!");
                        break;
                    case 2:
                        auth.logout();
                        return;
                    case 3:
                        return;
                    default:
                        System.out.print("Invalid choice!");
                        System.exit(0);
                }
            }
            else if (LoggedInUser instanceof Admin) {
                Scanner scan = new Scanner(System.in);
                String txtBlock = """ 
                        Please choose one of the following options:
                        1. Add Item
                        2. Remove Item
                        3. Logout
                        4. Return back
                        """;
                System.out.print(txtBlock);
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        // added when admin functions is implemented
                        // add item to catalog
                        break;
                    case 2:
                        // added when admin functions is implemented
                        // remove item from catalog
                        break;
                    case 3:
                        auth.logout();
                        return;
                    case 4:
                       return;
                    default:
                        System.out.print("Invalid choice!");
                        System.exit(0);
                }
            }
            else {
                Scanner scan = new Scanner(System.in);
                String txtBlock = """ 
                        Please choose one of the following options:
                        1. Register
                        2. Login
                        3. Return back
                        4. Exit
                        """;
                System.out.print(txtBlock);
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        auth.register();
                        break;
                    case 2:
                        auth.login();
                        break;
                    case 3:
                        return;
                    case 4:
                        System.out.print("Thank you for using our application!");
                        System.exit(0);
                    default:
                        System.out.print("Invalid choice!");
                        System.exit(0);
                }

            }
        }

    }

    public void viewCartScreen() {
        Scanner choice = new Scanner(System.in);
        Scanner input = new Scanner(System.in); // m3rfsh eh el bug bs da m5sos lel int
//        ha5od cart el logged in
        ShoppingCart cart = ((Customer)Authentication.LoggedInUser).getCart(); // dummy cart
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
        DM.setVoucher(new Voucher("-50" , 50));
        DM.setVoucher(new Voucher("-40" , 40));
        DM.setVoucher(new Voucher("-30" , 30));
        DM.setVoucher(new Voucher("-20" , 20));
        DM.setVoucher(new Voucher("-10" , 10));

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
                int choice = scan.nextInt();
                if (choice == 1) {
                    viewCartScreen();
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
                        3. View Orders
                        4. Logout
                        """;
                System.out.print(txtBlock);
                int choice = scan.nextInt();
                if (choice == 1) {
//                viewUsersScreen();
                } else if (choice == 2) {
                    viewItemsScreen();
                } else if (choice == 3) {
//                    viewOrdersScreen();
                }
                else if (choice == 4) {
                    auth.logout();
                }
                else {
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
                        4. Exit
                        """;
                System.out.print(txtBlock);
                int choice = scan.nextInt();
                if (choice == 1) {
                    auth.register();
                } else if (choice == 2) {
                    auth.login();
                } else if (choice == 3) {
                    viewItemsScreen();
                }
                else if (choice == 4) {
                    System.out.print("Thank you for using our application!");
                    System.exit(0);
                } else {
                    System.out.print("Invalid choice!");
                    System.exit(0);
                }
            }
        }
    }


}
