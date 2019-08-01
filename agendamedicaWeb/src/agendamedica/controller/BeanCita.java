package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath.Axis;

import agendamedica.model.entities.Turno;
import agendamedica.model.manager.ManagerCita;
import agendamedica.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanCita implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerCita managerCita;
	private List<Turno> listaCita;
	private List<Turno> listaCitaHistorial;
	private Turno turno;
	private Turno turnoSeleccionado;
	private BarChartModel barra;

	@PostConstruct
	private void inicializar() {
		listaCita = managerCita.listar();

		graficar();

		listaCita = managerCita.findAllCitas();
		listaCitaHistorial = managerCita.findAllCitasHistorial();
		turno = new Turno();
	}

	public ManagerCita getmanagerCita() {
		return managerCita;
	}

	public void graficar() {
		barra = new BarChartModel();
		for (int i = 0; i < managerCita.listar().size(); i++) {
			ChartSeries serie = new BarChartSeries();

			serie.setLabel(managerCita.listar().get(i).getEstado().getNombreEstado());// esta parte es del los
																						// nombresdel lengend // de la
																						// columna
			serie.set(managerCita.listar().get(i).getEstado().getNombreEstado(),
					managerCita.listar().get(i).getCantmedicinaTurno());
			barra.addSeries(serie);
		}
		barra.setTitle("Citas");
		barra.setLegendPosition("ne");
		barra.setAnimate(true);

		org.primefaces.model.chart.Axis xAxis = barra.getAxis(AxisType.X);
		xAxis.setLabel("Estados");
		org.primefaces.model.chart.Axis yAxis = barra.getAxis(AxisType.Y);
		yAxis.setLabel("Cantidad");
		yAxis.setMin(0);
		yAxis.setMax(30);

	}

	public void graficarr() {
		barra = new BarChartModel();
		for (Turno turn : managerCita.listar()) {
			ChartSeries serie = new BarChartSeries();
			serie.set(turn.getEstado(), turn.getCantmedicinaTurno());

		}
		barra.setTitle("cantidad");
		barra.setLegendPosition("ne");
		barra.setAnimate(true);

		org.primefaces.model.chart.Axis xAxis = barra.getAxis(AxisType.X);
		xAxis.setLabel("Estados");
		org.primefaces.model.chart.Axis yAxis = barra.getAxis(AxisType.Y);
		yAxis.setLabel("Cantidad");
		yAxis.setMin(0);
		yAxis.setMax(30);
	}

	public void actionListenerEliminarTurno(int id) {
		managerCita.eliminarCita(id);
		listaCita = managerCita.findAllCitas();
		JSFUtil.crearMensajeInfo("Cita Eliminado.");
	}

	public void actionListenerSeleccionarTurno(Turno turno) {
		turnoSeleccionado = turno;
	}

	public void actionListenerActualizarTurno() {
		try {
			managerCita.actualizarTurno(turnoSeleccionado, 2);
			listaCita = managerCita.findAllCitas();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Turno> getListaCita() {
		return listaCita;
	}

	public void setListaCita(List<Turno> listaCita) {
		this.listaCita = listaCita;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Turno getTurnoSeleccionado() {
		return turnoSeleccionado;
	}

	public void setTurnoSeleccionado(Turno turnoSeleccionado) {
		this.turnoSeleccionado = turnoSeleccionado;
	}

	public BarChartModel getBarra() {
		return barra;
	}

	public void setBarra(BarChartModel barra) {
		this.barra = barra;
	}

	public List<Turno> getListaCitaHistorial() {
		return listaCitaHistorial;
	}

	public void setListaCitaHistorial(List<Turno> listaCitaHistorial) {
		this.listaCitaHistorial = listaCitaHistorial;
	}
	

}
