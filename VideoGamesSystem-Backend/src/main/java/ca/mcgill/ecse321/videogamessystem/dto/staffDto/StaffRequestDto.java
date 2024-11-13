package ca.mcgill.ecse321.videogamessystem.dto.StaffDto;

public class StaffRequestDto {
    private String userName;
    private String email;
    private String password;
    private Boolean admin;
    

    public StaffRequestDto(String userName, String email, String password, Boolean admin) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.admin = admin;
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

    

    public Boolean getadmin(){
        return admin;
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

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    
}
