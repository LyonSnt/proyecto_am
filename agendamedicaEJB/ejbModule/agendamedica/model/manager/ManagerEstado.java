package agendamedica.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import agendamedica.model.entities.Estado;
import agendamedica.model.entities.Medico;

/**
 * Session Bean implementation class ManagerEstado
 */
@Stateless
@LocalBean
public class ManagerEstado {

	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO managerDAO;
	
    public ManagerEstado() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
	public List<Estado> findAllEstado() {
		return managerDAO.findAll(Estado.class, "o.nombreEstado");
	}
    
	/**
	 * Metodo finder para la consulta de un medicdo especifico.
	 * 
	 * @param cedula cedula del medico que se desea buscar.
	 * @return datos del paciente.
	 * @throws Exception
	 */
	public Estado findEstadoByIds(Integer id) throws Exception {
				Estado estado= null;
		try {
			estado = (Estado) managerDAO.findById(Estado.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar Estado: " + e.getMessage());
		}
		return estado;
	}
}
