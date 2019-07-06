package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Paciente;

/**
 * Session Bean implementation class ManagerPaciente
 */
@Stateless
@LocalBean
public class ManagerPaciente {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerPaciente() {

	}

	public List<Paciente> findAllPaciente() {
		String consulta = "select p from Paciente p ";
		Query q = em.createQuery(consulta, Paciente.class);
		return q.getResultList();
	}

	public Paciente findPacienteByCedula(String cedula) {
		return em.find(Paciente.class, cedula);
	}

	public void insertarPaciente(Paciente paciente) throws Exception {
		if (findPacienteByCedula(paciente.getCedulaPaciente()) != null)
			throw new Exception("Ya existe la cedula indicada");
		em.persist(paciente);
	}

	public void eliminarPaciente(String cedula) {
		Paciente paciente = findPacienteByCedula(cedula);
		if (paciente != null)
			em.remove(paciente);
	}

	public void actualizarPaciente(Paciente paciente) throws Exception {
		Paciente p = findPacienteByCedula(paciente.getCedulaPaciente());
		if (p == null)
			throw new Exception("No existe el estudiante con la cédula especificada.");
		p.setApellidoPaciente(paciente.getApellidoPaciente());
		p.setNombrePaciente(paciente.getNombrePaciente());
		p.setCelularPaciente(paciente.getCelularPaciente());
		p.setDireccionPaciente(paciente.getDireccionPaciente());
		p.setCorreoPaciente(paciente.getCorreoPaciente());
		em.merge(p);

	}

}
