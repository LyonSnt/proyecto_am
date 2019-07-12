package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Especialidad;
import agendamedica.model.entities.Responsableturno;

/**
 * Session Bean implementation class ManagerResponsable
 */
@Stateless
@LocalBean
public class ManagerResponsable {
//	@PersistenceContext(unitName = "bdd_amDS")
//	private EntityManager em;
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

	public List<Responsableturno> findResponsableturnoByCedula(String cedulaResponsableturno) {
		String consulta = "SELECT u FROM Responsableturno u where u.cedulaResponsableturno='" + cedulaResponsableturno + "'";
		Query q = em.createQuery(consulta, Responsableturno.class);
		return q.getResultList();

	}

	public void insertarResponsableturno(Responsableturno responsableturno) throws Exception {
		if (findResponsableturnoByCedula(responsableturno.getCedulaResponsableturno()).size() > 0)
			throw new Exception("Ya existe la cedula");
		em.persist(responsableturno);

	}

	public Responsableturno findResponsableturnoById(int id) {
		return em.find(Responsableturno.class, id);
	}

	public void eliminarResponsableturno(int id) {
		Responsableturno responsableturno = findResponsableturnoById(id);
		if (responsableturno != null)
			em.remove(responsableturno);
	}

	public void actualizarResponsableturno(Responsableturno responsableturno) throws Exception {
		Responsableturno respon = findResponsableturnoById(responsableturno.getIdResponsableturno());
		if (respon == null)
			throw new Exception("no existe.");
		respon.setNombreResponsableturno(responsableturno.getNombreResponsableturno());
		respon.setApellidoResponsableturno(responsableturno.getApellidoResponsableturno());
		respon.setCelularResponsableturno(responsableturno.getCelularResponsableturno());
	
//		user.setCedulaUsuario(usuario.getCedulaUsuario());
//		user.setNombreUsuario(usuario.getNombreUsuario());
//		user.setApellidoUsuario(usuario.getApellidoUsuario());
//		user.setDireccionUsuario(usuario.getDireccionUsuario());
//		user.setCorreoUsario(usuario.getCorreoUsario());
//		user.setTelefonoUsuario(usuario.getTelefonoUsuario());
//		user.setUsername(usuario.getUsername());
//		user.setContrasena(usuario.getContrasena());
//		user.setRol(usuario.getRol());
//		user.setEmpresa(usuario.getEmpresa());
		em.merge(respon);
	}

}
