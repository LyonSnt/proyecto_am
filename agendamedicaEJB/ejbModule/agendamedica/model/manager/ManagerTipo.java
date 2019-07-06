package agendamedica.model.manager;

import agendamedica.model.util.ModelUtil;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import agendamedica.model.entities.Tipousuario;

/**
 * Session Bean implementation class ManagerTipo
 */
@Stateless
@LocalBean
public class ManagerTipo {
	@PersistenceContext
	private EntityManager em;
//	@PersistenceContext(unitName = "bdd_amDS")
//	private EntityManager em;

	public ManagerTipo() {
		// TODO Auto-generated constructor stub
	}

	public List<Tipousuario> findAllTipousuario() {
		String consulta = "select o from Tipousuario o";
		Query q = em.createQuery(consulta, Tipousuario.class);
		return q.getResultList();

	}

	public Tipousuario findTipousuarioByCedula(String cedula) {
    	return em.find(Tipousuario.class, cedula);
    }

	  public void insertarTipo(Tipousuario tipo) throws Exception{
	    	if(findTipousuarioByCedula(tipo.getNombreTipousuario())!=null)
	    			throw new Exception("Ya existe el el nombre");
	    	em.persist(tipo);
	    	
	    }
	  
//	  public void registrarNuevoTipo(Integer idUsuario,String nomtipo) throws Exception{
//		
//			  Tipousuario u=findTipoByCedula(idUsuario);
//			  if(u!=null)
//			  throw new Exception("Ya existe un usuario "+idUsuario);
//			  //finalmente se puede guardar el nuevo usuario:
//			  u=new Tipousuario();
//			u.setIdTipousuario(idUsuario);
//			  u.setNombreTipousuario(nomtipo);
//			  em.persist(u);
//			  }
	
}
