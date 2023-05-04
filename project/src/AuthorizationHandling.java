
public class AuthorizationHandling {

   public boolean checkIfAuthorizedForClass(TeacherModel teacher, String course){
      for (String c : teacher.getCourse()) {
         if (c.equals(course)){
            return true;
         }
      }
      return false;
   }
}
