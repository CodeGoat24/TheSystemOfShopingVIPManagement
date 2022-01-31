package domain;

public class ShoppingRecordBean {
    private String id;
    private String vipname;
    private String phone;
    private String comname;
    private double price;
    private int amount;
    private double totalMoney;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShoppingRecordBean{" +
                "id='" + id + '\'' +
                ", vipname='" + vipname + '\'' +
                ", phone='" + phone + '\'' +
                ", comname='" + comname + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", totalMoney=" + totalMoney +
                ", date='" + date + '\'' +
                '}';
    }
}
