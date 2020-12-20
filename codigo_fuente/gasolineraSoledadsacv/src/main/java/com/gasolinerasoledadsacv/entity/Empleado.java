package com.gasolinerasoledadsacv.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;
    @Basic(optional = false)
    @Column(name = "numero_empleado")
    private String numeroEmpleado;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "curp")
    private String curp;
    @Basic(optional = false)
    @Column(name = "rfc")
    private String rfc;
    @Basic(optional = false)
    @Column(name = "escolaridad")
    private String escolaridad;
    @Basic(optional = false)
    @Column(name = "puesto")
    private String puesto;
    @Basic(optional = false)
    @Column(name = "nss_imss")
    private String nssImss;
    @Basic(optional = false)
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "foto")
    private String rutaFoto;
    @Basic(optional = false)
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "genero")
    private String genero;
    @Column(name = "enfermedad")
    private String enfermedad;
    @Column(name = "incidentes_laborales")
    private String incidentesLaborales;
    @Column(name = "cursos_capacitacion")
    private String cursosCapacitacion;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_contacto", referencedColumnName = "id_contacto")
    @OneToOne(optional = false)
    private Contacto contacto;
    @JoinColumn(name = "id_direccion", referencedColumnName = "id_direccion")
    @OneToOne(optional = false)
    private Direccion direccion;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(Integer idEmpleado, String numeroEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String curp, String rfc, String escolaridad, String puesto, String nssImss, String estatus, Date fechaIngreso) {
        this.idEmpleado = idEmpleado;
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.rfc = rfc;
        this.escolaridad = escolaridad;
        this.puesto = puesto;
        this.nssImss = nssImss;
        this.estatus = estatus;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNssImss() {
        return nssImss;
    }

    public void setNssImss(String nssImss) {
        this.nssImss = nssImss;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getIncidentesLaborales() {
        return incidentesLaborales;
    }

    public void setIncidentesLaborales(String incidentesLaborales) {
        this.incidentesLaborales = incidentesLaborales;
    }

    public String getCursosCapacitacion() {
        return cursosCapacitacion;
    }

    public void setCursosCapacitacion(String cursosCapacitacion) {
        this.cursosCapacitacion = cursosCapacitacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.numeroEmpleado, other.numeroEmpleado)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidoPaterno, other.apellidoPaterno)) {
            return false;
        }
        if (!Objects.equals(this.apellidoMaterno, other.apellidoMaterno)) {
            return false;
        }
        if (!Objects.equals(this.curp, other.curp)) {
            return false;
        }
        if (!Objects.equals(this.rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(this.escolaridad, other.escolaridad)) {
            return false;
        }
        if (!Objects.equals(this.puesto, other.puesto)) {
            return false;
        }
        if (!Objects.equals(this.nssImss, other.nssImss)) {
            return false;
        }
        if (!Objects.equals(this.estatus, other.estatus)) {
            return false;
        }
        if (!Objects.equals(this.rutaFoto, other.rutaFoto)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.enfermedad, other.enfermedad)) {
            return false;
        }
        if (!Objects.equals(this.incidentesLaborales, other.incidentesLaborales)) {
            return false;
        }
        if (!Objects.equals(this.cursosCapacitacion, other.cursosCapacitacion)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.idEmpleado, other.idEmpleado)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        if (!Objects.equals(this.fechaIngreso, other.fechaIngreso)) {
            return false;
        }
        if (!Objects.equals(this.contacto, other.contacto)) {
            return false;
        }
        return Objects.equals(this.direccion, other.direccion);
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", numeroEmpleado=" + numeroEmpleado + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", curp=" + curp + ", rfc=" + rfc + ", escolaridad=" + escolaridad + ", puesto=" + puesto + ", nssImss=" + nssImss + ", estatus=" + estatus + ", rutaFoto=" + rutaFoto + ", fechaIngreso=" + fechaIngreso + ", genero=" + genero + ", enfermedad=" + enfermedad + ", incidentesLaborales=" + incidentesLaborales + ", cursosCapacitacion=" + cursosCapacitacion + ", observaciones=" + observaciones + ", contacto=" + contacto + ", direccion=" + direccion + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Empleado e = (Empleado) super.clone();
        e.direccion = (Direccion) this.direccion.clone();
        e.contacto = (Contacto) this.contacto.clone();
        return e;
    }

}
