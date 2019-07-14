package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Medico;

/**
 * Session Bean implementation class ManagerMedico
 */
@Stateless
@LocalBean
public class ManagerMedico {

	@PersistenceContext
	private EntityManager em;
	
    public ManagerMedico() {
        // TODO Auto-generated constructor stub
    }

    public List<Medico> findAllMedicos() {
		String consulta = "select o from Medico o";
		Query q = em.createQuery(consulta, Medico.class);
		return q.getResultList();
	}

	public List<Medico> findMedicoByCedula(String cedulaMedico) {
		String consulta = "SELECT u FROM Medico u where u.cedulaMedico='" + cedulaMedico + "'";
		Query q = em.createQuery(consulta, Medico.class);
		return q.getResultList();

	}

	public void insertarMedico(Medico medico) throws Exception {
		if (findMedicoByCedula(medico.getCedulaMedico()).size() > 0)
			throw new Exception("Ya existe la cedula");
		em.persist(medico);

	}

	public Medico findMedicoById(int id) {
		return em.find(Medico.class, id);
	}

	public void eliminarMedico(int id) {
		Medico medico = findMedicoById(id);
		if (medico != null)
			em.remove(medico);
	}

	public void actualizarMedico(Medico medico) throws Exception {
		
		Medico medi = findMedicoById(medico.getIdMedico());
		if (medi == null)
			throw new Exception("no existe.");
		medi.setNombreMedico(medico.getNombreMedico());
		medi.setApellidoMedico(medico.getApellidoMedico());
		medi.setCelularMedico(medico.getCelularMedico());
		medi.setDireccionMedico(medico.getDireccionMedico());
		medi.setCorreoMedico(medico.getCorreoMedico());
		medi.setEspecialidad(medico.getEspecialidad());
		medi.setHorario(medico.getHorario());
		em.merge(medi);
	}

}
