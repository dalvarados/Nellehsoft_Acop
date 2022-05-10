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
@Table(name = "nivel2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivel2.findAll", query = "SELECT n FROM Nivel2 n")
    , @NamedQuery(name = "Nivel2.findById", query = "SELECT n FROM Nivel2 n WHERE n.id = :id")
    , @NamedQuery(name = "Nivel2.findByReferencia", query = "SELECT n FROM Nivel2 n WHERE n.referencia = :referencia")})
public class Nivel2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "referencia")
    private String referencia;
    @OneToMany(mappedBy = "idNivel2")
    private Collection<Nivel3> nivel3Collection;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne
    private TipoNivel idTipo;
    @JoinColumn(name = "id_nivel1", referencedColumnName = "id")
    @ManyToOne
    private Nivel1 idNivel1;
    @OneToMany(mappedBy = "idNivel2")
    private Collection<Mantenimiento> mantenimientoCollection;
    @OneToMany(mappedBy = "idNivel2")
    private Collection<Pqr> pqrCollection;
    @OneToMany(mappedBy = "idNivel2")
    private Collection<Reserva> reservaCollection;

    public Nivel2() {
    }

    public Nivel2(Integer id) {
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

    @XmlTransient
    public Collection<Nivel3> getNivel3Collection() {
        return nivel3Collection;
    }

    public void setNivel3Collection(Collection<Nivel3> nivel3Collection) {
        this.nivel3Collection = nivel3Collection;
    }

    public TipoNivel getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoNivel idTipo) {
        this.idTipo = idTipo;
    }

    public Nivel1 getIdNivel1() {
        return idNivel1;
    }

    public void setIdNivel1(Nivel1 idNivel1) {
        this.idNivel1 = idNivel1;
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
        if (!(object instanceof Nivel2)) {
            return false;
        }
        Nivel2 other = (Nivel2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Nivel2[ id=" + id + " ]";
    }
    
}
