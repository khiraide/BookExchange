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
public class Book extends Model {
  /**
   * serialVersionUID used by the database.
   */
  private static final long serialVersionUID = 7268900706085963780L;
  /** 
   * Database primary key 
   */
  @Id
  private long primaryKey;
  /**
   * This Book can be requested many times.
   */
  @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
  private List<Request> requests = new ArrayList<>();
  /**
   * This Book can be offered many times.
   */
  @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
  private List<Offer> offers = new ArrayList<>();
  /**
   * Natural Id for this Book.
   */
  @Required
  private String bookId;
  /**
   * This Book's title.
   */
  @Required
  private String name;
  /**
   * This Book's edition Eg: First edition, Second edition.
   */
  private String edition;
  @Required
  /**
   * International Standard Book Number of this Book.
   */
  private String ISBN;
  /**
   * This Book's market price.
   */
  private double price;
  
  /**
   * This Book's constructor. 
   * @param bookId Natural Id for this Book.
   * @param name This Book's title.
   * @param edition This Book's edition. Eg: First edition, Second edition.
   * @param ISBN International standard book number of this Book.
   * @param price This Book's market price.
   */
  public Book(String bookId, String name, String edition, String ISBN, double price) {
    this.bookId = bookId;
    this.name = name;
    this.edition = edition;
    this.ISBN = ISBN;
    this.price = price;
  }
  
  /**
   * Helper for Ebean queries.
   * @return Results of our query.
   */
  public static Finder<Long, Book> find() {
    return new Finder<Long, Book>(Long.class, Book.class);
  }
  
  /**
   * Returns information about this Book.
   */
  @Override
  public String toString() {
    return String.format("[Book %s %s %s %s %s]", this.bookId, this.name, this.edition, 
        this.ISBN, this.price);
  }

  /**
   * Get this Book's primaryKey field value.
   * @return This Book's primaryKey field value.
   */
  public long getPrimaryKey() {
    return this.primaryKey;
  }

  /**
   * Set this Book's primaryKey field value.
   * @param primaryKey This Book's primaryKey field value.
   */
  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }

  /**
   * Gets this Book's natural Id.
   * @return This Book's natural Id.
   */
  public String getBookId() {
    return this.bookId;
  }

  /**
   * Sets this Book's natural Id.
   * @param bookId The natural Id that you want to set.
   */
  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  /**
   * Gets this Book's title.
   * @return This Book's title.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the title of this Book.
   * @param name The title of this Book.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets this Book's edition.
   * @return This Book's edition.
   */
  public String getEdition() {
    return this.edition;
  }

  /**
   * Sets this Book's edition.
   * @param edition This Book's edition.
   */
  public void setEdition(String edition) {
    this.edition = edition;
  }

  /**
   * Gets this Book's International Standard Book Number.
   * @return
   */
  public String getISBN() {
    return this.ISBN;
  }

  /**
   * Sets this Book's International Standard Book Number.
   * @param ISBN This Book's International Standard Book Number.
   */
  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
  }

  /**
   * Gets the market price of this Book.
   * @return The market price of this Book.
   */
  public double getPrice() {
    return this.price;
  }

  /**
   * Sets the market price of this Book.
   * @param price The market price of this Book.
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Gets the list of requests associated with this Book.
   * @return The list of requests associated with this Book.
   */
  public List<Request> getRequests() {
    return requests;
  }

  /**
   * Sets a list of requests to be associated with this Book.
   * @param requests The list of requests that you want associated with this Book.
   */
  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }

  /**
   * Gets the list of offers associated with this Book.
   * @return The list of offers associated with this Book.
   */
  public List<Offer> getOffers() {
    return offers;
  }

  /**
   * Sets the list of offers associated with this Book.
   * @param offers The list of offers associated with this Book.
   */
  public void setOffers(List<Offer> offers) {
    this.offers = offers;
  }
}
