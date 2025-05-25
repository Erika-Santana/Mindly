package model.dao;

import java.util.List;

import model.entities.Appointments;
import model.entities.Speciality;
import model.entities.Professional;

public interface DatabaseProfessionalDAO {

	/*Cadastro prestador
Disponibilização horários
edição dos dados
Busca prestadores e lista
Histórico de pedidos 
Padrão de projeto State*/
	
	
	public boolean registerProfessional(Professional prestador);
	public boolean registerWorkHour();//socorro como eu faço isso...)
	//Faz uma página que vai mostrar todos os agendamentos do prestador. Nessa página, coloca um botão
	//para mudança de estado apenas do Pendente, para aceito. Os próximos estados dependerão apenas do tempo em que 
	//foi agendado e só o sistema mudará automáticamente caso for finalizado
	public List<Appointments> historyAppointments(int ID);
	public List<Professional> listProfessionals();
	public Professional getProfessionalByID(int ID);
	public Professional getProfessionalByCNPJ(String CNPJ);
	public boolean doesProfessionalExists(String CNPJ);
	public boolean doesProfessionalLoginExists(String login);
	public boolean registerWorkHour(int start_hour, int end_hour, int duration, String[] dayOfWeek, Speciality ID_professional_approuch);
	
}
