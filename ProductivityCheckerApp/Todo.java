package ProductivityCheckerApp;
import java.util.ArrayList;

public class Todo {
    String title, description;
    int id;
    ArrayList<String> tasks = new ArrayList<String>();

    Todo (String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }
    

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
   
    public void addTask(String newTask) {
        tasks.add(newTask);
    }
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber+1);
    }
    public void editTask(int taskNumber, String newTask) {
        tasks.set(taskNumber, newTask);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    


}