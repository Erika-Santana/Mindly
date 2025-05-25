package model.dao;

import java.sql.Date;
import java.util.List;

import model.entities.Appointments;

public interface DatabaseAppointmentsDAO {

	public List<Appointments> getAllAppointments();
	public List<Appointments> getAllAppointmentsByIDProfessional(String ID_professioal);
	public List<Appointments> getAllAppointmentsByIDC(String ID_client);
	public boolean createAppointment(String ID_professional, String ID_client, Date data);
	public boolean deleteAppointment(String ID_appointment);
	
}
