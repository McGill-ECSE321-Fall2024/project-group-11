package ca.mcgill.ecse321.videogamessystem.dto.CustomerDto;
import ca.mcgill.ecse321.videogamessystem.dto.WishlistDto.WishlistResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Wishlist;



public class CustomerResponseDto {

    private Long id;
    private String userName;
    private String email;
    private int phoneNumber;
    private String adress;
    private WishlistResponseDto wishlist;



    protected CustomerResponseDto() {
    }

    public CustomerResponseDto(Customer model) {

        this.id = model.getId();
        this.userName = model.getUserName();
        this.email = model.getEmail();
        this.phoneNumber = model.getPhoneNumber();
        this.adress = model.getAdress();
        this.wishlist= WishlistResponseDto.convertToWishlistResponseDto(model.getWishlist());
        
    }

    // Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id= id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber= phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress){
        this.adress= adress;
    }

    public WishlistResponseDto getWishlistResponseDto(){
        return wishlist;
    }

    public static CustomerResponseDto convertToCustomerResponseDto(Customer customer){
        if (customer == null){
            throw new IllegalArgumentException("Customer cannot be null");
        }

        CustomerResponseDto dto = new CustomerResponseDto(customer);
        dto.setId(customer.getId());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setAdress(customer.getAdress());
        dto.setEmail(customer.getEmail());
        dto.setUserName(customer.getUserName());
    
        Wishlist wishlist = customer.getWishlist();
        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist cannot be null.");
        }
    
        return dto;
    }

}