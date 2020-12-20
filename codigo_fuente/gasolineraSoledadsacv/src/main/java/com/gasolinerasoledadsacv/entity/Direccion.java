package com.gasolinerasoledadsacv.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_direccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;
    @Basic(optional = false)
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero_interior")
    private String numeroInterior;
    @Basic(optional = false)
    @Column(name = "numero_exterior")
    private String numeroExterior;
    @Basic(optional = false)
    @Column(name = "colonia")
    private String colonia;
    @Basic(optional = false)
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "estado")
    private String estado;
    @Column(name = "datos_adicionales")
    private String datosAdicionales;

    public Direccion() {
    }

    public Direccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Direccion(Integer idDireccion, String calle, String numeroExterior, String colonia, String municipio) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.colonia = colonia;
        this.municipio = municipio;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccion != null ? idDireccion.hashCode() : 0);
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
        final Direccion other = (Direccion) obj;
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.numeroInterior, other.numeroInterior)) {
            return false;
        }
        if (!Objects.equals(this.numeroExterior, other.numeroExterior)) {
            return false;
        }
        if (!Objects.equals(this.colonia, other.colonia)) {
            return false;
        }
        if (!Objects.equals(this.municipio, other.municipio)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.datosAdicionales, other.datosAdicionales)) {
            return false;
        }
        return Objects.equals(this.idDireccion, other.idDireccion);
    }

    @Override
    public String toString() {
        return "Direccion{" + "idDireccion=" + idDireccion + ", calle=" + calle + ", numeroInterior=" + numeroInterior + ", numeroExterior=" + numeroExterior + ", colonia=" + colonia + ", municipio=" + municipio + ", estado=" + estado + ", datosAdicionales=" + datosAdicionales + '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
