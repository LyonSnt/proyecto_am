package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Turno;

/**
 * Session Bean implementation class ManagerCita
 */
@Stateless
@LocalBean
public class ManagerCita {

	@PersistenceContext
	private EntityManager em;

	public ManagerCita() {
		// TODO Auto-generated constructor stub
		 //listar();
	}

//	public List<Turno> listar() {
//		Query q = em
//				.createQuery("select t from Turno t", Turno.class);
//		return q.getResultList();
//	}
	
	public List<Turno> listar() {
		String consulta = "select t from Turno t where t.estado=1";
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}

	
//	public List<Turno> listar() {
//		Query q = em.createNativeQuery(
//				"select id_estado,fecha_turno\n" + 
//				"	from turno\n" + 
//				"	where id_estado=1\n" + 
//				"	group by fecha_turno,id_estado",
//				Turno.class);
//		return q.getResultList();
//	}
	

	public Turno listarr() {
		String consulta = "select count(id_estado) from turno group by id_estado";
		return em.createQuery(consulta, Turno.class).getSingleResult();
	}

	public List<Turno> findAllCitas() {
		String consulta = "select t from Turno t";
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}

	public Turno findCitaById(int id) {
		return em.find(Turno.class, id);
	}

	public void eliminarCita(int id) {
		Turno turno = findCitaById(id);
		if (turno != null)
			em.remove(turno);
	}

	public void actualizarTurno(Turno turno) throws Exception {

		Turno tur = findCitaById(turno.getIdTurno());
		if (tur == null)
			throw new Exception("no existe.");

		tur.setEnfermedadTurno(turno.getEnfermedadTurno());
		tur.setSintomaTurno(turno.getSintomaTurno());
		tur.setAlergiaTurno(turno.getAlergiaTurno());
		tur.setTiposangreTurno(turno.getTiposangreTurno());
		tur.setNombremedicinaTurno(turno.getNombremedicinaTurno());
		tur.setCantmedicinaTurno(turno.getCantmedicinaTurno());
		tur.setDosisdiariaTurno(turno.getDosisdiariaTurno());
		// tur.setEstado(tur.getEstado());
		em.merge(tur);
	}

}
