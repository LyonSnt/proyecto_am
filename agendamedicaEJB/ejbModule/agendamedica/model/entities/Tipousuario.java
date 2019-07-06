package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipousuario database table.
 * 
 */
@Entity
@Table(name="tipousuario")
@NamedQuery(name="Tipousuario.findAll", query="SELECT t FROM Tipousuario t")
public class Tipousuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPOUSUARIO_IDTIPOUSUARIO_GENERATOR", sequenceName="SEQ_TIPOUSUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOUSUARIO_IDTIPOUSUARIO_GENERATOR")
//	@Column(name="id_tipousuario", unique=true, nullable=false)
//	private Integer idTipousuario;

	@Column(name="nombre_tipousuario", length=50)
	private String nombreTipousuario;

	public Tipousuario() {
	}

//	public Integer getIdTipousuario() {
//		return this.idTipousuario;
//	}
//
//	public void setIdTipousuario(Integer idTipousuario) {
//		this.idTipousuario = idTipousuario;
//	}

	public String getNombreTipousuario() {
		return this.nombreTipousuario;
	}

	public void setNombreTipousuario(String nombreTipousuario) {
		this.nombreTipousuario = nombreTipousuario;
	}

}