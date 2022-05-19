package persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nellehsoft
 */
@Entity
@Table(name = "seguimiento_adm_pqr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoAdmPqr.findAll", query = "SELECT s FROM SeguimientoAdmPqr s"),
    @NamedQuery(name = "SeguimientoAdmPqr.findById", query = "SELECT s FROM SeguimientoAdmPqr s WHERE s.id = :id"),
    @NamedQuery(name = "SeguimientoAdmPqr.findByFechaCreacion", query = "SELECT s FROM SeguimientoAdmPqr s WHERE s.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "SeguimientoAdmPqr.findByDescripcion", query = "SELECT s FROM SeguimientoAdmPqr s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "SeguimientoAdmPqr.findByEstado", query = "SELECT s FROM SeguimientoAdmPqr s WHERE s.estado = :estado")})
public class SeguimientoAdmPqr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "fecha_ejecucion")
    @Temporal(TemporalType.DATE)
    private Date fechaEjecucion;    
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idSegAdmPqr")
    private Collection<SeguimientoUsuarioPqr> seguimientoUsuarioPqrCollection;
    @JoinColumn(name = "id_pqr", referencedColumnName = "id")
    @ManyToOne
    private Pqr idPqr;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;

    public SeguimientoAdmPqr() {
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public SeguimientoAdmPqr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<SeguimientoUsuarioPqr> getSeguimientoUsuarioPqrCollection() {
        return seguimientoUsuarioPqrCollection;
    }

    public void setSeguimientoUsuarioPqrCollection(Collection<SeguimientoUsuarioPqr> seguimientoUsuarioPqrCollection) {
        this.seguimientoUsuarioPqrCollection = seguimientoUsuarioPqrCollection;
    }

    public Pqr getIdPqr() {
        return idPqr;
    }

    public void setIdPqr(Pqr idPqr) {
        this.idPqr = idPqr;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof SeguimientoAdmPqr)) {
            return false;
        }
        SeguimientoAdmPqr other = (SeguimientoAdmPqr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.SeguimientoAdmPqr[ id=" + id + " ]";
    }
    
}
