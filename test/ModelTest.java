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
    Student student = new Student("Keone", "keone@example.com");
    Book book1 = new Book("Book1Title", "edition", "ISBN", 10.00);
    Request request = new Request(student, book1, "new", 5.00);
    student.requests.add(request);
    
    // Same student is associated with an offer.
    Book book2 = new Book("Book2Title", "edition", "ISBN", 7.00);
    Offer offer = new Offer(student, book2, "worn", 2.00);
    student.offers.add(offer);
    
    // Persist the sample model by saving all entities and relationships.
   
    student.save();
    book1.save();
    request.save();
    book2.save();
    offer.save();
    
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
    assertEquals("Student-Request", students.get(0).requests.get(0),requests.get(0));
    assertEquals("Request-Student", requests.get(0).student, students.get(0));
    assertEquals("Request-Book", requests.get(0).book, books.get(0));
    assertEquals("Book-Request", books.get(0).request, requests.get(0));
    assertEquals("Offer-Book", offers.get(0).book, books.get(1));
    assertEquals("Book-Offer", books.get(1).offer, offers.get(0));
    
    // Some code to illustrate model manipulation with ORM.
    student.offers.clear();
    student.save();
    assertTrue("Previously retrieved student still has request", !students.get(0).offers.isEmpty());
    offer.delete();
    assertTrue("No more rofferss in database", Offer.find().findList().isEmpty());
    
  }
}
