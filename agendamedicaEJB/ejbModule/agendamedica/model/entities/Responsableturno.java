package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the responsableturno database table.
 * 
 */
@Entity
@Table(name="responsableturno")
@NamedQuery(name="Responsableturno.findAll", query="SELECT r FROM Responsableturno r")
public class Responsableturno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@Column(name="id_responsableturno", unique=true, nullable=false)
//	private Integer idResponsableturno;

	@Column(name="apellido_responsableturno", length=50)
	private String apellidoResponsableturno;

	@Column(name="cedula_responsableturno", length=10)
	private String cedulaResponsableturno;

	@Column(name="celular_responsableturno", precision=10)
	private BigDecimal celularResponsableturno;

	@Column(name="nombre_responsableturno", length=50)
	private String nombreResponsableturno;

	//bi-directional many-to-one association to Turno
	@OneToMany(mappedBy="responsableturno")
	private List<Turno> turnos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="responsableturno")
	private List<Usuario> usuarios;

	public Responsableturno() {
	}

//	public Integer getIdResponsableturno() {
//		return this.idResponsableturno;
//	}
//
//	public void setIdResponsableturno(Integer idResponsableturno) {
//		this.idResponsableturno = idResponsableturno;
//	}

	public String getApellidoResponsableturno() {
		return this.apellidoResponsableturno;
	}

	public void setApellidoResponsableturno(String apellidoResponsableturno) {
		this.apellidoResponsableturno = apellidoResponsableturno;
	}

	public String getCedulaResponsableturno() {
		return this.cedulaResponsableturno;
	}

	public void setCedulaResponsableturno(String cedulaResponsableturno) {
		this.cedulaResponsableturno = cedulaResponsableturno;
	}

	public BigDecimal getCelularResponsableturno() {
		return this.celularResponsableturno;
	}

	public void setCelularResponsableturno(BigDecimal celularResponsableturno) {
		this.celularResponsableturno = celularResponsableturno;
	}

	public String getNombreResponsableturno() {
		return this.nombreResponsableturno;
	}

	public void setNombreResponsableturno(String nombreResponsableturno) {
		this.nombreResponsableturno = nombreResponsableturno;
	}

	public List<Turno> getTurnos() {
		return this.turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Turno addTurno(Turno turno) {
		getTurnos().add(turno);
		turno.setResponsableturno(this);

		return turno;
	}

	public Turno removeTurno(Turno turno) {
		getTurnos().remove(turno);
		turno.setResponsableturno(null);

		return turno;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setResponsableturno(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setResponsableturno(null);

		return usuario;
	}

}