package productivitycheckerapp;

import java.util.*;
public class User {
    private String username;
    private String password;
    private ArrayList<Todo> todos = new ArrayList<>();
    public User(String username,String password,ArrayList<Todo> todos){
        this.password=password;
        this.username=username;
        this.todos = todos;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public boolean equals(String username,String password) {
        return (username==this.username && password ==this.password);

        }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }
}