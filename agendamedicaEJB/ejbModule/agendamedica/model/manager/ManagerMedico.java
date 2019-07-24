package agendamedica.model.manager;

import java.util.List;

import javax.ejb.EJB;
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
	@EJB
	private ManagerDAO managerDAO;

	
    public ManagerMedico() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	public List<Medico> findAllMedicoss(){
  		return managerDAO.findAll(Medico.class, "o.apellidoMedico");
  	}
    
    public Medico findMedicosById(String cedula) throws Exception{
    	Medico cliente=null;
  		try {
  			cliente=(Medico)managerDAO.findById(Medico.class, cedula);
  		} catch (Exception e) {
  			e.printStackTrace();
  			throw new Exception("Error al buscar cliente: "+e.getMessage());
  		}
  		return cliente;
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
	
	/**
  	 * Metodo finder para la consulta de un paciente especifico.
  	 * @param cedula cedula del paciente que se desea buscar.
  	 * @return datos del paciente.
  	 * @throws Exception
  	 */
  	public Medico findMedicoById(String cedula) throws Exception{
  		Medico medico=null;
  		try {
  			medico=(Medico)managerDAO.findById(Medico.class, cedula);
  		} catch (Exception e) {
  			e.printStackTrace();
  			throw new Exception("Error al buscar medico: "+e.getMessage());
  		}
  		return medico;
  	}

}
