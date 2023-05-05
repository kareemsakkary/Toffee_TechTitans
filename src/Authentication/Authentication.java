package Authentication;

import Manager.DataManager;

public class Authentication {
    public static Account LoggedInUser;

    void setLoggedInUser(Account acc){
        LoggedInUser = acc;
    }
    public void login(){
        DataManager dm = new  DataManager();
        LoggedInUser = dm.getCustomer("22");
        //        dm.print();
    }
    public void register(){

    }
    public void logout(){

    }
}
