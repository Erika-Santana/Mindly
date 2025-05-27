package model.entities;

import exceptions.InvalidIdentifiers;

public class Client {
	private int ID;
	private String client_name;
	private String CPF;
	private AddressI address;
	private String contact;
	private String password;
	private String login;
	private String profile;
	
	public Client() {
		
	}

	public Client(String client_name, String cPF, AddressI address, String contact, String password, String login, String profile) {

		this.client_name = client_name;
		CPF = cPF;
		this.address = address;
		this.contact = contact;
		this.password = password;
		this.login = login;
		this.profile = profile;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public int getID() {
		return ID;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getProfile() {
		return profile;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) throws InvalidIdentifiers {
			CPF = cPF;
	}

	public AddressI getAddress() {
		return address;
	}

	public void setAddress(AddressI address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) throws InvalidIdentifiers {
			this.contact = contact;
	}

}
