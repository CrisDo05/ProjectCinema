package model;

public abstract class TicketDecorator extends Ticket{
	protected Ticket ticket;
	
	public TicketDecorator(Ticket ticket) {
		this.ticket = ticket;
	}
	public abstract String displayInfor();
	

}
