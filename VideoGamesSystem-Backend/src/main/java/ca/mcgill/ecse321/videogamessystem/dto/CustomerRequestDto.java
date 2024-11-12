package ca.mcgill.ecse321.videogamessystem.dto;

public class CustomerRequestDto {
    private String userName;
    private String email;
    private String password;
    private int phoneNumber;
    private String adress;

    public CustomerRequestDto(String userName, String email, String password, int phoneNumber, String adress) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.adress= adress;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdress(String adress) {
        this.adress = adress;

}
}
