/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;


// line 102 "model.ump"
// line 158 "model.ump"
public class SpecificReviewRating
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificReviewRating Attributes
  private ReviewRating reviewRating;

  //SpecificReviewRating Associations
  private Review review;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificReviewRating(ReviewRating aReviewRating, Review aReview, Customer aCustomer)
  {
    reviewRating = aReviewRating;
    if (!setReview(aReview))
    {
      throw new RuntimeException("Unable to create SpecificReviewRating due to aReview. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setCustomer(aCustomer))
    {
      throw new RuntimeException("Unable to create SpecificReviewRating due to aCustomer. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReviewRating(ReviewRating aReviewRating)
  {
    boolean wasSet = false;
    reviewRating = aReviewRating;
    wasSet = true;
    return wasSet;
  }

  public ReviewRating getReviewRating()
  {
    return reviewRating;
  }
  /* Code from template association_GetOne */
  public Review getReview()
  {
    return review;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setReview(Review aNewReview)
  {
    boolean wasSet = false;
    if (aNewReview != null)
    {
      review = aNewReview;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (aNewCustomer != null)
    {
      customer = aNewCustomer;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    review = null;
    customer = null;
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reviewRating" + "=" + (getReviewRating() != null ? !getReviewRating().equals(this)  ? getReviewRating().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "review = "+(getReview()!=null?Integer.toHexString(System.identityHashCode(getReview())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}