/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "nivel3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivel3.findAll", query = "SELECT n FROM Nivel3 n")
    , @NamedQuery(name = "Nivel3.findById", query = "SELECT n FROM Nivel3 n WHERE n.id = :id")
    , @NamedQuery(name = "Nivel3.findByReferencia", query = "SELECT n FROM Nivel3 n WHERE n.referencia = :referencia")
    , @NamedQuery(name = "Nivel3.findByCapacidad", query = "SELECT n FROM Nivel3 n WHERE n.capacidad = :capacidad")
    , @NamedQuery(name = "Nivel3.findByMedidas", query = "SELECT n FROM Nivel3 n WHERE n.medidas = :medidas")
    , @NamedQuery(name = "Nivel3.findByIdNivel2", query = "SELECT e FROM Nivel3 e JOIN E.idNivel2 AS a WHERE a.id=e.idNivel2.id and  a.id = :idNivel2")        })
public class Nivel3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "referencia")
    private String referencia;
    @Size(max = 45)
    @Column(name = "capacidad")
    private String capacidad;
    @Size(max = 45)
    @Column(name = "medidas")
    private String medidas;
    @JoinTable(name = "propietario_nivel3", joinColumns = {
        @JoinColumn(name = "id_nivel3", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_propietario", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Propietario> propietarioCollection;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne
    private TipoNivel idTipo;
    @JoinColumn(name = "id_nivel2", referencedColumnName = "id")
    @ManyToOne
    private Nivel2 idNivel2;
    @OneToMany(mappedBy = "idNivel3")
    private Collection<Mantenimiento> mantenimientoCollection;
    @OneToMany(mappedBy = "idNivel3")
    private Collection<Pqr> pqrCollection;
    @OneToMany(mappedBy = "idNivel3")
    private Collection<Reserva> reservaCollection;

    public Nivel3() {
    }

    public Nivel3(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    @XmlTransient
    public Collection<Propietario> getPropietarioCollection() {
        return propietarioCollection;
    }

    public void setPropietarioCollection(Collection<Propietario> propietarioCollection) {
        this.propietarioCollection = propietarioCollection;
    }

    public TipoNivel getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoNivel idTipo) {
        this.idTipo = idTipo;
    }

    public Nivel2 getIdNivel2() {
        return idNivel2;
    }

    public void setIdNivel2(Nivel2 idNivel2) {
        this.idNivel2 = idNivel2;
    }

    @XmlTransient
    public Collection<Mantenimiento> getMantenimientoCollection() {
        return mantenimientoCollection;
    }

    public void setMantenimientoCollection(Collection<Mantenimiento> mantenimientoCollection) {
        this.mantenimientoCollection = mantenimientoCollection;
    }

    @XmlTransient
    public Collection<Pqr> getPqrCollection() {
        return pqrCollection;
    }

    public void setPqrCollection(Collection<Pqr> pqrCollection) {
        this.pqrCollection = pqrCollection;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
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
        if (!(object instanceof Nivel3)) {
            return false;
        }
        Nivel3 other = (Nivel3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Nivel3[ id=" + id + " ]";
    }
    
}
