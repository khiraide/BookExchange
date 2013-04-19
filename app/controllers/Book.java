package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Controller for our Book model.
 * @author Keone Hiraide
 */
public class Book extends Controller {
  /**
   * Grabs the list of Books that are in the database.
   * @return Result containing the information of the Books in the database.
   */
  public static Result index() {
    List<models.Book> books = models.Book.find().findList();
    return ok(books.isEmpty() ? "No books" : books.toString());
  }
  
  /**
   * Grabs are Book according to its Id.
   * @param bookId The Id of the book that you would like to receive.
   * @return Result indicating that the Book you specified to grab was successful or not.
   */
  public static Result details(String bookId) {
      models.Book book = models.Book.find().where().eq("bookId", bookId).findUnique();
      return (book == null) ? notFound("No book found") : ok(book.toString());
  }
  
  /**
   * Creates a new Book, checks if its parameters are valid, and adds it to the database.
   * @return Result indicating that the Book added to the database was successful or not.
   */
  public static Result newBook() {
    // Create a book form and bind the request variables to it.
    Form<models.Book> bookForm = form(models.Book.class).bindFromRequest();
    // Validate the form values.
    if (bookForm.hasErrors()) {
      return badRequest("Book ID, book name, and book ISBN are required");
    }
    
    // form is OK, so make a Book and save it.
    models.Book book = bookForm.get();
    book.save();
    return ok(book.toString());
  }
  
  /**
   * Delete a Book according to its bookId from the database.
   * @param bookId The bookId of the Book that you want to delete.
   * @return Result indicating that the delete was successful or not.
   */
  public static Result delete(String bookId) {
    models.Book book = models.Book.find().where().eq("bookId", bookId).findUnique();
    if (book != null) {
      book.delete();
    }
    return ok();
  }
}
