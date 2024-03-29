package agendamedica.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import agendamadica.model.dto.LoginDTO;
//import marketdemo.model.entities.Usuario;
import agendamedica.model.entities.Usuario;

/**
 * Implementa la logica de manejo de usuarios y autenticacion
 */
@Stateless
@LocalBean
public class ManagerSeguridad {
	@EJB
	private ManagerDAO managerDAO;
    /**
     * Default constructor. 
     */
    public ManagerSeguridad() {
        
    }
    
    /**
	 * Metodo que le permite a un usuario acceder al sistema.
	 * @param codigoUsuario Identificador del usuario.
	 * @param clave Clave de acceso.
	 * @return Retorna el tipo de usuario. Puede tener dos valores:
	 * 			SP (supervisor) o VD (vendedor).
	 * @throws Exception Cuando no coincide la clave proporcionada o si ocurrio
	 * un error con la consulta a la base de datos.
	 */
	public LoginDTO accederSistema(String codigoUsuario,String clave) throws Exception{
		Usuario usuario=(Usuario)managerDAO.findById(Usuario.class, codigoUsuario);
		
		if(usuario==null)
			throw new Exception("Error en usuario y/o clave.");
		
		if(!usuario.getPasswordUsuario().equals(clave))
			throw new Exception("Error en usuario y/o clave.");
		
		LoginDTO loginDTO=new LoginDTO();
		loginDTO.setCodigoUsuario(usuario.getIdUsuario());
		loginDTO.setUsuario(usuario.getNombreUsuario());
		loginDTO.setTipoUsuario(usuario.getTipousuario());
		
		//dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags web:
		if(usuario.getTipousuario().equals("admin"))
			loginDTO.setRutaAcceso("/admin/headerAdmin.xhtml");
		else if(usuario.getTipousuario().equals("medico"))
			loginDTO.setRutaAcceso("/medico/index.xhtml");
		else if(usuario.getTipousuario().equals("secretaria"))
			loginDTO.setRutaAcceso("/secretaria/index.xhtml");
		return loginDTO;
	}
	
	public Usuario findUsuarioById(String codigoUsuario) throws Exception {
		Usuario usuario=(Usuario)managerDAO.findById(Usuario.class, codigoUsuario);
		return usuario;
	}

}
