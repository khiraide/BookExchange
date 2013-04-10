package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import play.db.ebean.Model;

@Entity
public class Request extends Model {
  
  private static final long serialVersionUID = 8859723603667790149L;
  @Id
  public long id;
  @ManyToOne(cascade=CascadeType.ALL)
  public Student student;
  
  @OneToOne(cascade=CascadeType.ALL)
  public Book book;
  public String condition;
  public double targetPrice;
  
  public Request(Student student, Book book, String condition, double targetPrice) {
    this.student = student;
    this.book = book;
    this.condition = condition;
    this.targetPrice = targetPrice;
  }
  
  public static Finder<Long, Request> find() {
    return new Finder<Long, Request>(Long.class, Request.class);
  }
}