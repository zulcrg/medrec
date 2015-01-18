/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "pemeriksaan_pendukung", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PemeriksaanPendukung.findAll", query = "SELECT p FROM PemeriksaanPendukung p"),
    @NamedQuery(name = "PemeriksaanPendukung.findByPemId", query = "SELECT p FROM PemeriksaanPendukung p WHERE p.pemId = :pemId"),
    @NamedQuery(name = "PemeriksaanPendukung.findByPemNmFile", query = "SELECT p FROM PemeriksaanPendukung p WHERE p.pemNmFile = :pemNmFile"),
    @NamedQuery(name = "PemeriksaanPendukung.findByPemTipeFile", query = "SELECT p FROM PemeriksaanPendukung p WHERE p.pemTipeFile = :pemTipeFile"),
    @NamedQuery(name = "PemeriksaanPendukung.findByPemKeterangan", query = "SELECT p FROM PemeriksaanPendukung p WHERE p.pemKeterangan = :pemKeterangan")})
public class PemeriksaanPendukung implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PEM_ID", nullable = false, length = 32)
    private String pemId;
    @Column(name = "PEM_NM_FILE", length = 30)
    private String pemNmFile;
    @Column(name = "PEM_TIPE_FILE", length = 5)
    private String pemTipeFile;
    @Lob
    @Column(name = "PEM_FILE")
    private byte[] pemFile;
    @Column(name = "PEM_KETERANGAN", length = 255)
    private String pemKeterangan;
    @JoinColumns({
        @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID", nullable = false),
        @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private MedicalRecord medicalRecord;

    public PemeriksaanPendukung() {
    }

    public PemeriksaanPendukung(String pemId) {
        this.pemId = pemId;
    }

    public String getPemId() {
        return pemId;
    }

    public void setPemId(String pemId) {
        this.pemId = pemId;
    }

    public String getPemNmFile() {
        return pemNmFile;
    }

    public void setPemNmFile(String pemNmFile) {
        this.pemNmFile = pemNmFile;
    }

    public String getPemTipeFile() {
        return pemTipeFile;
    }

    public void setPemTipeFile(String pemTipeFile) {
        this.pemTipeFile = pemTipeFile;
    }

    public byte[] getPemFile() {
        return pemFile;
    }

    public void setPemFile(byte[] pemFile) {
        this.pemFile = pemFile;
    }

    public String getPemKeterangan() {
        return pemKeterangan;
    }

    public void setPemKeterangan(String pemKeterangan) {
        this.pemKeterangan = pemKeterangan;
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
        hash += (pemId != null ? pemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PemeriksaanPendukung)) {
            return false;
        }
        PemeriksaanPendukung other = (PemeriksaanPendukung) object;
        if ((this.pemId == null && other.pemId != null) || (this.pemId != null && !this.pemId.equals(other.pemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.PemeriksaanPendukung[ pemId=" + pemId + " ]";
    }
    
}
