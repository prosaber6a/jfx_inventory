package sample.dashboard;

public class Product {
    private int pro_id;
    private String name;
    private String unit;
    private float quantity;
    private float rate;
    private float total;

    public Product() {
    }

    public Product(int pro_id, String name, String unit, float quantity, float rate, float total) {
        this.pro_id = pro_id;
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.rate = rate;
        this.total = total;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
