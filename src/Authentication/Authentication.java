package Authentication;

import Manager.DataManager;
import Manager.Manager;
import OrderManagement.ShoppingCart;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Date;
import java.util.Random;
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
        if(checkOTP(customer)){
            DM.setCustomer(customer);
            System.out.println("Registered successfully!");
        }else{
            System.out.println("Registration canceled!");
        }
    }
    public void logout(){
        LoggedInUser = null;
        System.out.println("Logged out successfully!");
    }
    public static boolean SendOTP(String email , int code){
        String host = "smtp.gmail.com";
        String username = "fcai.toffeeshop@gmail.com";
        String password = "dfpzbhgihyfxtbjp";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("TOFFE SHOP VERIFICATION CODE");
            message.setText("Your OTP IS : " + code );

            Transport.send(message);
            System.out.println("Email sent successfully!");
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean  checkOTP(Customer customer){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int code = rand.nextInt(1000000);
        SendOTP(customer.getEmail(),code);
        while (true) {
            System.out.print("Enter OTP which sent to your Email or -1 to cancel: ");
            int ansCode = scan.nextInt();
            if(ansCode==code) {

                return true;
            }else if(ansCode==-1){
                return false;
            }else{
                System.out.print("Wrong OTP try again, ");
            }
        }
    }
    public void showAllUsers(){
        System.out.print("Users : ");
        for(Account account : DM.getAccounts()){
            System.out.println("ID : " + account.getAccountID());
            System.out.println("Name : " + account.getName());
            System.out.println("Email : " + account.getEmail());
            System.out.println("Phone : " + account.getPhone());
            if(account.isAdmin())
                System.out.println("Type : admin");
            else
                System.out.println("Type : customer");
            System.out.println("=============================");
        }
    }
}
