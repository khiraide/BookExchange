package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Request extends Model {
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
   * Many Requests can be made to a single Student.
   */
  @Required
  @ManyToOne(cascade=CascadeType.PERSIST)
  private Student student;
  
  /**
   * Many Requests can be made to a single Book.
   */
  @Required
  @ManyToOne(cascade=CascadeType.ALL)
  private Book book;
  
  /**
   * Natural Id for Requests.
   */
  @Required
  private String requestId;
  
  /**
   * The condition of the book that this is being requested.
   */
  @Required
  private String condition;
  
  /**
   * This Requests book target price that is being requested.
   */
  @Required
  private double targetPrice;
  
  /**
   * Constructor of this Request.
   * @param requestId Natural Id for Requests.
   * @param book Book associated with this Request. 
   * @param student Student associated with this Request.
   * @param condition The condition of the book that this is being requested.
   * @param targetPrice This Requests book target price that is being requested.
   */
  public Request(String requestId, Book book, Student student, String condition, double targetPrice) {
    this.book = book;
    this.requestId = requestId;
    this.student = student;
    this.condition = condition;
    this.targetPrice = targetPrice;
  }
  
  /**
   * Helper for Ebean queries.
   * @return Results of our query.
   */
  public static Finder<Long, Request> find() {
    return new Finder<Long, Request>(Long.class, Request.class);
  }
  
  /**
   * Returns information about this Book.
   */
  @Override
  public String toString() {
    return String.format("[Request %s %s %s %s %s]", this.requestId, this.book.getBookId(), 
        this.student.getStudentId(), this.condition, this.targetPrice);
  }

  /**
   * Get this Request's primaryKey field value.
   * @return This Request's primaryKey field value.
   */
  public long getPrimaryKey() {
    return primaryKey;
  }

  /**
   * Set this Request's primaryKey field value.
   * @param primaryKey This Request's primaryKey field value.
   */
  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }

  /**
   * Get the Student instance associated with this Request.
   * @return The Student instance associated with this Request.
   */
  public Student getStudent() {
    return student;
  }

  /**
   * Set the Student instance associated with this Request.
   * @param student The Student instance associated with this Request.
   */
  public void setStudent(Student student) {
    this.student = student;
  }

  /**
   * Get the natural Id associated with this Request.
   * @return The natural Id associated with this Request.
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * Set the natural Id associated with this Request.
   * @param requestId The natural Id associated with this Request.
   */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /**
   * Get the target price associated with this Request.
   * @return The target price associated with this Request.
   */
  public double getTargetPrice() {
    return targetPrice;
  }

  /**
   * Set the natural Id associated with this Request.
   * @param targetPrice The natural Id associated with this Request.
   */
  public void setTargetPrice(double targetPrice) {
    this.targetPrice = targetPrice;
  }

  /**
   * Get the Book instance associated with this Request.
   * @return The Book instance associated with this Request.
   */
  public Book getBook() {
    return book;
  }

  /**
   * Set the Book instance associated with this Request.
   * @param book The Book instance associated with this Request.
   */
  public void setBook(Book book) {
    this.book = book;
  }
  
  /**
   * Get the condition of the book associated with this Request.
   * @return The condition of the book associated with this Request.
   */
  public String getCondition() {
    return condition;
  }

  /**
   * Set the condition of the book associated with this Request.
   * @param condition The condition of the book associated with this Request.
   */
  public void setCondition(String condition) {
    this.condition = condition;
  }
}
