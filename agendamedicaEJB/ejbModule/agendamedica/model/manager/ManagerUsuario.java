package agendamedica.model.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import agendamedica.model.entities.Usuario;
import agendamedica.model.util.ModelUtil;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {

	@PersistenceContext(unitName = "bdd_amDS")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerUsuario() {
		// TODO Auto-generated constructor stub
	}

	public boolean comprobarUsuario(String idUsuario, String clave) throws Exception {
		Usuario u = em.find(Usuario.class, idUsuario);
		if (u == null)
			throw new Exception("No existe el usuario " + idUsuario);
//    	if(!u.getActivo())
//    	throw new Exception("El usuario no está activo.");
		if (u.getPasswordUsuario().equals(clave))
			return true;
		throw new Exception("Contraseña no válida.");
	}

	public Usuario findUsuarioById(String idUsuario) {
		Usuario u = em.find(Usuario.class, idUsuario);
		return u;
	}

	public void registrarNuevoUsuario(String idUsuario, String nombreUsua, String claveUsua, 
			Integer tipoUsua, String estado,Integer respon, Integer medico)
			throws Exception {
		if (ModelUtil.isEmpty(idUsuario))
			throw new Exception("Debe especificar un ID de usuario.");
		// verificamos la contraseña:
		if (ModelUtil.isEmpty(claveUsua))
			throw new Exception("Debe especificar una clave.");
		Usuario u = findUsuarioById(idUsuario);
		if (u != null)
			throw new Exception("Ya existe un usuario " + idUsuario);
		// finalmente se puede guardar el nuevo usuario:
		u = new Usuario();
		u.setIdUsuario(idUsuario);
		u.setNombreUsuario(nombreUsua);
		u.setPasswordUsuario(claveUsua);
		//u.setTipousuario(});
		u.setEstadoUsuario(estado);
//		u.setResponsableturno(respon);
//		u.setMedico(medico);

		//u.setActivo(true);
		em.persist(u);
	}

}
