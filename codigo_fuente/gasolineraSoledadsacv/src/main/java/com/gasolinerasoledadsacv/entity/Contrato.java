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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contrato")
public class Contrato implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Integer idContrato;
    @Basic(optional = false)
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "fecha_finiquito")
    @Temporal(TemporalType.DATE)
    private Date fechaFiniquito;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Contrato() {
    }

    public Contrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Contrato(Integer idContrato, Date fechaInicial) {
        this.idContrato = idContrato;
        this.fechaInicial = fechaInicial;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaFiniquito() {
        return fechaFiniquito;
    }

    public void setFechaFiniquito(Date fechaFiniquito) {
        this.fechaFiniquito = fechaFiniquito;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
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
        final Contrato other = (Contrato) obj;
        if (!Objects.equals(this.idContrato, other.idContrato)) {
            return false;
        }
        if (!Objects.equals(this.fechaInicial, other.fechaInicial)) {
            return false;
        }
        if (!Objects.equals(this.fechaFinal, other.fechaFinal)) {
            return false;
        }
        if (!Objects.equals(this.fechaFiniquito, other.fechaFiniquito)) {
            return false;
        }
        if (!Objects.equals(this.empleado.getIdEmpleado(), other.empleado.getIdEmpleado())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contrato{" + "idContrato=" + idContrato + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", fechaFiniquito=" + fechaFiniquito + ", empleado=" + empleado + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
