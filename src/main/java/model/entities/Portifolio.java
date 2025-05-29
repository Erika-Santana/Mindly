package model.entities;

import java.util.List;

public class Portifolio {

	private int ID;
	private Professional prof;
	private List<Images> listImages;
	private String aboutMe;
	private String aboutArea;
	
	public Portifolio() {
		
	}
	
	public Portifolio(int iD, Professional prof, List<Images> listImages, String aboutMe, String aboutArea) {
		super();
		ID = iD;
		this.prof = prof;
		this.listImages = listImages;
		this.aboutMe = aboutMe;
		this.aboutArea = aboutArea;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Professional getProf() {
		return prof;
	}
	public void setProf(Professional prof) {
		this.prof = prof;
	}
	public List<Images> getListImages() {
		return listImages;
	}
	public void setListImages(List<Images> listImages) {
		this.listImages = listImages;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getAboutArea() {
		return aboutArea;
	}
	public void setAboutArea(String aboutArea) {
		this.aboutArea = aboutArea;
	}
	
	
	
}
