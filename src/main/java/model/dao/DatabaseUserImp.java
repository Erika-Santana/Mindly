package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidIdentifiers;
import model.dao.connection.DatabaseConnection;
import model.entities.AddressI;
import model.entities.Client;
import model.entities.Professional;
import model.entities.Specialty;

public class DatabaseUserImp implements DatabaseUserDAO {

    private static final String DOES_USER_EXIST = "SELECT cpf FROM client_user WHERE cpf = ?";
    private static final String DOES_LOGIN_EXIST = "SELECT login FROM client_user WHERE login = ?";
    private static final String INSERT_CLIENT = "INSERT INTO client_user (client_name, cpf, ID_address, "
            + "contact, password_, login, profile_image) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CLIENT = "SELECT password_, login FROM client_user WHERE login = ?";
    private static final String INSERT_APPOINTMENT = "INSERT INTO appointment (ID_professional, ID_client,"
            + " appointment) VALUES (?, ?, ?)";
    private static final String DELETE_APPOINTMENT = "DELETE FROM appointment WHERE ID_client = ?";
    private static final String GET_BY_ID_CLIENT = "SELECT ID FROM client_user WHERE cpf = ?";
    private static final String GET_CLIENT_BY_CPF = "SELECT ID, client_name, cpf, contact, password_, profile_image, login, ID_address "
            + "FROM client_user WHERE cpf = ?";
    private static final String GET_CLIENT_BY_LOGIN = "SELECT ID, client_name, cpf, contact, password_, profile_image, login, ID_address "
            + "FROM client_user WHERE login = ?";
    private static final String GET_CLIENT_BY_ID = "SELECT client_name, cpf, ID_address, contact, password_, login, profile_image "
            + "FROM client_user WHERE ID = ?";
    private static final String GET_ADDRESS_BY_ID = "SELECT street, number_, city, state, country FROM address WHERE ID = ?";
    private static final String REGISTER_ADDRESS = "INSERT INTO address (street, number_, city, state, country) "
            + "VALUES (?, ?, ?, ?, ?)";

    @Override
    public boolean doesUserExists(String CPF) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(DOES_USER_EXIST)) {
            preparedStatement.setString(1, CPF);
            try (var resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException SQL) {
            SQL.printStackTrace();
        }
        return false;
    }

    public boolean doesLoginExists(String login) {
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

    public boolean registerUser(Client cliente) {
        int ID = 0;
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(INSERT_CLIENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cliente.getClient_name());
            preparedStatement.setString(2, cliente.getCPF());
            preparedStatement.setInt(3, cliente.getAddress().getID_address());
            preparedStatement.setString(4, cliente.getContact());
            preparedStatement.setString(5, cliente.getPassword());
            preparedStatement.setString(6, cliente.getLogin());
            preparedStatement.setString(7, cliente.getProfile());
            preparedStatement.executeUpdate();
            ResultSet getIDGenerated = preparedStatement.getGeneratedKeys();
            if (getIDGenerated.next()) {
                ID = getIDGenerated.getInt(1);
                cliente.setID(ID);
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public AddressI registerAddress(AddressI address) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(REGISTER_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {
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
    public boolean authenticateUser(String login, String password) {
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_CLIENT)) {
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

    @Override
    public Client getClient(String login) {
        Client client = null;
        AddressI address = null;
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(GET_CLIENT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()) {
                client = new Client();
                client.setID(resultado.getInt("ID"));
                client.setClient_name(resultado.getString("client_name"));
                client.setContact(resultado.getString("contact"));
                client.setCPF(resultado.getString("cpf"));
                client.setLogin(resultado.getString("login"));
                client.setPassword(resultado.getString("password_"));
                client.setProfile(resultado.getString("profile_image"));
                int addressID = resultado.getInt("ID_address");
                address = getAddressByID(addressID);
                client.setAddress(address);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (InvalidIdentifiers e) {
            e.printStackTrace();
        }
        return client;
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

	@Override
	public Client getClientByID(int ID) {

	   
	        Client client = null;
	        AddressI address = null;
	        try (var connection = DatabaseConnection.getConnection();
	             var preparedStatement = connection.prepareStatement(GET_CLIENT_BY_ID)) {
	            preparedStatement.setInt(1, ID);
	            ResultSet resultado = preparedStatement.executeQuery();
	            if (resultado.next()) {
	                client = new Client();
	                client.setClient_name(resultado.getString("client_name"));
	                client.setContact(resultado.getString("contact"));
	                client.setCPF(resultado.getString("cpf"));
	                client.setLogin(resultado.getString("login"));
	                client.setPassword(resultado.getString("password_"));
	                client.setProfile(resultado.getString("profile_image"));
	                int addressID = resultado.getInt("ID_address");
	                address = getAddressByID(addressID);
	                client.setAddress(address);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } catch (InvalidIdentifiers e) {
	            e.printStackTrace();
	        }
	        return client;
	
	}
    
    
    
  

}
