package model;

public class UserFactory {
    public static User createUser( String id,String username, String email, String password,String type) {
        if (type.equalsIgnoreCase("Admin")) {
            return new Admin(id,username, email, password,type);
        } else {
            return new Customer(id,username, email, password,type);
        }
    }
}