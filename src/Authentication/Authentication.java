package Authentication;

import Manager.DataManager;
import Manager.Manager;
import OrderManagement.ShoppingCart;

import java.util.Date;
import java.util.Scanner;

public class Authentication {
    public static Account LoggedInUser;
    DataManager DM;
    public Authentication(){
        DM = new DataManager();
    }
    void setLoggedInUser(Account acc){
        LoggedInUser = acc;
    }
    public void login(){
        Scanner scan = new Scanner(System.in);
        Account acc = null;
        System.out.println("Login page enter your info to login or\n write \"back\" to return to main menu");
        while (acc == null) {
            System.out.print("Enter your email : ");
            String email = scan.nextLine();
            if(email.equals("back")){
                break;
            }
            System.out.print("Enter your password : ");
            String pass = scan.nextLine();
            if(pass.equals("back")){
                break;
            }
            acc = DM.checkAuth(email, pass);
            if (acc == null) {
                System.out.println("email and password didn`t match !!");
            }else{
                System.out.println("logged in successfully");
                setLoggedInUser(acc);
                break;
            }
        }
    }
    public void register(){
        Scanner scan = new Scanner(System.in);
        Account acc = null;
        System.out.println("Register page enter your Register to login or\n write \"back\" to return to main menu");
       System.out.print("Enter your name : ");
        String name = scan.nextLine();
        if(name.equals("back")){
            return;
        }
        String email = null;
        while (true) {
            System.out.print("Enter your email : ");
            email = scan.nextLine();
            if(DM.emailExist(email)){
                System.out.print("Email already exist");
            }else{
                break;
            }
        }
        if(email.equals("back")){
            return;
        }
        System.out.print("Enter your password : ");
        String pass = scan.nextLine();
        if(pass.equals("back")){
            return;
        }
        System.out.print("Enter your phone : ");
        String phone = scan.nextLine();
        if(phone.equals("back")){
            return;
        }
        System.out.print("Enter your address : ");
        String address = scan.nextLine();
        if(address.equals("back")){
            return;
        }
        String id = Integer.toString(DM.accountSize()+1);
        Customer customer = new Customer(
                id,
                name,
                pass,
                phone,
                email,
                false,
                new ShoppingCart(),
                address
        );
        DM.setCustomer(customer);
        System.out.println("Registered successfully!");
    }
    public void logout(){
        LoggedInUser = null;
        System.out.println("Logged out successfully!");
    }
}
