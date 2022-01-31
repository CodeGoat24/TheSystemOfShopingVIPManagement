package domain;

public class CommodityBean {
    private int id;
    private String name;
    private String birthPlace;
    private String birthDay;
    private double price;
    private int stock;
    private String introduction;
    private String ps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    @Override
    public String toString() {
        return "CommodityBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", introduction='" + introduction + '\'' +
                ", ps='" + ps + '\'' +
                '}';
    }
}
