package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name="paciente")
@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PACIENTE_IDPACIENTE_GENERATOR", sequenceName="SEQ_PACIENTE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PACIENTE_IDPACIENTE_GENERATOR")
	@Column(name="id_paciente", unique=true, nullable=false)
	private Integer idPaciente;

	@Column(name="apellido_paciente", nullable=false, length=50)
	private String apellidoPaciente;

	@Column(name="cedula_paciente", nullable=false, length=10)
	private String cedulaPaciente;

	@Column(name="celular_paciente", precision=10)
	private BigDecimal celularPaciente;

	@Column(name="correo_paciente", length=50)
	private String correoPaciente;

	@Column(name="direccion_paciente", length=50)
	private String direccionPaciente;

	@Column(name="nombre_paciente", nullable=false, length=50)
	private String nombrePaciente;

	//bi-directional many-to-one association to Turno
	@OneToMany(mappedBy="paciente")
	private List<Turno> turnos;

	public Paciente() {
	}

	public Integer getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getApellidoPaciente() {
		return this.apellidoPaciente;
	}

	public void setApellidoPaciente(String apellidoPaciente) {
		this.apellidoPaciente = apellidoPaciente;
	}

	public String getCedulaPaciente() {
		return this.cedulaPaciente;
	}

	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	public BigDecimal getCelularPaciente() {
		return this.celularPaciente;
	}

	public void setCelularPaciente(BigDecimal celularPaciente) {
		this.celularPaciente = celularPaciente;
	}

	public String getCorreoPaciente() {
		return this.correoPaciente;
	}

	public void setCorreoPaciente(String correoPaciente) {
		this.correoPaciente = correoPaciente;
	}

	public String getDireccionPaciente() {
		return this.direccionPaciente;
	}

	public void setDireccionPaciente(String direccionPaciente) {
		this.direccionPaciente = direccionPaciente;
	}

	public String getNombrePaciente() {
		return this.nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public List<Turno> getTurnos() {
		return this.turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Turno addTurno(Turno turno) {
		getTurnos().add(turno);
		turno.setPaciente(this);

		return turno;
	}

	public Turno removeTurno(Turno turno) {
		getTurnos().remove(turno);
		turno.setPaciente(null);

		return turno;
	}

}