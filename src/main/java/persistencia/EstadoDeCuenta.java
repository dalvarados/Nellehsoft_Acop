/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "estado_de_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoDeCuenta.findAll", query = "SELECT e FROM EstadoDeCuenta e")
    , @NamedQuery(name = "EstadoDeCuenta.findById", query = "SELECT e FROM EstadoDeCuenta e WHERE e.id = :id")
    , @NamedQuery(name = "EstadoDeCuenta.findByFechaCreacion", query = "SELECT e FROM EstadoDeCuenta e WHERE e.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "EstadoDeCuenta.findByFechaCorte", query = "SELECT e FROM EstadoDeCuenta e WHERE e.fechaCorte = :fechaCorte")
    , @NamedQuery(name = "EstadoDeCuenta.findByValor", query = "SELECT e FROM EstadoDeCuenta e WHERE e.valor = :valor")
    , @NamedQuery(name = "EstadoDeCuenta.findByIdUsuario", query = "SELECT e FROM EstadoDeCuenta e JOIN E.idUsuario AS a WHERE a.id=e.idUsuario.id and  a.id = :id")        
    , @NamedQuery(name = "EstadoDeCuenta.findByEstado", query = "SELECT e FROM EstadoDeCuenta e WHERE e.estado = :estado")})
public class EstadoDeCuenta implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_corte")
    @Temporal(TemporalType.DATE)
    private Date fechaCorte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Size(max = 85)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_propietario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Propietario idPropietario;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public EstadoDeCuenta() {
    }

    public EstadoDeCuenta(Integer id) {
        this.id = id;
    }

    public EstadoDeCuenta(Integer id, Date fechaCreacion, Date fechaCorte) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaCorte = fechaCorte;
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

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Propietario getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Propietario idPropietario) {
        this.idPropietario = idPropietario;
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
        if (!(object instanceof EstadoDeCuenta)) {
            return false;
        }
        EstadoDeCuenta other = (EstadoDeCuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.EstadoDeCuenta[ id=" + id + " ]";
    }
    
}
