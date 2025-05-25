package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.connection.DatabaseConnection;
import model.entities.AddressI;
import model.entities.Appointments;
import model.entities.Client;
import model.entities.Especialidade;
import model.entities.Professional;
import model.entities.Speciality;

public class DatabaseProfessionalImp implements DatabaseProfessionalDAO{

	private static final String INSERT_PROFESSIONAL = "INSERT INTO professional_user "
			+ "(professional_name, fantasy_name, address, description, date_creation, cnpj, password, login, workImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_APPROUCH = "INSERT INTO user_specialty_approuch "
			+ "(ID_professional, ID_specialist, ID_approuch) VALUES (?, ?, ?)";
	private static final String DELETE_APPOINTMENT = "DELETE FROM appointment WHERE ID_professional = ?";
	private static final String SELECT_PROFESSIONALS = "SELECT professional_name, fantasy_name, address, description, date_creation, cnpj, password, login, appointment_hour FROM professional_user";
	private static final String SELECT_PROFESSIONALS_BY_ID = "SELECT professional_name, fantasy_name, address_ID, description_, date_creation, cnpj, password, login, appointment_hour FROM professional_user WHERE ID = ?";
	private static final String SELECT_PROFESSIONALS_BY_CNPJ = "SELECT professional_name, fantasy_name, address_ID, description_, date_creation, cnpj, password, login, workImage FROM professional_user WHERE cnpj = ?";
	private static final String DOES_PROFESSIONAL_EXISTS = "SELECT COUNT(cnpj) FROM professional_user WHERE cnpj = ? ";
	private static final String DOES_LOGIN_EXIST = "SELECT login FROM professional_user WHERE login = ?";
	private static final String REGISTER_ADDRESS = "INSERT INTO address (street, number_, city, state, country)\r\n"
			
			+ "VALUES (?, ?, ?, ?,?)";
	
	@Override
	public boolean registerProfessional(Professional prestador) {
		
		try(var connection = DatabaseConnection.getConnection(); 
				var preparedStatement = connection.prepareStatement(INSERT_PROFESSIONAL)){
			
			preparedStatement.setString(1, prestador.getName());
			preparedStatement.setString(2, prestador.getTrade_name());
			preparedStatement.setString(3, prestador.getAddress());
			preparedStatement.setString(4, prestador.getDescription());
			preparedStatement.setDate(5, prestador.getDataCreation());
			preparedStatement.setString(6, prestador.getCNPJ());
			preparedStatement.setString(7, prestador.getPassword());
			preparedStatement.setString(8, prestador.getLogin());
			preparedStatement.setString(9, prestador.getWorkImage());
			
			
			 try (var resultSet = preparedStatement.executeQuery()) {
		            return resultSet.next(); 
		        }
			
			
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	public boolean registerAreaAndApprouch(Speciality especialidade) {
		
		
		
		return false;
	}
	@Override
	public boolean registerWorkHour(int start_hour, int end_hour, int duration, String[] dayOfWeek, Especialidade especialidade) {
	
		return false;
	}

	@Override
	public List<Appointments> historyAppointments(int ID) {
		
		return null;
	}
	
	public List<Professional> listProfessionals(){
		List<Professional> listProfessionals = new ArrayList<>();
		Professional professional = null;
		
		try( var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(SELECT_PROFESSIONALS)){
			
			ResultSet professionals = preparedStatement.executeQuery();
			
			while(professionals.next()) {
			
				professional = new Professional(professionals.getString("professional_name"),
						professionals.getString("fantasy_name"), professionals.getString("address_ID"),
						professionals.getString("description"),
						professionals.getDate("date_creation"), 
						professionals.getString("cnpj"),
						professionals.getString("password"),
						professionals.getString("login"), 
						professionals.getString("workImage"));
				listProfessionals.add(professional);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return listProfessionals;
	}
	
	public boolean doesProfessionalExists(String CNPJ) {
		
		try (var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(DOES_PROFESSIONAL_EXISTS)) {
			
			preparedStatement.setString(1, CNPJ);
			
			  try (var resultSet = preparedStatement.executeQuery()) {
		            return resultSet.next(); 
		        }
		}catch(SQLException SQL){
			SQL.printStackTrace();
		}
		return false;
	}
	
	public boolean doesProfessionalLoginExists(String login)  {
		try (var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(DOES_LOGIN_EXIST)) {
			preparedStatement.setString(1, login);
			
			 try (var resultSet = preparedStatement.executeQuery()) {
		            return resultSet.next(); 
		        }
		}catch(SQLException SQL){
			SQL.printStackTrace();
		}
		return false;
		
	}
	

	public AddressI registerAddress(AddressI address) {
		
		try(var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(REGISTER_ADDRESS, java.sql.Statement.RETURN_GENERATED_KEYS))	{
			
			int ID = 0;
			preparedStatement.setString(1, address.getStreet());
			preparedStatement.setInt(2, address.getNumber());
			preparedStatement.setString(3, address.getCity());
			preparedStatement.setString(4, address.getState());
			preparedStatement.setString(5, address.getCountry());
			preparedStatement.executeUpdate();
			
			ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
			
			if(getIDGenerated.next()) {
				 ID = getIDGenerated.getInt(1);
				 address.setID_address(ID);
				
			}else {
				System.out.print("Erro ao pegar o ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return address;
		
	}
	
	@Override
	public Professional getProfessionalByCNPJ(String CNPJ) {
		Professional professional = null;

		try (var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(SELECT_PROFESSIONALS_BY_CNPJ)) {
			
			preparedStatement.setString(1, CNPJ);

			ResultSet resultado = preparedStatement.executeQuery();

			if (resultado.next()) {
				professional = new Professional(resultado.getString("professional_name")
						,resultado.getString("fantasy_name"),
						resultado.getString("address_ID")
						,resultado.getString("description"),
						resultado.getDate("date_creation"), 
						resultado.getString("cnpj"),
						resultado.getString("password"),
						resultado.getString("login"), 
						resultado.getString("workImage"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return professional;

	}

	@Override
	public Professional getProfessionalByID(int ID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
