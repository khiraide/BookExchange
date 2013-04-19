package controllers;

import static play.data.Form.form;
import java.util.List;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Controller for our Student model.
 * @author Keone Hiraide
 */
public class Student extends Controller {
  /**
   * Grabs the list of Students that are in the database.
   * @return Result containing the information of the Students in the database.
   */
  public static Result index() {
    List<models.Student> students = models.Student.find().findList();
    return ok(students.isEmpty() ? "No students" : students.toString());
  }
  
  /**
   * Grabs are Student according to its Id.
   * @param studentId The Id of the student that you would like to receive.
   * @return Result indicating that the Student you specified to grab was successful or not.
   */
  public static Result details(String studentId) {
      models.Student student = models.Student.find().where().eq("studentId", studentId).findUnique();
      return (student == null) ? notFound("No student found") : ok(student.toString());
  }
  
  /**
   * Creates a new Student, checks if its parameters are valid, and adds it to the database.
   * @return Result indicating that the Student added to the database was successful or not.
   */
  public static Result newStudent() {
    // Create a student form and bind the request variables to it.
    Form<models.Student> studentForm = form(models.Student.class).bindFromRequest();
    // Validate the form values.
    if("ChuckNorris".equals(studentForm.field("name").value())) {
      studentForm.reject("name", "You can't be ChuckNorris...");
    }
    if (studentForm.hasErrors()) {
      return badRequest("Student ID, name, and e-mail is required.");
    }
    
    // form is OK, so make a Student and save it.
    models.Student student = studentForm.get();
    student.save();
    return ok(student.toString());
  }
  
  /**
   * Delete a Student according to its studentId from the database.
   * @param studentId The studentId of the Student that you want to delete.
   * @return Result indicating that the delete was successful or not.
   */
  public static Result delete(String studentId) {
    models.Student student = models.Student.find().where().eq("studentId", studentId).findUnique();
    if (student != null) {
      student.delete();
    }
    return ok();
  }
}
