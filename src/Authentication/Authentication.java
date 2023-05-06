package Authentication;

import Manager.DataManager;
import Manager.Manager;

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

    }
    public void logout(){
        LoggedInUser = null;
    }
}
