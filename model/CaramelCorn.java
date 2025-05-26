package model;

public class CaramelCorn extends TicketDecorator{

	public CaramelCorn(Ticket ticket) {
		super(ticket);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayInfor() {
		// TODO Auto-generated method stub
		return ticket.displayInfor()+"\nBắp caramel, ";
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return ticket.price()+45000;
	}

}
