package model.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import exceptions.InvalidIdentifiers;

public class Professional {

	private int ID;
	private String name;
	private String trade_name;
	private AddressI address;
	private String about_me;
	private String about_my_job;
	private String CNPJ;
	private String password;
	private String login;
	private List<String> workImage;
	private String profileImage;
	private String contato;
	private List<String> approach;
	private List<String> specialty;

	public Professional(String name, String trade_name, AddressI address, String about_me, String about_my_job,
			String cNPJ, String password, String login, String contato, String perfil) {
		
		this.name = name;
		this.trade_name = trade_name;
		this.address = address;
		this.about_me = about_me;
		this.about_my_job = about_my_job;
		CNPJ = cNPJ;
		this.password = password;
		this.login = login;
		this.contato = contato;
		profileImage = perfil;
	
	}

	
	public Professional() {
		
	}
	
	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}
	
	public String getAbout_me() {
		return about_me;
	}

	public void setApproach(List<String> approach) {
		this.approach = approach;
	}
	
	public void setSpecialty(List<String> specialty) {
		this.specialty = specialty;
	}
	
	public List<String> getApproach() {
		return approach;
	}
	
	public List<String> getSpecialty() {
		return specialty;
	}
	public String getProfileImage() {
		return profileImage;
	}
	
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	
	public String getContato() {
		return contato;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setWorkImage(List<String> workImage) {
		this.workImage = workImage;
	}
	
	public List<String> getWorkImage() {
		return workImage;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public AddressI getAddress() {
		return address;
	}
	public void setAddress(AddressI address) {
		this.address = address;
	}

	public void setAbout_my_job(String about_my_job) {
		this.about_my_job = about_my_job;
	}
	
	public String getAbout_my_job() {
		return about_my_job;
	}
	
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String cNPJ){
			CNPJ = cNPJ;
	}
	
	
}
