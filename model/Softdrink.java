package model;

public class Softdrink extends TicketDecorator{

	public Softdrink(Ticket ticket) {
		super(ticket);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayInfor() {
		// TODO Auto-generated method stub
		return ticket.displayInfor()+"+\nNước ngọt, ";
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return ticket.price()+28000;
	}
}
