package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import enums.DatabaseType;
import model.dao.connection.DatabaseConnection;
import model.entities.Appointments;
import model.entities.Client;
import model.entities.Professional;

public class DatabaseAppointmentImp implements DatabaseAppointmentsDAO{

	private static final String GET_ALL_APPOINTMENTS = "SELECT pu.professional_name, au.approuch, pu.address "
			+ "FROM appointment a JOIN professional_user pu ON a.ID_professional = pu.ID "
			+ "JOIN user_specialty_approuch usa ON pu.ID = usa.ID_professional"
			+ " JOIN approuch_professional au ON usa.ID_approuch = au.ID;";
	private static final String GET_APPOINTMENTS_BY_ID_PROFESSIONAL = "SELECT \r\n"
			+ "    ID,\r\n"
			+ "    ID_professional_approuch,\r\n"
			+ "    ID_set_hours_professional,\r\n"
			+ "    ID_client,\r\n"
			+ "    day_appointment,\r\n"
			+ "    appointment_time,\r\n"
			+ "    status_appointment,\r\n"
			+ "    create_at\r\n"
			+ "FROM appointment;\r\n"
			+ "";
	
	private static DatabaseUserDAO dataUser = UserDatabaseFactory.factory(DatabaseType.MYSQL);
	private static DatabaseProfessionalDAO dataProfessional = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);
	
	@Override
	public List<Appointments> getAllAppointments() {
	
		
		
		return null;
	}

	@Override
	public List<Appointments> getAllAppointmentsByIDProfessional(int ID_professioal) {
	List<Appointments> listAppointments = new ArrayList<>();
	Appointments appointment = null;
		
		try(var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(GET_ALL_APPOINTMENTS)){
			
			ResultSet resultado = preparedStatement.executeQuery();
			
			int ID_professional;
			int ID_client;
			Client cliente = null;
			Professional professional = null;
			Date data = null;
			Time time = null;
			
			while(resultado.next())
			{
				ID_client = resultado.getInt("ID_client");
				ID_professional = resultado.getInt("ID_professional");
				data = resultado.getDate("day_appointment");
				time = resultado.getTime("appointment_time");
				var enumProfissional = resultado.getString("status_appointment");
				
				cliente = dataUser.getClientByID(ID_client);
				professional = dataProfessional.getProfessionalByID(ID_professional);
				
				appointment = new Appointments(cliente, professional, data, time);
				listAppointments.add(appointment);
			}
			
		}catch(SQLException ex) {
			
		}
		return listAppointments;
	}

	public List<Appointments> getAllAppointmentsByIDClient(String ID_client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createAppointment(String ID_professional, String ID_client, Date data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAppointment(String ID_appointment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Appointments> getAllAppointmentsByIDC(String ID_client) {
		// TODO Auto-generated method stub
		return null;
	}

}
