package model;

public class HSSV_OldPersonTicket extends Ticket{
	
	public HSSV_OldPersonTicket() {
		typeOfTicket = "Hssv-Người cao tuổi";
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return 45000;
	}

}
