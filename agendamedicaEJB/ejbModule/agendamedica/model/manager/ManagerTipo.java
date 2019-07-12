package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Especialidad;
import agendamedica.model.entities.Tipousuario;

/**
 * Session Bean implementation class ManagerTipo
 */
@Stateless
@LocalBean
public class ManagerTipo {
	@PersistenceContext
	private EntityManager em;

	public ManagerTipo() {
		// TODO Auto-generated constructor stub
	}

	public List<Tipousuario> findAllTipousuarios() {
		String consulta = "select o from Tipousuario o";
		Query q = em.createQuery(consulta, Tipousuario.class);
		return q.getResultList();
	}

	public List<Tipousuario> findTipousuarioBynombreTipoUsuario(String nombreTipoUsuario) {
		String consulta = "SELECT u FROM Tipousuario u where u.nombreTipousuario='" + nombreTipoUsuario + "'";
		Query q = em.createQuery(consulta, Tipousuario.class);
		return q.getResultList();

	}

	  public void insertarTipo(Tipousuario tipoUsuario) throws Exception{
	    	if(findTipousuarioBynombreTipoUsuario(tipoUsuario.getNombreTipousuario()).size() > 0)
	    			throw new Exception("Ya existe el nombre");
	    	em.persist(tipoUsuario);
	    	
	    }
	  
	  public Tipousuario findTipousuarioById(int id) {
			return em.find(Tipousuario.class, id);
		}


		public void eliminarTipousuario(int id) {
			Tipousuario tipousuario = findTipousuarioById(id);
			if (tipousuario != null)
				em.remove(tipousuario);
		}
		
		public void actualizarTipousuario(Tipousuario tipousuario) throws Exception  {
			Tipousuario tipo = findTipousuarioById(tipousuario.getIdTipousuario());
			if (tipo == null)
				throw new Exception("no existe.");
			tipo.setNombreTipousuario(tipousuario.getNombreTipousuario());
			
//			user.setCedulaUsuario(usuario.getCedulaUsuario());
//			user.setNombreUsuario(usuario.getNombreUsuario());
//			user.setApellidoUsuario(usuario.getApellidoUsuario());
//			user.setDireccionUsuario(usuario.getDireccionUsuario());
//			user.setCorreoUsario(usuario.getCorreoUsario());
//			user.setTelefonoUsuario(usuario.getTelefonoUsuario());
//			user.setUsername(usuario.getUsername());
//			user.setContrasena(usuario.getContrasena());
//			user.setRol(usuario.getRol());
//			user.setEmpresa(usuario.getEmpresa());
			em.merge(tipo);
		}
	
}