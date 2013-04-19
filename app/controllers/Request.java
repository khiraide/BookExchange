package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
/**
 * Controller for our Request model.
 * @author Keone Hiraide
 */
public class Request extends Controller {
  /**
   * Grabs the list of Requests that are in the database.
   * @return Result containing the information of the Requests in the database.
   */
  public static Result index() {
    List<models.Request> requests = models.Request.find().findList();
    return ok(requests.isEmpty() ? "No requests" : requests.toString());
  }
  
  /**
   * Grabs are Request according to its Id.
   * @param requestId The Id of the request that you would like to receive.
   * @return Result indicating that the Request you specified to grab was successful or not.
   */
  public static Result details(String requestId) {
      models.Request request = models.Request.find().where().eq("requestId", requestId).findUnique();
      return (request == null) ? notFound("No request found") : ok(request.toString());
  }
  
  /**
   * Creates a new Request, checks if its parameters are valid, and adds it to the database.
   * @return Result indicating that the Request added to the database was successful or not.
   */
  public static Result newRequest() {
    // Create a request form and bind the request variables to it.
    Form<models.Request> requestForm = form(models.Request.class).bindFromRequest();
    // Validate the form values.
    if (requestForm.hasErrors()) {
      return badRequest("requestId, book info, student info, " +
      		"required book condition, and target price are required.");
    }
    
    // form is OK, so make a Request and save it.
    models.Request request = requestForm.get();
    request.save();
    return ok(request.toString());
  }
  
  /**
   * Delete a Request according to its requestId from the database.
   * @param requestId The requestId of the Request that you want to delete.
   * @return Result indicating that the delete was successful or not.
   */
  public static Result delete(String requestId) {
    models.Request request = models.Request.find().where().eq("requestId", requestId).findUnique();
    if (request != null) {
      request.delete();
    }
    return ok();
  }
}
