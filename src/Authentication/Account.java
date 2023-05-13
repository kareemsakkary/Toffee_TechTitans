package Authentication;

/**
 * This class stores any information related to the user him/herself
 * and any information we might need to verify the user's identity.
 */
public class Account {
    private String accountID;
    private String name;
    private String password;
    private String phone;
    private String email;
    private boolean isAdmin;

    /**
     * Parameterized constructor for Account.
     *
     * @param accountID Account ID.
     * @param name      Account Name.
     * @param password  Account password.
     * @param phone     Account phone number.
     * @param email     Account email.
     * @param isAdmin   Account permissions.
     */
    public Account(String accountID, String name, String password, String phone, String email, boolean isAdmin) {
        this.accountID = accountID;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    /**
     * Account name getter.
     *
     * @return account name.
     */
    public String getName() {
        return name;
    }

    /**
     * Account name setter.
     *
     * @param name sets account name to this name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Account id getter.
     *
     * @return account id.
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Account id setter.
     *
     * @param accountID sets account id to this id.
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    /**
     * Account password getter.
     *
     * @return account password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Account password setter.
     *
     * @param password sets account password to this password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Account phone number getter.
     *
     * @return account phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Account phone number setter.
     *
     * @param phone sets account phone number to this number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Account email getter.
     *
     * @return account email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Account email setter.
     *
     * @param email sets account email to this email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Account permission getter.
     *
     * @return account permission.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Account permission setter.
     *
     * @param admin sets account permission to this value.
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
