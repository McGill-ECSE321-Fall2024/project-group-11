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

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Account(String aUserName, Date aCreationDate) {
    userName = aUserName;
    creationDate = acreationDate;
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

  public void delete() {
  }

  public String toString() {
    return super.toString() + "[" +
        "userName" + ":" + getUserName() + "]";
  }
}