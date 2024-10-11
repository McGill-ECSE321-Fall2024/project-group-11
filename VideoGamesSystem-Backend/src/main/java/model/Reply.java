/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.sql.Date;

// line 81 "model.ump"
// line 148 "model.ump"
public class Reply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reply Attributes
  private String reply;
  private Date replyDate;

  //Reply Associations
  private Review review;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reply(String aReply, Date aReplyDate, Review aReview)
  {
    reply = aReply;
    replyDate = aReplyDate;
    boolean didAddReview = setReview(aReview);
    if (!didAddReview)
    {
      throw new RuntimeException("Unable to create reply due to review. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReply(String aReply)
  {
    boolean wasSet = false;
    reply = aReply;
    wasSet = true;
    return wasSet;
  }

  public boolean setReplyDate(Date aReplyDate)
  {
    boolean wasSet = false;
    replyDate = aReplyDate;
    wasSet = true;
    return wasSet;
  }

  public String getReply()
  {
    return reply;
  }

  public Date getReplyDate()
  {
    return replyDate;
  }
  /* Code from template association_GetOne */
  public Review getReview()
  {
    return review;
  }
  /* Code from template association_SetOneToMany */
  public boolean setReview(Review aReview)
  {
    boolean wasSet = false;
    if (aReview == null)
    {
      return wasSet;
    }

    Review existingReview = review;
    review = aReview;
    if (existingReview != null && !existingReview.equals(aReview))
    {
      existingReview.removeReply(this);
    }
    review.addReply(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Review placeholderReview = review;
    this.review = null;
    if(placeholderReview != null)
    {
      placeholderReview.removeReply(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "reply" + ":" + getReply()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "replyDate" + "=" + (getReplyDate() != null ? !getReplyDate().equals(this)  ? getReplyDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "review = "+(getReview()!=null?Integer.toHexString(System.identityHashCode(getReview())):"null");
  }
}