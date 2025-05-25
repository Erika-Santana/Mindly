package model.entities;

public class AddressI {

	private String street;
	private String city;
	private String state;
	private int number;
	private String country;
	private int ID_address;
	
	public AddressI() {
		
	}
	
	public AddressI(String street, String city, String state, int number, String country) {
	
		this.street = street;
		this.city = city;
		this.state = state;
		this.number = number;
		this.country = country;
	}
	
	public int getID_address() {
		return ID_address;
	}
	
	public void setID_address(int iD_address) {
		ID_address = iD_address;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
}
