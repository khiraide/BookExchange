package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Request extends Model {
  
  private static final long serialVersionUID = 8859723603667790149L;
  @Id
  private long primaryKey;
  
  @Required
  @ManyToOne(cascade=CascadeType.PERSIST)
  private Student student;
  
  @Required
  @ManyToOne(cascade=CascadeType.ALL)
  private Book book;
  
  @Required
  private String condition;
  
  @Required
  private String requestId;

  @Required
  private double targetPrice;
  
  public Request(String requestId, Book book, Student student, String condition, double targetPrice) {
    this.requestId = requestId;
    this.book = book;
    this.student = student;
    this.condition = condition;
    this.targetPrice = targetPrice;
  }
  
  public static Finder<Long, Request> find() {
    return new Finder<Long, Request>(Long.class, Request.class);
  }
  
  public String toString() {
    return String.format("[Request %s]", this.requestId);
  }

  public long getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public double getTargetPrice() {
    return targetPrice;
  }

  public void setTargetPrice(double targetPrice) {
    this.targetPrice = targetPrice;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }
}