package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import play.db.ebean.Model;

@Entity
public class Offer extends Model {
  
  private static final long serialVersionUID = -6466424005957100597L;
  @Id
  public long id;
  @ManyToOne(cascade=CascadeType.ALL)
  public Student student;
  @OneToOne(cascade=CascadeType.ALL)
  public Book book;
  public String condition;
  public double targetPrice;
  public Offer(Student student, Book book, String condition, double targetPrice) {
    this.student = student;
    this.book = book;
    this.condition = condition;
    this.targetPrice = targetPrice;
  }
  
  public static Finder<Long, Offer> find() {
    return new Finder<Long, Offer>(Long.class, Offer.class);
  }
}
