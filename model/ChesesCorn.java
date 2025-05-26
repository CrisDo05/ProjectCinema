package model;

public class ChesesCorn extends TicketDecorator{

	public ChesesCorn(Ticket ticket) {
		super(ticket);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayInfor() {
		// TODO Auto-generated method stub
		return ticket.displayInfor()+"\nBắp phô mai, ";
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return ticket.price()+60000;
	}

}
