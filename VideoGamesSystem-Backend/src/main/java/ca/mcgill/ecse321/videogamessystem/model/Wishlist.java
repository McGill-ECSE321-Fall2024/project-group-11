package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 114 "model.ump"
// line 205 "model.ump"
public class Wishlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Wishlist Attributes
  private int wishlistID;
  private int nbOfItems;

  //Wishlist Associations
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Wishlist(int aWishlistID, int aNbOfItems, Customer aCustomer)
  {
    wishlistID = aWishlistID;
    nbOfItems = aNbOfItems;
    if (aCustomer == null || aCustomer.getWishlist() != null)
    {
      throw new RuntimeException("Unable to create Wishlist due to aCustomer. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    customer = aCustomer;
  }

  public Wishlist(int aWishlistID, int aNbOfItems, String aUserNameForCustomer, int aPhoneNumnerForCustomer, String aAdressForCustomer, int aCustomerIDForCustomer)
  {
    wishlistID = aWishlistID;
    nbOfItems = aNbOfItems;
    customer = new Customer(aUserNameForCustomer, aPhoneNumnerForCustomer, aAdressForCustomer, aCustomerIDForCustomer, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWishlistID(int aWishlistID)
  {
    boolean wasSet = false;
    wishlistID = aWishlistID;
    wasSet = true;
    return wasSet;
  }

  public boolean setNbOfItems(int aNbOfItems)
  {
    boolean wasSet = false;
    nbOfItems = aNbOfItems;
    wasSet = true;
    return wasSet;
  }

  public int getWishlistID()
  {
    return wishlistID;
  }

  public int getNbOfItems()
  {
    return nbOfItems;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }

  public void delete()
  {
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "wishlistID" + ":" + getWishlistID()+ "," +
            "nbOfItems" + ":" + getNbOfItems()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}