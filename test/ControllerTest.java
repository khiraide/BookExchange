import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static play.test.Helpers.status;
import static play.test.Helpers.stop;
import java.util.HashMap;
import java.util.Map;
import models.Book;
import models.Offer;
import models.Student;
import models.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;


public class ControllerTest {
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
  public void testBookController() {
    // Test GET /book on an empty database.
    Result result = callAction(controllers.routes.ref.Book.index());
    assertTrue("Empty books", contentAsString(result).contains("No books"));
    
    // Test GET /book on a database containing a single book.
    String bookId = "Book-01";
    Book book = new Book(bookId, "name", "edition", "ISBN", 808);
    book.save();
    result = callAction(controllers.routes.ref.Book.index());
    assertTrue("One book", contentAsString(result).contains(bookId));
    
    // Test GET /book/Book-01
    result = callAction(controllers.routes.ref.Book.details(bookId));
    assertTrue("Book detail", contentAsString(result).contains(bookId));
    
    // Test GET /book/BadBookId and make sure we get a 404
    result = callAction(controllers.routes.ref.Book.details("BadBookId"));
    assertEquals("Book detail (bad)", NOT_FOUND, status(result));
    
    // Test POST /books (with simulated, valid form data).
    Map<String, String> bookData = new HashMap<String, String>();
    bookData.put("bookId", "Book-02");
    bookData.put("name", "Baby Goggia");
    bookData.put("edition", "first");
    bookData.put("ISBN", "testISBN");
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(bookData);
    result = callAction(controllers.routes.ref.Book.newBook(), request);
    assertEquals("Create a new book", OK, status(result));
    
    // Test POST /books (with simulated, invalid form data).
    request = fakeRequest();
    result = callAction(controllers.routes.ref.Book.newBook(), request);
    assertEquals("Create a bad book fails", BAD_REQUEST, status(result));
    
    // Test DELETE /books/Book-01 (a valid BookId)
    result = callAction(controllers.routes.ref.Book.delete(bookId));
    assertEquals("Delete current book OK", OK, status(result));
    result = callAction(controllers.routes.ref.Book.details(bookId));
    assertEquals("Deleted book gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.Book.delete(bookId));
    assertEquals("Delete missing book also OK", OK, status(result));
  }

  @Test
  public void testStudentController() {
    // Test GET /book on an empty database.
    Result result = callAction(controllers.routes.ref.Student.index());
    assertTrue("Empty students", contentAsString(result).contains("No students"));
    
    // Test student on a database containing a single student.
    String studentId = "Student-01";
    Student student = new Student(studentId, "name", "emailAddress");
    student.save();
    result = callAction(controllers.routes.ref.Student.index());
    assertTrue("One student", contentAsString(result).contains(studentId));
    
    // Test GET /student/Student-01
    result = callAction(controllers.routes.ref.Student.details(studentId));
    assertTrue("Student detail", contentAsString(result).contains(studentId));
    
    // Test GET /student/BadStudentId and make sure we get a 404
    result = callAction(controllers.routes.ref.Student.details("BadStudentId"));
    assertEquals("Student detail (bad)", NOT_FOUND, status(result));
    
    // Test POST /students (with simulated, valid form data).
    Map<String, String> studentData = new HashMap<String, String>();
    studentData.put("studentId", "Student-02");
    studentData.put("name", "Keone");
    studentData.put("emailAddress", "e@example.com");
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(studentData);
    result = callAction(controllers.routes.ref.Student.newStudent(), request);
    assertEquals("Create a new student", OK, status(result));
    
    // Test POST /students (with simulated, invalid form data).
    studentData = new HashMap<String, String>();
    studentData.put("studentId", "Student-03");
    studentData.put("name", "ChuckNorris"); // Look at controllers.Student.newStudent for this validation.
    studentData.put("emailAddress", "e@example.com");
    request = fakeRequest();
    request.withFormUrlEncodedBody(studentData);
    result = callAction(controllers.routes.ref.Student.newStudent(), request);
    // Can't have a student named "ChuckNorris".
    assertEquals("Create a bad student fails", BAD_REQUEST, status(result));
    
    // Test DELETE /students/Student-01 (a valid StudentId)
    result = callAction(controllers.routes.ref.Student.delete(studentId));
    assertEquals("Delete current student OK", OK, status(result));
    result = callAction(controllers.routes.ref.Student.details(studentId));
    assertEquals("Deleted student gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.Student.delete(studentId));
    assertEquals("Delete missing student also OK", OK, status(result));
  }
 
  @Test
  public void testOfferController() {
    // Test GET /book on an empty database.
    Result result = callAction(controllers.routes.ref.Offer.index());
    assertTrue("Empty offers", contentAsString(result).contains("No offers"));
    
    // Test GET /offer on a database containing a single offer.
    String studentId = "Student-01";
    Student student = new Student(studentId, "name", "emailAddress");
    student.save();
    String bookId = "Book-01";
    Book book = new Book(bookId, "name", "edition", "ISBN", 808);
    book.save();
    String offerId = "Offer-01";
    Offer offer = new Offer(offerId, book, student, "condition", 808.0);
    offer.save();
    result = callAction(controllers.routes.ref.Offer.index());
    assertTrue("One offer", contentAsString(result).contains(offerId));
    
    // Test GET /offer/Offer-01
    result = callAction(controllers.routes.ref.Offer.details(offerId));
    assertTrue("Offer detail", contentAsString(result).contains(offerId));
    
    // Test GET /offer/BadOfferId and make sure we get a 404
    result = callAction(controllers.routes.ref.Offer.details("BadOfferId"));
    assertEquals("Offer detail (bad)", NOT_FOUND, status(result));
    
    // Test POST /offers (with simulated, valid form data).
    Map<String, String> offerData = new HashMap<String, String>();
    offerData.put("offerId", "Offer-02");
    offerData.put("book.bookId", "bookId");
    offerData.put("book.name", "bookName");
    offerData.put("book.edition", "1");
    offerData.put("book.ISBN", "342526");
    offerData.put("book.price", "808");
    offerData.put("student.studentId", "studentId");
    offerData.put("student.name", "keone"); 
    offerData.put("student.emailAddress", "example.com");
    offerData.put("condition", "condition");
    offerData.put("targetPrice", "20.0");
    FakeRequest request = fakeRequest();
    request.withFormUrlEncodedBody(offerData);
    result = callAction(controllers.routes.ref.Offer.newOffer(), request);
    assertEquals("Create a new offer", OK, status(result));
    assertTrue("The second student added should be named: keone", Student.find().findList().get(1).getName().equals("keone"));
    assertTrue("There should  be 2 students", Student.find().findList().size() == 2);
    assertTrue("targetPrice should be 20.0", Offer.find().findList().get(1).getTargetPrice() == 20.0);
    // Test POST /offers (with simulated, invalid form data).
    request = fakeRequest();
    result = callAction(controllers.routes.ref.Offer.newOffer(), request);
    assertEquals("Create a bad offer fails", BAD_REQUEST, status(result));
    
    // Test DELETE /offers/Offer-01 (a valid OfferId)
    result = callAction(controllers.routes.ref.Offer.delete(offerId));
    assertEquals("Delete current offer OK", OK, status(result));
    result = callAction(controllers.routes.ref.Offer.details(offerId));
    assertEquals("Deleted offer gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.Offer.delete(offerId));
    assertEquals("Delete missing offer also OK", OK, status(result));
  }

  @Test
  public void testRequestController() {
    // Test GET /book on an empty database.
    Result result = callAction(controllers.routes.ref.Request.index());
    assertTrue("Empty requests", contentAsString(result).contains("No requests"));
    
    // Test GET /request on a database containing a single request.
    String studentId = "Student-06";
    Student student = new Student(studentId, "name", "emailAddress");
    String bookId = "Book-01";
    Book book = new Book(bookId, "name", "edition", "ISBN", 808);
    String requestId = "Request-01";
    Request request = new Request(requestId, book, student, "condition", 808.0);
    request.save();
    result = callAction(controllers.routes.ref.Request.index());
    assertTrue("One request", contentAsString(result).contains(requestId));
    
    // Test GET /request/Request-01
    result = callAction(controllers.routes.ref.Request.details(requestId));
    assertTrue("Request detail", contentAsString(result).contains(requestId));
    
    // Test GET /request/BadRequestId and make sure we get a 404
    result = callAction(controllers.routes.ref.Request.details("BadRequestId"));
    assertEquals("Request detail (bad)", NOT_FOUND, status(result));
    
    // Test POST /requests (with simulated, valid form data).
    Map<String, String> requestData = new HashMap<String, String>();
    requestData.put("requestId", "Request-02");
    requestData.put("book.bookId", "bookId");
    requestData.put("book.name", "bookName");
    requestData.put("book.edition", "1");
    requestData.put("book.ISBN", "342526");
    requestData.put("book.price", "808");
    requestData.put("student.studentId", "studentId2");
    requestData.put("student.name", "keone"); 
    requestData.put("student.emailAddress", "example.com");
    requestData.put("condition", "good");
    requestData.put("targetPrice", "90.0");
    FakeRequest fakeRequest = fakeRequest();
    fakeRequest.withFormUrlEncodedBody(requestData);
    result = callAction(controllers.routes.ref.Request.newRequest(), fakeRequest);
    assertEquals("Create a new request", OK, status(result));
    
    // Test POST /requests (with simulated, invalid form data).
    fakeRequest = fakeRequest();
    result = callAction(controllers.routes.ref.Request.newRequest(), fakeRequest);
    assertEquals("Create a bad request fails", BAD_REQUEST, status(result));
    
    // Test DELETE /requests/Request-01 (a valid RequestId)
    result = callAction(controllers.routes.ref.Request.delete(requestId));
    assertEquals("Delete current request OK", OK, status(result));
    result = callAction(controllers.routes.ref.Request.details(requestId));
    assertEquals("Deleted request gone", NOT_FOUND, status(result));
    result = callAction(controllers.routes.ref.Request.delete(requestId));
    assertEquals("Delete missing request also OK", OK, status(result));
  }
 
}
