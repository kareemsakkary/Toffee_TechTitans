package OrderManagement;

public class Voucher {
    String code;
    double value;

    /**
     * Voucher constructor
     * @param code Voucher's code
     * @param value Voucher's value
     */
    public Voucher(String code, double value) {
        this.code = code;
        this.value = value;
    }

    /**
     * Voucher code setter
     * @param code the code to be set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Voucher value setter
     * @param value the value to be set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Voucher code getter
     * @return string the Voucher's code
     */
    public String getCode() {
        return code;
    }

    /**
     * Voucher value getter
     * @return double the Voucher's value
     */
    public double getValue() {
        return value;
    }
}
