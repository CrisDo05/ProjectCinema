package model;

public interface User {
   
    boolean login(String password);
    void addMovie(Phim phim);
    String getUsername();
    String getPassword();
    String getEmail();
   
}