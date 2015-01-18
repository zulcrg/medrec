/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "anamnesa", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anamnesa.findAll", query = "SELECT a FROM Anamnesa a"),
    @NamedQuery(name = "Anamnesa.findByPasId", query = "SELECT a FROM Anamnesa a WHERE a.anamnesaPK.pasId = :pasId"),
    @NamedQuery(name = "Anamnesa.findByMedId", query = "SELECT a FROM Anamnesa a WHERE a.anamnesaPK.medId = :medId"),
    @NamedQuery(name = "Anamnesa.findByAnKeluhan", query = "SELECT a FROM Anamnesa a WHERE a.anKeluhan = :anKeluhan"),
    @NamedQuery(name = "Anamnesa.findByAnKhusus", query = "SELECT a FROM Anamnesa a WHERE a.anKhusus = :anKhusus"),
    @NamedQuery(name = "Anamnesa.findByAnRiwayat", query = "SELECT a FROM Anamnesa a WHERE a.anRiwayat = :anRiwayat"),
    @NamedQuery(name = "Anamnesa.findByAnRiwayatKel", query = "SELECT a FROM Anamnesa a WHERE a.anRiwayatKel = :anRiwayatKel")})
public class Anamnesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AnamnesaPK anamnesaPK;
    @Column(name = "AN_KELUHAN", length = 255)
    private String anKeluhan;
    @Column(name = "AN_KHUSUS", length = 255)
    private String anKhusus;
    @Column(name = "AN_RIWAYAT", length = 255)
    private String anRiwayat;
    @Column(name = "AN_RIWAYAT_KEL", length = 255)
    private String anRiwayatKel;
    @JoinColumns({
        @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private MedicalRecord medicalRecord;

    public Anamnesa() {
    }

    public Anamnesa(AnamnesaPK anamnesaPK) {
        this.anamnesaPK = anamnesaPK;
    }

    public Anamnesa(String pasId, long medId) {
        this.anamnesaPK = new AnamnesaPK(pasId, medId);
    }

    public AnamnesaPK getAnamnesaPK() {
        return anamnesaPK;
    }

    public void setAnamnesaPK(AnamnesaPK anamnesaPK) {
        this.anamnesaPK = anamnesaPK;
    }

    public String getAnKeluhan() {
        return anKeluhan;
    }

    public void setAnKeluhan(String anKeluhan) {
        this.anKeluhan = anKeluhan;
    }

    public String getAnKhusus() {
        return anKhusus;
    }

    public void setAnKhusus(String anKhusus) {
        this.anKhusus = anKhusus;
    }

    public String getAnRiwayat() {
        return anRiwayat;
    }

    public void setAnRiwayat(String anRiwayat) {
        this.anRiwayat = anRiwayat;
    }

    public String getAnRiwayatKel() {
        return anRiwayatKel;
    }

    public void setAnRiwayatKel(String anRiwayatKel) {
        this.anRiwayatKel = anRiwayatKel;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anamnesaPK != null ? anamnesaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anamnesa)) {
            return false;
        }
        Anamnesa other = (Anamnesa) object;
        if ((this.anamnesaPK == null && other.anamnesaPK != null) || (this.anamnesaPK != null && !this.anamnesaPK.equals(other.anamnesaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.Anamnesa[ anamnesaPK=" + anamnesaPK + " ]";
    }
    
}
