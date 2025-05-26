package model;

import java.util.Random;
import java.util.UUID;

import view.MainView;

public abstract class Ticket {
	public int ticketID;
	protected String typeOfTicket;

	public Ticket() {
		super();
		typeOfTicket="";
		Random ran = new Random();
		ticketID = 100000+ran.nextInt(900000);
		
	}
	public String displayInfor() {
		return "Mã vé: "+ticketID+"\t"+ "Hạng vé: "+ typeOfTicket;
	}
	public abstract double price();
}
