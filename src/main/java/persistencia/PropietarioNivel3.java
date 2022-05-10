/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "propietario_nivel3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropietarioNivel3.findAll", query = "SELECT p FROM PropietarioNivel3 p")
    , @NamedQuery(name = "PropietarioNivel3.findById", query = "SELECT p FROM PropietarioNivel3 p WHERE p.id = :id")})
public class PropietarioNivel3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_nivel3", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Nivel3 idNivel3;
    @JoinColumn(name = "id_propietario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Propietario idPropietario;

    public PropietarioNivel3() {
    }

    public PropietarioNivel3(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Nivel3 getIdNivel3() {
        return idNivel3;
    }

    public void setIdNivel3(Nivel3 idNivel3) {
        this.idNivel3 = idNivel3;
    }

    public Propietario getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Propietario idPropietario) {
        this.idPropietario = idPropietario;
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
        if (!(object instanceof PropietarioNivel3)) {
            return false;
        }
        PropietarioNivel3 other = (PropietarioNivel3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.PropietarioNivel3[ id=" + id + " ]";
    }
    
}
