/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "follow_up", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FollowUp.findAll", query = "SELECT f FROM FollowUp f"),
    @NamedQuery(name = "FollowUp.findByFuWaktu", query = "SELECT f FROM FollowUp f WHERE f.fuWaktu = :fuWaktu"),
    @NamedQuery(name = "FollowUp.findByFuDiagnosis", query = "SELECT f FROM FollowUp f WHERE f.fuDiagnosis = :fuDiagnosis"),
    @NamedQuery(name = "FollowUp.findByFuTindakan", query = "SELECT f FROM FollowUp f WHERE f.fuTindakan = :fuTindakan"),
    @NamedQuery(name = "FollowUp.findByFuRuangan", query = "SELECT f FROM FollowUp f WHERE f.fuRuangan = :fuRuangan")})
public class FollowUp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FU_WAKTU", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fuWaktu;
    @Basic(optional = false)
    @Column(name = "FU_DIAGNOSIS", nullable = false, length = 255)
    private String fuDiagnosis;
    @Basic(optional = false)
    @Column(name = "FU_TINDAKAN", nullable = false, length = 255)
    private String fuTindakan;
    @Basic(optional = false)
    @Column(name = "FU_RUANGAN", nullable = false, length = 20)
    private String fuRuangan;
    @JoinColumns({
        @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID"),
        @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID")})
    @ManyToOne(fetch = FetchType.LAZY)
    private MedicalRecord medicalRecord;

    public FollowUp() {
    }

    public FollowUp(Date fuWaktu) {
        this.fuWaktu = fuWaktu;
    }

    public FollowUp(Date fuWaktu, String fuDiagnosis, String fuTindakan, String fuRuangan) {
        this.fuWaktu = fuWaktu;
        this.fuDiagnosis = fuDiagnosis;
        this.fuTindakan = fuTindakan;
        this.fuRuangan = fuRuangan;
    }

    public Date getFuWaktu() {
        return fuWaktu;
    }

    public void setFuWaktu(Date fuWaktu) {
        this.fuWaktu = fuWaktu;
    }

    public String getFuDiagnosis() {
        return fuDiagnosis;
    }

    public void setFuDiagnosis(String fuDiagnosis) {
        this.fuDiagnosis = fuDiagnosis;
    }

    public String getFuTindakan() {
        return fuTindakan;
    }

    public void setFuTindakan(String fuTindakan) {
        this.fuTindakan = fuTindakan;
    }

    public String getFuRuangan() {
        return fuRuangan;
    }

    public void setFuRuangan(String fuRuangan) {
        this.fuRuangan = fuRuangan;
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
        hash += (fuWaktu != null ? fuWaktu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FollowUp)) {
            return false;
        }
        FollowUp other = (FollowUp) object;
        if ((this.fuWaktu == null && other.fuWaktu != null) || (this.fuWaktu != null && !this.fuWaktu.equals(other.fuWaktu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.FollowUp[ fuWaktu=" + fuWaktu + " ]";
    }
    
}
