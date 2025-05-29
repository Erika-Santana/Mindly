package model.dao;

import java.util.Collection;
import java.util.List;

import jakarta.servlet.http.Part;
import model.entities.AddressI;
import model.entities.Appointments;
import model.entities.Images;
import model.entities.Specialty;
import model.entities.WorkHourProfessional;
import model.entities.Professional;

public interface DatabaseProfessionalDAO {
	
	
	public int registerProfessional(Professional prestador);
	public List<Appointments> historyAppointments(int ID);
	public Professional getProfessionalByID(int ID);
	public Professional getProfessionalByLogin(String login);
	public boolean doesProfessionalExists(String CNPJ);
	public boolean doesProfessionalLoginExists(String login);
	public AddressI registerAddress(AddressI address);
	public int registerApprouch(String approuch);
	public int registerArea(String area);
    public Professional getProfessionalByCNPJ(String CNPJ);
	public boolean registerWorkHour(WorkHourProfessional horario);
	public boolean registerSpecialty(Specialty specialty);
	public boolean authenticateUser(String login, String password);
	public List<Professional> getAllProfessional();
	public List<Professional> listProfessionalByCity(String city, int page, int pageSize);
	public List<Professional> listProfessionals(String specialty, String city, int page, int pageSize);
    public int countProfessionalsByCity(String city);
    public List<Professional> listProfessionalsByApproach(String approach, int page, int pageSize);
    public List<Professional> listProfessionalsBySpecialty(String area, int page, int pageSize);
    public int countProfessionalsByApproach(String approach);
    public int countProfessionalsBySpecialty(String specialty);
    public boolean doesProfessionalHasPortfolio(int idProfessional);
    public List<Images> getImagesByProfessional(Professional prof);
    public boolean saveImages(Professional prof, Collection<Part> parts);
}
