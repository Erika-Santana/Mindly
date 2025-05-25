package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private DatabaseUserDAO dataUser;
	private DatabaseProfessionalDAO dataProfessional;
	
	@Override
	public List<Appointments> getAllAppointments() {
	
		List<Appointments> listAppointments = new ArrayList<>();
		
		try(var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(GET_ALL_APPOINTMENTS)){
			
			ResultSet resultado = preparedStatement.executeQuery();
			
			dataUser = UserDatabaseFactory.factory(DatabaseType.MYSQL);
			dataProfessional = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);
			
			int ID_professional;
			int ID_client;
			Client cliente = null;
			Professional professional = null;
			
			while(resultado.next())
			{
				ID_client = resultado.getInt("ID_client");
				ID_professional = resultado.getInt("ID_professional");
				
				cliente = dataUser.getClientByID(ID_client);
				professional = dataProfessional.getProfessionalByID(ID_professional);
				
				
			}
			
		}catch(SQLException ex) {
			
		}
		
		return null;
	}

	@Override
	public List<Appointments> getAllAppointmentsByIDProfessional(String ID_professioal) {
		
		return null;
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
