package model;

import java.util.ArrayList;

public class Customer implements User {
    private String id;
    private String username;
    private String email;
    private String role;
    private Ticket ticket;
    private String password;
//    private PaymentProcessor payProcessor;

 


    public Customer(String id, String username, String email,String password,String role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password=password;
		this.role=role;
		}

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
    public boolean login(String password) { return true; }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void bookTicket(Ticket ticket) { this.ticket = ticket; }

	@Override
	public void addMovie(Phim phim) {
		// TODO Auto-generated method stub
		
	}


//    public List<Ticket> viewHistory() { 
//    	return new ArrayList<>(); 
//    	}

    

 

  
}