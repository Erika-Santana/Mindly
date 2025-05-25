package model.dao;

import model.entities.AddressI;
import model.entities.Appointments;
import model.entities.Client;

public interface DatabaseUserDAO {

	/*Cadastro usuário
Consulta usuário existe
Realiza agendamento
Edição dados
*/
	
	public boolean registerUser(Client cliente);
	public boolean authenticateUser(Client cliente);
	public Client getClient(String CPF);
	public Client getClientByID(int ID);
	public AddressI registerAddress(AddressI address);
	public boolean doesUserExists(String CPF);
	public boolean doesLoginExists(String Login);
	
	
}
