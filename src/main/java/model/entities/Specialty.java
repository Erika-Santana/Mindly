package model.entities;

public class Specialty {
	
	private int ID;
	private Professional profissional;
	private int abordagem;
	private int area;
	
	public Specialty() {
	}

	
	public Specialty(Professional profissional, int abordagem, int area) {
		super();
		
		this.profissional = profissional;
		this.abordagem = abordagem;
		this.area = area;
	}


	public Professional getProfissional() {
		return profissional;
	}

	public void setProfissional(Professional profissional) {
		this.profissional = profissional;
	}

	public int getAbordagem() {
		return abordagem;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public int getID() {
		return ID;
	}

	public void setAbordagem(int abordagem) {
		this.abordagem = abordagem;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}


}
