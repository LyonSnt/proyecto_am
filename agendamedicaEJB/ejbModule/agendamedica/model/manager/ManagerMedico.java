package agendamedica.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Especialidad;
import agendamedica.model.entities.Horario;
import agendamedica.model.entities.Medico;

/**
 * Session Bean implementation class ManagerMedico
 */
@Stateless
@LocalBean
public class ManagerMedico {

	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO managerDAO;

	public ManagerMedico() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Medico> findAllMedicoss() {
		return managerDAO.findAll(Medico.class, "o.apellidoMedico");
	}
	
	/**
	 * Metodo finder para la consulta de un medicdo especifico.
	 * 
	 * @param cedula cedula del medico que se desea buscar.
	 * @return datos del paciente.
	 * @throws Exception
	 */
	public Medico findMedicoByIds(Integer id) throws Exception {
		Medico medico = null;
		try {
			medico = (Medico) managerDAO.findById(Medico.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar medico: " + e.getMessage());
		}
		return medico;
	}
	
	
//	SELECT id_medico, cedula_medico, nombre_medico, apellido_medico, celular_medico, 
//    direccion_medico, correo_medico, id_especialidad, id_horario
//FROM public.medico;


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
	public List<Medico> findMedicoByIdMedico(int idMedico) {
		String consulta = "SELECT u FROM Medico u where u.idMedico='" + idMedico + "'";
		Query q = em.createQuery(consulta, Medico.class);
		return q.getResultList();

	}
	public void insertarMedicoo(Medico medico) throws Exception {
		if (findMedicoByIdMedico(medico.getIdMedico()).size() > 0)
			throw new Exception("Ya existe la cedula");
		em.persist(medico);

	}

	public Especialidad buscarEspecialidad(int idespecialidad) {
		return em.find(Especialidad.class, idespecialidad);
	}
	public Horario buscarHorario(int idhorario) {
		return em.find(Horario.class, idhorario);
	}


	
	public void insertarmed( String cedula, String nombre, String apellido, String celular,
			String direccion, String correo, int idespecialidad, int idhorario) {

		Medico medico = new Medico();
		Especialidad espe = buscarEspecialidad(idespecialidad);
		Horario hor = buscarHorario(idhorario);
		
		
		medico.setCedulaMedico(cedula);
		medico.setNombreMedico(nombre);
		medico.setApellidoMedico(apellido);
		medico.setCelularMedico(celular);
		medico.setDireccionMedico(direccion);
		medico.setCorreoMedico(correo);
		medico.setEspecialidad(espe);
		medico.setHorario(hor);
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
	public void insertar(Medico medico) throws Exception {

		Medico medi = findMedicoById(medico.getIdMedico());
		medi.setCedulaMedico(medico.getCedulaMedico());
		medi.setNombreMedico(medico.getNombreMedico());
		medi.setApellidoMedico(medico.getApellidoMedico());
		medi.setCelularMedico(medico.getCelularMedico());
		medi.setDireccionMedico(medico.getDireccionMedico());
		medi.setCorreoMedico(medico.getCorreoMedico());
		medi.setEspecialidad(medico.getEspecialidad());
		medi.setHorario(medico.getHorario());
		em.merge(medi);
	}

	/**
	 * Metodo finder para la consulta de un paciente especifico.
	 * 
	 * @param cedula cedula del paciente que se desea buscar.
	 * @return datos del paciente.
	 * @throws Exception
	 */
	public Medico findMedicoById(String cedula) throws Exception {
		Medico medico = null;
		try {
			medico = (Medico) managerDAO.findById(Medico.class, cedula);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar medico: " + e.getMessage());
		}
		return medico;
	}

}
