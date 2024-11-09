/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 94 "model.ump"
// line 187 "model.ump"
public class Wishlist
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Wishlist Attributes
  private int nbOfItems;

  //Wishlist Associations
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Wishlist(int aNbOfItems, Customer aCustomer)
  {
    nbOfItems = aNbOfItems;
    if (aCustomer == null || aCustomer.getWishlist() != null)
    {
      throw new RuntimeException("Unable to create Wishlist due to aCustomer. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    customer = aCustomer;
  }

  public Wishlist(int aNbOfItems, String aUserNameForCustomer, String aEmailForCustomer, String aPasswordForCustomer, int aPhoneNumberForCustomer, String aAdressForCustomer)
  {
    nbOfItems = aNbOfItems;
    customer = new Customer(aUserNameForCustomer, aEmailForCustomer, aPasswordForCustomer, aPhoneNumberForCustomer, aAdressForCustomer, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNbOfItems(int aNbOfItems)
  {
    boolean wasSet = false;
    nbOfItems = aNbOfItems;
    wasSet = true;
    return wasSet;
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
            "nbOfItems" + ":" + getNbOfItems()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}