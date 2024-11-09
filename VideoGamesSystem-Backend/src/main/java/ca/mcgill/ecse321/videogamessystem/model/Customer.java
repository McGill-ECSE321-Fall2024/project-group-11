/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 67 "model.ump"
// line 152 "model.ump"
public class Customer extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private int phoneNumber;
  private String adress;

  //Customer Associations
  private Wishlist wishlist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aUserName, String aEmail, String aPassword, int aPhoneNumber, String aAdress, Wishlist aWishlist)
  {
    super(aUserName, aEmail, aPassword);
    phoneNumber = aPhoneNumber;
    adress = aAdress;
    if (aWishlist == null || aWishlist.getCustomer() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aWishlist. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    wishlist = aWishlist;
  }

  public Customer(String aUserName, String aEmail, String aPassword, int aPhoneNumber, String aAdress, int aNbOfItemsForWishlist)
  {
    super(aUserName, aEmail, aPassword);
    phoneNumber = aPhoneNumber;
    adress = aAdress;
    wishlist = new Wishlist(aNbOfItemsForWishlist, this);
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