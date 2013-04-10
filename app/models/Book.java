package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import play.db.ebean.Model;
@Entity
public class Book extends Model {
  private static final long serialVersionUID = 7268900706085963780L;
  @Id
  public long id;
  
  @OneToOne(mappedBy="book",cascade=CascadeType.ALL)
  public Request request;
  
  @OneToOne(mappedBy="book",cascade=CascadeType.ALL)
  public Offer offer;
  
  public String name;
  public String edition;
  public String ISBN;
  public double price;
  
  public Book(String name, String edition, String ISBN, double price) {
    this.name = name;
    this.edition = edition;
    this.ISBN = ISBN;
    this.price = price;
  }
  
  public static Finder<Long, Book> find() {
    return new Finder<Long, Book>(Long.class, Book.class);
  }
}
