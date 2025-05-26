package model;

public class AdultTicket extends Ticket{
	
	public AdultTicket() {
		typeOfTicket="Người lớn";
	}
	
	@Override
	public double price() {
		// TODO Auto-generated method stub
		return 60000;
	}

}
