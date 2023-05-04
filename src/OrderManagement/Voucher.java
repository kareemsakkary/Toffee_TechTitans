package OrderManagement;

public class Voucher { // momken n3ml set of vouchers bnload feha el data men file el json
    String code;
    double value;

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public double getValue() {
        return value;
    }
}
