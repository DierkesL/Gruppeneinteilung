import java.util.ArrayList;

public class TeacherModel extends BaseModel {


    private ArrayList<String> courses;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordmd() {
        return this.password;
    }

    //get courses and resolve student relation at later point
    public ArrayList<String> getCourse() {
        return  this.courses;
    }
    //optinal todo: add addAll for collection of students
    public void addCourse(String course) {
        this.courses.add(course);
    }
}
