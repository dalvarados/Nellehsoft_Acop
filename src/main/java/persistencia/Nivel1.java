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
@Table(name = "nivel1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivel1.findAll", query = "SELECT n FROM Nivel1 n")
    , @NamedQuery(name = "Nivel1.findById", query = "SELECT n FROM Nivel1 n WHERE n.id = :id")
    , @NamedQuery(name = "Nivel1.findByReferencia", query = "SELECT n FROM Nivel1 n WHERE n.referencia = :referencia")})
public class Nivel1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "referencia")
    private String referencia;
    @OneToMany(mappedBy = "idNivel1")
    private Collection<Nivel2> nivel2Collection;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    @ManyToOne
    private TipoNivel idTipo;

    public Nivel1() {
    }

    public Nivel1(Integer id) {
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
    public Collection<Nivel2> getNivel2Collection() {
        return nivel2Collection;
    }

    public void setNivel2Collection(Collection<Nivel2> nivel2Collection) {
        this.nivel2Collection = nivel2Collection;
    }

    public TipoNivel getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoNivel idTipo) {
        this.idTipo = idTipo;
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
        if (!(object instanceof Nivel1)) {
            return false;
        }
        Nivel1 other = (Nivel1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Nivel1[ id=" + id + " ]";
    }
    
}
