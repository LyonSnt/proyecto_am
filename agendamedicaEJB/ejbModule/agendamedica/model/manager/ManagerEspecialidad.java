package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Especialidad;
import agendamedica.model.entities.Tipousuario;
import agendamedica.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerEspecialidad
 */
@Stateless
@LocalBean
public class ManagerEspecialidad {
	@PersistenceContext
	private EntityManager em;

	public ManagerEspecialidad() {
		// TODO Auto-generated constructor stub
	}

	public List<Especialidad> findAllEspecialidades() {
		String consulta = "select o from Especialidad o";
		Query q = em.createQuery(consulta, Especialidad.class);
		return q.getResultList();
	}


	public List<Especialidad> findUsuarioByNombreEspecialidad(String nombre_especialidad) {
		String consulta = "SELECT u FROM Especialidad u where u.nombreEspecialidad='" + nombre_especialidad + "'";
		Query q = em.createQuery(consulta, Especialidad.class);
		return q.getResultList();

	}
	public void insertarEspecialidad(Especialidad especialidad) throws Exception {
		if (findUsuarioByNombreEspecialidad(especialidad.getNombreEspecialidad()) .size() > 0)
			throw new Exception("Ya existe el el nombre");
		em.persist(especialidad);

	}
	
	public Especialidad findEspecialidadById(int id) {
		return em.find(Especialidad.class, id);
	}


	public void eliminarEspecialidad(int id) {
		Especialidad especialidad = findEspecialidadById(id);
		if (especialidad != null)
			em.remove(especialidad);
	}
	
	public void actualizarEspecialidad(Especialidad especialidad) throws Exception  {
		Especialidad espe = findEspecialidadById(especialidad.getIdEspecialidad());
		if (espe == null)
			throw new Exception("no existe.");
		espe.setNombreEspecialidad(especialidad.getNombreEspecialidad());
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
		em.merge(espe);
	}

}
