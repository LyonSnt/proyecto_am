package agendamedica.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import agendamedica.model.entities.Tipousuario;
import agendamedica.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {
	@PersistenceContext
	   private EntityManager em;
    public ManagerLogin() {
        // TODO Auto-generated constructor stub
    }
    
    public List <Tipousuario> findAllTipoUsauarios() {
		String consulta = "SELECT r FROM Tipousuario r";
		Query q = em.createQuery(consulta, Tipousuario.class);
		return q.getResultList();
	}
    
    public Usuario findUsuarioByNombreUsuario(String nombreUsuario) {
		return em.find(Usuario.class,nombreUsuario);
	}
    
    @SuppressWarnings("unlikely-arg-type")
	public boolean comprobarUsuarios(String nombreUsuario, String clave, String tipo) throws Exception {
		Usuario u = findUsuarioByNombreUsuario(nombreUsuario);
		if (u == null)
			throw new Exception("No existe el usuario " + nombreUsuario);
//    	if(!u.getActivo())
//    	throw new Exception("El usuario no está activo.");
		if (u.getPasswordUsuario().equals(clave) && u.getTipousuario().equals(tipo))
			return true;
		throw new Exception("Contraseña no válida.");
	}

}
