package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especialidad database table.
 * 
 */
@Entity
@Table(name="especialidad")
@NamedQuery(name="Especialidad.findAll", query="SELECT e FROM Especialidad e")
public class Especialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_especialidad", unique=true, nullable=false)
	private Integer idEspecialidad;

	@Column(name="nombre_especialidad", length=50)
	private String nombreEspecialidad;

	//bi-directional many-to-one association to Medico
	@OneToMany(mappedBy="especialidad")
	private List<Medico> medicos;

	public Especialidad() {
	}

	public Integer getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombreEspecialidad() {
		return this.nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public List<Medico> getMedicos() {
		return this.medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Medico addMedico(Medico medico) {
		getMedicos().add(medico);
		medico.setEspecialidad(this);

		return medico;
	}

	public Medico removeMedico(Medico medico) {
		getMedicos().remove(medico);
		medico.setEspecialidad(null);

		return medico;
	}

}