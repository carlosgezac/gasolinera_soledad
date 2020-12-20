package com.gasolinerasoledadsacv.entity;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "checador")
public class Checador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_checador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChecador;
    @Basic(optional = false)
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;

    public Checador() {
    }

    public Checador(Integer idChecador) {
        this.idChecador = idChecador;
    }

    public Checador(Integer idChecador, Date fechaHora) {
        this.idChecador = idChecador;
        this.fechaHora = fechaHora;
    }

    public Integer getIdChecador() {
        return idChecador;
    }

    public void setIdChecador(Integer idChecador) {
        this.idChecador = idChecador;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChecador != null ? idChecador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Checador)) {
            return false;
        }
        Checador other = (Checador) object;
        if ((this.idChecador == null && other.idChecador != null) || (this.idChecador != null && !this.idChecador.equals(other.idChecador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gasolinerasoledadsacv.entity.Checador[ idChecador=" + idChecador + " ]";
    }

}
