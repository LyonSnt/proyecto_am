package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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
	@Column(name="id_usuario", unique=true, nullable=false, length=10)
	private String idUsuario;

	@Column(name="estado_usuario", length=10)
	private String estadoUsuario;

	@Column(name="fecharegistro_usuario")
	private Timestamp fecharegistroUsuario;

	@Column(name="id_medico")
	private Integer idMedico;

	@Column(name="id_responsableturno")
	private Integer idResponsableturno;

	@Column(name="id_tipousuario")
	private Integer idTipousuario;

	@Column(name="nombre_usuario", length=50)
	private String nombreUsuario;

	@Column(name="password_usuario", length=20)
	private String passwordUsuario;

	public Usuario() {
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
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

	public Integer getIdMedico() {
		return this.idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public Integer getIdResponsableturno() {
		return this.idResponsableturno;
	}

	public void setIdResponsableturno(Integer idResponsableturno) {
		this.idResponsableturno = idResponsableturno;
	}

	public Integer getIdTipousuario() {
		return this.idTipousuario;
	}

	public void setIdTipousuario(Integer idTipousuario) {
		this.idTipousuario = idTipousuario;
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

}