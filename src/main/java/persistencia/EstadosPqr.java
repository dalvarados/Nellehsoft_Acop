/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
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
    @NamedQuery(name = "EstadosPqr.findByEstadoPpal", query = "SELECT e FROM EstadosPqr e WHERE e.estadoPpal = :nombreEstado and e.idRol.id=:id"),
    @NamedQuery(name = "EstadosPqr.findByNombreNoCerrada", query = "SELECT e FROM EstadosPqr e WHERE e.id IN (SELECT MIN(e.id) FROM EstadosPqr e WHERE e.nombre <> 'Cerrada' GROUP BY e.nombre)")
    //@NamedQuery(name = "EstadosPqr.findByNombreNoCerrada", query = "SELECT e FROM EstadosPqr e WHERE e.nombre <> 'Cerrada'")
})
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
    @Size(max = 145)
    @Column(name = "estado_ppal")
    private String estadoPpal;    
    @OneToMany(mappedBy = "idEstadoPqr")
    private Collection<Pqr> pqrCollection;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @ManyToOne
    private Rol idRol;

    public EstadosPqr() {
    }

    public String getEstadoPpal() {
        return estadoPpal;
    }

    public void setEstadoPpal(String estadoPpal) {
        this.estadoPpal = estadoPpal;
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

    @XmlTransient
    public Collection<Pqr> getPqrCollection() {
        return pqrCollection;
    }

    public void setPqrCollection(Collection<Pqr> pqrCollection) {
        this.pqrCollection = pqrCollection;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
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
