package Authentication;

public class Admin extends Account {

    public Admin(String accountID,String name, String password, String phone, String email) {
        super(accountID, name, password, phone, email, true);
    }
}
