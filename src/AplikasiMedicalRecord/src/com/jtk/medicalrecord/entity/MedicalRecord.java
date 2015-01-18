/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "medical_record", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicalRecord.findAll", query = "SELECT m FROM MedicalRecord m"),
    @NamedQuery(name = "MedicalRecord.findByPasId", query = "SELECT m FROM MedicalRecord m WHERE m.medicalRecordPK.pasId = :pasId"),
    @NamedQuery(name = "MedicalRecord.findByMedId", query = "SELECT m FROM MedicalRecord m WHERE m.medicalRecordPK.medId = :medId"),
    @NamedQuery(name = "MedicalRecord.findByMedTanggal", query = "SELECT m FROM MedicalRecord m WHERE m.medTanggal = :medTanggal")})
public class MedicalRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicalRecordPK medicalRecordPK;
    @Basic(optional = false)
    @Column(name = "MED_TANGGAL", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date medTanggal;
    @OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private List<FollowUp> followUpList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private Diagnosis diagnosis;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private PemeriksaanFisik pemeriksaanFisik;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private Anamnesa anamnesa;
    @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pasien pasien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private List<PemeriksaanPendukung> pemeriksaanPendukungList;

    public MedicalRecord() {
    }

    public MedicalRecord(MedicalRecordPK medicalRecordPK) {
        this.medicalRecordPK = medicalRecordPK;
    }

    public MedicalRecord(MedicalRecordPK medicalRecordPK, Date medTanggal) {
        this.medicalRecordPK = medicalRecordPK;
        this.medTanggal = medTanggal;
    }

    public MedicalRecord(String pasId, long medId) {
        this.medicalRecordPK = new MedicalRecordPK(pasId, medId);
    }

    public MedicalRecordPK getMedicalRecordPK() {
        return medicalRecordPK;
    }

    public void setMedicalRecordPK(MedicalRecordPK medicalRecordPK) {
        this.medicalRecordPK = medicalRecordPK;
    }

    public Date getMedTanggal() {
        return medTanggal;
    }

    public void setMedTanggal(Date medTanggal) {
        this.medTanggal = medTanggal;
    }

    @XmlTransient
    public List<FollowUp> getFollowUpList() {
        return followUpList;
    }

    public void setFollowUpList(List<FollowUp> followUpList) {
        this.followUpList = followUpList;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public PemeriksaanFisik getPemeriksaanFisik() {
        return pemeriksaanFisik;
    }

    public void setPemeriksaanFisik(PemeriksaanFisik pemeriksaanFisik) {
        this.pemeriksaanFisik = pemeriksaanFisik;
    }

    public Anamnesa getAnamnesa() {
        return anamnesa;
    }

    public void setAnamnesa(Anamnesa anamnesa) {
        this.anamnesa = anamnesa;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    @XmlTransient
    public List<PemeriksaanPendukung> getPemeriksaanPendukungList() {
        return pemeriksaanPendukungList;
    }

    public void setPemeriksaanPendukungList(List<PemeriksaanPendukung> pemeriksaanPendukungList) {
        this.pemeriksaanPendukungList = pemeriksaanPendukungList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicalRecordPK != null ? medicalRecordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicalRecord)) {
            return false;
        }
        MedicalRecord other = (MedicalRecord) object;
        if ((this.medicalRecordPK == null && other.medicalRecordPK != null) || (this.medicalRecordPK != null && !this.medicalRecordPK.equals(other.medicalRecordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.MedicalRecord[ medicalRecordPK=" + medicalRecordPK + " ]";
    }

}
