/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

// line 76 "model.ump"
// line 163 "model.ump"
@Entity
public class Customer extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes

  private int phoneNumber;
  private String adress;

  //Customer Associations
  @OneToOne
  private Wishlist wishlist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aUserName, String aEmail, String aPassword, int aPhoneNumber, String aAdress)
  {
    super(aUserName, aEmail, aPassword);
    phoneNumber = aPhoneNumber;
    adress = aAdress;
    Wishlist whishlist = new Wishlist();
    this.wishlist = whishlist;
  }

  public Customer(){

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneNumber(int aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setAdress(String aAdress)
  {
    boolean wasSet = false;
    adress = aAdress;
    wasSet = true;
    return wasSet;
  }

  public Long getId(){
    return super.getId();
  }

  public int getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getAdress()
  {
    return adress;
  }
  /* Code from template association_GetOne */
  public Wishlist getWishlist()
  {
    return wishlist;
  }

  public boolean setWishlist(Wishlist wishlist){
    boolean wasSet= false;
    this.wishlist= wishlist;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Wishlist existingWishlist = wishlist;
    wishlist = null;
    if (existingWishlist != null)
    {
      existingWishlist.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "adress" + ":" + getAdress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "wishlist = "+(getWishlist()!=null?Integer.toHexString(System.identityHashCode(getWishlist())):"null");
  }
}