/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "diagnosis", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnosis.findAll", query = "SELECT d FROM Diagnosis d"),
    @NamedQuery(name = "Diagnosis.findByPasId", query = "SELECT d FROM Diagnosis d WHERE d.diagnosisPK.pasId = :pasId"),
    @NamedQuery(name = "Diagnosis.findByMedId", query = "SELECT d FROM Diagnosis d WHERE d.diagnosisPK.medId = :medId"),
    @NamedQuery(name = "Diagnosis.findByDgKerja", query = "SELECT d FROM Diagnosis d WHERE d.dgKerja = :dgKerja"),
    @NamedQuery(name = "Diagnosis.findByDgBanding", query = "SELECT d FROM Diagnosis d WHERE d.dgBanding = :dgBanding"),
    @NamedQuery(name = "Diagnosis.findByDgPengobatan", query = "SELECT d FROM Diagnosis d WHERE d.dgPengobatan = :dgPengobatan"),
    @NamedQuery(name = "Diagnosis.findByDgPrognosis", query = "SELECT d FROM Diagnosis d WHERE d.dgPrognosis = :dgPrognosis")})
public class Diagnosis implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiagnosisPK diagnosisPK;
    @Column(name = "DG_KERJA", length = 255)
    private String dgKerja;
    @Column(name = "DG_BANDING", length = 255)
    private String dgBanding;
    @Column(name = "DG_PENGOBATAN", length = 255)
    private String dgPengobatan;
    @Column(name = "DG_PROGNOSIS", length = 255)
    private String dgPrognosis;
    @JoinColumns({
        @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private MedicalRecord medicalRecord;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis", fetch = FetchType.LAZY)
    private List<Dosis> dosisList;

    public Diagnosis() {
    }

    public Diagnosis(DiagnosisPK diagnosisPK) {
        this.diagnosisPK = diagnosisPK;
    }

    public Diagnosis(String pasId, long medId) {
        this.diagnosisPK = new DiagnosisPK(pasId, medId);
    }

    public DiagnosisPK getDiagnosisPK() {
        return diagnosisPK;
    }

    public void setDiagnosisPK(DiagnosisPK diagnosisPK) {
        this.diagnosisPK = diagnosisPK;
    }

    public String getDgKerja() {
        return dgKerja;
    }

    public void setDgKerja(String dgKerja) {
        this.dgKerja = dgKerja;
    }

    public String getDgBanding() {
        return dgBanding;
    }

    public void setDgBanding(String dgBanding) {
        this.dgBanding = dgBanding;
    }

    public String getDgPengobatan() {
        return dgPengobatan;
    }

    public void setDgPengobatan(String dgPengobatan) {
        this.dgPengobatan = dgPengobatan;
    }

    public String getDgPrognosis() {
        return dgPrognosis;
    }

    public void setDgPrognosis(String dgPrognosis) {
        this.dgPrognosis = dgPrognosis;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @XmlTransient
    public List<Dosis> getDosisList() {
        return dosisList;
    }

    public void setDosisList(List<Dosis> dosisList) {
        this.dosisList = dosisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagnosisPK != null ? diagnosisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnosis)) {
            return false;
        }
        Diagnosis other = (Diagnosis) object;
        if ((this.diagnosisPK == null && other.diagnosisPK != null) || (this.diagnosisPK != null && !this.diagnosisPK.equals(other.diagnosisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.Diagnosis[ diagnosisPK=" + diagnosisPK + " ]";
    }
    
}
