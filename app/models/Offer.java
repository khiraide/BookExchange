package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Offer extends Model {
  /**
   * serialVersionUID used by the database.
   */
  private static final long serialVersionUID = -6466424005957100597L;
  /**
   * Database primary key.
   */
  @Id
  private long primaryKey;
  
  /**
   * Many Offers can be made to a single Student.
   */
  @Required
  @ManyToOne(cascade=CascadeType.PERSIST)
  private Student student;
  
  /**
   * Many Offers can be made to a single Book.
   */
  @Required
  @ManyToOne(cascade=CascadeType.ALL)
  private Book book;
  
  /**
   * Natural Id for Offers.
   */
  @Required
  private String offerId;
  
  /**
   * The condition of the book that this is being offered.
   */
  @Required
  private String condition;
  
  /**
   * This Offers book target price that is being offered.
   */
  @Required
  private double targetPrice;
  
  /**
   * Constructor of this Offer.
   * @param offerId Natural Id for Offers.
   * @param book Book associated with this Offer. 
   * @param student Student associated with this Offer.
   * @param condition The condition of the book that this is being offered.
   * @param targetPrice This Offers book target price that is being offered.
   */
  public Offer(String offerId, Book book, Student student, String condition, double targetPrice) {
    this.book = book;
    this.offerId = offerId;
    this.student = student;
    this.condition = condition;
    this.targetPrice = targetPrice;
  }
  
  /**
   * Helper for Ebean queries.
   * @return Results of our query.
   */
  public static Finder<Long, Offer> find() {
    return new Finder<Long, Offer>(Long.class, Offer.class);
  }
  
  /**
   * Returns information about this Book.
   */
  @Override
  public String toString() {
    return String.format("[Offer %s %s %s %s %s]", this.offerId, this.book.getBookId(), 
        this.student.getStudentId(), this.condition, this.targetPrice);
  }

  /**
   * Get this Offer's primaryKey field value.
   * @return This Offer's primaryKey field value.
   */
  public long getPrimaryKey() {
    return primaryKey;
  }

  /**
   * Set this Offer's primaryKey field value.
   * @param primaryKey This Offer's primaryKey field value.
   */
  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }

  /**
   * Get the Student instance associated with this Offer.
   * @return The Student instance associated with this Offer.
   */
  public Student getStudent() {
    return student;
  }

  /**
   * Set the Student instance associated with this Offer.
   * @param student The Student instance associated with this Offer.
   */
  public void setStudent(Student student) {
    this.student = student;
  }

  /**
   * Get the natural Id associated with this Offer.
   * @return The natural Id associated with this Offer.
   */
  public String getOfferId() {
    return offerId;
  }

  /**
   * Set the natural Id associated with this Offer.
   * @param offerId The natural Id associated with this Offer.
   */
  public void setOfferId(String offerId) {
    this.offerId = offerId;
  }

  /**
   * Get the target price associated with this Offer.
   * @return The target price associated with this Offer.
   */
  public double getTargetPrice() {
    return targetPrice;
  }

  /**
   * Set the natural Id associated with this Offer.
   * @param targetPrice The natural Id associated with this Offer.
   */
  public void setTargetPrice(double targetPrice) {
    this.targetPrice = targetPrice;
  }

  /**
   * Get the Book instance associated with this Offer.
   * @return The Book instance associated with this Offer.
   */
  public Book getBook() {
    return book;
  }

  /**
   * Set the Book instance associated with this Offer.
   * @param book The Book instance associated with this Offer.
   */
  public void setBook(Book book) {
    this.book = book;
  }
  
  /**
   * Get the condition of the book associated with this Offer.
   * @return The condition of the book associated with this Offer.
   */
  public String getCondition() {
    return condition;
  }

  /**
   * Set the condition of the book associated with this Offer.
   * @param condition The condition of the book associated with this Offer.
   */
  public void setCondition(String condition) {
    this.condition = condition;
  }
}
