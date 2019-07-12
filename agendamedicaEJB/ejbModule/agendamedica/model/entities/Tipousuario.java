package agendamedica.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@SequenceGenerator(name="TIPOUSUARIO_IDTIPOUSUARIO_GENERATOR", sequenceName="SEQ_TIPOUSUARIO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOUSUARIO_IDTIPOUSUARIO_GENERATOR")
	@Column(name="id_tipousuario", unique=true, nullable=false)
	private Integer idTipousuario;

	@Column(name="nombre_tipousuario", length=50)
	private String nombreTipousuario;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tipousuario")
	private List<Usuario> usuarios;

	public Tipousuario() {
	}

	public Integer getIdTipousuario() {
		return this.idTipousuario;
	}

	public void setIdTipousuario(Integer idTipousuario) {
		this.idTipousuario = idTipousuario;
	}

	public String getNombreTipousuario() {
		return this.nombreTipousuario;
	}

	public void setNombreTipousuario(String nombreTipousuario) {
		this.nombreTipousuario = nombreTipousuario;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTipousuario(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTipousuario(null);

		return usuario;
	}

}