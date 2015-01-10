/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "dosis", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dosis.findAll", query = "SELECT d FROM Dosis d"),
    @NamedQuery(name = "Dosis.findByObatId", query = "SELECT d FROM Dosis d WHERE d.dosisPK.obatId = :obatId"),
    @NamedQuery(name = "Dosis.findByPasId", query = "SELECT d FROM Dosis d WHERE d.dosisPK.pasId = :pasId"),
    @NamedQuery(name = "Dosis.findByMedId", query = "SELECT d FROM Dosis d WHERE d.dosisPK.medId = :medId"),
    @NamedQuery(name = "Dosis.findByObatDosis", query = "SELECT d FROM Dosis d WHERE d.obatDosis = :obatDosis")})
public class Dosis implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DosisPK dosisPK;
    @Basic(optional = false)
    @Column(name = "OBAT_DOSIS", nullable = false, length = 6)
    private String obatDosis;
    @JoinColumn(name = "OBAT_ID", referencedColumnName = "OBAT_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Obat obat;
    @JoinColumns({
        @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Diagnosis diagnosis;

    public Dosis() {
    }

    public Dosis(DosisPK dosisPK) {
        this.dosisPK = dosisPK;
    }

    public Dosis(DosisPK dosisPK, String obatDosis) {
        this.dosisPK = dosisPK;
        this.obatDosis = obatDosis;
    }

    public Dosis(String obatId, String pasId, long medId) {
        this.dosisPK = new DosisPK(obatId, pasId, medId);
    }

    public DosisPK getDosisPK() {
        return dosisPK;
    }

    public void setDosisPK(DosisPK dosisPK) {
        this.dosisPK = dosisPK;
    }

    public String getObatDosis() {
        return obatDosis;
    }

    public void setObatDosis(String obatDosis) {
        this.obatDosis = obatDosis;
    }

    public Obat getObat() {
        return obat;
    }

    public void setObat(Obat obat) {
        this.obat = obat;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dosisPK != null ? dosisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosis)) {
            return false;
        }
        Dosis other = (Dosis) object;
        if ((this.dosisPK == null && other.dosisPK != null) || (this.dosisPK != null && !this.dosisPK.equals(other.dosisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.Dosis[ dosisPK=" + dosisPK + " ]";
    }
    
}
