package agendamedica.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import agendamedica.model.entities.Medico;
import agendamedica.model.manager.ManagerMedico;

import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class BeanGrafico implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private BarChartModel animatedModel2;
	private List<Medico> listaMedico;
	private Medico medico;
 
    @PostConstruct
    public void init() {
        createAnimatedModels();
       //listaMedico = managerMedico.findAllMedicos();
		//medico = new Medico();
        
    }
 
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
      //  Medico med = new Medico();
        ChartSeries boys = new ChartSeries();
        
        boys.setLabel("Atendido");
      //  boys.set(managerCita.listar().get(i).getIdTurno(), managerCita.listar().get(i).getCantmedicinaTurno());

        boys.set("Enero", 5);
        boys.set("Febrero", 10);
        boys.set("Marzo", 9);
        boys.set("Abril", 8);
        boys.set("Mayo", 7);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Pendiente");
        girls.set("Enero", 7);
        girls.set("Febrero", 11);
        girls.set("Marzo", 10);
        girls.set("Abril", 9);
        girls.set("Mayo", 8);
        
        ChartSeries can = new ChartSeries();
        can.setLabel("Cancelado");
        can.set("Enero", 0);
        can.set("Febrero", 11);
        can.set("Marzo", 10);
        can.set("Abril", 9);
        can.set("Mayo", 8);
 
        model.addSeries(boys);
        model.addSeries(girls);
        model.addSeries(can);
 
        return model;
    }
 
 
    private void createAnimatedModels() {
    	
    	
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Citas medicas");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        org.primefaces.model.chart.Axis yAxis  = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(30);
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
 

  

}
