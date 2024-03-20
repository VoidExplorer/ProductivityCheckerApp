package productivitychecckerapp.demo;

public class Main {
    public static void main(String[]args){
       Todo todo1 = new Todo("First Todo");
       todo1.setDescription("This good description");
       Task task1 = new Task("Study for Quiz","15 March");
       Task task2 = new Task("Study for Midterm", "1 April");
       todo1.addTask(task1);
       todo1.addTask(task2);

       System.out.println(todo1.getTask(0).getTaskText());
       // new comment

    }
}
