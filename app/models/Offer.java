package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Offer extends Model {
  
  private static final long serialVersionUID = -6466424005957100597L;
  @Id
  private long primaryKey;
  
  @Required
  @ManyToOne(cascade=CascadeType.PERSIST)
  private Student student;
  
  @Required
  @ManyToOne(cascade=CascadeType.ALL)
  private Book book;
  
  @Required
  private String offerId;
  
  @Required
  private String condition;
  
  @Required
  private double targetPrice;
  
  public Offer(String offerId, Book book, Student student, String condition, double targetPrice) {
    this.book = book;
    this.offerId = offerId;
    this.student = student;
    this.condition = condition;
    this.targetPrice = targetPrice;
  }
  
  public static Finder<Long, Offer> find() {
    return new Finder<Long, Offer>(Long.class, Offer.class);
  }
  
  public String toString() {
    return String.format("[Offer %s %s %s %s %s]", this.offerId, this.book.getBookId(), 
        this.student.getStudentId(), this.condition, this.targetPrice);
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

  public String getOfferId() {
    return offerId;
  }

  public void setOfferId(String offerId) {
    this.offerId = offerId;
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
