package agendamedica.model.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import agendamedica.model.entities.Turno;
/**
 * Session Bean implementation class ManagerTurno
 */
@Stateless
@LocalBean
public class ManagerTurno {
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerSeguridad managerSeguridad;
	@EJB
	private ManagerPaciente managerPaciente;
	@EJB
	private ManagerMedico managerMedico;

	public ManagerTurno() {
		
	}
	//MANEJO DE FACTURAS:
	
		/**
		 * Metodo finder para la consulta de facturas.
		 * Hace uso del componente {@link agendamedica.model.manager.ManagerDAO ManagerDAO} de la capa model.
		 * @return Listado de turnos ordenadas por fecha de emision y numero de turno.
		 */
		@SuppressWarnings("unchecked")
		public List<Turno> findAllTurno(){
			List<Turno> listado= managerDAO.findAll(Turno.class, "o.fecha_turno desc,o.id_turno desc");
			//recorremos los turnos cabecera para extraer los datos de los detalles:
			
			return listado;
		}
		/**
		 * Crea una nueva cabecera de turno temporal, para que desde el programa
		 * cliente pueda manipularla y llenarle con la informacion respectiva.
		 * Esta informacion solo se mantiene en memoria.
		 * @return la nueva factura temporal.
		 */
		public Turno crearTurnoTmp(){
			Turno turnoTmp=new Turno();
			turnoTmp.setFechaTurno(new Date());
			return turnoTmp;
		}

}
