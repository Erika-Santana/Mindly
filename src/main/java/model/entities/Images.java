package model.entities;

public class Images {

	private int ID;
	private Professional professional;
	private String imagemPath;
	
	public Images() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Images(int iD, Professional professional, String imagemPath) {
		super();
		ID = iD;
		this.professional = professional;
		this.imagemPath = imagemPath;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
	public String getImagemPath() {
		return imagemPath;
	}
	public void setImagemPath(String imagemPath) {
		this.imagemPath = imagemPath;
	}
	
	
	
}
