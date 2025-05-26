package repository;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Customer;
import model.User;

public class UserRepository {
	  private static UserRepository instance;
	  private List<User> users;
    public UserRepository() {
        users = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    public void addUser(User user) {
        users.add(user);
        System.out.println("name"+user.getUsername());
    }

    public boolean usernameExists(String username) {
        return users.stream().anyMatch(u -> u.getUsername().equals(username));
    }
    public boolean emailExists(String email) {
        return users.stream().anyMatch(u -> u.getEmail().equals(email));
    }
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.login(password)) {
                return user; 
            }
        }
        return null; // đăng nhập thất bại
    }
    public User findUserByUsername(String username) {
        return users.stream()
                    .filter(u -> (u instanceof Customer && ((Customer) u).getUsername().equals(username)) ||
                                 (u instanceof Admin && ((Admin) u).getUsername().equals(username)))
                    .findFirst().orElse(null);
    }

    
}