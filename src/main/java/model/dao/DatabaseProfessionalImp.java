package model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;

import exceptions.InvalidIdentifiers;
import model.dao.connection.DatabaseConnection;
import model.entities.AddressI;
import model.entities.Appointments;
import model.entities.Client;
import model.entities.Professional;
import model.entities.Specialty;
import model.entities.WorkHourProfessional;

public class DatabaseProfessionalImp implements DatabaseProfessionalDAO{

	private static final String INSERT_PROFESSIONAL = "INSERT INTO professional_user "
			+ "(professional_name, fantasy_name, ID_address, description_, cnpj, password_, login, workImage, phone_number, profile_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_APPOINTMENT = "DELETE FROM appointment WHERE ID_professional = ?";
	private static final String SELECT_PROFESSIONALS = "SELECT professional_name, fantasy_name, ID_address, description_, cnpj, password_, login, workImage, phone_number, profile_image FROM professional_user";
	private static final String SELECT_PROFESSIONALS_BY_ID = "SELECT professional_name, fantasy_name, address_ID, description_, date_creation, cnpj, password, login, appointment_hour FROM professional_user WHERE ID = ?";
	private static final String SELECT_PROFESSIONALS_BY_CNPJ = "SELECT professional_name, fantasy_name, address_ID, description_, date_creation, cnpj, password, login, workImage FROM professional_user WHERE cnpj = ?";
	private static final String DOES_PROFESSIONAL_EXISTS = "SELECT cnpj FROM professional_user WHERE cnpj = ? ";
	private static final String DOES_LOGIN_EXIST = "SELECT login FROM professional_user WHERE login = ?";
	private static final String REGISTER_ADDRESS = "INSERT INTO address (street, number_, city, state, country)\r\n" + "VALUES (?, ?, ?, ?,?)";
	private static final String INSERT_AREA_AND_APPROUCH = "INSERT INTO user_specialty_approuch (ID_professional, ID_specialist, ID_approuch)\r\n" + " VALUES (?, ?, ?)";
	private static final String INSERT_AREA  = "INSERT INTO specialty_professional(specialty) VALUES (?) ";
	private static final String INSERT_APPROUCH = "INSERT INTO approuch_professional(approuch) VALUES (?) ";
	private static final String INSERT_SPECIALTY = "INSERT INTO user_specialty_approuch (ID_professional, ID_specialist, ID_approuch) VALUES (?,?,?)";
	private static final String INSERT_WORK_HOUR = "INSERT INTO set_hours_professional( ID_professional_approuch, start_hour, end_hour, appoitment_duration) VALUES (?, ?, ?, ?)";
	private static final String INSERT_DAYS = "INSERT INTO days_of_week (ID_hours_professional, day_of_week) VALUES (?, ?)";
	private static final String GET_ADDRESS_BY_ID = "SELECT street, number_, city, state, country FROM address WHERE ID = '?'";
	@Override
	public int registerProfessional(Professional prestador) {
		
		int ID = 0;
		try(var connection = DatabaseConnection.getConnection(); 
				var preparedStatement = connection.prepareStatement(INSERT_PROFESSIONAL, Statement.RETURN_GENERATED_KEYS)){
			
			System.out.print(prestador.toString());
			
			preparedStatement.setString(1, prestador.getName());
			preparedStatement.setString(2, prestador.getTrade_name());			
			preparedStatement.setInt(3, prestador.getAddress().getID_address());
			preparedStatement.setString(4, prestador.getDescription());
			preparedStatement.setString(5, prestador.getCNPJ());
			preparedStatement.setString(6, prestador.getPassword());
			preparedStatement.setString(7, prestador.getLogin());
			preparedStatement.setString(8, prestador.getWorkImage());
			preparedStatement.setString(9, prestador.getContato());
			preparedStatement.setString(10, prestador.getProfileImage());
			preparedStatement.executeUpdate();
			
			ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
			
			if(getIDGenerated.next()) {
				 ID = getIDGenerated.getInt(1);
				 prestador.setID(ID);
				 return ID;
				
			}else {
				System.out.print("Erro ao pegar o ID");
			}
			

		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return 0;
	}

	public boolean registerAreaAndApprouch(Specialty especialidade) {
		int ID = 0;
		try(var connection = DatabaseConnection.getConnection(); 
				var preparedStatement = connection.prepareStatement(INSERT_AREA_AND_APPROUCH, Statement.RETURN_GENERATED_KEYS)){
			
			preparedStatement.setInt(1, especialidade.getProfissional().getID());
			preparedStatement.setInt(2, especialidade.getArea());
			preparedStatement.setInt(3, especialidade.getAbordagem());
			
			ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
			
			if(getIDGenerated.next()) {
				 ID = getIDGenerated.getInt(1);
				 especialidade.setID(ID);
				 return true;
				
			}else {
				System.out.print("Erro ao pegar o ID");
			}
			
			
			
		}catch(SQLException exception) {
			exception.printStackTrace();
		}
		
		
		return false;
	}
	
	public int registerApprouch(String approuch) {
		
		try( var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_APPROUCH, Statement.RETURN_GENERATED_KEYS)){
			
			preparedStatement.setString(1, approuch);
			preparedStatement.executeUpdate();
			
			ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
			
			if (getIDGenerated.next()) {
				return getIDGenerated.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int registerArea(String area) {

		try( var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_AREA, Statement.RETURN_GENERATED_KEYS)){
			
			preparedStatement.setString(1, area);
			preparedStatement.executeUpdate();
			
			ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
			
			if (getIDGenerated.next()) {
				return getIDGenerated.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public Professional findById(int id) throws SQLException {
		Professional professional = null;
		AddressI address = null;

		String professionalQuery = "SELECT ID, professional_name, fantasy_name, ID_address, description_, "
				+ "profile_image, phone_number, date_creation, cnpj, login, workImage "
				+ "FROM professional_user WHERE ID = ?";

		try (var connection = DatabaseConnection.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(professionalQuery)) {
			
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					professional = new Professional();
					professional.setID(rs.getInt("ID"));
					professional.setName(rs.getString("professional_name"));
					professional.setTrade_name(rs.getString("fantasy_name"));
					address = getAddressByID(rs.getInt("ID_address"));
					professional.setAddress(address);
					professional.setDescription(rs.getString("description_"));
					professional.setProfileImage(rs.getString("profile_image"));
					professional.setContato(rs.getString("phone_number"));
				
					try {
						professional.setCNPJ(rs.getString("cnpj"));
					} catch (InvalidIdentifiers | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					professional.setLogin(rs.getString("login"));
					professional.setWorkImage(rs.getString("workImage"));
				}
			}
		}

		if (professional != null) {
			// Query para especialidades
			String specialtyQuery = "SELECT sp.ID, sp.specialty " + "FROM specialty_professional sp "
					+ "INNER JOIN user_specialty_approuch usa ON sp.ID = usa.ID_specialist "
					+ "WHERE usa.ID_professional = ?";

			List<String> specialties = new ArrayList<>();
			try (var connection = DatabaseConnection.getConnection(); 
					PreparedStatement stmt = connection.prepareStatement(specialtyQuery)) {
				stmt.setInt(1, id);
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						String specialty =rs.getString("specialty");
						specialties.add(specialty);
					}
				}
			}
			professional.setSpecialty(specialties);

			
			String approachQuery = "SELECT ap.ID, ap.approuch " + "FROM approuch_professional ap "
					+ "INNER JOIN user_specialty_approuch usa ON ap.ID = usa.ID_approuch "
					+ "WHERE usa.ID_professional = ?";

			List<String> approaches = new ArrayList<>();
			try (var connection = DatabaseConnection.getConnection(); 
					PreparedStatement stmt = connection.prepareStatement(approachQuery)) {
				stmt.setInt(1, id);
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						String approach = rs.getString("approuch");
						approaches.add(approach);
					}
				}
			}
			professional.setApproach(approaches);
		}

		return professional;
	}
	
	
	public boolean registerSpecialty(Specialty specialty) {
		try( var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_SPECIALTY, Statement.RETURN_GENERATED_KEYS)){
			preparedStatement.setInt(1, specialty.getProfissional().getID());
			preparedStatement.setInt(2, specialty.getArea());
			preparedStatement.setInt(3, specialty.getAbordagem());
			preparedStatement.executeUpdate();
			
			ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
		
			if (getIDGenerated.next()) {
				 specialty.setID(getIDGenerated.getInt(1));
				 return true;
				 
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean registerWorkHour(WorkHourProfessional horario) {

		try( var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_WORK_HOUR, Statement.RETURN_GENERATED_KEYS)){
			
			preparedStatement.setInt(1, horario.getEspecialidade().getProfissional().getID());
			preparedStatement.setString(2, horario.getInicio()+ ":00");
			preparedStatement.setString(3, horario.getFim() + ":00");
			preparedStatement.setString(4, horario.getDuracao() + ":00");
			preparedStatement.executeUpdate();
			
			ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
	
			if (getIDGenerated.next()) {
				 horario.setID(getIDGenerated.getInt(1));
				 return registerDays(horario.getDaysOfWeek(), horario.getID());				 
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean registerDays(String [] daysOfWeek, int ID_appointment) {
		try( var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_DAYS)){

			        for (String day : daysOfWeek) {
			        	if(day != null || !day.isBlank()) {
			        		preparedStatement.setInt(1, ID_appointment);
				            preparedStatement.setString(2, day); 
				            preparedStatement.addBatch(); 
			        	}else {
			        		return false;
			        	}
			        }

			        int[] results = preparedStatement.executeBatch(); 
			        return results.length == daysOfWeek.length;

			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }

	}

	@Override
	public List<Appointments> historyAppointments(int ID) {
		
		return null;
	}

	public List<Professional> listProfessionals(){
		List<Professional> listProfessionals = new ArrayList<>();
		Professional professional = null;
		AddressI address = null;
		
		try( var connection = DatabaseConnection.getConnection();
				var preparedStatement = connection.prepareStatement(SELECT_PROFESSIONALS)){

			ResultSet professionals = preparedStatement.executeQuery();
			
			while(professionals.next()){

						var nome =  professionals.getString("professional_name");
						var fantasy = professionals.getString("fantasy_name");
						int ID = professionals.getInt("ID_address");
						address = getAddressByID(ID);
						var descricao = professionals.getString("description_");
						var cnpj = professionals.getString("cnpj");
						var senha =professionals.getString("password_");
						var login =professionals.getString("login");
						var imagemTrabalho = professionals.getString("workImage");
						var contato =professionals.getString("phone_number");
						var perfil = professionals.getString("profile-image");
						professional = new Professional(nome, fantasy, address, descricao,
								cnpj, senha, login, imagemTrabalho, contato, perfil);
					
						listProfessionals.add(professional);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return listProfessionals;
	}
	
	public AddressI getAddressByID(int ID) {
		AddressI  address = null;
		try (var connection = DatabaseConnection.getConnection();
				var preparedStatementAdress = connection.prepareStatement(GET_ADDRESS_BY_ID)){
			
			preparedStatementAdress.setInt(1, ID);

			ResultSet resultado = preparedStatementAdress.executeQuery();

			if (resultado.next()) {
				address = new AddressI();
				
				address.setCity(resultado.getString("city"));
				address.setCountry(resultado.getString("country"));
				address.setNumber(resultado.getInt("number_"));
				address.setState(resultado.getString("state"));
				address.setStreet(resultado.getString("street"));
				
			}
			
			
		}catch(SQLException statement) {
			statement.printStackTrace();
		}
		return address;
		
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

			/*
			if (resultado.next()) {
				professional = new Professional(resultado.getString("professional_name")
						,resultado.getString("fantasy_name"),
						resultado.getString("address_ID")
						,resultado.getString("description"),
						resultado.getString("cnpj"),
						resultado.getString("password"),
						resultado.getString("login"), 
						resultado.getString("workImage"));
			}
			*/
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

	@Override
	public boolean registerWorkHour() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerWorkHour(int start_hour, int end_hour, int duration, String[] dayOfWeek,
			Specialty ID_professional_approuch) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean authenticateUser(String login, String password) {
		// TODO Auto-generated method stub
		return false;
	}	
	

}
