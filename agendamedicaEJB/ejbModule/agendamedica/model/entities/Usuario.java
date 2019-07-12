package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_IDUSUARIO_GENERATOR", sequenceName="SEQ_USUARIO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="id_usuario", unique=true, nullable=false)
	private Integer idUsuario;

	@Column(name="estado_usuario", length=10)
	private String estadoUsuario;

	@Column(name="fecharegistro_usuario")
	private Timestamp fecharegistroUsuario;

	@Column(name="nombre_usuario", length=50)
	private String nombreUsuario;

	@Column(name="password_usuario", length=20)
	private String passwordUsuario;

	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="usuario")
	private List<Evento> eventos;

	//bi-directional many-to-one association to Medico
	@ManyToOne
	@JoinColumn(name="id_medico")
	private Medico medico;

	//bi-directional many-to-one association to Responsableturno
	@ManyToOne
	@JoinColumn(name="id_responsableturno")
	private Responsableturno responsableturno;

	//bi-directional many-to-one association to Tipousuario
	@ManyToOne
	@JoinColumn(name="id_tipousuario")
	private Tipousuario tipousuario;

	public Usuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Timestamp getFecharegistroUsuario() {
		return this.fecharegistroUsuario;
	}

	public void setFecharegistroUsuario(Timestamp fecharegistroUsuario) {
		this.fecharegistroUsuario = fecharegistroUsuario;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPasswordUsuario() {
		return this.passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setUsuario(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setUsuario(null);

		return evento;
	}

	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Responsableturno getResponsableturno() {
		return this.responsableturno;
	}

	public void setResponsableturno(Responsableturno responsableturno) {
		this.responsableturno = responsableturno;
	}

	public Tipousuario getTipousuario() {
		return this.tipousuario;
	}

	public void setTipousuario(Tipousuario tipousuario) {
		this.tipousuario = tipousuario;
	}

}