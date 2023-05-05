import OrderManagement.*;
import StockManagement.*;
import Authentication.*;

import java.util.Scanner;

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
    public void viewItemsScreen(){

    }
    public void viewCartScreen(){

    }
    public void run(){
        if(LoggedInUser instanceof Customer){
            Scanner scan = new Scanner(System.in);
            String txtBlock = """ 
                Please choose one of the following options:
                1. View Cart
                2. View Items
                3. Logout
                """;
            System.out.print(txtBlock);
            int choice = scan.nextInt();
            if(choice == 1){
                viewCartScreen();
            }
            else if(choice == 2){
                viewItemsScreen();
            }
            else if(choice == 3){
                auth.logout();
            }
            else{
                System.out.print("Invalid choice!");
                System.exit(0);
            }
        }

        else if(LoggedInUser instanceof Admin){
            Scanner scan = new Scanner(System.in);
            String txtBlock = """ 
                Please choose one of the following options:
                1. View Users
                2. View Items
                3. Logout
                """;
            System.out.print(txtBlock);
            int choice = scan.nextInt();
            if(choice == 1){
//                viewUsersScreen();
            }
            else if(choice == 2){
                viewItemsScreen();
            }
            else if(choice == 3){
                viewItemsScreen();
            }
            else{
                System.out.print("Invalid choice!");
                System.exit(0);
            }
        }

        else{
            Scanner scan = new Scanner(System.in);
            String txtBlock = """ 
                Please choose one of the following options:
                1. Register
                2. Login
                3. View Items
                """;
            System.out.print(txtBlock);
            int choice = scan.nextInt();
            if(choice == 1){
                auth.register();
            }
            else if(choice == 2){
                auth.login();
            }
            else if(choice == 3){
                viewItemsScreen();
            }
            else{
                System.out.print("Invalid choice!");
                System.exit(0);
            }
        }
    }
}
