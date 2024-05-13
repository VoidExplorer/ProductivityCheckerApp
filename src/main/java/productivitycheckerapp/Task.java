package productivitycheckerapp;

public class Task implements search {
    @Override
    public String getTitle() {
        return "";
    }

    private String taskText, dueTime;
    private boolean isCompleted = false;

    Task(String taskText, String dueTime){
        this.taskText = taskText;
        this.dueTime  = dueTime;
    }

    public void checkCompleted() {
        isCompleted = true;
    }

    public void uncheckCompleted() {
        isCompleted = false;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getDueTime() {
        return dueTime;
    }

    public String getTaskText() {
        return taskText;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
