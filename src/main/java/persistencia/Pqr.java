/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "pqr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pqr.findAll", query = "SELECT p FROM Pqr p")
    , @NamedQuery(name = "Pqr.findById", query = "SELECT p FROM Pqr p WHERE p.id = :id")
    , @NamedQuery(name = "Pqr.findByFechaCreacion", query = "SELECT p FROM Pqr p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Pqr.findByAsunto", query = "SELECT p FROM Pqr p WHERE p.asunto = :asunto")
    , @NamedQuery(name = "Pqr.findByDescripcion", query = "SELECT p FROM Pqr p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Pqr.findByIdUsuario", query = "SELECT e FROM Pqr e JOIN E.idUsuario AS a WHERE a.id=e.idUsuario.id and  a.id = :id")        
    , @NamedQuery(name = "Pqr.findByEstado", query = "SELECT p FROM Pqr p WHERE p.estado = :estado")})
public class Pqr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 45)
    @Column(name = "asunto")
    private String asunto;
    @Size(max = 85)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 85)
    @Column(name = "estado")
    private String estado;
    @Lob
    @Column(name = "adjunto")
    private byte[] adjunto;
    @JoinColumn(name = "id_estado_pqr", referencedColumnName = "id")
    @ManyToOne
    private EstadosPqr idEstadoPqr;    
    @JoinColumn(name = "id_tipo_pqr", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoPqr idTipoPqr;
    @JoinColumn(name = "id_nivel2", referencedColumnName = "id")
    @ManyToOne
    private Nivel2 idNivel2;
    @JoinColumn(name = "id_nivel3", referencedColumnName = "id")
    @ManyToOne
    private Nivel3 idNivel3;
    @JoinColumn(name = "id_tercero", referencedColumnName = "id")
    @ManyToOne
    private Tercero idTercero;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;
    private Collection<SeguimientoAdmPqr> seguimientoAdmPqrCollection;
    
    public Pqr() {
    }

    public EstadosPqr getIdEstadoPqr() {
        return idEstadoPqr;
    }

    public void setIdEstadoPqr(EstadosPqr idEstadoPqr) {
        this.idEstadoPqr = idEstadoPqr;
    }
    
    public Pqr(Integer id) {
        this.id = id;
    }

    public Pqr(Integer id, Date fechaCreacion) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
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

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
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

    public byte[] getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(byte[] adjunto) {
        this.adjunto = adjunto;
    }

    public TipoPqr getIdTipoPqr() {
        return idTipoPqr;
    }

    public void setIdTipoPqr(TipoPqr idTipoPqr) {
        this.idTipoPqr = idTipoPqr;
    }

    public Nivel2 getIdNivel2() {
        return idNivel2;
    }

    public void setIdNivel2(Nivel2 idNivel2) {
        this.idNivel2 = idNivel2;
    }

    public Nivel3 getIdNivel3() {
        return idNivel3;
    }

    public void setIdNivel3(Nivel3 idNivel3) {
        this.idNivel3 = idNivel3;
    }

    public Tercero getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(Tercero idTercero) {
        this.idTercero = idTercero;
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

    @XmlTransient
    public Collection<SeguimientoAdmPqr> getSeguimientoAdmPqrCollection() {
        return seguimientoAdmPqrCollection;
    }

    public void setSeguimientoAdmPqrCollection(Collection<SeguimientoAdmPqr> seguimientoAdmPqrCollection) {
        this.seguimientoAdmPqrCollection = seguimientoAdmPqrCollection;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pqr)) {
            return false;
        }
        Pqr other = (Pqr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Pqr[ id=" + id + " ]";
    }

}
