package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the horario database table.
 * 
 */
@Entity
@Table(name="horario")
@NamedQuery(name="Horario.findAll", query="SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HORARIO_IDHORARIO_GENERATOR", sequenceName="SEQ_HORARIO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HORARIO_IDHORARIO_GENERATOR")
	@Column(name="id_horario", unique=true, nullable=false)
	private Integer idHorario;

	@Column(name="fin_horario")
	private Time finHorario;

	@Column(name="inicio_horario")
	private Time inicioHorario;

	@Column(name="jueves_horario")
	private Integer juevesHorario;

	@Column(name="lunes_horario")
	private Integer lunesHorario;

	@Column(name="martes_horario")
	private Integer martesHorario;

	@Column(name="miercoles_horario")
	private Integer miercolesHorario;

	@Column(name="sabado_horario")
	private Integer sabadoHorario;

	@Column(name="viernes_horario")
	private Integer viernesHorario;

	//bi-directional many-to-one association to Medico
	@OneToMany(mappedBy="horario")
	private List<Medico> medicos;

	public Horario() {
	}

	public Integer getIdHorario() {
		return this.idHorario;
	}

	public void setIdHorario(Integer idHorario) {
		this.idHorario = idHorario;
	}

	public Time getFinHorario() {
		return this.finHorario;
	}

	public void setFinHorario(Time finHorario) {
		this.finHorario = finHorario;
	}

	public Time getInicioHorario() {
		return this.inicioHorario;
	}

	public void setInicioHorario(Time inicioHorario) {
		this.inicioHorario = inicioHorario;
	}

	public Integer getJuevesHorario() {
		return this.juevesHorario;
	}

	public void setJuevesHorario(Integer juevesHorario) {
		this.juevesHorario = juevesHorario;
	}

	public Integer getLunesHorario() {
		return this.lunesHorario;
	}

	public void setLunesHorario(Integer lunesHorario) {
		this.lunesHorario = lunesHorario;
	}

	public Integer getMartesHorario() {
		return this.martesHorario;
	}

	public void setMartesHorario(Integer martesHorario) {
		this.martesHorario = martesHorario;
	}

	public Integer getMiercolesHorario() {
		return this.miercolesHorario;
	}

	public void setMiercolesHorario(Integer miercolesHorario) {
		this.miercolesHorario = miercolesHorario;
	}

	public Integer getSabadoHorario() {
		return this.sabadoHorario;
	}

	public void setSabadoHorario(Integer sabadoHorario) {
		this.sabadoHorario = sabadoHorario;
	}

	public Integer getViernesHorario() {
		return this.viernesHorario;
	}

	public void setViernesHorario(Integer viernesHorario) {
		this.viernesHorario = viernesHorario;
	}

	public List<Medico> getMedicos() {
		return this.medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Medico addMedico(Medico medico) {
		getMedicos().add(medico);
		medico.setHorario(this);

		return medico;
	}

	public Medico removeMedico(Medico medico) {
		getMedicos().remove(medico);
		medico.setHorario(null);

		return medico;
	}

}