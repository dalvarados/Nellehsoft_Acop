/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
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
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findById", query = "SELECT r FROM Reserva r WHERE r.id = :id")
    , @NamedQuery(name = "Reserva.findByFechaCreacion", query = "SELECT r FROM Reserva r WHERE r.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Reserva.findByCantAsistentes", query = "SELECT r FROM Reserva r WHERE r.cantAsistentes = :cantAsistentes")
    , @NamedQuery(name = "Reserva.findByFechaInicio", query = "SELECT r FROM Reserva r WHERE r.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Reserva.findByFechaFin", query = "SELECT r FROM Reserva r WHERE r.fechaFin = :fechaFin")
    , @NamedQuery(name = "Reserva.findByAsunto", query = "SELECT r FROM Reserva r WHERE r.asunto = :asunto")
    , @NamedQuery(name = "Reserva.findByDescripcion", query = "SELECT r FROM Reserva r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Reserva.findByEstado", query = "SELECT r FROM Reserva r WHERE r.estado = :estado")})
public class Reserva implements Serializable {

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
    @Column(name = "cant_asistentes")
    private Integer cantAsistentes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 45)
    @Column(name = "asunto")
    private String asunto;
    @Size(max = 85)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 85)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_tipo_reserva", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoReserva idTipoReserva;
    @JoinColumn(name = "id_nivel2", referencedColumnName = "id")
    @ManyToOne
    private Nivel2 idNivel2;
    @JoinColumn(name = "id_nivel3", referencedColumnName = "id")
    @ManyToOne
    private Nivel3 idNivel3;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;

    public Reserva() {
    }

    public Reserva(Integer id) {
        this.id = id;
    }

    public Reserva(Integer id, Date fechaCreacion, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public Integer getCantAsistentes() {
        return cantAsistentes;
    }

    public void setCantAsistentes(Integer cantAsistentes) {
        this.cantAsistentes = cantAsistentes;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoReserva getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(TipoReserva idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public Nivel2 getIdNivel2() {
        return idNivel2;
    }

    public void setIdNivel2(Nivel2 idNivel2) {
        this.idNivel2 = idNivel2;
    }

    public Nivel3 getIdNivel3() {
        return idNivel3;
    }

    public void setIdNivel3(Nivel3 idNivel3) {
        this.idNivel3 = idNivel3;
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
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Reserva[ id=" + id + " ]";
    }
    
}
