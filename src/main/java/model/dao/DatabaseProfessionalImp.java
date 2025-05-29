package model.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import exceptions.InvalidIdentifiers;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import model.dao.connection.DatabaseConnection;
import model.entities.AddressI;
import model.entities.Appointments;
import model.entities.Images;
import model.entities.Professional;
import model.entities.Specialty;
import model.entities.WorkHourProfessional;

public class DatabaseProfessionalImp implements DatabaseProfessionalDAO {

    private static final String INSERT_PROFESSIONAL = "INSERT INTO professional_user "
            + "(professional_name, fantasy_name, ID_address, description_, cnpj, password_, login, workImage, phone_number, profile_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PROFESSIONALS_BY_ID = "SELECT professional_name, fantasy_name, ID_address, description_, date_creation, cnpj, password_, login, workImage, phone_number, profile_image FROM professional_user WHERE ID = ?";
    private static final String SELECT_PROFESSIONALS_BY_CNPJ = "SELECT professional_name, fantasy_name, ID_address, description_, date_creation, cnpj, password_, login, workImage, phone_number, profile_image FROM professional_user WHERE cnpj = ?";
    private static final String SELECT_PROFESSIONALS_BY_LOGIN = "SELECT ID, professional_name, fantasy_name, ID_address, description_, date_creation, cnpj, password_, login, workImage, phone_number, profile_image FROM professional_user WHERE login = ?";
    private static final String SELECT_ALL_PROFESSIONALS = "SELECT ID, professional_name, fantasy_name, ID_address, description_, date_creation, cnpj, password_, login, workImage, phone_number, profile_image FROM professional_user";
    private static final String DOES_PROFESSIONAL_EXISTS = "SELECT cnpj FROM professional_user WHERE cnpj = ?";
    private static final String DOES_LOGIN_EXIST = "SELECT login FROM professional_user WHERE login = ?";
    private static final String REGISTER_ADDRESS = "INSERT INTO address (street, number_, city, state, country) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_AREA_AND_APPROUCH = "INSERT INTO user_specialty_approuch (ID_professional, ID_specialist, ID_approuch) VALUES (?, ?, ?)";
    private static final String INSERT_AREA = "INSERT INTO specialty_professional(specialty) VALUES (?)";
    private static final String INSERT_APPROUCH = "INSERT INTO approuch_professional(approuch) VALUES (?)";
    private static final String INSERT_SPECIALTY = "INSERT INTO user_specialty_approuch (ID_professional, ID_specialist, ID_approuch) VALUES (?, ?, ?)";
    private static final String INSERT_WORK_HOUR = "INSERT INTO set_hours_professional(ID_professional_approuch, start_hour, end_hour, appoitment_duration) VALUES (?, ?, ?, ?)";
    private static final String INSERT_DAYS = "INSERT INTO days_of_week (ID_hours_professional, day_of_week) VALUES (?, ?)";
    private static final String GET_ADDRESS_BY_ID = "SELECT street, number_, city, state, country FROM address WHERE ID = ?";
    private static final String SELECT_AUTHENTICATE = "SELECT password_, login FROM professional_user WHERE login = ?";
    private static final String SELECT_PORTFOLIO_BY_PROFESSIONAL_ID = "SELECT 1 FROM portifolio WHERE ID_professional = ? LIMIT 1";
    @Override
    public int registerProfessional(Professional prestador) {
        int ID = 0;
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(INSERT_PROFESSIONAL, Statement.RETURN_GENERATED_KEYS)) {
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
            if (getIDGenerated.next()) {
                ID = getIDGenerated.getInt(1);
                prestador.setID(ID);
                return ID;
            } else {
                System.out.print("Erro ao pegar o ID");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean registerAreaAndApprouch(Specialty especialidade) {
        int ID = 0;
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(INSERT_AREA_AND_APPROUCH, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, especialidade.getProfissional().getID());
            preparedStatement.setInt(2, especialidade.getArea());
            preparedStatement.setInt(3, especialidade.getAbordagem());
            ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
            if (getIDGenerated.next()) {
                ID = getIDGenerated.getInt(1);
                especialidade.setID(ID);
                return true;
            } else {
                System.out.print("Erro ao pegar o ID");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public int registerApprouch(String approuch) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(INSERT_APPROUCH, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, approuch);
            preparedStatement.executeUpdate();
            ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
            if (getIDGenerated.next()) {
                return getIDGenerated.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int registerArea(String area) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(INSERT_AREA, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, area);
            preparedStatement.executeUpdate();
            ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
            if (getIDGenerated.next()) {
                return getIDGenerated.getInt(1);
            }
        } catch (SQLException e) {
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
                    professional.setCNPJ(rs.getString("cnpj"));
                    professional.setLogin(rs.getString("login"));
                    professional.setWorkImage(rs.getString("workImage"));
                }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (professional != null) {
            String specialtyQuery = "SELECT sp.specialty FROM specialty_professional sp "
                    + "INNER JOIN user_specialty_approuch usa ON sp.ID = usa.ID_specialist "
                    + "WHERE usa.ID_professional = ?";
            List<String> specialties = new ArrayList<>();
            try (var connection = DatabaseConnection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(specialtyQuery)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String specialty = rs.getString("specialty");
                        specialties.add(specialty);
                    }
                }
            }
            professional.setSpecialty(specialties);

            String approachQuery = "SELECT ap.approuch FROM approuch_professional ap "
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
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(INSERT_SPECIALTY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, specialty.getProfissional().getID());
            preparedStatement.setInt(2, specialty.getArea());
            preparedStatement.setInt(3, specialty.getAbordagem());
            preparedStatement.executeUpdate();
            ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
            if (getIDGenerated.next()) {
                specialty.setID(getIDGenerated.getInt(1));
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean registerWorkHour(WorkHourProfessional horario) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(INSERT_WORK_HOUR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, horario.getEspecialidade().getID());
            preparedStatement.setString(2, horario.getInicio() + ":00");
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

    public boolean registerDays(String[] daysOfWeek, int ID_appointment) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(INSERT_DAYS)) {
            for (String day : daysOfWeek) {
                if (day != null && !day.isBlank()) {
                    preparedStatement.setInt(1, ID_appointment);
                    preparedStatement.setString(2, day);
                    preparedStatement.addBatch();
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

    public List<Professional> listProfessionalByCity(String city, int page, int pageSize){
    	
    	    List<Professional> listProfessionals = new ArrayList<>();
    	    StringBuilder sql = new StringBuilder(
    	        "SELECT pu.ID, pu.professional_name, pu.fantasy_name, pu.ID_address, pu.description_, pu.cnpj, pu.password_, pu.login, pu.workImage, pu.phone_number, pu.profile_image, " +
    	        "a.street, a.number_, a.city, a.state, a.country " +
    	        "FROM professional_user pu " +
    	        "JOIN address a ON pu.ID_address = a.ID " +
    	        "WHERE 1=1"
    	    );

    	    List<String> params = new ArrayList<>();
    	    if (city != null && !city.isEmpty()) {
    	        sql.append(" AND a.city = ?");
    	        params.add(city);
    	    }

    	    sql.append(" GROUP BY pu.ID, a.ID LIMIT ? OFFSET ?");

    	    try (var connection = DatabaseConnection.getConnection();
    	         var preparedStatement = connection.prepareStatement(sql.toString())) {

    	        for (int i = 0; i < params.size(); i++) {
    	            preparedStatement.setString(i + 1, params.get(i));
    	        }

    	        preparedStatement.setInt(params.size() + 1, pageSize);
    	        preparedStatement.setInt(params.size() + 2, (page - 1) * pageSize);

    	        ResultSet professionals = preparedStatement.executeQuery();
    	        while (professionals.next()) {
    	            Professional professional = new Professional();
    	            professional.setID(professionals.getInt("ID"));
    	            professional.setName(professionals.getString("professional_name"));
    	            professional.setTrade_name(professionals.getString("fantasy_name"));
    	            AddressI address = new AddressI();
    	            address.setID_address(professionals.getInt("ID_address"));
    	            address.setStreet(professionals.getString("street"));
    	            address.setNumber(professionals.getInt("number_"));
    	            address.setCity(professionals.getString("city"));
    	            address.setState(professionals.getString("state"));
    	            address.setCountry(professionals.getString("country"));
    	            professional.setAddress(address);
    	            professional.setDescription(professionals.getString("description_"));
    	            professional.setCNPJ(professionals.getString("cnpj"));
    	            professional.setPassword(professionals.getString("password_"));
    	            professional.setLogin(professionals.getString("login"));
    	            professional.setWorkImage(professionals.getString("workImage"));
    	            professional.setContato(professionals.getString("phone_number"));
    	            professional.setProfileImage(professionals.getString("profile_image"));

    	      
    	            String specialtyQuery = "SELECT sp.specialty FROM specialty_professional sp " +
    	                                    "INNER JOIN user_specialty_approuch usa ON sp.ID = usa.ID_specialist " +
    	                                    "WHERE usa.ID_professional = ?";
    	            List<String> specialties = new ArrayList<>();
    	            try (var conn2 = DatabaseConnection.getConnection();
    	                 PreparedStatement stmt = conn2.prepareStatement(specialtyQuery)) {
    	                stmt.setInt(1, professional.getID());
    	                try (ResultSet rs = stmt.executeQuery()) {
    	                    while (rs.next()) {
    	                        specialties.add(rs.getString("specialty"));
    	                    }
    	                }
    	            }
    	            professional.setSpecialty(specialties);

    	            String approachQuery = "SELECT ap.approuch FROM approuch_professional ap " +
    	                                   "INNER JOIN user_specialty_approuch usa ON ap.ID = usa.ID_approuch " +
    	                                   "WHERE usa.ID_professional = ?";
    	            List<String> approaches = new ArrayList<>();
    	            try (var conn2 = DatabaseConnection.getConnection();
    	                 PreparedStatement stmt = conn2.prepareStatement(approachQuery)) {
    	                stmt.setInt(1, professional.getID());
    	                try (ResultSet rs = stmt.executeQuery()) {
    	                    while (rs.next()) {
    	                        approaches.add(rs.getString("approuch"));
    	                    }
    	                }
    	            }
    	            professional.setApproach(approaches);

    	            listProfessionals.add(professional);
    	        }
    	    } catch (SQLException ex) {
    	        System.err.println("Erro na consulta SQL: " + ex.getMessage());
    	        ex.printStackTrace();
    	    }

    	    return listProfessionals;
    	}


    public List<Professional> listProfessionalsBySpecialty(String specialty, int page, int pageSize) {

        List<Professional> listProfessionals = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT pu.ID, pu.professional_name, pu.fantasy_name, pu.ID_address, pu.description_, pu.cnpj, pu.password_, pu.login, pu.workImage, pu.phone_number, pu.profile_image, " +
            "a.street, a.number_, a.city, a.state, a.country " +
            "FROM professional_user pu " +
            "JOIN address a ON pu.ID_address = a.ID " +
            "LEFT JOIN user_specialty_approuch usa ON pu.ID = usa.ID_professional " +
            "LEFT JOIN specialty_professional sp ON usa.ID_specialist = sp.ID " +
            "WHERE 1=1"
        );

        List<String> params = new ArrayList<>();
        if (specialty != null && !specialty.isEmpty()) {
            sql.append(" AND sp.specialty = ?");
            params.add(specialty);
        }

        sql.append(" GROUP BY pu.ID, a.ID LIMIT ? OFFSET ?");

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, params.get(i));
            }

            preparedStatement.setInt(params.size() + 1, pageSize);
            preparedStatement.setInt(params.size() + 2, (page - 1) * pageSize);

            ResultSet professionals = preparedStatement.executeQuery();
            while (professionals.next()) {
                Professional professional = new Professional();
                professional.setID(professionals.getInt("ID"));
                professional.setName(professionals.getString("professional_name"));
                professional.setTrade_name(professionals.getString("fantasy_name"));
                AddressI address = new AddressI();
                address.setID_address(professionals.getInt("ID_address"));
                address.setStreet(professionals.getString("street"));
                address.setNumber(professionals.getInt("number_"));
                address.setCity(professionals.getString("city"));
                address.setState(professionals.getString("state"));
                address.setCountry(professionals.getString("country"));
                professional.setAddress(address);
                professional.setDescription(professionals.getString("description_"));
                professional.setCNPJ(professionals.getString("cnpj"));
                professional.setPassword(professionals.getString("password_"));
                professional.setLogin(professionals.getString("login"));
                professional.setWorkImage(professionals.getString("workImage"));
                professional.setContato(professionals.getString("phone_number"));
                professional.setProfileImage(professionals.getString("profile_image"));

                
                String specialtyQuery = "SELECT sp.specialty FROM specialty_professional sp " +
                                        "INNER JOIN user_specialty_approuch usa ON sp.ID = usa.ID_specialist " +
                                        "WHERE usa.ID_professional = ?";
                List<String> specialties = new ArrayList<>();
                try (var conn2 = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn2.prepareStatement(specialtyQuery)) {
                    stmt.setInt(1, professional.getID());
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            specialties.add(rs.getString("specialty"));
                        }
                    }
                }
                professional.setSpecialty(specialties);

               
                String approachQuery = "SELECT ap.approuch FROM approuch_professional ap " +
                                       "INNER JOIN user_specialty_approuch usa ON ap.ID = usa.ID_approuch " +
                                       "WHERE usa.ID_professional = ?";
                List<String> approaches = new ArrayList<>();
                try (var conn2 = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn2.prepareStatement(approachQuery)) {
                    stmt.setInt(1, professional.getID());
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            approaches.add(rs.getString("approuch"));
                        }
                    }
                }
                professional.setApproach(approaches);

                listProfessionals.add(professional);
            }
        } catch (SQLException ex) {
            System.err.println("Erro na consulta SQL (specialty): " + ex.getMessage());
            ex.printStackTrace();
        }

        return listProfessionals;
    }

    
    public List<Professional> listProfessionals(String specialty, String city, int page, int pageSize) {
        List<Professional> listProfessionals = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT pu.ID, pu.professional_name, pu.fantasy_name, pu.ID_address, pu.description_, pu.cnpj, pu.password_, pu.login, pu.workImage, pu.phone_number, pu.profile_image, " +
            "a.street, a.number_, a.city, a.state, a.country " +
            "FROM professional_user pu " +
            "JOIN address a ON pu.ID_address = a.ID " +
            "LEFT JOIN user_specialty_approuch usa ON pu.ID = usa.ID_professional " +
            "LEFT JOIN specialty_professional sp ON usa.ID_specialist = sp.ID " +
            "WHERE 1=1"
        );
        
        List<String> params = new ArrayList<>();
        if (specialty != null && !specialty.isEmpty()) {
            sql.append(" AND sp.specialty = ?");
            params.add(specialty);
        }
        if (city != null && !city.isEmpty()) {
            sql.append(" AND a.city = ?");
            params.add(city);
        }
        
        sql.append(" GROUP BY pu.ID, a.ID LIMIT ? OFFSET ?");
        
        System.out.println("SQL Query: " + sql.toString());
        System.out.println("Parâmetros de filtro: " + params + ", LIMIT: " + pageSize + ", OFFSET: " + (page - 1) * pageSize);
        
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql.toString())) {
         
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, params.get(i));
            }
            
            preparedStatement.setInt(params.size() + 1, pageSize);
            preparedStatement.setInt(params.size() + 2, (page - 1) * pageSize);
            
            ResultSet professionals = preparedStatement.executeQuery();
            while (professionals.next()) {
                Professional professional = new Professional();
                professional.setID(professionals.getInt("ID"));
                professional.setName(professionals.getString("professional_name"));
                professional.setTrade_name(professionals.getString("fantasy_name"));
                AddressI address = new AddressI();
                address.setID_address(professionals.getInt("ID_address"));
                address.setStreet(professionals.getString("street"));
                address.setNumber(professionals.getInt("number_"));
                address.setCity(professionals.getString("city"));
                address.setState(professionals.getString("state"));
                address.setCountry(professionals.getString("country"));
                professional.setAddress(address);
                professional.setDescription(professionals.getString("description_"));
                professional.setCNPJ(professionals.getString("cnpj"));
                professional.setPassword(professionals.getString("password_"));
                professional.setLogin(professionals.getString("login"));
                professional.setWorkImage(professionals.getString("workImage"));
                professional.setContato(professionals.getString("phone_number"));
                professional.setProfileImage(professionals.getString("profile_image"));
             
                String specialtyQuery = "SELECT sp.specialty FROM specialty_professional sp " +
                                       "INNER JOIN user_specialty_approuch usa ON sp.ID = usa.ID_specialist " +
                                       "WHERE usa.ID_professional = ?";
                List<String> specialties = new ArrayList<>();
                try (var conn2 = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn2.prepareStatement(specialtyQuery)) {
                    stmt.setInt(1, professional.getID());
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            specialties.add(rs.getString("specialty"));
                        }
                    }
                }
                professional.setSpecialty(specialties);
                System.out.println("Especialidades para " + professional.getName() + ": " + specialties);

                String approachQuery = "SELECT ap.approuch FROM approuch_professional ap " +
                                      "INNER JOIN user_specialty_approuch usa ON ap.ID = usa.ID_approuch " +
                                      "WHERE usa.ID_professional = ?";
                List<String> approaches = new ArrayList<>();
                try (var conn2 = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn2.prepareStatement(approachQuery)) {
                    stmt.setInt(1, professional.getID());
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            approaches.add(rs.getString("approuch"));
                        }
                    }
                }
                professional.setApproach(approaches);
                System.out.println("Abordagens para " + professional.getName() + ": " + approaches);

                listProfessionals.add(professional);
            }
        } catch (SQLException ex) {
            System.err.println("Erro na consulta SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("Total de profissionais retornados: " + listProfessionals.size());
        return listProfessionals;
    }
    
    public List<Professional> listProfessionalsByApproach(String approach, int page, int pageSize) {
        List<Professional> listProfessionals = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT pu.ID, pu.professional_name, pu.fantasy_name, pu.ID_address, pu.description_, pu.cnpj, pu.password_, pu.login, pu.workImage, pu.phone_number, pu.profile_image, " +
            "a.street, a.number_, a.city, a.state, a.country " +
            "FROM professional_user pu " +
            "JOIN address a ON pu.ID_address = a.ID " +
            "LEFT JOIN user_specialty_approuch usa ON pu.ID = usa.ID_professional " +
            "LEFT JOIN approuch_professional ap ON usa.ID_approuch = ap.ID " +
            "WHERE 1=1"
        );

        List<String> params = new ArrayList<>();
        if (approach != null && !approach.isEmpty()) {
            sql.append(" AND ap.approuch = ?");
            params.add(approach);
        }

        sql.append(" GROUP BY pu.ID, a.ID LIMIT ? OFFSET ?");

        System.out.println("SQL Query (por abordagem): " + sql.toString());
        System.out.println("Parâmetros: " + params + ", LIMIT: " + pageSize + ", OFFSET: " + (page - 1) * pageSize);

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, params.get(i));
            }

            preparedStatement.setInt(params.size() + 1, pageSize);
            preparedStatement.setInt(params.size() + 2, (page - 1) * pageSize);

            ResultSet professionals = preparedStatement.executeQuery();
            while (professionals.next()) {
                Professional professional = new Professional();
                professional.setID(professionals.getInt("ID"));
                professional.setName(professionals.getString("professional_name"));
                professional.setTrade_name(professionals.getString("fantasy_name"));
                AddressI address = new AddressI();
                address.setID_address(professionals.getInt("ID_address"));
                address.setStreet(professionals.getString("street"));
                address.setNumber(professionals.getInt("number_"));
                address.setCity(professionals.getString("city"));
                address.setState(professionals.getString("state"));
                address.setCountry(professionals.getString("country"));
                professional.setAddress(address);
                professional.setDescription(professionals.getString("description_"));
                professional.setCNPJ(professionals.getString("cnpj"));
                professional.setPassword(professionals.getString("password_"));
                professional.setLogin(professionals.getString("login"));
                professional.setWorkImage(professionals.getString("workImage"));
                professional.setContato(professionals.getString("phone_number"));
                professional.setProfileImage(professionals.getString("profile_image"));

                String specialtyQuery = "SELECT sp.specialty FROM specialty_professional sp " +
                                        "INNER JOIN user_specialty_approuch usa ON sp.ID = usa.ID_specialist " +
                                        "WHERE usa.ID_professional = ?";
                List<String> specialties = new ArrayList<>();
                try (var conn2 = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn2.prepareStatement(specialtyQuery)) {
                    stmt.setInt(1, professional.getID());
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            specialties.add(rs.getString("specialty"));
                        }
                    }
                }
                professional.setSpecialty(specialties);
                System.out.println("Especialidades para " + professional.getName() + ": " + specialties);

                String approachQuery = "SELECT ap.approuch FROM approuch_professional ap " +
                                       "INNER JOIN user_specialty_approuch usa ON ap.ID = usa.ID_approuch " +
                                       "WHERE usa.ID_professional = ?";
                List<String> approaches = new ArrayList<>();
                try (var conn2 = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn2.prepareStatement(approachQuery)) {
                    stmt.setInt(1, professional.getID());
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            approaches.add(rs.getString("approuch"));
                        }
                    }
                }
                professional.setApproach(approaches);
                System.out.println("Abordagens para " + professional.getName() + ": " + approaches);

                listProfessionals.add(professional);
            }
        } catch (SQLException ex) {
            System.err.println("Erro na consulta SQL: " + ex.getMessage());
            ex.printStackTrace();
        }

        System.out.println("Total de profissionais retornados: " + listProfessionals.size());
        return listProfessionals;
    }

    
    @Override
    public int countProfessionalsBySpecialty(String specialty) {
    	
    	    StringBuilder sql = new StringBuilder(
    	        "SELECT COUNT(DISTINCT pu.ID) as total " +
    	        "FROM professional_user pu " +
    	        "LEFT JOIN user_specialty_approuch usa ON pu.ID = usa.ID_professional " +
    	        "LEFT JOIN specialty_professional sp ON usa.ID_specialist = sp.ID " +
    	        "WHERE 1=1"
    	    );

    	    List<String> params = new ArrayList<>();
    	    if (specialty != null && !specialty.isEmpty()) {
    	        sql.append(" AND sp.specialty = ?");
    	        params.add(specialty);
    	    }

    	    System.out.println("Count SQL Query (specialty): " + sql.toString());
    	    System.out.println("Parâmetros: " + params);

    	    try (var connection = DatabaseConnection.getConnection();
    	         var preparedStatement = connection.prepareStatement(sql.toString())) {

    	        for (int i = 0; i < params.size(); i++) {
    	            preparedStatement.setString(i + 1, params.get(i));
    	        }

    	        ResultSet rs = preparedStatement.executeQuery();
    	        if (rs.next()) {
    	            int total = rs.getInt("total");
    	            System.out.println("Total de profissionais com specialty: " + total);
    	            return total;
    	        }
    	    } catch (SQLException ex) {
    	        System.err.println("Erro ao contar profissionais: " + ex.getMessage());
    	        ex.printStackTrace();
    	    }

    	    return 0;
    	
    }
    


    public int countProfessionals(String specialty, String city) {
        StringBuilder sql = new StringBuilder(
            "SELECT COUNT(DISTINCT pu.ID) as total " +
            "FROM professional_user pu " +
            "JOIN address a ON pu.ID_address = a.ID " +
            "LEFT JOIN user_specialty_approuch usa ON pu.ID = usa.ID_professional " +
            "LEFT JOIN specialty_professional sp ON usa.ID_specialist = sp.ID " +
            "WHERE 1=1"
        );
        
        List<String> params = new ArrayList<>();
        if (specialty != null && !specialty.isEmpty()) {
            sql.append(" AND sp.specialty = ?");
            params.add(specialty);
        }
        if (city != null && !city.isEmpty()) {
            sql.append(" AND a.city = ?");
            params.add(city);
        }
        
        System.out.println("Count SQL Query: " + sql.toString());
        System.out.println("Count Parâmetros: " + params);
        
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, params.get(i));
            }

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                System.out.println("Total de profissionais contados: " + total);
                return total;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao contar profissionais: " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }

    
    public int countProfessionalsByCity(String city) {
        StringBuilder sql = new StringBuilder(
            "SELECT COUNT(DISTINCT pu.ID) as total " +
            "FROM professional_user pu " +
            "JOIN address a ON pu.ID_address = a.ID " +
            "WHERE 1=1"
        );

        List<String> params = new ArrayList<>();
        if (city != null && !city.isEmpty()) {
            sql.append(" AND a.city = ?");
            params.add(city);
        }

        System.out.println("Count SQL Query (somente cidade): " + sql.toString());
        System.out.println("Parâmetros: " + params);

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, params.get(i));
            }

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                System.out.println("Total de profissionais na cidade: " + total);
                return total;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao contar profissionais por cidade: " + ex.getMessage());
            ex.printStackTrace();
        }

        return 0;
    }
    
    public int countProfessionalsByApproach(String approach) {
        StringBuilder sql = new StringBuilder(
            "SELECT COUNT(DISTINCT pu.ID) as total " +
            "FROM professional_user pu " +
            "LEFT JOIN user_specialty_approuch usa ON pu.ID = usa.ID_professional " +
            "LEFT JOIN approuch_professional ap ON usa.ID_approuch = ap.ID " +
            "WHERE 1=1"
        );

        List<String> params = new ArrayList<>();
        if (approach != null && !approach.isEmpty()) {
            sql.append(" AND ap.approuch = ?");
            params.add(approach);
        }

        System.out.println("Count SQL Query (somente abordagem): " + sql.toString());
        System.out.println("Parâmetros: " + params);

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, params.get(i));
            }

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                System.out.println("Total de profissionais por abordagem: " + total);
                return total;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao contar profissionais por abordagem: " + ex.getMessage());
            ex.printStackTrace();
        }

        return 0;
    }


    public AddressI getAddressByID(int ID) {
        AddressI address = null;
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatementAdress = connection.prepareStatement(GET_ADDRESS_BY_ID)) {
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
        } catch (SQLException statement) {
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
        } catch (SQLException SQL) {
            SQL.printStackTrace();
        }
        return false;
    }

    public boolean doesProfessionalLoginExists(String login) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(DOES_LOGIN_EXIST)) {
            preparedStatement.setString(1, login);
            try (var resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException SQL) {
            SQL.printStackTrace();
        }
        return false;
    }

    public AddressI registerAddress(AddressI address) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(REGISTER_ADDRESS, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            int ID = 0;
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getNumber());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getState());
            preparedStatement.setString(5, address.getCountry());
            preparedStatement.executeUpdate();
            ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
            if (getIDGenerated.next()) {
                ID = getIDGenerated.getInt(1);
                address.setID_address(ID);
            } else {
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
                professional = new Professional();
                professional.setName(resultado.getString("professional_name"));
                professional.setTrade_name(resultado.getString("fantasy_name"));
                professional.setAddress(getAddressByID(resultado.getInt("ID_address")));
                professional.setDescription(resultado.getString("description_"));
                professional.setCNPJ(resultado.getString("cnpj"));
                professional.setPassword(resultado.getString("password_"));
                professional.setLogin(resultado.getString("login"));
                professional.setWorkImage(resultado.getString("workImage"));
                professional.setContato(resultado.getString("phone_number"));
                professional.setProfileImage(resultado.getString("profile_image"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return professional;
    }

    @Override
    public Professional getProfessionalByID(int ID) {
        Professional professional = null;
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_PROFESSIONALS_BY_ID)) {
            preparedStatement.setInt(1, ID);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()) {
                professional = new Professional();
                professional.setName(resultado.getString("professional_name"));
                professional.setTrade_name(resultado.getString("fantasy_name"));
                professional.setAddress(getAddressByID(resultado.getInt("ID_address")));
                professional.setDescription(resultado.getString("description_"));
                professional.setCNPJ(resultado.getString("cnpj"));
                professional.setPassword(resultado.getString("password_"));
                professional.setLogin(resultado.getString("login"));
                professional.setWorkImage(resultado.getString("workImage"));
                professional.setContato(resultado.getString("phone_number"));
                professional.setProfileImage(resultado.getString("profile_image"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return professional;
    }

    @Override
    public boolean authenticateUser(String login, String password) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_AUTHENTICATE)) {
            preparedStatement.setString(1, login);
            ResultSet valores = preparedStatement.executeQuery();
            if (valores.next()) {
                String passwordsql = valores.getString("password_");
                String loginsql = valores.getString("login");
                if (login.equals(loginsql) && passwordsql.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Professional> getAllProfessional(){
    	
    	List<Professional> listProfessional = new ArrayList<>();
    	
    	 Professional professional = null;
         try (var connection = DatabaseConnection.getConnection();
              var preparedStatement = connection.prepareStatement(SELECT_ALL_PROFESSIONALS)) {
             ResultSet resultado = preparedStatement.executeQuery();
             
             while (resultado.next()) {
                 professional = new Professional();
                 professional.setID(resultado.getInt("ID"));
                 professional.setName(resultado.getString("professional_name"));
                 professional.setTrade_name(resultado.getString("fantasy_name"));
                 professional.setAddress(getAddressByID(resultado.getInt("ID_address")));
                 professional.setDescription(resultado.getString("description_"));
                 professional.setCNPJ(resultado.getString("cnpj"));
                 professional.setPassword(resultado.getString("password_"));
                 professional.setLogin(resultado.getString("login"));
                 professional.setWorkImage(resultado.getString("workImage"));
                 professional.setContato(resultado.getString("phone_number"));
                 professional.setProfileImage(resultado.getString("profile_image"));
                 
                 String specialtyQuery = "SELECT sp.specialty FROM specialty_professional sp "
                         + "INNER JOIN user_specialty_approuch usa ON sp.ID = usa.ID_specialist "
                         + "WHERE usa.ID_professional = ?";
                 
	                 List<String> specialties = new ArrayList<>();
	                 try (var connection2 = DatabaseConnection.getConnection();
	                      PreparedStatement stmt = connection2.prepareStatement(specialtyQuery)) {
	                     stmt.setInt(1, professional.getID());
	                     try (ResultSet rs = stmt.executeQuery()) {
	                         while (rs.next()) {
	                             String specialty = rs.getString("specialty");
	                             specialties.add(specialty);
	                         }
	                     }
	                 }
	                 professional.setSpecialty(specialties);
	                 String approachQuery = "SELECT ap.approuch FROM approuch_professional ap "
	                         + "INNER JOIN user_specialty_approuch usa ON ap.ID = usa.ID_approuch "
	                         + "WHERE usa.ID_professional = ?";
	                 List<String> approaches = new ArrayList<>();
	                 try (var connection2 = DatabaseConnection.getConnection();
	                      PreparedStatement stmt = connection2.prepareStatement(approachQuery)) {
	                     stmt.setInt(1, professional.getID());
	                     try (ResultSet rs = stmt.executeQuery()) {
	                         while (rs.next()) {
	                             String approach = rs.getString("approuch");
	                             approaches.add(approach);
	                         }
	                     }
                 }
                 professional.setApproach(approaches);
                 
                 listProfessional.add(professional);
             }
             
             
         } catch (SQLException ex) {
             ex.printStackTrace();
         } catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         return listProfessional;
    	
    }
    public Professional getProfessionalByLogin(String login) {
        Professional professional = null;
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_PROFESSIONALS_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()) {
                professional = new Professional();
                professional.setID(resultado.getInt("ID"));
                professional.setName(resultado.getString("professional_name"));
                professional.setTrade_name(resultado.getString("fantasy_name"));
                professional.setAddress(getAddressByID(resultado.getInt("ID_address")));
                professional.setDescription(resultado.getString("description_"));
                professional.setCNPJ(resultado.getString("cnpj"));
                professional.setPassword(resultado.getString("password_"));
                professional.setLogin(resultado.getString("login"));
                professional.setWorkImage(resultado.getString("workImage"));
                professional.setContato(resultado.getString("phone_number"));
                
                
                professional.setProfileImage(resultado.getString("profile_image"));
                String specialtyQuery = "SELECT sp.specialty FROM specialty_professional sp "
                        + "INNER JOIN user_specialty_approuch usa ON sp.ID = usa.ID_specialist "
                        + "WHERE usa.ID_professional = ?";
                
                List<String> specialties = new ArrayList<>();
                try (var connection2 = DatabaseConnection.getConnection();
                     PreparedStatement stmt = connection2.prepareStatement(specialtyQuery)) {
                    stmt.setInt(1, professional.getID());
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            String specialty = rs.getString("specialty");
                            specialties.add(specialty);
                        }
                    }
                }
                professional.setSpecialty(specialties);
                String approachQuery = "SELECT ap.approuch FROM approuch_professional ap "
                        + "INNER JOIN user_specialty_approuch usa ON ap.ID = usa.ID_approuch "
                        + "WHERE usa.ID_professional = ?";
                List<String> approaches = new ArrayList<>();
                try (var connection2 = DatabaseConnection.getConnection();
                     PreparedStatement stmt = connection2.prepareStatement(approachQuery)) {
                    stmt.setInt(1, professional.getID());
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            String approach = rs.getString("approuch");
                            approaches.add(approach);
                        }
                    }
                }
                professional.setApproach(approaches);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
			
			e.printStackTrace();
		}
        return professional;
    }
    
    public boolean doesProfessionalHasPortfolio(int idProfessional) {
        
        try (var connection2 = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection2.prepareStatement(SELECT_PORTFOLIO_BY_PROFESSIONAL_ID)) {

            stmt.setInt(1, idProfessional);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
    public List<Images> listImagensByIDProfessional(int ID_professional) {
        List<Images> imagens = new ArrayList<>();

        String sql = "SELECT ID, ID_professional, imagem FROM imagemWork WHERE ID_professional = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ID_professional);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int idProf = rs.getInt("ID_professional");
                    String imagem = rs.getString("imagem");
                    Professional prof = getProfessionalByID(idProf);
                    Images img = new Images(id, prof, imagem);
                    imagens.add(img);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imagens;
    }
    
    

    
    public Images registerImage(Professional prof, Part image) {
        List<String> tiposPermitidos = List.of("image/jpeg", "image/png", "image/jpg");

        try {
            String contentType = image.getContentType();
            if (!tiposPermitidos.contains(contentType)) {
                System.err.println("Tipo de imagem inválido: " + contentType);
                return null;
            }

            String fileName = UUID.randomUUID().toString() + "_" + 
                              Paths.get(image.getSubmittedFileName()).getFileName().toString();

            String fileUpload = "C:\\uploads";
            Files.createDirectories(Paths.get(fileUpload));
            image.write(fileUpload + File.separator + fileName);

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO imagemWork (ID_professional, imagem) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setInt(1, prof.getID());
                    stmt.setString(2, fileName);
                    stmt.executeUpdate();

                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int generatedId = rs.getInt(1);
                            return new Images(generatedId, prof, fileName);
                        }
                    }
                }
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




	
}
