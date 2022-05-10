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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "copropiedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Copropiedad.findAll", query = "SELECT c FROM Copropiedad c")
    , @NamedQuery(name = "Copropiedad.findById", query = "SELECT c FROM Copropiedad c WHERE c.id = :id")
    , @NamedQuery(name = "Copropiedad.findByNombre", query = "SELECT c FROM Copropiedad c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Copropiedad.findByNit", query = "SELECT c FROM Copropiedad c WHERE c.nit = :nit")
    , @NamedQuery(name = "Copropiedad.findByDireccion", query = "SELECT c FROM Copropiedad c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Copropiedad.findByTelefono", query = "SELECT c FROM Copropiedad c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Copropiedad.findByCorreo", query = "SELECT c FROM Copropiedad c WHERE c.correo = :correo")})
public class Copropiedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 85)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 85)
    @Column(name = "nit")
    private String nit;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @JoinColumn(name = "id_tercero", referencedColumnName = "id")
    @ManyToOne
    private Tercero idTercero;

    public Copropiedad() {
    }

    public Copropiedad(Integer id) {
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Tercero getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(Tercero idTercero) {
        this.idTercero = idTercero;
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
        if (!(object instanceof Copropiedad)) {
            return false;
        }
        Copropiedad other = (Copropiedad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Copropiedad[ id=" + id + " ]";
    }
    
}
