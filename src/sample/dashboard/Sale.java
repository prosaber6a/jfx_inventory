package sample.dashboard;

public class Sale {
    private int sale_id;
    private String cust_name;
    private String cust_mobile;
    private String cust_address;
    private String product;
    private float quantity;
    private float rate;
    private float total;

    public Sale() {
    }

    public Sale(int sale_id, String cust_name, String cust_mobile, String cust_address, String product, float quantity, float rate, float total) {
        this.sale_id = sale_id;
        this.cust_name = cust_name;
        this.cust_mobile = cust_mobile;
        this.cust_address = cust_address;
        this.product = product;
        this.quantity = quantity;
        this.rate = rate;
        this.total = total;
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_mobile() {
        return cust_mobile;
    }

    public void setCust_mobile(String cust_mobile) {
        this.cust_mobile = cust_mobile;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
