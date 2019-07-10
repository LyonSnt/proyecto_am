package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the turno database table.
 * 
 */
@Entity
@Table(name="turno")
@NamedQuery(name="Turno.findAll", query="SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@Column(name="id_turno", unique=true, nullable=false)
//	private Integer idTurno;

	@Column(name="alergia_turno", length=50)
	private String alergiaTurno;

	@Column(name="cantmedicina_turno")
	private Integer cantmedicinaTurno;

	@Column(name="cita_turno")
	private Integer citaTurno;

	@Column(name="dosisdiaria_turno", length=50)
	private String dosisdiariaTurno;

	@Column(name="enfermedad_turno", length=50)
	private String enfermedadTurno;

	@Column(name="fecha_turno")
	private Timestamp fechaTurno;

	@Column(name="nombremedicina_turno", length=50)
	private String nombremedicinaTurno;

	@Column(name="sintoma_turno", length=50)
	private String sintomaTurno;

	@Column(name="tiposangre_turno", length=50)
	private String tiposangreTurno;

	@Column(name="valor_turno")
	private double valorTurno;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to Medico
	@ManyToOne
	@JoinColumn(name="id_medico")
	private Medico medico;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;

	//bi-directional many-to-one association to Responsableturno
	@ManyToOne
	@JoinColumn(name="id_responsableturno")
	private Responsableturno responsableturno;

	public Turno() {
	}

//	public Integer getIdTurno() {
//		return this.idTurno;
//	}
//
//	public void setIdTurno(Integer idTurno) {
//		this.idTurno = idTurno;
//	}

	public String getAlergiaTurno() {
		return this.alergiaTurno;
	}

	public void setAlergiaTurno(String alergiaTurno) {
		this.alergiaTurno = alergiaTurno;
	}

	public Integer getCantmedicinaTurno() {
		return this.cantmedicinaTurno;
	}

	public void setCantmedicinaTurno(Integer cantmedicinaTurno) {
		this.cantmedicinaTurno = cantmedicinaTurno;
	}

	public Integer getCitaTurno() {
		return this.citaTurno;
	}

	public void setCitaTurno(Integer citaTurno) {
		this.citaTurno = citaTurno;
	}

	public String getDosisdiariaTurno() {
		return this.dosisdiariaTurno;
	}

	public void setDosisdiariaTurno(String dosisdiariaTurno) {
		this.dosisdiariaTurno = dosisdiariaTurno;
	}

	public String getEnfermedadTurno() {
		return this.enfermedadTurno;
	}

	public void setEnfermedadTurno(String enfermedadTurno) {
		this.enfermedadTurno = enfermedadTurno;
	}

	public Timestamp getFechaTurno() {
		return this.fechaTurno;
	}

	public void setFechaTurno(Timestamp fechaTurno) {
		this.fechaTurno = fechaTurno;
	}

	public String getNombremedicinaTurno() {
		return this.nombremedicinaTurno;
	}

	public void setNombremedicinaTurno(String nombremedicinaTurno) {
		this.nombremedicinaTurno = nombremedicinaTurno;
	}

	public String getSintomaTurno() {
		return this.sintomaTurno;
	}

	public void setSintomaTurno(String sintomaTurno) {
		this.sintomaTurno = sintomaTurno;
	}

	public String getTiposangreTurno() {
		return this.tiposangreTurno;
	}

	public void setTiposangreTurno(String tiposangreTurno) {
		this.tiposangreTurno = tiposangreTurno;
	}

	public double getValorTurno() {
		return this.valorTurno;
	}

	public void setValorTurno(double valorTurno) {
		this.valorTurno = valorTurno;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Responsableturno getResponsableturno() {
		return this.responsableturno;
	}

	public void setResponsableturno(Responsableturno responsableturno) {
		this.responsableturno = responsableturno;
	}

}