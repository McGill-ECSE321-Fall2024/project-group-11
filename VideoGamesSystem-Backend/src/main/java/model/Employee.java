/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 12 "model.ump"
// line 191 "model.ump"
@Entity
@DiscriminatorValue("Employee")
public class Employee extends Staff
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aUserName, int aIdNum)
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