package Authentication;

/**
 * A class derived from the account class that contains admin information.
 */
public class Admin extends Account {

    /**
     * Parameterized constructor for Admin.
     *
     * @param accountID Admin account ID.
     * @param name      Admin account name.
     * @param password  Admin password.
     * @param phone     Admin phone number.
     * @param email     Admin email.
     */
    public Admin(String accountID, String name, String password, String phone, String email) {
        super(accountID, name, password, phone, email, true);
    }
}
