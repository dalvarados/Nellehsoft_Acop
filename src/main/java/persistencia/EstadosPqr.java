package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "estados_pqr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosPqr.findAll", query = "SELECT e FROM EstadosPqr e"),
    @NamedQuery(name = "EstadosPqr.findById", query = "SELECT e FROM EstadosPqr e WHERE e.id = :id"),
    @NamedQuery(name = "EstadosPqr.findByNombre", query = "SELECT e FROM EstadosPqr e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadosPqr.findByTipoUsuario", query = "SELECT e FROM EstadosPqr e WHERE e.tipoUsuario = :tipoUsuario")})
public class EstadosPqr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 145)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    @OneToMany(mappedBy = "idEstadoPqr")
    private Collection<Pqr> pqrCollection;

    public EstadosPqr() {
    }

    public EstadosPqr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @XmlTransient
    public Collection<Pqr> getPqrCollection() {
        return pqrCollection;
    }

    public void setPqrCollection(Collection<Pqr> pqrCollection) {
        this.pqrCollection = pqrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosPqr)) {
            return false;
        }
        EstadosPqr other = (EstadosPqr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.EstadosPqr[ id=" + id + " ]";
    }
    
}
