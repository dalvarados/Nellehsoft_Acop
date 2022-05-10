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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tipo_nivel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNivel.findAll", query = "SELECT t FROM TipoNivel t")
    , @NamedQuery(name = "TipoNivel.findById", query = "SELECT t FROM TipoNivel t WHERE t.id = :id")
    , @NamedQuery(name = "TipoNivel.findByNombre", query = "SELECT t FROM TipoNivel t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoNivel.findByTipo", query = "SELECT t FROM TipoNivel t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TipoNivel.findByBandPqr", query = "SELECT t FROM TipoNivel t WHERE t.bandPqr = :bandPqr")})
public class TipoNivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 85)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "band_pqr")
    private Boolean bandPqr;

    public TipoNivel() {
    }

    public TipoNivel(Integer id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getBandPqr() {
        return bandPqr;
    }

    public void setBandPqr(Boolean bandPqr) {
        this.bandPqr = bandPqr;
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
        if (!(object instanceof TipoNivel)) {
            return false;
        }
        TipoNivel other = (TipoNivel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TipoNivel[ id=" + id + " ]";
    }
    
}
