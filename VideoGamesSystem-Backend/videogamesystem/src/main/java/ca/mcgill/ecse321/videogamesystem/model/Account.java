/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

// line 22 "model.ump"
// line 166 "model.ump"
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Id;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Account Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String userName;
  private Date creationDate;
  private String email;
  private String password;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Account(String aUserName, Date aCreationDate, String aEmail, String apassword) {
    userName = aUserName;
    creationDate = acreationDate;
    email = aEmail;
    password = apassword;
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setUserName(String aUserName) {
    boolean wasSet = false;
    userName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public String getUserName() {
    return userName;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public String getEmail() {
    return email;
  }

  public void delete() {
  }

  public String toString() {
    return super.toString() + "[" +
        "userName" + ":" + getUserName() + "]";
  }
}