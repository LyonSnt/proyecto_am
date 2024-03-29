package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {

//	@PersistenceContext(unitName = "bdd_amDS")
//	private EntityManager em;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerUsuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario findUsuarioByUsername(String username) {
		return em.find(Usuario.class, username);
	}

	//@SuppressWarnings("unlikely-arg-type")
	public boolean comprobarUsuarios(String nombreUsuario, String clave, String tipo) throws Exception {
		Usuario u = findUsuarioByUsername(nombreUsuario);
		if (u == null)
			throw new Exception("No existe el usuario " + nombreUsuario);
//    	if(!u.getActivo())
//    	throw new Exception("El usuario no está activo.");
		if (u.getPasswordUsuario().equals(clave) && u.getTipousuario().equals(tipo))
			return true;
		throw new Exception("Contraseña no válida.");
	}

//	public List <Tipousuario> findAllTipoUsauarios() {
//		String consulta = "SELECT r FROM Tipousuario r";
//		Query q = em.createQuery(consulta, Tipousuario.class);
//		return q.getResultList();
//	}

	public List<Usuario> findAllUsuarios() {
		String consulta = "select o from Usuario o";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
	}

	public List<Usuario> findUsuarioByNombreUsuario(String nombreUsuario) {
		String consulta = "SELECT u FROM Usuario u where u.nombreUsuario='" + nombreUsuario + "'";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();

	}

	public void insertarUsuario(Usuario usuario) throws Exception {
		if (findUsuarioByNombreUsuario(usuario.getNombreUsuario()).size() > 0)
			throw new Exception("Ya existe el usuario");
		em.persist(usuario);

	}

	public Usuario findUsuarioByIdUsuario(int idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}

	public Usuario findUsuarioByIdUsuarioActualizar(String idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}
	
	public void eliminarUsuario2(String idUsuario) {
		Usuario usuario = findUsuarioByIdUsuarioActualizar(idUsuario);
		if (usuario != null)
			em.remove(usuario);
	}
	
	public void eliminarUsuario(int idUsuario) {
		Usuario usuario = findUsuarioByIdUsuario(idUsuario);
		if (usuario != null)
			em.remove(usuario);
	}

	

	public void actualizarUsuario(Usuario usuario) throws Exception {
		Usuario u = findUsuarioByIdUsuarioActualizar(usuario.getIdUsuario());
		if (u == null)
			throw new Exception("No existe el usuario con el id especificado");
		u.setNombreUsuario(usuario.getNombreUsuario());
		u.setPasswordUsuario(usuario.getPasswordUsuario());
		u.setTipousuario(usuario.getTipousuario());
		em.merge(u);

	}

}
