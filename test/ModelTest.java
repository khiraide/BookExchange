import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;
import java.util.List;
import models.Book;
import models.Offer;
import models.Request;
import models.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.test.FakeApplication;
//import views.html.play20.book;


public class ModelTest {
private FakeApplication application;
  
  @Before
  public void startApp() {
    application = fakeApplication(inMemoryDatabase());
    start(application);
  }
  
  @After
  public void stopApp() {
    stop(application);
  }
  
  @Test
  public void testModel() {
    // Create 1 student that's associated with a request;
    Student student = new Student("studentId","Keone", "keone@example.com");
    student.save();
    Book book1 = new Book("book1Id","Book1Title", "edition", "ISBN", 10.00);
    book1.save();
    Request request = new Request("requestId", book1, student, "good", 5.00);
    request.save();
    student.getRequests().add(request);
    student.save();
    book1.getRequests().add(request);
    book1.save();
    
    // Same student is associated with an offer.
    Book book2 = new Book("book2Id","Book2Title", "edition", "ISBN", 7.00);
    book2.save();
    Offer offer = new Offer("offerId", book2, student, "good", 2.00);
    offer.save();
    student.getOffers().add(offer);
    student.save();
    book2.getOffers().add(offer);
    book2.save();
    
    // Persist the sample model by saving all entities and relationships.
   
    // Retreive the entire model from the database.
    List<Student> students = Student.find().findList();
    List<Book> books = Book.find().findList();
    List<Request> requests = Request.find().findList();
    List<Offer> offers = Offer.find().findList();
    
    // Check that we've recovered all our entities.
    assertEquals("Checking book", books.size(), 2);
    assertEquals("Checking offer", offers.size(), 1);
    assertEquals("Checking request", requests.size(), 1);
    assertEquals("Checking student", students.size(), 1);
    
    // Check that we've recovered all relationships.
    assertEquals("Student-Request", students.get(0).getRequests().get(0),requests.get(0));
    assertEquals("Request-Student", requests.get(0).getStudent(), students.get(0));
    assertEquals("Student-Offer", students.get(0).getOffers().get(0),offers.get(0));
    assertEquals("Offer-Student", offers.get(0).getStudent(), students.get(0));
    assertEquals("Request-Book", requests.get(0).getBook(), books.get(0));
    assertEquals("Book-Request", books.get(0).getRequests().get(0), requests.get(0));
    assertEquals("Offer-Book", offers.get(0).getBook(), books.get(1));
    assertEquals("Book-Offer", books.get(1).getOffers().get(0), offers.get(0));
    
    // Some code to illustrate model manipulation with ORM
   offer.delete();
   assertTrue("Student should still exist", Student.find().findList().size() == 1);
   assertTrue("Student should no longer have an offer", Student.find().findList().get(0).getOffers().isEmpty());
   assertTrue("There shouldn't be any offers", Offer.find().findList().isEmpty());
   assertTrue("Book count should be 1", Book.find().findList().size() == 1);
   request.delete();
   assertTrue("Student should still exist", Student.find().findList().size() == 1);
   assertTrue("Student should no longer have a request", Student.find().findList().get(0).getRequests().isEmpty());
   assertTrue("There shouldn't be any requests", Request.find().findList().isEmpty());
   assertTrue("Book count should be 0", Book.find().findList().size() == 0);
   student.delete();
   assertTrue("Students shouldn't exist anymore", Student.find().findList().isEmpty());
    
  }
}
