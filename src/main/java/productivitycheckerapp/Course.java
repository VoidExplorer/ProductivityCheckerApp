package productivitycheckerapp;

public class Course {
    private String name;
    private Course teacher;

    public Course(String name, Course teacher){
        this.name = name;
        this.teacher = teacher;
    }

    public Course getTeacher() {
        return teacher;
    }

    public void setTeacher(Course teacher) {
        this.teacher = teacher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
