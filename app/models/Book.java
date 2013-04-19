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
  private static final long serialVersionUID = 7268900706085963780L;
  @Id
  private long primaryKey;
  
  @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
  private List<Request> requests = new ArrayList<>();
  
  @OneToMany(mappedBy="book", cascade=CascadeType.ALL)
  private List<Offer> offers = new ArrayList<>();
  
  @Required
  private String bookId;
  @Required
  private String name;
  private String edition;
  @Required
  private String ISBN;
  private double price;
  
  public Book(String bookId, String name, String edition, String ISBN, double price) {
    this.bookId = bookId;
    this.name = name;
    this.edition = edition;
    this.ISBN = ISBN;
    this.price = price;
  }
  
  public static Finder<Long, Book> find() {
    return new Finder<Long, Book>(Long.class, Book.class);
  }
  
  public String toString() {
    return String.format("[Book %s %s %s %s %s]", this.bookId, this.name, this.edition, 
        this.ISBN, this.price);
  }

  public long getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(long primaryKey) {
    this.primaryKey = primaryKey;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEdition() {
    return edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String iSBN) {
    ISBN = iSBN;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
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
}
