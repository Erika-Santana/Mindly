package model.entities;

import java.sql.Date;

public class Appointments {

	private Client cliente;
	private Professional professional;
	private Date data;
	
	public Appointments(Client cliente, Professional professional, Date data) {
		super();
		this.cliente = cliente;
		this.professional = professional;
		this.data = data;
	}
	public Client getCliente() {
		return cliente;
	}
	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
}
