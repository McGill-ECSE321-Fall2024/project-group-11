package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 80 "model.ump"
// line 171 "model.ump"
public class Customer extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private int phoneNumner;
  private String adress;
  private int customerID;

  //Customer Associations
  private Wishlist wishlist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aUserName, int aPhoneNumner, String aAdress, int aCustomerID, Wishlist aWishlist)
  {
    super(aUserName);
    phoneNumner = aPhoneNumner;
    adress = aAdress;
    customerID = aCustomerID;
    if (aWishlist == null || aWishlist.getCustomer() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aWishlist. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    wishlist = aWishlist;
  }

  public Customer(String aUserName, int aPhoneNumner, String aAdress, int aCustomerID, int aWishlistIDForWishlist, int aNbOfItemsForWishlist)
  {
    super(aUserName);
    phoneNumner = aPhoneNumner;
    adress = aAdress;
    customerID = aCustomerID;
    wishlist = new Wishlist(aWishlistIDForWishlist, aNbOfItemsForWishlist, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneNumner(int aPhoneNumner)
  {
    boolean wasSet = false;
    phoneNumner = aPhoneNumner;
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

  public boolean setCustomerID(int aCustomerID)
  {
    boolean wasSet = false;
    customerID = aCustomerID;
    wasSet = true;
    return wasSet;
  }

  public int getPhoneNumner()
  {
    return phoneNumner;
  }

  public String getAdress()
  {
    return adress;
  }

  public int getCustomerID()
  {
    return customerID;
  }
  /* Code from template association_GetOne */
  public Wishlist getWishlist()
  {
    return wishlist;
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
            "phoneNumner" + ":" + getPhoneNumner()+ "," +
            "adress" + ":" + getAdress()+ "," +
            "customerID" + ":" + getCustomerID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "wishlist = "+(getWishlist()!=null?Integer.toHexString(System.identityHashCode(getWishlist())):"null");
  }
}