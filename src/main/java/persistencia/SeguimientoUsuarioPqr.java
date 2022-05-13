package persistencia;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nellehsoft
 */
@Entity
@Table(name = "seguimiento_usuario_pqr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoUsuarioPqr.findAll", query = "SELECT s FROM SeguimientoUsuarioPqr s"),
    @NamedQuery(name = "SeguimientoUsuarioPqr.findById", query = "SELECT s FROM SeguimientoUsuarioPqr s WHERE s.id = :id"),
    @NamedQuery(name = "SeguimientoUsuarioPqr.findByFechaCreacion", query = "SELECT s FROM SeguimientoUsuarioPqr s WHERE s.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "SeguimientoUsuarioPqr.findByEstado", query = "SELECT s FROM SeguimientoUsuarioPqr s WHERE s.estado = :estado"),
    @NamedQuery(name = "SeguimientoUsuarioPqr.findByDescripcion", query = "SELECT s FROM SeguimientoUsuarioPqr s WHERE s.descripcion = :descripcion")})
public class SeguimientoUsuarioPqr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_seg_adm_pqr", referencedColumnName = "id")
    @ManyToOne
    private SeguimientoAdmPqr idSegAdmPqr;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;

    public SeguimientoUsuarioPqr() {
    }

    public SeguimientoUsuarioPqr(Integer id) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public SeguimientoAdmPqr getIdSegAdmPqr() {
        return idSegAdmPqr;
    }

    public void setIdSegAdmPqr(SeguimientoAdmPqr idSegAdmPqr) {
        this.idSegAdmPqr = idSegAdmPqr;
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
        if (!(object instanceof SeguimientoUsuarioPqr)) {
            return false;
        }
        SeguimientoUsuarioPqr other = (SeguimientoUsuarioPqr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.SeguimientoUsuarioPqr[ id=" + id + " ]";
    }
    
}
