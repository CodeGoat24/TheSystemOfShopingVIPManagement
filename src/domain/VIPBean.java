package domain;

public class VIPBean {
    private String name;
    private String phone;
    private String identification;
    private String gender;
    private String address;
    private String postcode;
    private String joinDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "VIPBean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", identification='" + identification + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }
}
