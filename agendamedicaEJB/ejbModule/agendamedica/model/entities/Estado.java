package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@Table(name="estado")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@Column(name="id_estado", unique=true, nullable=false)
//	private Integer idEstado;

	private Integer estado;

	@Column(name="nombre_estado", length=50)
	private String nombreEstado;

	//bi-directional many-to-one association to Turno
	@OneToMany(mappedBy="estado")
	private List<Turno> turnos;

	public Estado() {
	}

//	public Integer getIdEstado() {
//		return this.idEstado;
//	}
//
//	public void setIdEstado(Integer idEstado) {
//		this.idEstado = idEstado;
//	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getNombreEstado() {
		return this.nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public List<Turno> getTurnos() {
		return this.turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Turno addTurno(Turno turno) {
		getTurnos().add(turno);
		turno.setEstado(this);

		return turno;
	}

	public Turno removeTurno(Turno turno) {
		getTurnos().remove(turno);
		turno.setEstado(null);

		return turno;
	}

}