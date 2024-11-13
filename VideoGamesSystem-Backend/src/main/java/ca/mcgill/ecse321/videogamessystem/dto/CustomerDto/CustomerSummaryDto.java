package ca.mcgill.ecse321.videogamessystem.dto.CustomerDto;

import ca.mcgill.ecse321.videogamessystem.model.Customer;

public class CustomerSummaryDto {
    private String userName;
    private String email;
    private String password;
    private int phoneNumber;
    private String adress;

    public CustomerSummaryDto(Customer customer){

        this.userName = customer.getUserName();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.phoneNumber = customer.getPhoneNumber();
        this.adress = customer.getAdress();
    }
    protected CustomerSummaryDto() {}

    public String GetUserName(){return userName;}

    public String GetEmail(){return email;}

    public String GetPassword(){return password;}

    public int GetPhoneNumber(){return phoneNumber;}

    public String GetAdress(){return adress;}

    
}
