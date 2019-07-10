package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Responsableturno;


/**
 * Session Bean implementation class ManagerResponsable
 */
@Stateless
@LocalBean
public class ManagerResponsable {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerResponsable() {
		// TODO Auto-generated constructor stub
	}

	public List<Responsableturno> findAllResponsableturno() {
		String consulta = "select o from Responsableturno o";
		Query q = em.createQuery(consulta, Responsableturno.class);
		return q.getResultList();
	}

	public Responsableturno findResponsableturnoByCedula(String cedula) {
		return em.find(Responsableturno.class, cedula);
	}

	public void insertarResponsable(Responsableturno responsable) throws Exception {
		if (findResponsableturnoByCedula(responsable.getCedulaResponsableturno())!=null)
			throw new Exception("Ya existe la cédula");
		em.persist(responsable);

	}
	
	 public void eliminarResponsable(String cedula) {
		 Responsableturno responsable = findResponsableturnoByCedula(cedula);
		  if(responsable!=null)
			  em.remove(responsable);
	  }
	  
	 public Responsableturno findResponsableturnoById(String idresponsable) {
		 Responsableturno r = em.find(Responsableturno.class, idresponsable);
			return r;
		}
	 
	 
		
//	  public void actualizarResponsable(Responsableturno responsable) throws Exception{
//		  Responsableturno  r = findUsuarioById(responsable.getNombreResponsableturno());
//		  if(r==null)
//			  throw new Exception("No existe el usuario con el id especificado");
//		  
////		  r.set
////		  u.setNombreUsuario(usuario.getNombreUsuario());
////		  u.setPasswordUsuario(usuario.getPasswordUsuario());
////		  em.merge(r);
//		  
//		  
//	  }

}
