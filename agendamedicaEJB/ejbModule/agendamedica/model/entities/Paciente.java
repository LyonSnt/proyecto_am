package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


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
	/*@Column(name="id_paciente", unique=true, nullable=false)
	private Integer idPaciente;*/

	@Column(name="apellido_paciente", length=50)
	private String apellidoPaciente;

	@Column(name="cedula_paciente", length=10)
	private String cedulaPaciente;

	@Column(name="celular_paciente", precision=10)
	private BigDecimal celularPaciente;

	@Column(name="correo_paciente", length=50)
	private String correoPaciente;

	@Column(name="direccion_paciente", length=50)
	private String direccionPaciente;

	@Column(name="nombre_paciente", length=50)
	private String nombrePaciente;

	public Paciente() {
	}

	/*public Integer getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}*/

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

}