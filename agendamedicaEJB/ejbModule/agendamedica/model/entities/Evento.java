package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the evento database table.
 * 
 */
@Entity
@Table(name="evento")
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_evento", unique=true, nullable=false)
	private Integer idEvento;

	@Column(name="descripcion_evento", length=50)
	private String descripcionEvento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_evento")
	private Date fechaEvento;

	@Column(name="hora_evento")
	private Time horaEvento;

	@Column(name="ip_evento", length=50)
	private String ipEvento;

	@Column(name="nombre_evento", length=50)
	private String nombreEvento;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Evento() {
	}

	public Integer getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getDescripcionEvento() {
		return this.descripcionEvento;
	}

	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}

	public Date getFechaEvento() {
		return this.fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public Time getHoraEvento() {
		return this.horaEvento;
	}

	public void setHoraEvento(Time horaEvento) {
		this.horaEvento = horaEvento;
	}

	public String getIpEvento() {
		return this.ipEvento;
	}

	public void setIpEvento(String ipEvento) {
		this.ipEvento = ipEvento;
	}

	public String getNombreEvento() {
		return this.nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}