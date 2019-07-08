package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Tipousuario;
import agendamedica.model.entities.Usuario;
import agendamedica.model.util.ModelUtil;

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
	
	
	public List<Usuario> findAllUsuario() {
		String consulta = "select o from Usuario o";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
	}

	public Usuario findUsuarioByCedula(String cedula) {
    	return em.find(Usuario.class, cedula);
    }

	  public void insertarUsuario(Usuario usuario) throws Exception{
	    	if(findUsuarioByCedula(usuario.getIdUsuario())!=null)
	    			throw new Exception("Ya existe la cédula");
	    	em.persist(usuario);
	    	
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
