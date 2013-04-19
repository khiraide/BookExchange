package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Student extends Model {

  /**
   * serialVersionUID used in the database.
   */
  private static final long serialVersionUID = 8373534522150582990L;
 
  /**
   * Primary key used in the database.
   */
  @Id
  private long primaryKey;
  /**
   * Zero or Many Requests are issued by a single Student.
   */
  @OneToMany(mappedBy="student",cascade=CascadeType.ALL)
  private List<Request> requests = new ArrayList<>();
  
  /**
   * Zero or Many Offers are issued by a single Student.
   */
  @OneToMany(mappedBy="student",cascade=CascadeType.ALL)
  private List<Offer> offers = new ArrayList<>();
  
  /**
   * Natural ordering Id for Students.
   */
  @Required
  private String studentId;
  
  /**
   * This Student's name Eg: John.
   */
  @Required
  private String name;
  
  /**
   * This Student's emailAddress. 
   */
  @Required
  private String emailAddress;
  
  /**
   * Constructor for this Student.
   * @param studentId Natural ordering Id associated with this Student.
   * @param name This Student's name.
   * @param emailAddress This Student's e-mail address.
   */
  public Student(String studentId, String name, String emailAddress) {
    this.studentId = studentId;
    this.name = name;
    this.emailAddress = emailAddress;
  }
  
  /**
   * Helper for Ebean queries.
   * @return Results of our query.
   */
  public static Finder<Long, Student> find() {
    return new Finder<Long, Student>(Long.class, Student.class);
  }
  
  /**
   * Returns information about this Student.
   */
  @Override
  public String toString() {
    return String.format("[Student %s %s %s]", this.studentId, this.name, this.emailAddress);
  }

  /**
   * Gets the list of requests associated with this Student.
   * @return The list of requests associated with this Student.
   */
  public List<Request> getRequests() {
    return requests;
  }

  /**
   * Sets the list of requests associated with this Student.
   * @param requests The list of requests associated with this Student.
   */
  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }

  /**
   * Gets the list of offers associated with this Student.
   * @return The list of offers associated with this Student.
   */
  public List<Offer> getOffers() {
    return offers;
  }

  /**
   * Sets the list of offers associated with this Student.
   * @param offers The list of offers associated with this Student.
   */
  public void setOffers(List<Offer> offers) {
    this.offers = offers;
  }

  /**
   * Gets the natural ordering Id of this Student.
   * @return The natural ordering Id of this Student.
   */
  public String getStudentId() {
    return studentId;
  }

  /**
   * Sets the natural ordering Id of this Student.
   * @param studenId The natural ordering Id of this Student.
   */
  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  /**
   * Gets the name of this Student.
   * @return The name of this Student.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this Student.
   * @param name The name of this Student.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the email address of this Student.
   * @return The email address of this Student.
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Sets the email address of this Student.
   * @param emailAddress The email address of this Student.
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * Gets the primary key of this Student.
   * @return The primary key of this Student.
   */
  public long getPrimaryKey() {
    return primaryKey;
  }

  /**
   * Sets the primary key of this Student.
   * @param primaryKey The primary key of this Student.
   */
  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }
}
