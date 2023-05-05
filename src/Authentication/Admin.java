package Authentication;

public class Admin extends Account {

    public Admin(int accountID, String password, String phone, String email) {
        super(accountID, password, phone, email, true);
    }
}
