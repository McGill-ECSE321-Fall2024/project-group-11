/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 17 "model.ump"
// line 196 "model.ump"
@Entity
@DiscriminatorValue("Owner")

public class Owner extends Staff
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Owner(String aUserName, int aIdNum)
  {
    super(aUserName, aIdNum);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}