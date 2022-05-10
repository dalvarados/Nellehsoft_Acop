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
@Table(name = "tercero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tercero.findAll", query = "SELECT t FROM Tercero t")
    , @NamedQuery(name = "Tercero.findById", query = "SELECT t FROM Tercero t WHERE t.id = :id")
    , @NamedQuery(name = "Tercero.findByFechaCreacion", query = "SELECT t FROM Tercero t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Tercero.findByTipoIdentificacion", query = "SELECT t FROM Tercero t WHERE t.tipoIdentificacion = :tipoIdentificacion")
    , @NamedQuery(name = "Tercero.findByIdentificacion", query = "SELECT t FROM Tercero t WHERE t.identificacion = :identificacion")
    , @NamedQuery(name = "Tercero.findByNombre", query = "SELECT t FROM Tercero t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tercero.findByDireccion", query = "SELECT t FROM Tercero t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "Tercero.findByTelefono", query = "SELECT t FROM Tercero t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Tercero.findByCorreo", query = "SELECT t FROM Tercero t WHERE t.correo = :correo")
    , @NamedQuery(name = "Tercero.findByTipo", query = "SELECT t FROM Tercero t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "Tercero.findByEsPorveedor", query = "SELECT t FROM Tercero t WHERE t.esPorveedor = :esPorveedor")})
public class Tercero implements Serializable {

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
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 150)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "esPorveedor")
    private Boolean esPorveedor;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @ManyToOne
    private Rol idRol;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idTercero")
    private Collection<Mantenimiento> mantenimientoCollection;
    @OneToMany(mappedBy = "idTercero")
    private Collection<Pqr> pqrCollection;
    @OneToMany(mappedBy = "idTercero")
    private Collection<Copropiedad> copropiedadCollection;

    public Tercero() {
    }

    public Tercero(Integer id) {
        this.id = id;
    }

    public Tercero(Integer id, Date fechaCreacion) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getEsPorveedor() {
        return esPorveedor;
    }

    public void setEsPorveedor(Boolean esPorveedor) {
        this.esPorveedor = esPorveedor;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
    public Collection<Copropiedad> getCopropiedadCollection() {
        return copropiedadCollection;
    }

    public void setCopropiedadCollection(Collection<Copropiedad> copropiedadCollection) {
        this.copropiedadCollection = copropiedadCollection;
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
        if (!(object instanceof Tercero)) {
            return false;
        }
        Tercero other = (Tercero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Tercero[ id=" + id + " ]";
    }
    
}
