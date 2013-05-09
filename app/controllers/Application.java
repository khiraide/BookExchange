package controllers;

import java.util.List;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
      
    public static Result index() {
        return ok(index.render("test"));
    }
    /*
    public static Result listing() {
      List<models.Book> books = models.Book.find().all();
      List<models.Request> requests = models.Request.find().all();
      List<models.Offer> offers = models.Offer.find().all();
      List<models.Student> students = models.Student.find().all();
      return ok(listing.render(books, offers, requests, students));
  }*/
  
}
