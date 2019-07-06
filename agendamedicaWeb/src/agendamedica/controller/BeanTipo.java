package agendamedica.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import agendamedica.view.util.JSFUtil;
import agendamedica.model.entities.Tipousuario;
import agendamedica.model.manager.ManagerTipo;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanTipo implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerTipo managerTipo;
	private List<Tipousuario> listaTipo;
	private Tipousuario tipo;
	
	
	@PostConstruct
	private void inicializar() {
		listaTipo = managerTipo.findAllTipousuario();
		
	}

	public void actionListerInsertarTipo() {
		try {
			
			managerTipo.insertarTipo(tipo);
			listaTipo = managerTipo.findAllTipousuario();
			tipo = new Tipousuario();
			JSFUtil.crearMensajeInfo("Datos de tipo insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
//	public String actionRegistrarTipo(){
//		try {
//		//managerTipo.registrarNuevoBlogger(idUsuario, clave, confirmacionClave, correo);
//		managerTipo.registrarNuevoTipo(23, nombretipo);
//		JSFUtil.crearMensajeInfo("Nuevo tipo registrado.");
//		return "admin/index";
//		} catch (Exception e) {
//		JSFUtil.crearMensajeError(e.getMessage());
//		e.printStackTrace();
//		}
//		return "";
//		}
	
	

	public Tipousuario getTipo() {
		return tipo;
	}

	public void setTipo(Tipousuario tipo) {
		this.tipo = tipo;
	}


	public List<Tipousuario> getListaTipo() {
		return listaTipo;
	}


	public void setListaTipo(List<Tipousuario> listaTipo) {
		this.listaTipo = listaTipo;
	}
	
	
	

}
