package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the medico database table.
 * 
 */
@Entity
@Table(name="medico")
@NamedQuery(name="Medico.findAll", query="SELECT m FROM Medico m")
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MEDICO_IDMEDICO_GENERATOR", sequenceName="SEQ_MEDICO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEDICO_IDMEDICO_GENERATOR")
	@Column(name="id_medico", unique=true, nullable=false)
	private Integer idMedico;

	@Column(name="apellido_medico", nullable=false, length=50)
	private String apellidoMedico;

	@Column(name="cedula_medico", nullable=false, length=10)
	private String cedulaMedico;

	@Column(name="celular_medico", length=50)
	private String celularMedico;

	@Column(name="correo_medico", length=50)
	private String correoMedico;

	@Column(name="direccion_medico", length=50)
	private String direccionMedico;

	@Column(name="nombre_medico", nullable=false, length=50)
	private String nombreMedico;

	//bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name="id_especialidad", nullable=false)
	private Especialidad especialidad;

	//bi-directional many-to-one association to Horario
	@ManyToOne
	@JoinColumn(name="id_horario", nullable=false)
	private Horario horario;

	//bi-directional many-to-one association to Turno
	@OneToMany(mappedBy="medico")
	private List<Turno> turnos;

	public Medico() {
	}

	public Integer getIdMedico() {
		return this.idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public String getApellidoMedico() {
		return this.apellidoMedico;
	}

	public void setApellidoMedico(String apellidoMedico) {
		this.apellidoMedico = apellidoMedico;
	}

	public String getCedulaMedico() {
		return this.cedulaMedico;
	}

	public void setCedulaMedico(String cedulaMedico) {
		this.cedulaMedico = cedulaMedico;
	}

	public String getCelularMedico() {
		return this.celularMedico;
	}

	public void setCelularMedico(String celularMedico) {
		this.celularMedico = celularMedico;
	}

	public String getCorreoMedico() {
		return this.correoMedico;
	}

	public void setCorreoMedico(String correoMedico) {
		this.correoMedico = correoMedico;
	}

	public String getDireccionMedico() {
		return this.direccionMedico;
	}

	public void setDireccionMedico(String direccionMedico) {
		this.direccionMedico = direccionMedico;
	}

	public String getNombreMedico() {
		return this.nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Horario getHorario() {
		return this.horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<Turno> getTurnos() {
		return this.turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Turno addTurno(Turno turno) {
		getTurnos().add(turno);
		turno.setMedico(this);

		return turno;
	}

	public Turno removeTurno(Turno turno) {
		getTurnos().remove(turno);
		turno.setMedico(null);

		return turno;
	}

}