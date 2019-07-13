package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Horario;

/**
 * Session Bean implementation class ManagerHorario
 */
@Stateless
@LocalBean
public class ManagerHorario {
	@PersistenceContext
	private EntityManager em;
	
    public ManagerHorario() {
        // TODO Auto-generated constructor stub
    }

    public List<Horario> findAllHorarios() {
		String consulta = "select o from Horario o";
		Query q = em.createQuery(consulta, Horario.class);
		return q.getResultList();
	}

	public List<Horario> findHorarioByIdHorario(int id) {
		String consulta = "SELECT u FROM Horario u where u.idHorario='" + id + "'";
		Query q = em.createQuery(consulta, Horario.class);
		return q.getResultList();

	}

	  public void insertarHorario(Horario horario) throws Exception{
	    	if(findHorarioByIdHorario(horario.getIdHorario()).size() > 0)
	    			throw new Exception("Ya existe el id");
	    	em.persist(horario);
	    	
	    }
	  
	  public Horario findHorarioById(int id) {
			return em.find(Horario.class, id);
		}


		public void eliminarHorario(int id) {
			Horario horario = findHorarioById(id);
			if (horario != null)
				em.remove(horario);
		}
		
		public void actualizarHorario(Horario horario) throws Exception  {
			Horario hora = findHorarioById(horario.getIdHorario());
			if (hora == null)
				throw new Exception("no existe.");
			
			hora.setInicioHorario(horario.getInicioHorario());
			hora.setFinHorario(horario.getFinHorario());
			hora.setLunesHorario(horario.getLunesHorario());
			hora.setMartesHorario(horario.getMartesHorario());
			hora.setMiercolesHorario(horario.getMiercolesHorario());
			hora.setJuevesHorario(horario.getJuevesHorario());
			hora.setViernesHorario(horario.getViernesHorario());
			hora.setSabadoHorario(horario.getSabadoHorario());
			em.merge(hora);
		}
}
