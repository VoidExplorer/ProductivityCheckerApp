package productivitycheckerapp;

import java.util.*;
public class User {
    private String username;
    private String password;
    public User(String username,String password){
        this.password=password;
        this.username=username;

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

    ArrayList<Todo> todos = new ArrayList<>();

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }
}