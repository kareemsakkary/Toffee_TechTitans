package Authentication;

public class Account {
    private int accountID ;
    private String password ;
    private String phone ;
    private String email ;
    private boolean isAdmin;

    public Account(int accountID, String password, String phone, String email, Boolean isAdmin) {
        this.accountID = accountID;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
