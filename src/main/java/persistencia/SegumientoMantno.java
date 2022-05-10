/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "segumiento_mantno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SegumientoMantno.findAll", query = "SELECT s FROM SegumientoMantno s")
    , @NamedQuery(name = "SegumientoMantno.findById", query = "SELECT s FROM SegumientoMantno s WHERE s.id = :id")
    , @NamedQuery(name = "SegumientoMantno.findByFechaCreacion", query = "SELECT s FROM SegumientoMantno s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SegumientoMantno.findByDescripcion", query = "SELECT s FROM SegumientoMantno s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "SegumientoMantno.findByEstado", query = "SELECT s FROM SegumientoMantno s WHERE s.estado = :estado")})
public class SegumientoMantno implements Serializable {

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
    @Size(max = 85)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 85)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_mantenimiento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Mantenimiento idMantenimiento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public SegumientoMantno() {
    }

    public SegumientoMantno(Integer id) {
        this.id = id;
    }

    public SegumientoMantno(Integer id, Date fechaCreacion) {
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

    public Mantenimiento getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Mantenimiento idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
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
        if (!(object instanceof SegumientoMantno)) {
            return false;
        }
        SegumientoMantno other = (SegumientoMantno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.SegumientoMantno[ id=" + id + " ]";
    }
    
}
