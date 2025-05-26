package model;

public class RegularCorn extends TicketDecorator {

	public RegularCorn(Ticket ticket) {
		super(ticket);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayInfor() {
		// TODO Auto-generated method stub
		return ticket.displayInfor()+"\nBắp thường, ";
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return ticket.price()+40000;
	}
	

}
