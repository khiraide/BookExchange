package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
/**
 * Controller for our Offer model.
 * @author Keone Hiraide
 */
public class Offer extends Controller {
  /**
   * Grabs the list of Offers that are in the database.
   * @return Result containing the information of the Offers in the database.
   */
  public static Result index() {
    List<models.Offer> offers = models.Offer.find().findList();
    return ok(offers.isEmpty() ? "No offers" : offers.toString());
  }
  
  /**
   * Grabs are Offer according to its Id.
   * @param offerId The Id of the offer that you would like to receive.
   * @return Result indicating that the Offer you specified to grab was successful or not.
   */
  public static Result details(String offerId) {
      models.Offer offer = models.Offer.find().where().eq("offerId", offerId).findUnique();
      return (offer == null) ? notFound("No offer found") : ok(offer.toString());
  }
  
  /**
   * Creates a new Offer, checks if its parameters are valid, and adds it to the database.
   * @return Result indicating that the Offer added to the database was successful or not.
   */
  public static Result newOffer() {
    // Create a offer form and bind the offer variables to it.
    Form<models.Offer> offerForm = form(models.Offer.class).bindFromRequest();
    // Validate the form values.
    if (offerForm.hasErrors()) {
      return badRequest("offerId, book info, student info, " +
      		"required book condition, and target price are required.");
    }
    
    // form is OK, so make a Offer and save it.
    models.Offer offer = offerForm.get();
    offer.save();
    return ok(offer.toString());
  }
  
  /**
   * Delete a Offer according to its offerId from the database.
   * @param offerId The offerId of the Offer that you want to delete.
   * @return Result indicating that the delete was successful or not.
   */
  public static Result delete(String offerId) {
    models.Offer offer = models.Offer.find().where().eq("offerId", offerId).findUnique();
    if (offer != null) {
      offer.delete();
    }
    return ok();
  }
}
