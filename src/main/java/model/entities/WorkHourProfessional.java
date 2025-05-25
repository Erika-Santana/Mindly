package model.entities;

public class WorkHourProfessional {
	private int ID;
	private String inicio;
	private String fim;
	private String duracao;
	private Specialty especialidade;
	private String [] daysOfWeek;
	
	public WorkHourProfessional() {
		// TODO Auto-generated constructor stub
	}
	
	public WorkHourProfessional(String inicio, String fim, String duracao, String [] daysOfWeek, Specialty especialidade) {
	
		this.inicio = inicio;
		this.fim = fim;
		this.duracao = duracao;
		this.especialidade = especialidade;
		this.daysOfWeek = daysOfWeek;
	}

	public int getID() {
		return ID;
	}
	
	public String[] getDaysOfWeek() {
		return daysOfWeek;
	}
	
	public void setDaysOfWeek(String[] daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public Specialty getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Specialty especialidade) {
		this.especialidade = especialidade;
	}
	

}
