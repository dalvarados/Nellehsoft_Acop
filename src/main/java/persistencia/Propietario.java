/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "propietario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Propietario.findAll", query = "SELECT p FROM Propietario p")
    , @NamedQuery(name = "Propietario.findById", query = "SELECT p FROM Propietario p WHERE p.id = :id")
    , @NamedQuery(name = "Propietario.findByFechaCreacion", query = "SELECT p FROM Propietario p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Propietario.findByTipoIdentificacion", query = "SELECT p FROM Propietario p WHERE p.tipoIdentificacion = :tipoIdentificacion")
    , @NamedQuery(name = "Propietario.findByIdentificacion", query = "SELECT p FROM Propietario p WHERE p.identificacion = :identificacion")
    , @NamedQuery(name = "Propietario.findByNombre", query = "SELECT p FROM Propietario p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Propietario.findByApellidos", query = "SELECT p FROM Propietario p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Propietario.findByTelefono", query = "SELECT p FROM Propietario p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Propietario.findByCorreo", query = "SELECT p FROM Propietario p WHERE p.correo = :correo")})
public class Propietario implements Serializable {

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
    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;
    @Size(max = 85)
    @Column(name = "identificacion")
    private String identificacion;
    @Size(max = 85)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 150)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;

    @ManyToMany(mappedBy = "propietarioCollection")
    private Collection<Nivel3> nivel3Collection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @ManyToOne
    private Rol idRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPropietario")
    private Collection<EstadoDeCuenta> estadoDeCuentaCollection;
    @OneToMany(mappedBy = "idPropietario")
    private Collection<Usuario> usuarioCollection;

    public Propietario() {
    }

    public Propietario(Integer id) {
        this.id = id;
    }

    public Propietario(Integer id, Date fechaCreacion) {
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

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    @XmlTransient
    public Collection<Nivel3> getNivel3Collection() {
        return nivel3Collection;
    }

    public void setNivel3Collection(Collection<Nivel3> nivel3Collection) {
        this.nivel3Collection = nivel3Collection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @XmlTransient
    public Collection<EstadoDeCuenta> getEstadoDeCuentaCollection() {
        return estadoDeCuentaCollection;
    }

    public void setEstadoDeCuentaCollection(Collection<EstadoDeCuenta> estadoDeCuentaCollection) {
        this.estadoDeCuentaCollection = estadoDeCuentaCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
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
        if (!(object instanceof Propietario)) {
            return false;
        }
        Propietario other = (Propietario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Propietario[ id=" + id + " ]";
    }
    
}
