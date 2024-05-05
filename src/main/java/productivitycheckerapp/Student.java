package productivitycheckerapp;

import java.util.ArrayList;

public class Student extends User {
private ArrayList<Course> courses;
    public Student(String username, String password, ArrayList<Todo> todos, ArrayList<Course> courses) {
        super(username, password, todos);
        this.courses = courses;
    }
   public ArrayList<Course> getCourses() {
        return courses;
   }
   public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
   }
}
