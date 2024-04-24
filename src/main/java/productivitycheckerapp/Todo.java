package productivitycheckerapp;

import java.util.ArrayList;

public class Todo {
    private String title, description;
    private int id;
    ArrayList<Task> tasks = new ArrayList<>();
    Todo (int id,String title, String description) {
        this.title = title;
        this.description = description;
        this.id =id;

    }
    Todo (String title) {
        this.title = title;
        this.description = "";
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }
    public void deleteTask(Task task){
        tasks.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public Task getTask(int i){
        return this.tasks.get(i);
    }
}

