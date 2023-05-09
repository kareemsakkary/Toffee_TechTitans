package Authentication;

public class Account {
    private String accountID ;
    private String name;
    private String password ;
    private String phone ;
    private String email ;
    private boolean isAdmin;

    public Account(String accountID,String name, String password, String phone, String email, boolean isAdmin ) {
        this.accountID = accountID;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
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

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
