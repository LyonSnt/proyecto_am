package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;

import agendamedica.model.entities.Medico;
import agendamedica.model.manager.ManagerMedico;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanMedico implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String direccion;
	private int idmedico;
	private String celular;
	private String correo;
	private int idespecialidad;
	private int idhorario;

	@EJB
	private ManagerMedico managerMedico;
	private List<Medico> listaMedico;
	private Medico medico;
	private boolean panelColapsado;
	private Medico medicoSeleccionado;
	
	private BarChartModel barra;

	@PostConstruct
	private void inicializar() {
		listaMedico = managerMedico.findAllMedicos();
		medico = new Medico();
		panelColapsado = true;
	}
	

	public String actionComprobarCedula() {
		try {
			// Medico c = managerClientes.findClienteById(cedula);
			Medico c = managerMedico.findMedicoById(cedula);
			// verificamos la existencia del cliente:
			if (c == null)
				return "registro";// debe registrarse

			// caso contrario si ya existe el cliente, recuperamos la informacion
			// para presentarla en la pagina de pedidos:
			nombres = c.getNombreMedico();
			apellidos = c.getApellidoMedico();
			direccion = c.getDireccionMedico();
			// creamos el pedido temporal y asignamos el cliente automaticamente:
			return "pedido";
		} catch (Exception e) {
			// error no esperado:
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
	}

	public void actionListenerColapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertaMed() {
		try {
			managerMedico.insertarmed(cedula, nombres, apellidos, celular, direccion, correo, idespecialidad,
					idhorario);
			listaMedico = managerMedico.findAllMedicos();
			medico = new Medico();
			JSFUtil.crearMensajeInfo("Datos insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListerInsertarMedico() {
		try {

			
			managerMedico.insertarmed( cedula, nombres, apellidos, celular, direccion, correo, idespecialidad, idhorario);;
			listaMedico = managerMedico.findAllMedicos();
			medico = new Medico();
			JSFUtil.crearMensajeInfo("Datos del medico insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarMedico(int id) {
		managerMedico.eliminarMedico(id);
		listaMedico = managerMedico.findAllMedicos();
		JSFUtil.crearMensajeInfo("Medico Eliminado.");
	}

	public void actionListenerSeleccionarMedico(Medico medico) {
		medicoSeleccionado = medico;
	}

	public void actionListenerActualizarMedico() {
		try {
			managerMedico.actualizarMedico(medicoSeleccionado);
			listaMedico = managerMedico.findAllMedicos();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Medico> getListaMedico() {
		return listaMedico;
	}

	public void setListaMedico(List<Medico> listaMedico) {
		this.listaMedico = listaMedico;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Medico getMedicoSeleccionado() {
		return medicoSeleccionado;
	}

	public void setMedicoSeleccionado(Medico medicoSeleccionado) {
		this.medicoSeleccionado = medicoSeleccionado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getIdmedico() {
		return idmedico;
	}

	public void setIdmedico(int idmedico) {
		this.idmedico = idmedico;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getIdespecialidad() {
		return idespecialidad;
	}

	public void setIdespecialidad(int idespecialidad) {
		this.idespecialidad = idespecialidad;
	}

	public int getIdhorario() {
		return idhorario;
	}

	public void setIdhorario(int idhorario) {
		this.idhorario = idhorario;
	}

	
	

}
