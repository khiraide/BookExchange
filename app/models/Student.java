package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

@Entity
public class Student extends Model {

  private static final long serialVersionUID = 8373534522150582990L;
 
  @Id
  public long id;
  @OneToMany(mappedBy="student",cascade=CascadeType.ALL)
  public List<Request> requests = new ArrayList<>();
  
  @OneToMany(mappedBy="student",cascade=CascadeType.ALL)
  public List<Offer> offers = new ArrayList<>();
  public String name;
  public String emailAddress;
  
  public Student(String name, String emailAddress) {
    this.name = name;
    this.emailAddress = emailAddress;
  }
  
  public static Finder<Long, Student> find() {
    return new Finder<Long, Student>(Long.class, Student.class);
  }
}
