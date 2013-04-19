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

  private static final long serialVersionUID = 8373534522150582990L;
 
  @Id
  private long primaryKey;
  @OneToMany(mappedBy="student",cascade=CascadeType.ALL)
  private List<Request> requests = new ArrayList<>();
  
  @OneToMany(mappedBy="student",cascade=CascadeType.ALL)
  private List<Offer> offers = new ArrayList<>();
  
  @Required
  private String studentId;
  @Required
  private String name;
  @Required
  private String emailAddress;
  
  public Student(String studentId, String name, String emailAddress) {
    this.studentId = studentId;
    this.name = name;
    this.emailAddress = emailAddress;
  }
  
  public static Finder<Long, Student> find() {
    return new Finder<Long, Student>(Long.class, Student.class);
  }
  
  public String toString() {
    return String.format("[Student %s %s %s]", this.studentId, this.name, this.emailAddress);
  }

  public List<Request> getRequests() {
    return requests;
  }

  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }

  public List<Offer> getOffers() {
    return offers;
  }

  public void setOffers(List<Offer> offers) {
    this.offers = offers;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public long getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }
}
