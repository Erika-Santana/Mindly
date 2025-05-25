package model.entities;

public class Speciality {
	
	private Professional profissional;
	private String abordagem;
	private String area;
	
	public Speciality() {
	}

	public Professional getProfissional() {
		return profissional;
	}

	public void setProfissional(Professional profissional) {
		this.profissional = profissional;
	}

	public String getAbordagem() {
		return abordagem;
	}

	public void setAbordagem(String abordagem) {
		this.abordagem = abordagem;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


}
